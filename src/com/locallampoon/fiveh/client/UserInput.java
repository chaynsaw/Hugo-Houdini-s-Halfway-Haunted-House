package com.locallampoon.fiveh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserInput {
    private static List<String> inputList(String reducedString) {
        String[] commands = reducedString.split("\\W");
        List<String> commandList = new ArrayList<>(Arrays.asList(commands));
        System.out.println(commandList);
        while (commandList.contains("")) {
            commandList.remove("");
        }
        System.out.println(commandList + " with empties removed");
        return commandList;
    }

    private static void parseCommand(List<String> wordsList) {
        String message;
        String verb;
        String noun;
        List<String> actions = new ArrayList<>(Arrays.asList("go", "move", "walk", "get", "take", "drop"));
        List<String> actionItems = new ArrayList<>(Arrays.asList("key", "book", "amulet", "oregano", "sword", "duffel",
                "north", "south", "east", "west"));

        if (wordsList.size() > 2) {
            System.out.println("Two word command expected I.E. 'get sword' or 'go north'");
        } else {
            verb = wordsList.get(0);
            noun = wordsList.get(1);
            if (!actions.contains(verb)) {
                System.out.println("Not an acceptable action");
            }
            if (!actionItems.contains(noun)) {
                System.out.println("Item not in room");
            }

        }

    }

    private static String runCommand(String stringInput) {
        String testResponse = "passing";
        String reducedString = stringInput.trim().toLowerCase();

        if (reducedString.equals("")) {
            testResponse = "You must enter a command(verb + noun)";
        } else {
            inputList(reducedString).forEach(word -> System.out.println(word));
//            parseCommand(wordsList);
        }
        return testResponse;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader;
        String input;
        String output;

        reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println(">>");
            input = reader.readLine();
            output = runCommand(input);
            System.out.println(output);
        } while (!"q".equals(input));
    }

}