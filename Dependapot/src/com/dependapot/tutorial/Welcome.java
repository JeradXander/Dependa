package com.dependapot.tutorial;


import java.util.Locale;
import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {
        questions();

    }

    static void questions() {
        System.out.println("Welcome to Dependa: From Rank to Riches\nThis tutorial will teach you the basics of the game." +
                "\nFirst a test to gauge your knowledge:");
        System.out.println("Question 1: Do you know what a dependapotomus is? ");
        Scanner questionOne = new Scanner(System.in);
        String questionAnswerOne = questionOne.nextLine().toLowerCase();
        while (!questionAnswerOne.equals("yes") && !questionAnswerOne.equals("no") && !questionAnswerOne.equals("y")
                && !questionAnswerOne.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerOne = questionOne.nextLine();
        }
        // after user type no => user want to know about dependa
        if (questionAnswerOne.equals("no") || questionAnswerOne.equals("n")) {
            System.out.println("A Dependapotomus is Traditionally a service-member’s dependent who is a “stay at home mom”\n" +
                    "that doesn’t do a damn thing all day besides sitting on the couch looking remarkably similar to Jabba\nthe" +
                    "Hut leaching off of military benefits and eating anything that gets too close.");
        }
        // when the user entered yes or no
        String questionAnswerTwo = questionOne.nextLine();
        System.out.println("Question 2: Do you know the ");

    }
}