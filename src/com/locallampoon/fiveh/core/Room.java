package com.locallampoon.fiveh.core;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

import static com.locallampoon.fiveh.core.XMLParser.hall;

/**
 *  This class defines the Room object
 *
 * @author Local Lampoon
 * @version 0.0.2
 */

public class Room {
    // FIELDS
    private String roomName;
    private String desc;
//    private int roomPID;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Room up;
    private Room down;
    List<String> adjacentRooms = new ArrayList<>(); // (East : diningRoom) (West : trophyRoom) Go East -> player.setCurrRoom(roomMap.get(trophyRoom))
    private List<String> items = new ArrayList<>();

    // METHODS
    // CONSTRUCTOR


    public Room(String roomName, String desc) {
        this.roomName = roomName;
        this.desc = desc;
//        this.roomPID = roomName.hashCode()*7;
    }

    /**
     * Public constructor to create new room object
     *
     * @param roomName String representing the name of the room.
     * @param desc String representing the description of the room.
     * @param adjacentRooms HashMap of String keys and String values representing the exits from the room to adjacent rooms.
     * @param items ArrayList of Strings representing the items that are currently in the room for later use
     */

    public Room(String roomName, String desc, List<String> adjacentRooms, List<String> items) {
        setRoomName(roomName);
        setDesc(desc);
        setAdjacentRooms(adjacentRooms);
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

//    private void setRoomExits(int roomPID, List<String> roomExits) {
//        for (int i = 0; i < roomExits.size(); i++) {
//            String adjacentRoom = roomExits.get(i);
//            if (!roomExits.get(i).equals("") || !roomExits.get(i).equals(" ")) {
//                switch (adjacentRoom) {
//                    case 22364559:
//                        hall.setNorth(ObjectName.getInstance());
//                }
//            }
//        }
//    }

    public List<String> getAdjacentRooms() {
        return adjacentRooms;
    }

    public void setAdjacentRooms(List<String> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }


//    public Room getCurrentRoom() {
//        return roomPID;
//    }
//
//    public void setCurrentRoom(Room currentRoom) {
//        this.roomPID = currentRoom;
//    }

    public Room getNorth() {
        return north;
    }

    public Room setNorth(Room north) {
        this.north = north;
        return this;
    }


//    public Room getNorth() {
//        return north;
//    }
//
//    public void setNorth(Room north) {
//        this.north = north;
//    }

    public Room getSouth() {
        return south;
    }

    public Room setSouth(Room south) {
        this.south = south;
        return this;
    }

    public Room getEast() {
        return east;
    }

    public Room setEast(Room east) {
        this.east = east;
        return this;
    }

    public Room getWest() {
        return west;
    }

    public Room setWest(Room west) {
        this.west = west;
        return this;
    }

    public Room getUp() {
        return up;
    }

    public Room setUp(Room up) {
        this.up = up;
        return this;
    }

    public Room getDown() {
        return down;
    }

    public Room setDown(Room down) {
        this.down = down;
        return this;
    }

    public List<String> getItems() {
        return items;
    }

    private void setItems(List<String> items) {
        this.items = items;
    }
}