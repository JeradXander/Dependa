package com.dependapot;

public class Dependa extends Character {
     private String special = "Baking";

// Dependa Constructor that pulls from Character class
    public Dependa(String _name, String _special) {
        super(_name);
        this.special = _special;
    }
    public String getName(){
        return super.getName();
    }
    public String getSpecial(){
        return special;
    }
}
