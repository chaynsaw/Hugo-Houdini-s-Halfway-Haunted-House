package com.locallampoon.fiveh.ui.mappanel;

import com.locallampoon.fiveh.core.Player;
import com.locallampoon.fiveh.core.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * load rooms and control positions on the map
 */
public class GameMap {
    private static GameMap gameMapInstance = new GameMap();
    private Map<String, Room> mapRooms = new HashMap<>();
    private Player player;

    private GameMap() {
    }

    public static GameMap getInstance() {
        if (gameMapInstance == null) {
            GameMap.gameMapInstance = new GameMap();
        }
        return GameMap.gameMapInstance;
    }

    public Map<String, Room> getRooms() {
        return this.mapRooms;
    }

    public void setRooms(Map<String, Room> rooms) {
        this.mapRooms = rooms;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
