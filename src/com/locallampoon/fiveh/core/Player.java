package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;


class Player {

    //INSTANCE VARIABLE
    private String character;
    private List<String> inventory = new ArrayList<>(5);
    private int health;
    private boolean isStrong;//Players will use to move heavy items
    private boolean isSmart;
    private Room currentRoom;
    private int smBagSize = 5;
    private int lgBagSize = 10;
    private boolean hasDuffelBag;
    private boolean isDead;
//    AttackMonster attackMonster;

    // CONSTRUCTOR
    Player() {
        this.health = 5;
        this.isDead = false;
        this.isSmart = false;
        this.isStrong = false;
        while (this.inventory.size() < smBagSize) {
            this.inventory.add("");
        }
        this.setHasDuffelBag(false);
    }

    Player(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;
    }
//    public Player(List<String> inventory, int health, boolean isStrong, boolean isSmart){
//        this();
//        this.inventory = inventory;
//        this.isStrong = isStrong;
//        this.isSmart = isSmart;
//
//    }

    //METHODS
    void move(Room room) {
        // Players will be able to move based on the command. (GO EAST, GO SOUTH..ETC)
        currentRoom = room;
    }

    void addItem(String item) {
        // Players will be able to add Items to their inventory until the maximum items are reached
        if (item.equals("duffel")){
            changeInventorySize();
            System.out.println("You now have a Duffel Bag, you can carry twice as many items!\n");
            getInventory();
        }
        else if (inventory.contains("")){
            int index = inventory.indexOf("");
            inventory.remove(index);
            inventory.add(index, item);
            System.out.println("You added " + item + " to your inventory");
        }
        else {
            System.out.println("You need to drop an item to add more to your inventory");
            getInventory();
        }
    }

    void dropItem(String item) {
        // Players will be able to drop Items from inventory if they want to switch to different items.
        if (inventory.contains(item)) {
            inventory.remove(item);
            System.out.println(item + " Dropped");
        } else {
            System.out.println("You do not have " + item + " in your inventory");
        }

    }

    void getInventory() {
        String bagName = !isHasDuffelBag() ? "FANNY PACK" : "DUFFEL BAG";
        System.out.println("\n" + bagName + " ITEMS: ");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((inventory.listIterator(i).nextIndex() + 1) + ".) " + inventory.listIterator(i).next());
        }
        System.out.println("\n");
    }

    private void changeInventorySize() {
        int spaceToAdd = getLgBagSize() - this.inventory.size();
        int i = 0;
        while (i < spaceToAdd) {
            this.inventory.add("");
            i++;
        }
        setHasDuffelBag(true);
    }

    void attack(Monster monster) {
        int damage = 1;
        monster.takeDamage(damage);
    }

    void takeDamage(int damage) {
        if (health - damage <= 0) {
            health = 0;
            isDead = true;
        } else {
            health -= damage;
        }
    }

    void getCurrentItemDetails() {
        if (inventory.isEmpty()) {
            System.out.println("Your nothing in your inventory");
        } else {
            System.out.println("You have following items in your inventory ");
            System.out.println(inventory);

        }


//        for(Item item : items){
//            returnString += "Name:" + item.getName() + "\n";
////            returnString += "Description:" + item.getDescription() + "\n";
//        }
//        return returnString;
    }


    //ACCESSOR METHOD

    Room getCurrentRoom() {
        return currentRoom;
    }

    int getLgBagSize() {
        return lgBagSize;
    }

    String getCharacter() {
        return character;
    }

    void setCharacter(String character) {
        this.character = character;
    }

    int getHealth() {
        return health;
    }

    boolean isStrong() {
        return isStrong;
    }

    boolean isSmart() {
        return isSmart;
    }

    boolean isDead() {
        return isDead;
    }

    public void setHasDuffelBag(boolean hasDuffelBag) {
        this.hasDuffelBag = hasDuffelBag;
    }

    public boolean isHasDuffelBag() {
        return hasDuffelBag;
    }
}