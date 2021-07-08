package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Inventory;
import com.welcomeToTheMilitary.attributes.Item;

public class ServiceMember extends Character {


    private String postName = "Fort Sill";
    public int heal;
    public int level;
    private String special = "Baking";
    private String location;
    private Inventory inventory = null;
    private String rank = null;
    private int health = 0;
    private int strength = 0;

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
        boolean isItemExist = inventory.checkInventory(_itemName);
        if (isItemExist) {
            System.out.println("You used " + _itemName);
            return _itemName;
        }
        return "";
    }

    public int attack() {
        System.out.println("Attack: " + this.getStrength());
        return this.getStrength();
    }

    public int getHealth(String name, int heal) {
       //super(name);
        this.heal = heal;
        //this.type = "Consumable";
        return heal;
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
                + "\nHealth " + this.getHealth() + "\nStrength: " + this.getStrength()
                + "\nInventory: " + inventory + "\n" + "=".repeat(20);
    }

}
