/*
package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private int health;
    private boolean isStrong;//Players will use to move heavy items
    private boolean isSmart;
    private Room currentRoom;
    private boolean isDead;

    public Character(int health, boolean isStrong, boolean isSmart, Room currentRoom, int maxItemSize, boolean isDead) {
        this.health = health;
        this.isStrong = isStrong;
        this.isSmart = isSmart;
        this.currentRoom = currentRoom;
        this.isDead = isDead;
    }

    public void takeDamage(int damage){
        if(health - damage <=0){
            isDead = true;
        }else {
            health -= damage;
        }
    }
    public void attack(){
        int damage = 0;
        takeDamage(damage);
    }


}*/
