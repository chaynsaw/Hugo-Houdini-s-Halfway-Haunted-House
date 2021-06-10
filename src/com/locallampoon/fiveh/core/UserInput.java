package com.locallampoon.fiveh.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserInput {

    private static final List<String> ACTIONS = new ArrayList<>(Arrays.asList("go", "move", "get", "grab", "drop", "talk", "inspect", "h", "help", "i", "inventory", "q", "quit"));
    private static final List<String> ACTION_ITEMS = new ArrayList<>(Arrays.asList("key", "book", "amulet", "oregano", "sword", "duffel",
            "north", "south", "east", "west", "up", "down"));


    private static List<String> inputCleaner(String reducedString) {
        reducedString = reducedString.trim().toLowerCase();
        String[] commands = reducedString.split("\\W");
        List<String> commandList = new ArrayList<>(Arrays.asList(commands));
        while (commandList.contains("")) {
            commandList.remove("");
        }
        return commandList;
    }

    private static String forceHelp(int usrAttempts, BufferedReader userInputReader) throws IOException {
        System.out.println("try new command or h for help >> ");
        if (usrAttempts >= 3){
            Game.getHelp();
        }
        return userInputReader.readLine();
    }

    static List<String> parseCommand(String commandInput, int attempts, BufferedReader userInputReader) throws IOException {

        List<String> wordsList = inputCleaner(commandInput);
        String verb;
        String noun;
        String inputRequest;

        if ((!wordsList.isEmpty()) && wordsList.size() <= 2) {
            boolean verbPresent = true;
            boolean nounPresent = true;
            verb = wordsList.get(0);
            if (!ACTIONS.contains(verb)) {
                attempts += 1;
                verbPresent = false;
                System.out.println("Not an acceptable action");
            }

            if (wordsList.size() == 2) {
                noun = wordsList.get(1);
                if (!ACTION_ITEMS.contains(noun)) {
                    System.out.println("Item not in room, check your spelling");
                    nounPresent = false;
                }
            }

            if (attempts > 0){

                if (!nounPresent && verbPresent) attempts = 2;

                inputRequest = forceHelp(attempts, userInputReader);
                parseCommand(inputRequest, attempts, userInputReader);
            }
        } else {
            System.out.println("Two word command expected I.E. 'get sword' or 'go north'");
            attempts += 1;
            inputRequest = forceHelp(attempts, userInputReader);
            parseCommand(inputRequest, attempts, userInputReader);
        }
        return wordsList;
    }
}