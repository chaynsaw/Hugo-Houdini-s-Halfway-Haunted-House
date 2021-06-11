package com.locallampoon.fiveh.core;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {
    private Player player;
    private Map<String, Room> houseMap;
    private static final String HELP_FILE = "src/com/locallampoon/fiveh/data/helpmenu.txt";
    private static final String MENU_FILE = "src/com/locallampoon/fiveh/data/mainmenu.txt";
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    // CONSTRUCTOR
    public Game() {
        setHouseMap(XMLParser.parseRooms());
        this.player = new Player(houseMap.get("hall"));
    }

    // GETTER/SETTER METHODS

    Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }
    Map<String, Room> getHouseMap() {
        return houseMap;
    }

    private void setHouseMap(Map<String, Room> houseMap) {
        this.houseMap = houseMap;
    }


    // METHODS

    private static void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("UH OH! If it weren't for you pesky kids, I would have printed the Menu!");
        }
    }

    public static void getHelp() {
        GameArt.renderHelper();
        readFile(HELP_FILE);
    }

    private Direction movementHelper(String newDirection) {
        newDirection = newDirection.toUpperCase();
        if (newDirection.equals("NORTH") || newDirection.equals("SOUTH") || newDirection.equals("EAST") ||
                newDirection.equals("WEST") || newDirection.equals("UP") || newDirection.equals("DOWN")) {

            return Direction.valueOf(newDirection);
        } else {
            return null;
        }
    }

    private void implementCommand(List<String> parsedCommandList, List<String> roomExits) {
        // TODO: Finish switch to contain all Verbs and Noun interaction
        switch (parsedCommandList.get(0)) {
            case "go":
            case "move":
                Direction dirMovement = movementHelper(parsedCommandList.get(1));
                if (dirMovement == null || roomExits.get(dirMovement.getDirection()).isBlank() ||
                        roomExits.get(dirMovement.getDirection()).isEmpty()) {
                    System.out.println("You can't travel in that direction!\n");
                    break;
                }
                Room roomKeyID = houseMap.get(roomExits.get(dirMovement.getDirection()));
                player.move(roomKeyID);
                break;
            case "get":
            case "grab":
                String grabbedItem = UserInput.nounItemHelper(parsedCommandList, player);
                player.addItem(grabbedItem);
                player.getCurrentRoom().removeItem(grabbedItem);
                break;
            case "drop":
                String droppedItem = UserInput.nounItemHelper(parsedCommandList, player);
                player.dropItem(droppedItem);
                player.getCurrentRoom().addItem(droppedItem);
                break;
            case "talk":
                // TODO: Player needs a Talk method to talk with characters in rooms
//                    player.talkWith(output.get(1));
//                    break;
            case "inspect":
                // TODO: Player needs an Inspect method to items in room
//                    player.inspectItem(output.get(1));
//                    break;
            case "fight":
                // TODO: Room needs an attribute to hold monster object and bool to check if it exists in room
//                    if (player.getCurrentRoom().hasMonster() && Room.monsterName(output.get(1))){
//                        player.attack(Room.monster);
//                    } else {
//                        System.out.println("There is no monster in this room");
//                    }
//                    break;
            case "flee":
                player.flee(houseMap);
                break;
                // TODO: Player needs a flee method to run from monster, will send player to a random room
//                    player.flee();
//                    break;
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
//            default:
//                System.out.println("Invalid command");
        }
    }

    private static void showIntro() throws IOException {
        GameArt.renderHouse();
        String menuPrompt = "Type one of the following Commands:\n\"New\" - Start a new game.\n\"Help\" - Help from the Caretaker.\n\"Quit\" - Exit the game.";
        String input;
        readFile(MENU_FILE);

        mainMenu:
        do {
            System.out.println(menuPrompt);
            System.out.print("\n> ");

            input = BUFFERED_READER.readLine().toLowerCase();

            switch (input) {
                case "new":
                    break mainMenu;
                case "help":
                    getHelp();
                    break;
                case "quit":
                    System.exit(0);
            }
        } while (input != null);
    }

    public void start() throws IOException {

        String input;
        List<String> output;

        showIntro();
        do {
            System.out.println(player.getCurrentRoom().getDesc());
            System.out.println("ITEMS IN ROOM: " + player.getCurrentRoom().getItems() + "\n");
            System.out.print("> ");

            input = BUFFERED_READER.readLine();
            output = UserInput.parseCommand(input);
            List<String> roomExits = player.getCurrentRoom().getExits();

            implementCommand(output, roomExits);
            if (input.equals("q") || input.equals("quit")){
                input = GameState.quitHelper(BUFFERED_READER);
            }

        } while (!"q".equals(input));
    }
}

