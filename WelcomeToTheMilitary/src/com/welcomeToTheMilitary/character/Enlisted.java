package com.welcomeToTheMilitary.character;

public class Enlisted extends Character{
    private String rank;
    private String name;
    private String attribute;
// Constructor
    public Enlisted(String _name, String _attribute, String _rank)
    {
        super(_name);
        name = super.getName();
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
        return "Enlisted{" +
                "name ='" + super.getName() +'\'' + "attribute = '" + attribute + '\'' ;
    }
}
