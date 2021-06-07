package com.locallampoon.fiveh.core;

class Monster {
    //INSTANCE VARIABLE
    private int health;
    private boolean isStrong;
    private boolean isSmart;
    private Room currentRoom;
    private boolean isDead;

    // CONSTRUCTOR

    public Monster(){
        this.health = 6;
        this.isStrong = false;
        this.isSmart = false;
        this.isDead = false;

    }


    public Monster(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;

    }
    public void  attack(Player ghost){
        int damage =1;
        ghost.takeDamage(damage);
    }
    public void takeDamage (int damage){
        if (health - damage <=0){
            health = 0;
            isDead = true;
        } else {
            health -= damage;
        }
    }
    public void monsterDetails(){

    }

    // ACCESSOR METHOD


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isStrong() {
        return isStrong;
    }

    public void setStrong(boolean strong) {
        isStrong = strong;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean isDead() {
        return isDead;
    }

}