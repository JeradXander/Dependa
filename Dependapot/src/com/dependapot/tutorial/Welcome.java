package com.dependapot.tutorial;


import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class Welcome {
    public static void main(String[] args) {
        questions();

    }


    public static void questions() {
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
        // if the user enters no they are given a definition of dependapotomus. If answer is yes user is asked the next question
        if (questionAnswerOne.equals("no") || questionAnswerOne.equals("n")) {
            System.out.println("A Dependapotomus is Traditionally a service-member’s dependent who is a “stay at home mom\n" +
                    "that doesn't do a damn thing all day besides sitting on the couch looking remarkably similar to Jabba the Hut\n" +
                    "while leaching off of military benefits and eating anything that gets too close.\n"+
                    "They use their spouses rank to impose authority on others as if they were in the military. The higher the rank the more power the Dependa becomes.");
        }
        System.out.println("Question 2: Do you know and understand the Army enlisted rank structure?");
        String questionAnswerTwo = questionOne.nextLine().toLowerCase();
        while (!questionAnswerTwo.equals("yes") && !questionAnswerTwo.equals("no") && !questionAnswerTwo.equals("y")
                && !questionAnswerTwo.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerTwo = questionOne.nextLine();
        }
        // if the user enters no they are given a rundown of the military rank structure, otherwise if answered yes it moves onto the next question.
        if (questionAnswerTwo.equals("no") || questionAnswerTwo.equals("n")){
            System.out.println("The Army enlisted rank structure consists of 9 grades from E-1 to E-9, and 13 ranks are held within those grades. Some grades have multiple ranks.\n" +
                    "The grades and ranks are as follows:  \n" +
                    "E-1/Private\n" +
                    "E-2/Private Second Class\n" +
                    "E-3/Private First Class\n" +
                    "E-4/Corporal or Specialist\n" +
                    "E-5/Sergeant\n" +
                    "E-6/Staff Sergeant\n" +
                    "E-7/Sergeant First Class\n" +
                    "E-8/Master Sergeant or First Sergeant\n" +
                    "E-9/Command Sergeant Major or Sergeant Major of the Army\n" +
                    "First Sergeant and Sergeant Major of the Army are both ranks that one must be appointed to and are limited to a select few.");
        }
        // if the user enters no they are given a brief overview of what the goal of the game is.
        System.out.println("Question 3: Do you understand the goal of this game? ");
        String questionAnswerThree = questionOne.nextLine().toLowerCase();
        while (!questionAnswerThree.equals("yes") && !questionAnswerThree.equals("no") && !questionAnswerThree.equals("y")
                && !questionAnswerThree.equals("n")) {
            System.out.println("Please enter yes/y or no/n.");
            questionAnswerThree = questionOne.nextLine();
        }
        if (questionAnswerThree.equals("no") || questionAnswerThree.equals("n")){
            System.out.println("You start off as a dependapotomus, or dependa for short, for an E-1/Private. Your goal is to climb the ranks any way possible to\n" +
                    "achieve the highest rank available to become the strongest Dependa in the Army.\n" +
                    "Every dependa starts off at Fort Sill with 100 points of health, three spells, and chooses a beginner weapon");
        }

    }

    public static void spells(){

    }
}