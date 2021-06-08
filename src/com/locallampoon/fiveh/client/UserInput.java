package com.locallampoon.fiveh.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
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

//    public static List<String> runCommand(String stringInput) {
//        String testResponse = "passing";

//        if (reducedString.equals("q") || reducedString.equals("quit")) {
//            testResponse = "You must enter a command(verb + noun)";
//            return inputList(reducedString);
//            game saving logic is a stretch goal
//        } else if (reducedString.equals("save")) {
//
//        } else if (reducedString.equals("load")) {
//
//        } else if (reducedString.equals("h") || reducedString.equals("help")) {
//            return gameInstructions();
//        } else {
//            inputList(reducedString).forEach(word -> System.out.println(word));
//            parseCommand(wordsList);
//        }
//        return inputList(reducedString);
//    }

//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader;
//        String input;
//        String output;
//
//        reader = new BufferedReader(new InputStreamReader(System.in));
//        do {
//            System.out.println(">>");
//            input = reader.readLine();
//            output = runCommand(input);
//            System.out.println(output);
//        } while (!"q".equals(input));
//    }

}