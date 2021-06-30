package com.dependapot.minigame;

import java.util.Scanner;

public class RPC implements iMinigame{

    @Override
    public boolean play() {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Rock, Paper, Scissors");

        String userName = myObj.nextLine();  // Read user input
        System.out.println("Username is: " + userName);  // Output user input
        System.out.println("egwegegwegegew");
        return false;
    }
}
