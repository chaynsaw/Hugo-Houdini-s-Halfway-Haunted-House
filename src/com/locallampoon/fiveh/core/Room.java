package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class defines the Room object
 *
 * @author Local Lampoon
 * @version 0.0.2
 */

class Room {
    // FIELDS
    private String roomName;
    private String desc;
    List<String> exits = new ArrayList<>(); // (East : diningRoom) (West : trophyRoom) Go East -> player.setCurrRoom(roomMap.get(trophyRoom))
    private List<String> items = new ArrayList<>();

    // METHODS
    // CONSTRUCTOR


    /**
     * Public constructor to create new room object
     *
     * @param roomName String representing the name of the room.
     * @param desc String representing the description of the room.
     * @param exits HashMap of String keys and String values representing the exits from the room to adjacent rooms.
     * @param items ArrayList of Strings representing the items that are currently in the room for later use
     */

    Room(String roomName, String desc, List<String> exits, List<String> items) {
        setRoomName(roomName);
        setDesc(desc);
        setExits(exits);
        setItems(items);
    }

    // Helpers

    // item being added to room (dropped by player)
    public void addItem(String item) {
        items.add(item);
    }

    // item being removed from room (picked up by player)
    public void removeItem(String item) {
        items.remove(item);
    }

    // ACCESSORS

    public String getRoomName() {
        return roomName;
    }

    private void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getExits() {
        return exits;
    }

    public void setExits(List<String> exits) {
        this.exits = exits;
    }

    public List<String> getItems() {
        return items;
    }

    private void setItems(List<String> items) {
        this.items = items;
    }
}