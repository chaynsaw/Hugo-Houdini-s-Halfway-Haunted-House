package com.locallampoon.fiveh.core;

import java.io.*;
import java.util.List;
import java.util.Locale;
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
                player.addItem(parsedCommandList.get(1));
                break;
            case "drop":
                player.dropItem(parsedCommandList.get(1));
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
                player.getInventory();
                break;
            case "help":
            case "h":
                getHelp();
                break;
            case "quit":
            case "q":
                parsedCommandList.remove(0);
                try {
                    parsedCommandList.add(GameState.quitHelper(BUFFERED_READER));
                } catch (IOException e) {
                    System.out.println("MUH HA HAHA! Looks like you pesky kids weren't able to stop me and save the day!");
                    System.out.println("You have to keep playing the game!");
                }
                break;
            default:
                System.out.println("invalid command");
                System.out.println(houseMap);
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
            System.out.print("> ");

            input = BUFFERED_READER.readLine();
            output = UserInput.parseCommand(input, 0, BUFFERED_READER);
            List<String> roomExits = player.getCurrentRoom().getExits();

            implementCommand(output, roomExits);

        } while (!"q".equals(input));
    }
}

