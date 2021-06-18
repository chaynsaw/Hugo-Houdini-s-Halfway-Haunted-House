package com.locallampoon.fiveh.ui.mappanel;

import com.locallampoon.fiveh.core.Monster;
import com.locallampoon.fiveh.core.Room;

import java.util.List;

/**
 * servers as our room or node where the pla
 */
public class MapRoom extends Room {
    private int dx;
    private int dy;

    public MapRoom(String roomName, String desc, List<String> exits, List<String> items, List<String> npcs, Monster roomMonster, int x, int y){
        super(roomName, desc, exits, items, npcs, roomMonster);
        this.dx = x;
        this.dy = y;
    }

    int getDx(){
        return this.dx;
    }

    int getDy(){
        return this.dy;
    }

    void setCoordinates(int x, int y){
        this.dx = x;
        this.dy = y;
    }
}
