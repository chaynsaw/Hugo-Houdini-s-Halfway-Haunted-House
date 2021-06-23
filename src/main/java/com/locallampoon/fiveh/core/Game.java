package com.locallampoon.fiveh.core;

import com.locallampoon.fiveh.ui.*;

import java.io.*;
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
            System.out.println("UH OH! If it weren't for you pesky kids, I would have printed the Menu!");
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
            implementCommandTwoWords(parsedCommandList,roomExits);
        } else {
            System.out.println("Invalid Action");
        }
    }

    private static void implementCommandTwoWords(List<String> parsedCommandList, List<String> roomExits) {
        Room playerCurrentRoom = player.getCurrentRoom();
        switch (parsedCommandList.get(0)) {
            case "go", "move" -> {
                Direction dirMovement = movementHelper(parsedCommandList.get(1));
                if (dirMovement == null || roomExits.get(dirMovement.getDirection()).isBlank() ||
                        roomExits.get(dirMovement.getDirection()).isEmpty()) {
                    System.out.println("You can't travel in that direction!\n");
                    break;
                }
                Room roomKeyID = houseMap.get(roomExits.get(dirMovement.getDirection()));
                player.move(roomKeyID);
            }
            case "get", "grab", "take" -> {
                String grabbedItem = UserInput.nounItemHelper(parsedCommandList, player);
                player.addItem(grabbedItem);
                playerCurrentRoom.removeItem(grabbedItem);
            }
            case "drop" -> {
                String droppedItem = UserInput.nounItemHelper(parsedCommandList, player);
                player.dropItem(droppedItem);
                playerCurrentRoom.addItem(droppedItem);
            }
            case "recruit" -> {
                String recruitedNpc = UserInput.nounItemHelper(parsedCommandList, player);
                player.addNpc(recruitedNpc);
                playerCurrentRoom.removeNpc(recruitedNpc);
                switch (recruitedNpc.toLowerCase()) {
                    case "jock" -> player.setStrong(true);
                    case "chess geek" -> player.setSmart(true);
                    case "bleacher kid" -> player.setBrave(true);
                    default -> System.out.println("Invalid Action");
                }
            }
            default -> {
                System.out.println("Invalid Action");
            }
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
                System.out.println("Invalid Action");
            };
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

    private static void printDescription() {
        Room playerCurrentRoom = player.getCurrentRoom();
        narrativePanel.appendTextArea("YOU ARE IN: " + playerCurrentRoom.getRoomName() + "\n");
        narrativePanel.appendTextArea(playerCurrentRoom.getDesc());
        narrativePanel.appendTextArea("ITEMS IN ROOM: " + playerCurrentRoom.getItems() + "\n");
        narrativePanel.appendTextArea("PEOPLE IN ROOM: " + playerCurrentRoom.getNpcs() + "\n");
    }

    private static void checkMonster() {
        Room playerCurrentRoom = player.getCurrentRoom();
        Monster monsterInRoom = playerCurrentRoom.getRoomMonster();
        if (monsterInRoom != null) {
            switch (monsterInRoom.getName()) {
                case "Vampire" -> artPanel.setTextArea(GameArt.renderMan());
                case "Ghost" -> artPanel.setTextArea(GameArt.renderGhost());
                case "Ware Wolf" -> artPanel.setTextArea(GameArt.renderWolf());
            }
            narrativePanel.appendTextArea("MONSTERS IN ROOM: " + monsterInRoom.getName() + "\n");
            statsPanel.appendTextArea("MONSTER HEALTH: " + monsterInRoom.getHealth());
        }
        statsPanel.appendTextArea("HEALTH: " + player.getHealth());
        statsPanel.appendTextArea((player.getInventoryItemsString().toString()));
        statsPanel.appendTextArea("THE SQUAD: " + player.getSquad() + "\n");
    }

    public void startV2() {
        printDescription();
    }

    public static void handleCommand(String input) {
        Room playerCurrentRoom = player.getCurrentRoom();
        List<String> output = UserInput.parseCommand(input);
        List<String> roomExits = playerCurrentRoom.getExits();

        if (input.equals("q") || input.equals("quit")) {
            System.exit(0);
        }

        // clear panels
        narrativePanel.setTextArea("");
        artPanel.setTextArea("");
        statsPanel.setTextArea("");
        // send command to game switch logic
        implementCommand(output, roomExits);
        mapPanel.updateMapGUI(); //need to repaint after player current position updated
        // print description
        printDescription();
        // handle monster scenario
        checkMonster();
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

