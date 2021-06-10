package com.locallampoon.fiveh.core;

import java.util.PrimitiveIterator;

class Monster {
    //INSTANCE VARIABLE
    private int health;
    private int strength;
    private boolean isStrong;
    private boolean isSmart;
    private Room currentRoom;
    private boolean isDead;

    // CONSTRUCTOR

    Monster(){
        this.health = 6;
        this.strength=10;
        this.isStrong = false;
        this.isSmart = false;
        this.isDead = false;

    }


    Monster(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;

    }


    void  attack(Player player){
        int damage =0;
        player.takeDamage(damage);
    }

    void takeDamage (int damage){
        if (health - damage <=0){
//            health = 0;
            isDead = true;
            System.out.println("Player killed the monster");
        } else {
            health -= damage;
            System.out.println(health);
        }
    }



    // ACCESSOR METHOD


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    boolean isStrong() {
        return isStrong;
    }

    void setStrong(boolean strong) {
        isStrong = strong;
    }

    boolean isSmart() {
        return isSmart;
    }

    void setSmart(boolean smart) {
        isSmart = smart;
    }

    Room getCurrentRoom() {
        return currentRoom;
    }

    boolean isDead() {
        return isDead;
    }

}