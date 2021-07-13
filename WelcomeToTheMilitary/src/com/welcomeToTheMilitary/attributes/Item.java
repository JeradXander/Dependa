package com.welcomeToTheMilitary.attributes;

public class Item {
    private String name;
    private Attributes attr;
    private int amount;
    private boolean stackable = false;
    private String type;
    private String description = null;

    public Item(String _name, String _description, String _type){
        this.name = _name;
        this.description = _description;
        this.attr = new Attributes(0, 0);
        this.type = _type;
    }

    public String getName(){
        return name;
    }

    // getters and setters
    public String getDescription() {
        return this.description;
    }

    public String getType() {
        return this.type;
    }

    // toString method for print
    @Override
    public String toString() {
        return "\nItem name: '" + this.getName() + '\n' +
                "Item Description : " + this.getDescription() +
                "\ntype='" + type + "\n";
    }
}
