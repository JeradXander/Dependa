package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.Random;
import java.util.Scanner;

public class RPC implements iMinigame{

    String[] moves = {"Rock","Paper","Scissors"};

    @Override
    public boolean play() {



        boolean isValid = false;
        int x = 1;


            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            clearConsole();

                boolean isDraw = false;
                boolean resultDraw =false;

                while(!isDraw){
                    clearConsole();
                    if(resultDraw){
                        System.out.println("Last Game was a Draw!! play again\n" +
                                           "_________________________________3");
                        resultDraw = false;
                        
                    }else{
                        System.out.println("Rock, Paper, Scissors\n" +
                                           "---------------------");
                    }
                    x =1;
                    while( x< moves.length +1){
                        System.out.println(x + ". " + moves[x-1]);
                        x++;
                    }

                    System.out.println("Pick your Attack:\n(Type the number assocatiated with Attack)\n" +
                                       "----------------");
                    String pick = myObj.nextLine();

                    try {
                        int pickInt = Integer.parseInt(pick);

                        if(pickInt > 0 && pickInt < moves.length +1){
                            int compMove = new Random().nextInt(moves.length);
                            pickInt--;
                            String resultstr = getWinner(pickInt,compMove);

                            if(resultstr.equals("win") || resultstr.equals("loss")){

                                return !resultstr.equals("loss");
                            }else{
                               resultDraw = true;
                            }
                        }
                    } catch (NumberFormatException ignored) {

                    }
                }
                return false;
                }

    private String getWinner(int user, int comp){

        System.out.println("You Picked "+ moves[user]+"\nThe Soldier Picked " + moves[comp]);
        if (user == 0) {
            //condiotional for comp moves
            if(comp == 0) {
                return "draw";
            }
            else if (comp ==1){
                System.out.println("Paper covers Rock");
            return "loss";
            }else if(comp ==2){
                System.out.println("Rock crushes Scissors");
                return "win";
            }
        }else if(user == 1){
            //    paper
            if(comp == 0) {
                System.out.println("Paper covers Rock");
                return "win";
            }
            else if (comp ==1){
                return "draw";
            }else if(comp ==2){
                System.out.println("Scissors cuts Paper");
                return "loss";
            }
        }else if(user == 2) {
            //    paper
            if (comp == 0) {
                System.out.println("Rock crushes Scissors");
                return "loss";
            } else if (comp == 1) {
                System.out.println("Scissors cuts Paper");
                return "win";
            } else if (comp == 2) {

                return "draw";
            }
        }
        return "";
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // placeholder for now, will figure it out
    @Override
    public boolean play(ServiceMember usr) {
        return false;
    }

}

//class Test{
//
//
//    static RPC game = new RPC();
//
//    public static void main(String[] args) {
//        boolean result = game.play();
//
//        System.out.println("result " + result);
//    }
//
//}