package com.welcomeToTheMilitary.character;

import java.util.Random;

public class FinalBoss{
    private String rank;
    private String name;
    private int strength;
    private int vitality;


    //public Weapons(String _name,int strength, int vitality, int level){
    //CID soldier is the final boss
    public FinalBoss (String rank, String name, int _strength, int _vitality){
        this.strength = _strength;
        this.vitality = _vitality;
        this.rank = rank;
        this.name = name;
    }

    public String getRank() {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public int attack(){
        int max = 9;
        int min = 2;
        Random damage = new Random();
        int x = damage.nextInt((max - min) +1) +min;
        System.out.println(x);
        int att = this.strength/x;
        Math.round(att);
        System.out.println(this.rank +" "+ this.name + " attacked you for " + att + " points of damage");
        return att;
    }

    public int getVitality() {
        return this.vitality;
    }

    public void setVitality(int damage) {
        this.vitality = this.vitality - damage;
    }

    public String fortSillFinalBossSpeech() {
        return "The Final Boss has appeared. Defeat him or you will be demoted back to E-1.";
    }

    @Override
    public String toString() {
        return "FortSill Final Boss: {  Rank: " + rank +
                ", Name: " + name +
                ", Strength: " + strength +
                ", Vitality: " + vitality +
                '}';
    }

//    public static void main(String[] args) {
//        FinalBoss ssg = new FinalBoss("SFC", "Daniels", 20,30,new Weapons("Fists",5,5,5));
//        System.out.println(ssg);
//        System.out.println(ssg.attack());
//    }
}
