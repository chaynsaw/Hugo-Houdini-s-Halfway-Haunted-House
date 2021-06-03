package com.locallampoon.fiveh.core;

/**
 *  This class defines the Room object
 *
 * @author Local Lampoon
 * @version 0.0.1
 */

public class Room {
    // FIELDS
    private String roomName;
    private String desc;
    private String[] exits;
    private String[] items;

    // METHODS
    // CONSTRUCTOR

    /**
     * Public constructor to create new room object
     *
     * @param roomName String representing the name of the room.
     * @param desc String representing the description of the room.
     * @param exits Array of Strings representing the exits from the room to adjacent rooms.
     * @param items Array of Strings representing the items that are currently in the room for later use
     */

    public Room(String roomName, String desc, String[] exits, String[] items) {
        setRoomName(roomName);
        setDesc(desc);
        setExits(exits);
        setItems(items);
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

    public String[] getExits() {
        return exits;
    }

    private void setExits(String[] exits) {
        this.exits = exits;
    }

    public String[] getItems() {
        return items;
    }

    private void setItems(String[] items) {
        this.items = items;
    }

//    @Override    // override class Object toString to print room details
//    public String toString() {
//        return "Room Name: " + getRoomName()
//    }
}