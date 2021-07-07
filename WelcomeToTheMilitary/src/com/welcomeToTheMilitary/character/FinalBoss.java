package com.welcomeToTheMilitary.character;

import com.welcomeToTheMilitary.attributes.Weapons;
import java.util.Random;

public class FinalBoss{
    private String rank;
    private String name;
    private Weapons cidWeapon = new Weapons("Baton", 10,15,6);
    private int strength;
    private int vitality;


    //public Weapons(String _name,int strength, int vitality, int level){
    //CID soldier is the final boss
    public FinalBoss (String rank, String name, int _strength, int _vitality, Weapons cidWeapon){
        this.strength = _strength;
        this.vitality = _vitality;
        this.rank = rank;
        this.name = name;
        this.cidWeapon = cidWeapon;
    }

    public String attack(){
        int max = 9;
        int min = 2;
        Random damage = new Random();
        int x = damage.nextInt((max - min) +1) +min;
        System.out.println(x);
        int att = this.strength/x;
        Math.round(att);
        return(this.rank +" "+ this.name + " attacked you for " + att + " points of damage");
    }

    public int getVitality() {
        return this.vitality;
    }

    @Override
    public String toString() {
        return "The Final Boss has appeared. Defeat him or you will be demoted back to E-1. {" +
                "Rank: " + rank +
                ", Name: " + name +
                ", Weapon: " + cidWeapon +
                ", Strength: " + strength +
                ", Vitality: " + vitality +
                '}';
    }


    public static void main(String[] args) {
        FinalBoss ssg = new FinalBoss("SFC", "Daniels", 20,30,new Weapons("Fists",5,5,5));
        System.out.println(ssg);
        System.out.println(ssg.attack());
    }
}
