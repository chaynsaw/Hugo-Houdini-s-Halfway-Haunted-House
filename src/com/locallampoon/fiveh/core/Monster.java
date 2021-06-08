package com.locallampoon.fiveh.core;

class Monster {
    //INSTANCE VARIABLE
    private int health;
    private boolean isStrong;
    private boolean isSmart;
    private Room currentRoom;
    private boolean isDead;

    // CONSTRUCTOR

    Monster(){
        this.health = 6;
        this.isStrong = false;
        this.isSmart = false;
        this.isDead = false;

    }


    Monster(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;

    }
    void  attack(Player ghost){
        int damage =1;
        ghost.takeDamage(damage);
    }
    void takeDamage (int damage){
        if (health - damage <=0){
            health = 0;
            isDead = true;
        } else {
            health -= damage;
        }
    }
    void monsterDetails(){

    }

    // ACCESSOR METHOD


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