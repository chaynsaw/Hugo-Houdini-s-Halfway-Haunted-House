package com.locallampoon.fiveh.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.locallampoon.fiveh.core.Game.actionPanel;
import static com.locallampoon.fiveh.core.Game.narrativePanel;
import static com.locallampoon.fiveh.ui.PanelStyles.Global.BG_COLOR;
import static com.locallampoon.fiveh.ui.PanelStyles.Global.FG_COLOR;


public class Player {

    final List<String> inventory = new ArrayList<>(5);
    private final List<String> squad;
    //INSTANCE VARIABLE
    private String character;
    private int health;
    private int strength;
    private boolean isStrong;//Players will use to move heavy items
    private boolean isSmart;
    private boolean isBrave;
    private Room currentRoom;
    private boolean hasDuffelBag;
    private boolean isDead;
    private boolean ranAway;
//    AttackMonster attackMonster;

    // CONSTRUCTOR
    Player() {
        setHealth(20);
        setDead(false);
        setSmart(false);
        setStrength(1);// I am debating whether to make it boolean or int.
        setStrong(false);
        setBrave(false);
        this.ranAway = false;
        int smBagSize = 5;
        while (this.inventory.size() < smBagSize) {
            this.inventory.add("");
        }
        this.squad = new ArrayList<>(3);
        this.setHasDuffelBag(false);
    }

    Player(Room currentRoom) {
        this();
        this.currentRoom = currentRoom;
    }

    //METHODS
    void move(Room room) {
        // Players will be able to move based on the command. (GO EAST, GO SOUTH..ETC)
        currentRoom = room;
    }

    void addItem(String item) {
        // Players will be able to add Items to their inventory until the maximum items are reached
        if (item.equals("duffel")) {
            changeInventorySize();
            actionPanel.appendTextArea("You now have a Duffel Bag, you can carry twice as many items!\n", FG_COLOR);
            printInventoryItems();
        } else if (inventory.contains("")) {
            int index = inventory.indexOf("");
            inventory.remove(index);
            inventory.add(index, item);

            if (actionPanel!=null) {
                actionPanel.appendTextArea("You added " + item + " to your inventory", FG_COLOR);
            }
            printInventoryItems();
        } else {
            actionPanel.appendTextArea("You need to drop an item to add more to your inventory", FG_COLOR);
            printInventoryItems();
        }
    }

