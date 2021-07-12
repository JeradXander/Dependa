package com.welcomeToTheMilitary.character;

public class SeniorEnlist extends Character {
    private String rank;
    private String name;
    private String attribute;
    // Constructor
    public SeniorEnlist(String _name, String _attribute, String _rank)
    {
        super(_name);
        this.attribute = _attribute;
        this.rank = _rank;
    }

    public String getRank() {
        return this.rank;
    }

    public String getAttribute(){
        return this.attribute;}

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "SeniorEnlist{" +
                "name ='" + name +'\'' + "attribute = '" + attribute + '\'' ;
    }
}
