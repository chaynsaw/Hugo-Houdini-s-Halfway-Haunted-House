package com.locallampoon.fiveh.core;

import java.io.BufferedReader;
import java.io.IOException;

class GameState {

     private static String quitSave(BufferedReader saveReader) throws IOException {
         String saveResponse;

         do {
             System.out.println("Do you want to save the game?");
             saveResponse = saveReader.readLine().toLowerCase();

             switch (saveResponse) {
                 case "no":
                 case "n":
                     return "q";
                 case "yes":
                 case "y":
                     System.out.println("Please name your save file:  >   ");
                     String saveFileName = saveReader.readLine();
                     //TODO: Write a save game/load game methods
                     //                     saveGame(saveFileName);
                     return "q";
                 case "continue":
                 case "c":
                     return "i";
             }
             System.out.println("INVALID RESPONSE: Enter Y to save and quit \n\t N to quit without saving \n\t C to continue playing");

         } while (true);
    }

    public static String quitHelper(BufferedReader quitReader) throws IOException {
        String quitCommand;
        do {
            System.out.println("Are you sure you want to quit the game?");
            String quitResponse = quitReader.readLine().toLowerCase();

            if (quitResponse.equals("no") || quitResponse.equals("n")) {
                quitCommand = "i";
                break;

            }
            else if (quitResponse.equals("yes") || quitResponse.equals("y")){
                quitCommand = quitSave(quitReader);
                break;
            }
            System.out.println("INVALID RESPONSE: Enter Y to quit \n\t N to continue");
        } while (true);

        return quitCommand;
    }
}