package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;


public class Player {

    //INSTANCE VARIABLE
    private String character;
    private List<String> inventory = new ArrayList<>();
    private int health = 5;
    private boolean isStrong;
    private boolean isSmart;
    private Room currentRoom;
    private int maxSize = 5;

    // CONSTRUCTOR
    public Player(){
    }

    public Player(String character, List<String> inventory, int health, boolean isStrong, boolean isSmart) {
        this.character = character;
        this.inventory = inventory;
        this.health = health;
        this.isStrong = isStrong;
        this.isSmart = isSmart;
    }

    //METHODS
    public void move(Room room){
        // Players will be able to move based on the command. (GO EAST, GO SOUTH..ETC)
    }
    public void jump(){
        // Players will be able to jump when he/she has to jump across to avoid obstacles
    }
    public void addItem(String item){
        // Players will be able to add Items to their inventory until the maximum items are reached
        if (inventory.size()< maxSize)
            this.inventory.add(item);
        else{
            System.out.println("You need to drop an item to add more to your inventory");
            System.out.println(inventory);
        }
         }

    public void dropItem(String item){
        // Players will be able to drop Items from inventory if they want to switch to different items.
            if(inventory.contains(item)){
                inventory.remove(item);
                System.out.println(item + " Dropped");
            }
            else {
                System.out.println("You do not have " + item + " in your inventory");
            }


    }
    public void browse(){
        // Players will get full description of the room and current location.

    }
    public void getCurrentItemDetails(){
        if(inventory.isEmpty()){
            System.out.println("Your nothing in your inventory");
        }
        else{
            System.out.println("You have following items in your inventory ");
            System.out.println(inventory);

        }


//        for(Item item : items){
//            returnString += "Name:" + item.getName() + "\n";
////            returnString += "Description:" + item.getDescription() + "\n";
//        }
//        return returnString;
    }


    public void fight(){

    }




    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public int getHealth() {
        return health;
    }

    public boolean isStrong() {
        return isStrong;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public static void main(String[] args) {
        Player p1 = new Player();

        p1.addItem("Book");
        p1.addItem("Pencil");
        p1.addItem("Water");
        p1.addItem("Fire");
        p1.addItem("Pen");
        p1.addItem("phone");
        p1.addItem("rope");


        System.out.println(p1.inventory);

        p1.dropItem("Water");


        System.out.println(p1.inventory);
    }
}