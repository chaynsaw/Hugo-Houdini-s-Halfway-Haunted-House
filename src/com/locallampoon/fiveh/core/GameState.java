package com.locallampoon.fiveh.core;

import java.io.BufferedReader;
import java.io.IOException;

class GameState {

    private static String quitSave(BufferedReader saveReader) throws IOException {
        String msg;
        String saveCommand;

        while (true) {
            System.out.println("Do you want to save the game?");
            String saveResponse = saveReader.readLine().toLowerCase();

            if ("yes".equals(saveResponse) || "y".equals(saveResponse)) {
                System.out.println("Please name your save file:  >   ");
                String saveFileName = saveReader.readLine();
                //TODO: Write a save game/load game methods
                //                     saveGame(saveFileName);
                saveCommand = "q";
                break;
            } else if ("no".equals(saveResponse) || "n".equals(saveResponse)) {
                saveCommand = "q";
                break;
            } else if ("continue".equals(saveResponse) || "c".equals(saveResponse)) {
                saveCommand = "i";
                break;
            } else {
                msg = "INVALID RESPONSE: \n\t Enter Y to save and quit \n\t N to quit without saving \n\t C to continue playing";
                System.out.println(msg);
            }
        }
        return saveCommand;
    }

    public static String quitHelper(BufferedReader quitReader) throws IOException {
        String quitCommand;
        System.out.println("Are you sure you want to quit the game?");

        while (true) {
            String quitResponse = quitReader.readLine().toLowerCase();

            if (quitResponse.equals("no") || quitResponse.equals("n")) {
                quitCommand = "i";
                break;
            } else if (quitResponse.equals("yes") || quitResponse.equals("y")) {
                quitCommand = quitSave(quitReader);
                break;
            } else {
                System.out.println("INVALID RESPONSE: \n\tEnter Y to quit \n\t N to continue");
            }
        }
        return quitCommand;
    }
}