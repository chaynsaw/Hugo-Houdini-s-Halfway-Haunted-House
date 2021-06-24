package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;

public class Monster {
    //INSTANCE VARIABLE
    private String name;
    private int health;
    private int strength;
    private boolean isStrong;
    private boolean isSmart;
    private boolean isDead;
    private String questItem;
    private List<String> weaknesses = new ArrayList<>();

    // CONSTRUCTOR

    Monster(){
        setHealth(6);
        setStrength(3);
        setStrong(false);
        setSmart(false);
        setDead(false);
    }

    Monster(String name, String questItem, List<String> weaknesses) {
        this();
        setName(name);
        setQuestItem(questItem);
        setWeaknesses(weaknesses);
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getWeaknesses() {
        return this.weaknesses;
    }

    void attack(Player player){
        int damage = getStrength();
        player.takeDamage(damage);
    }

    void takeDamage (int damage){
        if (getHealth() - damage <=0){
            setHealth(health - damage);
            setDead(true);
        } else {
            setHealth(health - damage);
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