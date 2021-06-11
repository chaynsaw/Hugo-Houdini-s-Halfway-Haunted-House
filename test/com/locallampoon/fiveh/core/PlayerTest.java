package com.locallampoon.fiveh.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayerTest {
    Player player;
    Map<String, Room> houseMap;


    @Before
    public void setUp() throws Exception {
        houseMap = XMLParser.parseRooms();
        player = new Player(houseMap.get("hall"));
        player.addItem("key");
        player.addItem("pencil");
    }

    @Test
    public void move_ShouldReturnTrueWhenPlayerMovesToDiningRoom() {
        String roomName = "Dining Room";
        player.move(houseMap.get("diningRoom"));
        assertEquals(roomName, player.getCurrentRoom().getRoomName());
    }

    @Test
    public void move_ShouldReturnFalseWhenPlayerMovesToDiningRoom() {
        Room oldRoom = player.getCurrentRoom();
        player.move(houseMap.get("diningRoom"));
        assertNotEquals(oldRoom.getRoomName(), player.getCurrentRoom().getRoomName());
    }
}

  /*  @Test
    public void addItem_ShouldReturnTrueWhenTwoItemsAdded() {
        player.addItem("Shoe");
        player.addItem("pen");
        List<String> items = new ArrayList<>(List.of("key", "pencil", "Shoe", "pen"));

//        assertEquals(items, player.getInventory());
    }


    /*@Test
    public void dropItem_ShouldReturnTrueWhenItemIsDropped() {
        List<String> items = new ArrayList<>(List.of("pencil"));
        player.dropItem("key");

//        assertEquals(items, player.getInventory());
    }


    // uncomment when attack() targets a monster
//    @Test
//    public void attack_ShouldReturnTrueWhenPlayerDealsLessThanSixDamage() {
//        Monster m = new Monster();
//        player.attack(m);
//        assertEquals(5, m.getHealth());
//    }

    @Test
    public void takeDamage_ShouldReturnTrueWHenPlayerTakesDamageLessThanFive() {
        player.takeDamage(3);
        assertEquals(2, player.getHealth());
    }

    @Test
    public void getCurrentItemDetails() {
    }

    @Test
    public void getCurrentRoom_ShouldReturnTrueWhenPlayerRoomPassed() {
        Room r = houseMap.get("hall");
        assertSame(r, player.getCurrentRoom());
//        assert
    }


    @Test
    public void getMaxItemSize_ShouldReturnTrueWhenMaxSizeIsFive() {
//        assertEquals(5, player.getMaxItemSize());
    }

    @Test
    public void getMaxItemSize_ShouldFailWhenWrongNumberPassed() {
//        assertNotEquals(6, player.getMaxItemSize());
    }


    @Test
    public void getCharacter() {
    }

    @Test
    public void setCharacter() {
    }

  /*  @Test
    public void getInventory_ShouldReturnTrueWhenKeyAndPencilListPassed() {
        List<String> items = new ArrayList<>(List.of("key", "pencil"));

//        assertEquals(items, player.getInventory());
    }

   /* @Test
    public void getInventory_ShouldReturnFalseWhenListsDoNotMatch() {
        List<String> items = new ArrayList<>(List.of("key", "pencil", "something else"));

//        assertNotEquals(items, player.getInventory());
    }


    @Test
    public void getHealth_ShouldReturnTrueWhenPlayerHealthIsFive() {
        assertEquals(5, player.getHealth());
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
        player.takeDamage(7);
        assertTrue(player.isDead());
    }
}
   */
