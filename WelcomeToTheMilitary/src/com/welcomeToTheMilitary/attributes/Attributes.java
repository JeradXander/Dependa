package com.welcomeToTheMilitary.attributes;

public class Attributes {
//    Adding strength, intellet, spells, and vitality for our users.
    public int strength;
    public int intellect;
    public int spells;
    public int vitality;

//    public Attributes(int _strength, int _intellect, int _spells, int _vitality){
    public Attributes(int _strength, int _vitality){
        this.strength = _strength;
//        this.intellect = _intellect;
//        this.spells = _spells;
        this.vitality = _vitality;
    }

    public int getStrength(){
        return strength;
    }

    public int getIntellect() {
    return intellect;
    }

    public int getSpells(){
        return spells;
    }

    public int getVitality(){
        return vitality;
    }

    public void increaseStrength(int _amount){
        strength += _amount;
    }

    public void increaseSpells(int _amount){
        spells += _amount;
    }

    public void increaseIntellect(int _amount){
        intellect += _amount;
    }

    public void increaseVitality (int _amount){
        vitality += _amount;
    }

    @Override
    public String toString() {
        return "" +
                "strength=" + strength +
                ", intellect=" + intellect +
                ", spells=" + spells +
                ", vitality=" + vitality +
                '}';
    }
}
