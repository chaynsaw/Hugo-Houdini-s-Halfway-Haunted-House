package com.locallampoon.fiveh.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserInputTest {
    String workingString1 = "go north";
    String workingString2 = "go North";
    String workingString3 = "Go north";
    String workingString4 = "Go North";
    String workingString5 = "go NORTH";
    String workingString6 = "gO noRth";

    String brokenString1 = "go@#north";
    String brokenString2 = "go3 north";
    String brokenString3 = "go   $$     north";
    String brokenString4 = "go north!!!@@";
    String brokenString5 = "go##% north363";
    String brokenString6 = "go n0rth";
    String brokenString7 = "g0 north";

    @Test
    public void parseCommand_normalStringsTest() {
        String[] expectedArray = new String[]{"go", "north"};

        String[] workingArray1 = UserInput.parseCommand(workingString1).toArray(new String[0]);
        String[] workingArray2 = UserInput.parseCommand(workingString2).toArray(new String[0]);
        String[] workingArray3 = UserInput.parseCommand(workingString3).toArray(new String[0]);
        String[] workingArray4 = UserInput.parseCommand(workingString4).toArray(new String[0]);
        String[] workingArray5 = UserInput.parseCommand(workingString5).toArray(new String[0]);
        String[] workingArray6 = UserInput.parseCommand(workingString6).toArray(new String[0]);

        assertArrayEquals(expectedArray, workingArray1);
        assertArrayEquals(expectedArray, workingArray2);
        assertArrayEquals(expectedArray, workingArray3);
        assertArrayEquals(expectedArray, workingArray4);
        assertArrayEquals(expectedArray, workingArray5);
        assertArrayEquals(expectedArray, workingArray6);
    }

    @Test
    public void parseCommand_pollutedStringsTest() {
        String[] expectedArray = new String[]{"go", "north"};

        String[] brokenWorkingArray1 = UserInput.parseCommand(brokenString1).toArray(new String[0]);
        String[] brokenWorkingArray2 = UserInput.parseCommand(brokenString2).toArray(new String[0]);
        String[] brokenWorkingArray3 = UserInput.parseCommand(brokenString3).toArray(new String[0]);
        String[] brokenWorkingArray4 = UserInput.parseCommand(brokenString4).toArray(new String[0]);
        String[] brokenWorkingArray5 = UserInput.parseCommand(brokenString5).toArray(new String[0]);

        assertArrayEquals(expectedArray, brokenWorkingArray1);
        assertArrayEquals(expectedArray, brokenWorkingArray2);
        assertArrayEquals(expectedArray, brokenWorkingArray3);
        assertArrayEquals(expectedArray, brokenWorkingArray4);
        assertArrayEquals(expectedArray, brokenWorkingArray5);
    }

    @Test
    public void parseCommand_pollutedStrings_middleOfWord_test() {
        String[] expectedArray = new String[]{"go", "north"};

        String[] brokenWorkingArray6 = UserInput.parseCommand(brokenString6).toArray(new String[0]);
        String[] brokenWorkingArray7 = UserInput.parseCommand(brokenString7).toArray(new String[0]);

        assertNotEquals(expectedArray[1], brokenWorkingArray6[1]);
        assertNotEquals(expectedArray[0], brokenWorkingArray7[0]);
    }
}