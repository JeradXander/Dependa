package com.welcomeToTheMilitary.character;

public class LowerEnlist extends Character{
    private String rank;
    private String name;
    private String attribute;
// Constructor
    public LowerEnlist(String _name, String _attribute, String _rank)
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
        return "LowerEnlist{" +
                "name ='" + super.getName() +'\'' + "attribute = '" + attribute + '\'' ;
    }
}
