package com.welcomeToTheMilitary.attributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Item> inventory = new HashMap<>();

    public boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }

    public void addItem(Item item){
        if(!inventory.containsKey(item.getName())){
            inventory.put(item.getName(), item);
//            System.out.println("From Inventory class: " + this.inventory);
        }
    }

    public Item getItem(String itemname) {
        if (!inventory.containsKey(itemname)) {
            System.out.println("Cannot find the item in my inventory");
            return null;
        }
        return inventory.get(itemname);
    }

    // method to print existing items in inventory
    public void viewInventory() {
        if (inventory == null || inventory.size() == 0) {
            System.out.println("Your inventory is empty");
            return;
        }
        for (String eachKey : inventory.keySet()) {
            System.out.println("=".repeat(20));
            System.out.println("My Inventory");
            System.out.println(inventory.get(eachKey));
        }
    }

    // method to check if the item exist in the inventory
    public boolean checkInventory(String _itemName) {
        return inventory.containsKey(_itemName);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory=" + inventory +
                '}';
    }
}