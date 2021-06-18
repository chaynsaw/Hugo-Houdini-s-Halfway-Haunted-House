package com.locallampoon.fiveh.ui.mappanel;

import java.util.ArrayList;
import java.util.List;

/**
 * load rooms and control positions on the map
 */
class GameMap {
    private static GameMap gameMapInstance = new GameMap();
    private List<Room> rooms = new ArrayList<>();

    private GameMap(){
        this.demoMap();
    }

    public static GameMap getInstance(){
        if(gameMapInstance==null){
            GameMap.gameMapInstance = new GameMap();
        }
        return GameMap.gameMapInstance;
    }

    private void demoMap(){
        this.rooms.add(new Room("room1", GameMapPanel.UNIT_SIZE*10, GameMapPanel.UNIT_SIZE*10));
        this.rooms.add(new Room("room2", GameMapPanel.UNIT_SIZE*28, GameMapPanel.UNIT_SIZE*32));
        this.rooms.add(new Room("room3", GameMapPanel.UNIT_SIZE*18, GameMapPanel.UNIT_SIZE*40));
        this.rooms.add(new Room("room4", GameMapPanel.UNIT_SIZE*40, GameMapPanel.UNIT_SIZE*18));
    }

    public List<Room> getRooms(){
        return this.rooms;
    }
}
