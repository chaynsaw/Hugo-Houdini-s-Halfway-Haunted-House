package com.locallampoon.fiveh.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    Map<String, Room> houseMap;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;



    @Before
    public void setUp() throws Exception {
        houseMap = XMLParser.parseRooms();
        player = new Player(houseMap.get("kitchen"));
        player.addItem("key");
        player.addItem("pencil");

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void dropItem_ShouldReturnTrueWhenValidItemPassed() {
        List<String> testList = new ArrayList<>(List.of("", "pencil", "", "", ""));
        player.dropItem("key");
        assertEquals(testList, player.inventory);
    }

    @Test
    public void dropItem_ShouldReturnTrueWhenInvalidItemPassed() {
        List<String> testList = new ArrayList<>(List.of("key", "pencil", "", "", ""));
        player.dropItem("dart");
        assertEquals(testList, player.inventory);
    }

    @Test
    public void move_ShouldReturnTrueWhenPlayerMovesToDiningRoom() {
        String roomName = "Dining";
        player.move(houseMap.get("diningRoom"));
        assertEquals(roomName, player.getCurrentRoom().getRoomName());
    }

    @Test
    public void move_ShouldReturnFalseWhenPlayerMovesToDiningRoom() {
        Room oldRoom = player.getCurrentRoom();
        player.move(houseMap.get("diningRoom"));
        assertNotEquals(oldRoom.getRoomName(), player.getCurrentRoom().getRoomName());
    }

    @Test
    public void addItem_ShouldReturnTrueWhenTwoItemsAdded() {
        player.addItem("Shoe");
        player.addItem("pen");
        List<String> items = new ArrayList<>(List.of("key", "pencil", "Shoe", "pen", ""));

        assertEquals(items, player.inventory);
    }

    @Test
    public void attack_ShouldReturnTrueWhenPlayerDealsLessThanSixDamage() {
        Monster m = new Monster();
        player.attack(m);
        assertEquals(5, m.getHealth());
    }

    @Test
    public void attack_ShouldReturnTrueWhenPlayerKillsMonster() {
        Monster m = new Monster();
        player.attack(m);
        player.attack(m);
        player.attack(m);
        player.attack(m);
        player.attack(m);
        player.attack(m);
        assertTrue(m.isDead());
    }

    @Test
    public void attack_ShouldOneShotKillIfWeaknessItemInInventory() {
        Monster monster = new Monster();
        monster.setWeaknesses(Arrays.asList("weaknessItem"));
        player.addItem("weaknessItem");
        player.attack(monster);
        assertTrue(monster.isDead());
    }

    @Test
    public void takeDamage_ShouldReturnTrueWHenPlayerTakesDamageLessThanFive() {
        player.takeDamage(3);
        assertEquals(17, player.getHealth());
    }

    @Test
    public void getCurrentRoom_ShouldReturnTrueWhenPlayerRoomPassed() {
        Room r = houseMap.get("kitchen");
        assertSame(r, player.getCurrentRoom());
    }

    @Test
    public void getLgBagSize_ShouldReturnTrueWhenTenIsPassed() {
        assertEquals(10, player.getLgBagSize());
    }

    @Test
    public void getLgBagSize_ShouldReturnTrueWhenWrongNumberIsPassed() {
        assertNotEquals(-7, player.getLgBagSize());
    }

    @Test
    public void getInventory_ShouldReturnTrueWhenKeyAndPencilListPassed() {
        List<String> items = new ArrayList<>(List.of("key", "pencil", "", "", ""));

        assertEquals(items, player.getInventory());
    }

    @Test
    public void getInventory_ShouldReturnFalseWhenListsDoNotMatch() {
        List<String> items = new ArrayList<>(List.of("key", "pencil", "something else"));

        assertNotEquals(items, player.getInventory());
    }


    @Test
    public void getHealth_ShouldReturnTrueWhenPlayerHealthIsFive() {
        assertEquals(20, player.getHealth());
    }

    @Test
    public void getHealth_ShouldReturnFalseWhenPlayerHealthIsFive() {
        assertNotEquals(6, player.getHealth());
    }

    @Test
    public void isStrong_ShouldReturnTrueIfPlayerIsNotStrong() {
        assertFalse(player.isStrong());
    }

    @Test
    public void isSmart_ShouldReturnTrueIfPlayerIsNotSmart() {
        assertFalse(player.isSmart());
    }

    @Test
    public void isDead_ShouldReturnTrueIfPlayerIsAlive() {
        assertFalse(player.isDead());
    }

    @Test
    public void isDead_ShouldReturnTrueIfPlayerIsDead() {
        player.takeDamage(25);
        assertTrue(player.isDead());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}