package com.dependapot;

import com.dependapot.character.Dependa;

public class Main {

    public static void main(String[] args) {
        System.out.println("A wild Dependa appears");
        Dependa celeste = new Dependa("Celeste", "Robust Center");
        System.out.println("Your Dependa's name is " + celeste.getName() + " and her special power is " + celeste.getSpecial());


    }

}
