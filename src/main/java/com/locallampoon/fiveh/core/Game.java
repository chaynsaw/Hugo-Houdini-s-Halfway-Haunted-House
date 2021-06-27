package com.locallampoon.fiveh.core;

import com.locallampoon.fiveh.ui.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.locallampoon.fiveh.ui.PanelStyles.GameMap.*;
import static com.locallampoon.fiveh.ui.PanelStyles.Global.FG_COLOR;

public class Game implements Serializable {

    private static final String HELP_FILE = "/helpmenu.txt";
    private static final String HELP_FILE_ACTIONS = "/helpmenuActions.txt";
    static MainPanel mainPanel;
    static NarrativePanel narrativePanel;
    static ActionPanel actionPanel;
    private static HealthBarPanel playerHealthPanel;
    private static HealthBarPanel monsterHealthPanel;
    private static Player player;
    private static Map<String, Room> houseMap;
    private static ArtPanel artPanel;
    private static StatsPanel statsPanel;
    private static MapPanel mapPanel;
    private static HelpPanel helpPanel;

    public static boolean hasWon = false;
    public static boolean hasLost = false;
    public static boolean isHelp = false;

    // CONSTRUCTOR
    public Game() {
        setHouseMap(XMLParser.parseRooms());
        player = new Player(houseMap.get("hall"));
        initializeUI();
    }

    // GETTER/SETTER METHODS
    private void setHouseMap(Map<String, Room> houseMap) {
        Game.houseMap = houseMap;
    }

    // METHODS
    private static void initializeUI() {
        mainPanel = new MainPanel(
                new SplashPanel(),
                new NarrativePanel(),
                new ActionPanel(),
                new ConsolePanel(),
                new ArtPanel(),
                new StatsPanel(),
                new MapPanel(),
                new HelpPanel()
        );
        narrativePanel = mainPanel.getNarrativePanel();
        actionPanel = mainPanel.getActionPanel();
        artPanel = mainPanel.getArtPanel();
        statsPanel = mainPanel.getStatsPanel();
        mapPanel = mainPanel.getMapPanel();
        helpPanel = mainPanel.getHelpPanel();
        playerHealthPanel = mainPanel.getStatsPanel().getPlayerHealthPanel();
        monsterHealthPanel = mainPanel.getStatsPanel().getMonsterHealthPanel();
    }

