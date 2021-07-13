package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Inventory;
import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.attributes.RetrieveSpecialHelper;
import com.welcomeToTheMilitary.json_pack.JsonReader;

import java.util.HashMap;

public class ServiceMember extends Character {
    private String postName = "Fort Sill";
    public int healPotion;
    public int level;
    private String special = "Baking";
    private String location;
    private Inventory inventory = null;
    private String rank = null;
    private int health = 0;
    private int strength = 0;

    // private Item items = null;
    private String pcsRequest = null;
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
        this.health = 100;
        this.strength = 5;
        pcsRequest = "No request (Type: request pcs to request a pcs)";
        healPotion = 5;
    }

    public int getHealPotion() {
        return this.healPotion;
    }

    public void setHealPotion() {
        this.healPotion -= 1;
    }

    public String getPcsRequest() {
        return this.pcsRequest;
    }

    public void setPcsRequest(String _pcsRequest) {
        this.pcsRequest = _pcsRequest;
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
        this.setHealth( this.getHealth() + 2, true);
        this.setStrength( this.getStrength()  + 2);
        this.rank = _rank;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int _health, boolean isHeal) {
        if (isHeal) {
            this.health += _health;
        } else {
            this.health -= _health;
        }
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
    public void storeItemInVentory(Item itemName) {
        // System.out.println("Adding up the item: " + itemName);
        inventory.addItem(itemName);
    }

    public void viewMyInventory() {
        inventory.viewInventory();
    }

    public Item useItem(String _itemName) {
        System.out.println("Inside useItem method");
        // check if the item exist
        System.out.println(_itemName);
        boolean isItemExist = inventory.checkInventory(_itemName);
        if (isItemExist) {
            Item usedItem = inventory.getItem(_itemName);
            System.out.println(usedItem);
            useItemHelper(usedItem.getType());
            System.out.println("You used " + _itemName);
            return usedItem;
        }
        return null;
    }

    private void useItemHelper(String type) {
        if (type == null) {
            System.out.println("The item cannot be used (type does not exist)");
            return;
        }
        switch (type.toLowerCase()) {
            case "consumable":
                System.out.println("consumable type heal hp");
                this.setHealth(this.getHealth() + 5, true);
                break;
            case "effective":
                System.out.println("effective type");
                break;
            case "weapon":
                System.out.println("Weapon type attack damage increased!");
                this.setStrength(this.getStrength() + 2);
                break;
            default:
                System.out.println("The current item type is not supported");
                break;
        }
    }

    public int attack() {
//        System.out.println("Attack: " + this.getStrength());
        return this.getStrength();
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

}
