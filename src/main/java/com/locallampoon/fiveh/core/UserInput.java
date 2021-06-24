package com.locallampoon.fiveh.core;

import com.locallampoon.fiveh.ui.PanelStyles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.locallampoon.fiveh.core.Game.narrativePanel;

class UserInput {

    private static final List<String> ACTIONS = new ArrayList<>(Arrays.asList("fight", "go", "flee", "move", "get", "grab", "drop",
            "talk", "inspect", "h", "help", "i", "inventory", "q", "quit", "fight", "attack", "hit", "punch", "kick",
            "flee", "run", "recruit"));


    private static List<String> inputCleaner(String reducedString) {
        reducedString = reducedString.trim().toLowerCase();
        String[] commands = reducedString.split("\\W|\\d");
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
        } else if (verb.equals("recruit")) {
            tempRoomPlayerItemList.clear();
            tempRoomPlayerItemList = gamePlayer.getCurrentRoom().getNpcs();
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
            } else {
                narrativePanel.appendTextArea("Noun does not exist in room, please check your spelling\n", PanelStyles.FG_COLOR);
            }
            narrativePanel.appendTextArea("we looking at this room item!!!!  " + singleItem, PanelStyles.FG_COLOR);
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
                if (narrativePanel != null) {
                    narrativePanel.appendTextArea("Not an acceptable action\n", PanelStyles.FG_COLOR);
                }
            }
        } else {
            narrativePanel.appendTextArea("Two word command expected I.E. 'get sword' or 'go north'", PanelStyles.FG_COLOR);
            wordsList.add(0, reqCommandAgain);
        }
        return wordsList;

    }
}