    // METHODS
    /**
     * only dealing with help menu
     * color keywords in help menu
     *
     * @param filename
     */
    private static void readHelpMenu(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Game.class.getResourceAsStream(filename)))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                if (index == 0) {
                    helpPanel.appendTextArea("Action\t\t\tVerb\t\t\tNoun\t\t\tKeyboard\n", ROOM_TEXT_COLOR);
                    index++;
                }
                String[] tokens = line.split("\\|");
                if (tokens.length > 4) {
                    helpPanel.appendTextArea("Invalid help menu. No more than tokens");
                } else {
                    Color[] colors = {FG_COLOR, PLAYER_COLOR, NEIGHBOUR_COLOR, FG_COLOR};
                    for (int i = 0; i < tokens.length; i++) {
                        if (tokens[i].length() >= 9)
                            helpPanel.appendTextArea(tokens[i] + "\t\t", colors[i]);
                        else if (tokens[i].length() < 9) {
                            helpPanel.appendTextArea(tokens[i] + "\t\t\t", colors[i]);
                        }
                    }
                    helpPanel.appendTextArea("\n");
                }
            }
            helpPanel.getTextArea().setCaretPosition(0);
        } catch (IOException e) {
            e.printStackTrace();
            helpPanel.appendTextArea("UH OH! If it weren't for you pesky kids, I would have printed the Menu!\n");
        }
    }

    public static void getHelp() {
        readHelpMenu(HELP_FILE_ACTIONS);
    }

    private static Direction movementHelper(String newDirection) {
        newDirection = newDirection.toUpperCase();
        if (newDirection.equals("NORTH") || newDirection.equals("SOUTH") || newDirection.equals("EAST") ||
                newDirection.equals("WEST") || newDirection.equals("UP") || newDirection.equals("DOWN")) {

            return Direction.valueOf(newDirection);
        } else {
            return null;
        }
    }

    private static void implementCommand(List<String> parsedCommandList, List<String> roomExits) {
        if (parsedCommandList.size() == 1) {
            implementCommandOneWord(parsedCommandList);
        } else if (parsedCommandList.size() >= 2){
            implementCommandTwoWords(parsedCommandList, roomExits, null);
        }
    }

    static void implementCommandTwoWords(List<String> parsedCommandList, List<String> roomExits, Player playerDependency) {
        if (playerDependency == null) {
            playerDependency = player;
        }
        Room playerCurrentRoom = playerDependency.getCurrentRoom();
        switch (parsedCommandList.get(0)) {
            case "go", "move" -> {
                Direction dirMovement = movementHelper(parsedCommandList.get(1));
                if (dirMovement == null || roomExits.get(dirMovement.getDirection()).isBlank() ||
                        roomExits.get(dirMovement.getDirection()).isEmpty()) {
                    actionPanel.appendTextArea("You can't travel in that direction!");
                    break;
                }
                Room roomKeyID = houseMap.get(roomExits.get(dirMovement.getDirection()));
                playerDependency.move(roomKeyID);
            }
            case "get", "grab", "take" -> {
                String grabbedItem = UserInput.nounItemHelper(parsedCommandList, playerDependency);
                if (!playerDependency.isInventoryFull() && grabbedItem.split(" ").length > 1) {
                    playerDependency.addItem(grabbedItem);
                    playerCurrentRoom.removeItem(grabbedItem);
                } else {
                    if (actionPanel != null) {
                        actionPanel.appendTextArea("Invalid Action",FG_COLOR);
                    }
                }
            }
            case "drop" -> {
                String droppedItem = UserInput.nounItemHelper(parsedCommandList, playerDependency);
                if (!droppedItem.equals("")) {
                    playerDependency.dropItem(droppedItem);
                    playerCurrentRoom.addItem(droppedItem);
                }
            }
            case "recruit" -> {
                String recruitedNpc = UserInput.nounItemHelper(parsedCommandList, playerDependency);
                playerDependency.addNpc(recruitedNpc);
                playerCurrentRoom.removeNpc(recruitedNpc);
                switch (recruitedNpc.toLowerCase()) {
                    case "jock" -> playerDependency.setStrong(true);
                    case "chess geek" -> playerDependency.setSmart(true);
                    case "bleacher kid" -> playerDependency.setBrave(true);
                    default -> actionPanel.appendTextArea("Invalid Action");
                }
            }
            case "enter" -> {
                if (parsedCommandList.get(1).equalsIgnoreCase("passage") && Game.checkWinCondition()) {
                    hasWon = true;
                    mainPanel.hideGame();
                    mainPanel.showSplash();
                }
            }
            case "quit" -> {
                if (isHelp && parsedCommandList.get(1).equalsIgnoreCase("help")) {
                    isHelp = false;
                }
            }
            default -> actionPanel.appendTextArea("Invalid Action");
        }
    }

    public static boolean checkWinCondition() {
        return (player.getCurrentRoom().getRoomName().equalsIgnoreCase("Sacrificial Chamber") && player.getInventory().containsAll(Arrays.asList("Vampire Key", "Ghost Key", "Werewolf Key")));
    }

    private static void implementCommandOneWord(List<String> parsedCommandList) {
        switch (parsedCommandList.get(0)) {
            case "fight":
                engageInCombat();
                break;
            case "flee":
                player.flee(houseMap);
                break;
            case "help":
            case "h":
                isHelp = true;
                break;
            case "quit":
            case "q":
                System.exit(0);
            case "requestCommandAgain":
                break;
            default: {
                actionPanel.appendTextArea("Invalid Action", FG_COLOR);
            }
        }
    }

    public static void handleIntro(SplashOption option) {
        switch (option) {
            case NEW:
                mainPanel.showGame();
                mainPanel.hideSplash();
                break;
            // TODO: add help option
            case QUIT:
                System.exit(0);
            default:
                break;
        }
    }

    private static void printLocation() {
        Room playerCurrentRoom = player.getCurrentRoom();
        narrativePanel.appendTextArea("YOU ARE IN: ", FG_COLOR);
        narrativePanel.appendTextArea(playerCurrentRoom.getRoomName() + "\n", PLAYER_COLOR);
    }

    /**
     * append text with customized color
     * need to let console panel have the focus at the end
     */
    private static void printDescription() {
        Room playerCurrentRoom = player.getCurrentRoom();
        narrativePanel.appendTextArea("ITEMS IN ROOM: " + playerCurrentRoom.getItems() + "\n", FG_COLOR);
        narrativePanel.appendTextArea("PEOPLE IN ROOM: " + playerCurrentRoom.getNpcs() + "\n\n", FG_COLOR);

        String[] desc = playerCurrentRoom.getDesc().split("\\(|\\)");
        // color direction keywords from room description
        for (String i : desc) {
            if (Arrays.asList(DIRECTIONS).contains(i)) {
                narrativePanel.appendTextArea(i, NEIGHBOUR_COLOR);
            } else {
                narrativePanel.appendTextArea(i, FG_COLOR);
            }
        }

        if (Game.checkWinCondition() && !hasWon) {
            narrativePanel.appendTextArea("\n\nA small passageway reveals itself, glowing blue like the keys in your pack. Should you...", FG_COLOR);
            narrativePanel.appendTextArea("enter passage?", NEIGHBOUR_COLOR);
        }
        mainPanel.getConsolePanel().enableConsole();
    }

    private static void printPlayerStats() {
        statsPanel.appendTextArea((player.getInventoryItemsString().toString()));
        statsPanel.appendTextArea("THE SQUAD: " + player.getSquad());
    }

    private static void checkMonster() {
        Room playerCurrentRoom = player.getCurrentRoom();
        Monster monsterInRoom = playerCurrentRoom.getRoomMonster();
        if (monsterInRoom != null) {
            monsterHealthPanel.setVisible(true);
            switch (monsterInRoom.getName()) {
                case "Vampire" -> artPanel.setTextArea(GameArt.renderMan());
                case "Ghost" -> artPanel.setTextArea(GameArt.renderGhost());
                case "Werewolf" -> artPanel.setTextArea(GameArt.renderWolf());
            }
            narrativePanel.appendTextArea("MONSTERS IN ROOM: " + monsterInRoom.getName() + "\n", FG_COLOR);
            monsterHealthPanel.setHealthBar(monsterInRoom.getHealth());
            playerHealthPanel.setHealthBar(player.getHealth());
        } else {
            artPanel.setTextArea(GameArt.renderHouse());
            monsterHealthPanel.setVisible(false);
        }
    }

    public void start() {
        printLocation();
        printDescription();
        printPlayerStats();
        artPanel.setTextArea(GameArt.renderHouse());
    }

    private static void showHelp() {
        getHelp();
        helpPanel.getPanel().setVisible(true);
        narrativePanel.getPanel().setVisible(false);
    }

    private static void hideHelp() {
        helpPanel.getPanel().setVisible(false);
        narrativePanel.getPanel().setVisible(true);
    }

    private static void clearPanels() {
        // clear panels
        narrativePanel.setTextArea("");
        artPanel.setTextArea("");
        statsPanel.setTextArea("");
        helpPanel.setTextArea("");
    }

    private static void renderGameUI() {
        clearPanels();
        // repaint after player current position is updated
        mapPanel.updateMapGUI();
        // print location
        printLocation();
        // handle monster scenario
        checkMonster();
        // print player stats
        printPlayerStats();

        if (!Game.isHelp) {
            hideHelp();
            // print description
            printDescription();
        } else {
            showHelp();
        }
    }


    public static void handleCommand(String input) {
        isHelp = false;
        Room playerCurrentRoom = player.getCurrentRoom();
        List<String> output = UserInput.parseCommand(input);
        List<String> roomExits = playerCurrentRoom.getExits();

        actionPanel.setTextArea("");
        // send command to game switch logic
        implementCommand(output, roomExits);
        // render ui after command execution
        renderGameUI();
    }

    public static Player getPlayer() {
        return player;
    }

    public static void engageInCombat() {
        Monster monster = player.getCurrentRoom().getRoomMonster();
        if (monster != null) {
            player.attack(monster);
            if (monster.isDead()) {
                actionPanel.appendTextArea("You killed " + monster.getName(),FG_COLOR);
            } else {
                monster.attack(player);
            }
            if (player.isDead()) {
                hasLost = true;
                mainPanel.hideGame();
                mainPanel.showSplash();
            }
        } else {
            actionPanel.appendTextArea("There is no monster in this room", FG_COLOR);
        }
    }
}

