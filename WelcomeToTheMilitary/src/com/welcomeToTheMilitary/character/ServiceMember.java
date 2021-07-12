package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Inventory;
import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.attributes.RetrieveSpecialHelper;
import com.welcomeToTheMilitary.json_pack.JsonReader;

import java.util.HashMap;

public class ServiceMember extends Character {
    private JsonReader reader = null;

    private String postName = "Fort Sill";
    public int heal;
    public int level;
    private String special = "Baking";
    private String location;
    private Inventory inventory = null;
    private String rank = null;
    private int health = 0;
    private int strength = 0;

    // number of uses of spell
    private static int NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS = 3;

// ServiceMember Constructor that pulls from Character class
    public ServiceMember(String _name, String _special, String _location ) {
        super(_name);
        this.special = _special;
        this.location = _location;
        this.rank = "fuzzy"; // initial rank
        inventory = new Inventory();
        // this should be update everytime the soldier get promotes
        this.health = 10;
        this.strength = 5;
        // json reader
         reader = new JsonReader();
    }
    public String getName(){
        return super.getName();
    }
    public String getSpecial(){
        return special;
    }
    public String getLocation(){
        return location;
    }
    public void  setLocation(String _location){
        this.location = _location;
    }

    public String getRank() {
        return this.rank;
    }

    // this has to update service member's health and attack damage
    // will be modify with game controller
    public void setRank(String _rank) {
        // each level up health += 2
        // each level up strength += 2
        this.setHealth( this.getHealth() + 2);
        this.setStrength( this.getStrength()  + 2);
        this.rank = _rank;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int _health) {
        this.health = _health;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int _strength) {
        this.strength = _strength;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    // method to obtain item and store it in the inventory
    public void storeItemInVentory(String itemName) {
        System.out.println("Adding up the item: " + itemName);
        inventory.addItem(new Item(itemName));
    }

    public void viewMyInventory() {
        inventory.viewInventory();
    }

    public String useItem(String _itemName) {
        // check if the item exist
        System.out.println(_itemName);
        boolean isItemExist = inventory.checkInventory(_itemName);
        if (isItemExist) {
            System.out.println("You used " + _itemName);
            return _itemName;
        }
        return "";
    }

    public int attack() {
//        System.out.println("Attack: " + this.getStrength());
        return this.getStrength();
    }

    public int getHealth(String name, int heal) {
       //super(name);
        this.heal = heal;
        //this.type = "Consumable";
        return heal;
    }

    // setter to set Number of special the player can use to fight against boss
    public void setNumberOfUseSpecialOnEachFinalBoss() {
        NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS = 3;
        return;
    }

    public int getNumberOfUserSpecialOnEachFinalBoss() {
        return NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS;
    }


    public void decreaseNumberOfUseSpecialOnEachFinalBoss() {
        if (NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS > 0) {
            NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS -= 1;
            return;
        }
    }

    // use spell
    public int useSpecial() {
        if (NUMBER_OF_USE_SPECIAL_ON_EACH_FINAL_BOSS <= 0) {
            System.out.println("You cannot use spell anymore");
            System.out.println("Attacking the boss with the normal strength");
            return this.getStrength();
        }
        // if the player can use the special
        boolean isSpecialUseAbleFlag = false;
        HashMap<String, String> specialHash = RetrieveSpecialHelper.getSpecialName();
        // System.out.println(this.getSpecial());
        // make the key set into String array
        String[] possibleSpecial = specialHash.keySet().toArray(new String[0]);
        for (int i = 0; i < possibleSpecial.length; i++) {
            if (possibleSpecial[i].equals(this.getSpecial())) {
                System.out.println("Using my speciality! " + getSpecial() + specialHash.get(getSpecial()));
                isSpecialUseAbleFlag = true;
            }
        }
        if (isSpecialUseAbleFlag) {
            this.decreaseNumberOfUseSpecialOnEachFinalBoss();
            return this.getStrength() + RetrieveSpecialHelper.getSpecialDamage(getSpecial());
        }
        System.out.println("The special you typed does not exist");
        System.out.println("Attacking the boss with the normal strength");
        return this.getStrength();
    }

    @Override
    public String toString() {
        String inventory = "";
        if (this.inventory == null || this.inventory.isInventoryEmpty()) {
            inventory = "Inventory is empty";
        } else {
            inventory = this.inventory.toString();
        }
        return "=".repeat(5) + "Report Status" + " " + "=".repeat(5)
                + "\nName: " + this.getName() + "\nRank: " + this.getRank()
                + "\nSpecial " + this.getSpecial()
                + "\nHealth " + this.getHealth() + "\nStrength: " + this.getStrength()
                + "\nInventory: " + inventory + "\n" + "=".repeat(20);
    }

    public static void main(String[] args) {
        // String _name, String _special, String _location
        ServiceMember serviceMember = new ServiceMember("Park", "Dog Tags", "");
        serviceMember.useSpecial();
        serviceMember.useSpecial();
        serviceMember.useSpecial();
        System.out.println("====");
        serviceMember.useSpecial();
        serviceMember.useSpecial();
    }
}
