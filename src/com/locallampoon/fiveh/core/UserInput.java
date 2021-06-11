package com.locallampoon.fiveh.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserInput {

    private static final List<String> ACTIONS = new ArrayList<>(Arrays.asList("go", "move", "get", "grab", "drop",
            "talk", "inspect", "h", "help", "i", "inventory", "q", "quit"));


    private static List<String> inputCleaner(String reducedString) {
        reducedString = reducedString.trim().toLowerCase();
        String[] commands = reducedString.split("\\W");
        List<String> commandList = new ArrayList<>(Arrays.asList(commands));
        while (commandList.contains("")) {
            commandList.remove("");
        }
        return commandList;
    }

    public static String nounItemHelper(List<String> wordList, Player gamePlayer) {
        String itemSubstring = wordList.get(1);
        String verb = wordList.get(0);
        String noun = "";
        List<String> tempRoomPlayerItemList = gamePlayer.getCurrentRoom().getItems();
        List<String> tempItemList;
        boolean nounPresent = false;

        if (verb.equals("drop")) {
            tempRoomPlayerItemList.clear();
            tempRoomPlayerItemList = gamePlayer.getInventory();
        }

        for (int i = 0; i < tempRoomPlayerItemList.size(); i++) {

            String singleItem = tempRoomPlayerItemList.get(i);
            tempItemList = Arrays.asList(singleItem.split(" "));

            for (String word : tempItemList) {

                if (itemSubstring.equalsIgnoreCase(word)) {
                    nounPresent = true;
                    break;
                }
            }
            if (nounPresent) {
                noun = singleItem;
                break;
            }else{
                System.out.println("Noun does not exist in room, please check your spelling");
            }
            System.out.println("we looking at this room item!!!!  "+ singleItem);
            i++;
        }
        return noun;
    }

    public static List<String> parseCommand(String commandInput) {

        List<String> wordsList = inputCleaner(commandInput);
        String verb;
        String reqCommandAgain = "requestCommandAgain";

        if (!wordsList.isEmpty()) {
            verb = wordsList.get(0);
            if (!ACTIONS.contains(verb)) {
                System.out.println("Not an acceptable action");
            }
        } else {
            System.out.println("Two word command expected I.E. 'get sword' or 'go north'");
            wordsList.add(0, reqCommandAgain);
        }
        return wordsList;

    }
}