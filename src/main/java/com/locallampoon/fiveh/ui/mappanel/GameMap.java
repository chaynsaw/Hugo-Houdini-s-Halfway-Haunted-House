package com.locallampoon.fiveh.ui.mappanel;

import com.locallampoon.fiveh.core.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * load rooms and control positions on the map
 */
public class GameMap {
    private static GameMap gameMapInstance = new GameMap();
    private List<Room> mapRooms = new ArrayList<>();

    private GameMap(){
    }

    public static GameMap getInstance(){
        if(gameMapInstance==null){
            GameMap.gameMapInstance = new GameMap();
        }
        return GameMap.gameMapInstance;
    }

    public List<Room> getRooms(){
        return this.mapRooms;
    }
}
