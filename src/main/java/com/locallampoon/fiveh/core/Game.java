package com.locallampoon.fiveh.core;

import com.locallampoon.fiveh.ui.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {

    private static Player player;
    private static Map<String, Room> houseMap;
    private static final String HELP_FILE = "src/main/java/com/locallampoon/fiveh/data/helpmenu.txt";
    private static final String MENU_FILE = "src/main/java/com/locallampoon/fiveh/data/mainmenu.txt";
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    static MainPanel mainPanel;
    static NarrativePanel narrativePanel;
    private static ArtPanel artPanel;
    private static StatsPanel statsPanel;
    private static MapPanel mapPanel;

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
                new IntroPanel(),
                new NarrativePanel(),
                new ConsolePanel(),
                new ArtPanel(),
                new StatsPanel(),
                new MapPanel()
        );
        narrativePanel = mainPanel.getNarrativePanel();
        artPanel = mainPanel.getArtPanel();
        statsPanel = mainPanel.getStatsPanel();
        mapPanel = mainPanel.getMapPanel();
    }

    private static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                narrativePanel.appendTextArea(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            narrativePanel.appendTextArea("UH OH! If it weren't for you pesky kids, I would have printed the Menu!\n");
        }
    }

    public static void getHelp() {
        GameArt.renderHelper();
        readFile(HELP_FILE);
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
        } else if (parsedCommandList.size() == 2){
            implementCommandTwoWords(parsedCommandList, roomExits, null);
        } else {
            narrativePanel.appendTextArea("Invalid Action\n");
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
                    narrativePanel.appendTextArea("You can't travel in that direction!\n");
                    break;
                }
                Room roomKeyID = houseMap.get(roomExits.get(dirMovement.getDirection()));
                playerDependency.move(roomKeyID);
            }
            case "get", "grab", "take" -> {
                String grabbedItem = UserInput.nounItemHelper(parsedCommandList, playerDependency);
                if (!playerDependency.isInventoryFull()) {
                    playerDependency.addItem(grabbedItem);
                    playerCurrentRoom.removeItem(grabbedItem);
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
                    default -> narrativePanel.appendTextArea("Invalid Action");
                }
            }
            default -> narrativePanel.appendTextArea("Invalid Action");
        }
    }

    private static void implementCommandOneWord(List<String> parsedCommandList) {
        switch (parsedCommandList.get(0)) {
            case "fight":
                engageInCombat();
                break;
            case "flee":
                player.flee(houseMap);
                break;

            case "inventory":
            case "i":
                player.printInventoryItems();
                break;
            case "help":
            case "h":
                getHelp();
                break;
            case "quit":
            case "q":
            case "requestCommandAgain":
                break;
            default: {
                narrativePanel.appendTextArea("Invalid Action\n");
            }
        }
    }

    public static void handleIntro(IntroOption option) {
        switch (option) {
            case NEW:
                mainPanel.showGame();
                mainPanel.hideIntro();
                break;
            // TODO: add help option
            case QUIT:
                System.exit(0);
            default:
                break;
        }
    }

    /**
     * append text with customized color
     * need to let console panel have the focus at the end
     */
    private static void printDescription() {
        Room playerCurrentRoom = player.getCurrentRoom();
        narrativePanel.appendTextArea("ITEMS IN ROOM: " + playerCurrentRoom.getItems() + "\n", PanelStyles.FG_COLOR);
        narrativePanel.appendTextArea("PEOPLE IN ROOM: " + playerCurrentRoom.getNpcs() + "\n", PanelStyles.FG_COLOR);
        String[] desc = playerCurrentRoom.getDesc().split("\\(|\\)");
        // color direction keywords from room description
        for (String i : desc) {
            if (Arrays.asList(PanelStyles.DIRECTIONS).contains(i)) {
                narrativePanel.appendTextArea(i, PanelStyles.NEIGHBOUR_COLOR);
            } else {
                narrativePanel.appendTextArea(i, PanelStyles.FG_COLOR);
            }
        }
        narrativePanel.appendTextArea("\n\nYOU ARE IN: ", PanelStyles.FG_COLOR);
        narrativePanel.appendTextArea(playerCurrentRoom.getRoomName() + "\n", PanelStyles.PLAYER_COLOR);
        mainPanel.getConsolePanel().enableConsole();
    }

    private static void printPlayerStats() {
        statsPanel.appendTextArea("PLAYER HEALTH: " + player.getHealth());
        statsPanel.appendTextArea((player.getInventoryItemsString().toString()));
        statsPanel.appendTextArea("THE SQUAD: " + player.getSquad() + "\n");
    }

    private static void checkMonster() {
        Room playerCurrentRoom = player.getCurrentRoom();
        Monster monsterInRoom = playerCurrentRoom.getRoomMonster();
        if (monsterInRoom != null) {
            switch (monsterInRoom.getName()) {
                case "Vampire" -> artPanel.setTextArea(GameArt.renderMan());
                case "Ghost" -> artPanel.setTextArea(GameArt.renderGhost());
                case "Werewolf" -> artPanel.setTextArea(GameArt.renderWolf());
            }
            narrativePanel.appendTextArea("MONSTERS IN ROOM: " + monsterInRoom.getName() + "\n");
            statsPanel.appendTextArea("MONSTER HEALTH: " + monsterInRoom.getHealth());
        }
    }

    public void start() {
        printDescription();
        printPlayerStats();
    }

    private static void renderGameUI() {
        // clear panels
        narrativePanel.setTextArea("");
        artPanel.setTextArea("");
        statsPanel.setTextArea("");
        // repaint after player current position is updated
        mapPanel.updateMapGUI();
        // print description
        printDescription();
        // handle monster scenario
        checkMonster();
        // print player stats
        printPlayerStats();
    }

    public static void handleCommand(String input) {
        Room playerCurrentRoom = player.getCurrentRoom();
        List<String> output = UserInput.parseCommand(input);
        List<String> roomExits = playerCurrentRoom.getExits();

        if (input.equals("q") || input.equals("quit")) {
            System.exit(0);
        }

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
        if (monster != null){
            player.attack(monster);
            if (monster.isDead()) {
                narrativePanel.appendTextArea(" You killed " + monster.getName());
            } else {
                monster.attack(player);
            }
            if (player.isDead()) {
                System.exit(0);
            }
        } else {
            narrativePanel.appendTextArea("There is no monster in this room");
        }
    }
}

