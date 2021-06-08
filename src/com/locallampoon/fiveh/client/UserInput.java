package com.locallampoon.fiveh.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInput {


    public static List<String> inputList(String reducedString) {
        reducedString = reducedString.trim().toLowerCase();
        String[] commands = reducedString.split("\\W");
        List<String> commandList = new ArrayList<>(Arrays.asList(commands));
        while (commandList.contains("")) {
            commandList.remove("");
        }
        return commandList;
    }

}