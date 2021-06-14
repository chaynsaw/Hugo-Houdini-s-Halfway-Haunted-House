package com.locallampoon.fiveh.core;

class Monster {
    //INSTANCE VARIABLE
    private String name;
    private int health;
    private int strength;
    private boolean isStrong;
    private boolean isSmart;
    private boolean isDead;
    private String questItem;

    // CONSTRUCTOR

    Monster(){
        setHealth(6);
        setStrength(10);
        setStrong(false);
        setSmart(false);
        setDead(false);
    }

    Monster(String name, String questItem) {
        this();
        setName(name);
        setQuestItem(questItem);
    }

    void attack(Player player){
        int damage = 1;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getQuestItem() {
        return questItem;
    }

    public void setQuestItem(String questItem) {
        this.questItem = questItem;
    }
}