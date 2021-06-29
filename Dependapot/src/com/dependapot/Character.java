package com.dependapot;

public class Character {
    private String name = "Jon";
//    Using the underscore to make variables more readable
    public Character(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }
}
