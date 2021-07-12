package com.welcomeToTheMilitary.boss;

import java.util.Random;

public class Boss {
    private String rank;
    private String name;
    private int strength;
    private int vitality;

    public Boss(String _rank, String _name, int _strength, int _vitality) {
        this.rank = _rank;
        this.name = _name;
        this.strength = _strength;
        this.vitality = _vitality;
    }

    public String getRank() {
        return this.rank;
    }
    public String getName() {
        return this.name;
    }
    public int getVitality() {
        return this.vitality;
    }
    public void setVitality(int damage) {
        this.vitality = this.vitality - damage;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return this.strength;
    }

    public int attack() {
        System.out.println("base class Boss");
        return 0;
    }

    @Override
    public String toString() {
        return "Final Boss: {  Rank: " + rank +
                ", Name: " + name +
                ", Strength: " + strength +
                ", Vitality: " + vitality +
                '}';
    }
}
