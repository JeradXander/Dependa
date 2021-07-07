package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.Fort_Sill_Map;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.character.LowerEnlist;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import com.welcomeToTheMilitary.minigame.MinigameFactory;
import com.welcomeToTheMilitary.minigame.RPC;
import com.welcomeToTheMilitary.minigame.iMinigame;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import com.welcomeToTheMilitary.tutorial.Welcome;

import java.util.*;

public class GameController {

    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Fort_Sill_Map fortSill = new Fort_Sill_Map("Fort Sill", "Some post");
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> spellList = new ArrayList<>();

    // minigame
    private static MinigameFactory gameFactory = new MinigameFactory();
    private static iMinigame minigame = null;
    private static iMinigame rockPaperScissors = new RPC();

    public static void main(String[] args) {
//        spellList.add("Bumper Sticker");
//        spellList.add("LuLuRoe Business Card");

        ServiceMember usrDep = Welcome.intro(spellList);
        parser = new TextParser();

        // below this line while loop
        String userAction = "";
        int counter = 0;
        while (!userAction.equals("exit") && !userAction.equals("quit")) {
            if(counter ==0){
                System.out.println("Welcome to Fort Sill. Your Drill Instructor dropped you off at the gate.");
            }
            System.out.println("Enter your action [format= verb + noun] for help type (help me)");
            userAction = input.nextLine();
            response = parser.receiveAction(userAction, fortSill.getName());
            if (!(response.getVerb().equals("")) || !(response.getNoun().equals(""))) {
                System.out.println("Verb: " + response.getVerb());
                try {
                    switch (response.getVerb().trim()) {
                        case "go":
                        case "move":
                        case "drive":
                        case "walk":
                        case "run":
                            GameController.enteringBuildingController(response.getNoun(), usrDep);
                            break;
                        case "display":
                        case "show":
                            GameController.displayBuildings(response.getNoun(), usrDep);
                            break;
                        case "talk":
                        case "approach":
                        case "interact":
                            GameController.interactWithNPC(response.getNoun(), usrDep);
                            break;
                        case "help":
//                        case "quit":
//                        case "exit":
                            interactHelpRequest(response.getNoun(), usrDep);
                            break;
                        default:
                            System.out.println("Verb " + response.getVerb());
                            System.out.println("Noun: " + response.getNoun());
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid action: type 'help me' to get info");
                    e.printStackTrace();
                } // end of try-catch
            }// end of try-catch
            counter++;
        } // end of if statement
    } // end of while loop

    // generate random game
    private static String prepareRandomGame() {
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("rock paper scissors");
        gameList.add("memorization game");
        final int min = 0;
        final int max = gameList.size();
        int randomIndex = (int) (Math.random() * (max - min));
        return gameList.get(randomIndex);
    }

    private static void interactWithNPC(String noun, ServiceMember usrDep) {
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            return;
        }
        String playerCurrentLocation = usrDep.getLocation();
        // check the soldier's existence
        ArrayList<LowerEnlist> existingSolider = fortSill.getSolider(playerCurrentLocation);
        if (existingSolider == null || existingSolider.size() == 0) {
            System.out.println("There is no one in the area... T_T");
            return;
        }
        String targetSoldierName = noun.substring(0, 1).toUpperCase() + noun.substring(1);
        LowerEnlist solider = null;
        for (LowerEnlist eachSolider : existingSolider) {
            if (eachSolider.getName().equals(targetSoldierName)) {
                solider = eachSolider;
                break;
            }
        }
        // founded case
        if (solider != null) {
            System.out.println("Targeting:" + noun);
            System.out.println("You finally saw " + solider.getName() + "'s rank!\nIt is " + solider.getRank());
            // game start
            // boolean isWin = rockPaperScissors.play();
            minigame = gameFactory.playGame(prepareRandomGame());
            boolean isWin = minigame.play();
            System.out.println("Win or lose: " + isWin);
            if (isWin) {
                usrDep.storeItemInVentory(fortSill.getItemFromFacility(usrDep.getLocation()));
                boolean isWorthRank = PromoteHelper.isRankWorthIt(usrDep, solider);
                if (isWorthRank) {
                    usrDep.setRank(solider.getRank());
                    System.out.println("Congrats you won the interaction.\n" +
                            "Obtained item: " + fortSill.getItemFromFacility(usrDep.getLocation()) + "\n" +
                            "Player's current rank: " + usrDep.getRank());
                } else {
                    System.out.println("Congrats you won the interaction.");
                    System.out.println("You decided not to take their rank\n" + "It is lower than yours yuck!");
                }
            } else {
                System.out.println("You have lost. You maintain your rank but lost your dignity!!!");
            }
            return;
        }
        System.out.println("Cannot find the soldier you are looking for");
        return;
    }

    private static void interactHelpRequest(String noun, ServiceMember usrDep) {
        switch (noun) {
//            case"exit":
//            case "quit":
//                System.out.println("Thanks for playing");
//                System.exit(0);
            default:
                JsonReader.printHelpRequestDataFromJSON();
        }
    }

    private static void displayBuildings(String noun, ServiceMember usrDep) {
        switch (noun) {
//            add buildings
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }

    private static void enteringBuildingController(String noun, ServiceMember usrDep) {
        String lowerNoun =noun.toLowerCase();
        switch (lowerNoun) {
            case "dfac":
            case "px":
            case "church":
            case "gym":
            case "barracks":
            case "market":
                System.out.println("Entering: " + noun + " building");
                fortSill.enterToBuilding(noun);
//                setDependaLocation(noun, usrDep);
                usrDep.setLocation(noun);
                System.out.println("Curent " + usrDep.getName() + "'s location: " + usrDep.getLocation());
                break;
            default:
                System.out.println("These are the possible location you can go!!");
                System.out.println(fortSill.getBuildings());
                break;
        }
    }
}