    void dropItem(String item) {
        // Players will be able to drop Items from inventory if they want to switch to different items.
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            inventory.remove(item);
            inventory.add(index, "");

            if (actionPanel!=null) {
                actionPanel.appendTextArea(item + " Dropped", FG_COLOR);
            }
        } else {
            if (actionPanel!=null) {
                actionPanel.appendTextArea("You do not have " + item + " in your inventory", FG_COLOR);
            }
        }
        printInventoryItems();
    }

    void addNpc(String npc) {
        squad.add(npc);
    }

    void removeNpc(String npc) {
        squad.remove(npc);
    }

    public List<String> getSquad() {
        return squad;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public StringBuilder getInventoryItemsString() {
        StringBuilder result = new StringBuilder();
        String bagName = !isHasDuffelBag() ? "FANNY PACK" : "DUFFEL BAG";
        result.append("\n" + bagName + " ITEMS: " + "\n");
        for (int i = 0; i < inventory.size(); i++) {
            result.append((inventory.listIterator(i).nextIndex() + 1) + ".) " + inventory.listIterator(i).next() + "\n");
        }
        return result;
    }

    void printInventoryItems() {
        String bagName = !isHasDuffelBag() ? "FANNY PACK" : "DUFFEL BAG";
        if (narrativePanel != null) {
            narrativePanel.appendTextArea("\n" + bagName + " ITEMS: ", FG_COLOR);
        }
        for (int i = 0; i < inventory.size(); i++) {
            if (narrativePanel != null) {
                narrativePanel.appendTextArea((inventory.listIterator(i).nextIndex() + 1) + ".) " + inventory.listIterator(i).next(), FG_COLOR);
            }
        }
        if (!squad.isEmpty()) {
            if (narrativePanel != null) {
                narrativePanel.appendTextArea("\nThe Squad: " + getSquad(), FG_COLOR);
            }
        }
        if (narrativePanel != null) {
            narrativePanel.appendTextArea("\n", FG_COLOR);
        }
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

    // THis is fight loop method that will be used for pokemon style battle.
//    void fight(Monster monster) {
//        int fightStage = 1;
//        int healthDamageRate = 2;
//        while(this.health > 0 && monster.getHealth()> 0){
//            monster.setHealth(monster.getHealth()- healthDamageRate);
//            health = health - healthDamageRate;
//            if(health > 0 && monster.getHealth() > 0 ) {
//                System.out.println("After stage " + fightStage + " fight, your health is " + health);
//                System.out.println("Monster's health is " + monster.getHealth());
//            }else if ( health <0) {
//                System.out.println("The monster killed you");
//                System.exit(0);
//            }
//            else {
//                System.out.println("You killed monster");
//                this.getCurrentRoom().setMonster(null);
//            }
//            fightStage++;
//        }
//    }

    void attack(Monster monster) {
        int damage = getStrength();
        for (String item : this.getInventory()) {
            if (monster.getWeaknesses().contains(item)) {
                if (actionPanel != null) {
                    actionPanel.appendTextArea("You used " + item + "!", FG_COLOR);
                }
                damage += 5;
                break;
            }
        }
        monster.takeDamage(damage);
        if (monster.isDead()) {
            getCurrentRoom().addItem(monster.getQuestItem());
            getCurrentRoom().setRoomMonster(null);
        }

        if (actionPanel != null) {
            actionPanel.appendTextArea("You hit the monster, it took " + damage + " DAMAGE and has " + monster.getHealth() + " HEALTH left\n", FG_COLOR);

        }
    }

    void takeDamage(int damage) {
        if (getHealth() - damage <= 0) {
            setDead(true);

            if (actionPanel!=null) {
                actionPanel.appendTextArea("The monster killed you.", FG_COLOR);
                actionPanel.appendTextArea("GAME OVER", FG_COLOR);
            }
        } else {
            setHealth(health - damage);
            if (actionPanel != null) {
                actionPanel.appendTextArea(getCurrentRoom().getRoomMonster().getName() + " used attack!  You to took " + damage +
                        " DAMAGE and have " + health + " HEALTH left\n", FG_COLOR);
            }
        }
    }


    void flee(Map<String, Room> houseMap) {
        List<String> roomExits = getCurrentRoom().getExits();
        List<String> nonEmptyRoomExits = new ArrayList<>();
        for (String r : roomExits) {
            if (r.length() > 0)
                nonEmptyRoomExits.add(r);
        }
        Random rand = new Random(); //instance of random class
        int upperbound = nonEmptyRoomExits.size();
        int int_random = rand.nextInt(upperbound);
        Room destRoom = houseMap.get(nonEmptyRoomExits.get(int_random));
        move(destRoom);
    }


    //ACCESSOR METHOD

    void getCurrentItemDetails() {
        if (inventory.isEmpty()) {
            narrativePanel.appendTextArea("Your nothing in your inventory\n", FG_COLOR);
        } else {
            narrativePanel.appendTextArea("You have following items in your inventory ", FG_COLOR);
            narrativePanel.appendTextArea(inventory.toString(), FG_COLOR);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    int getLgBagSize() {
        return 10;
    }


    void setCharacter(String character) {
        this.character = character;
    }

    int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    boolean isStrong() {
        return isStrong;
    }

    public void setStrong(boolean strong) {
        isStrong = strong;
    }

    boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isHasDuffelBag() {
        return hasDuffelBag;
    }

    public void setHasDuffelBag(boolean hasDuffelBag) {
        this.hasDuffelBag = hasDuffelBag;
    }

    public boolean isBrave() {
        return isBrave;
    }

    public void setBrave(boolean brave) {
        isBrave = brave;
    }

    public boolean isInventoryFull() {
        boolean result = true;
        for (String item : this.getInventory()) {
            if (item.equals("")) {
                result = false;
                break;
            }
        }
        return result;
    }
}