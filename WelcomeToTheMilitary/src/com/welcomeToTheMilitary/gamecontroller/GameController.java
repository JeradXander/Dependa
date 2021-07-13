package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.bases.Fort_Bliss_Map;
import com.welcomeToTheMilitary.bases.Fort_Sill_Map;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.SeniorEnlist;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import com.welcomeToTheMilitary.minigame.MinigameFactory;
import com.welcomeToTheMilitary.minigame.RPC;
import com.welcomeToTheMilitary.minigame.iMinigame;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import com.welcomeToTheMilitary.tutorial.Welcome;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GameController {
    private static ParseResponse response = null;
    private static TextParser parser = null;
    private static Fort_Sill_Map fortSill = new Fort_Sill_Map("Fort Sill", "Some post");
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> spellList = new ArrayList<>();
    private static Fort_Bliss_Map fortBliss = new Fort_Bliss_Map("Fort Bliss", "So close to Mexico");

    // minigame
    private static FinalBoss fortSillFinalBoss =
            new FinalBoss("SFC", "Daniels", 1, 1);
    private static MinigameFactory gameFactory = new MinigameFactory();
    private static iMinigame minigame = null;

    public static void main(String[] args) {
//        spellList.add("Bumper Sticker");
//        spellList.add("LuLuRoe Business Card");

        ServiceMember usrSM = Welcome.intro(spellList);
        parser = new TextParser();

        // below this line while loop
        String userAction = "";
        int counter = 0;
        while (!userAction.equals("exit") && !userAction.equals("quit")) {
            // condition that checks if the player's rank is E-6 then it invoke the challenge against the boss
            if (usrSM.getRank().equals("E-6") && (usrSM.getPostName().equals("Fort Sill"))) {
                System.out.println("Reached E-6...");
                System.out.println("Final Challenge!");
                minigame = gameFactory.playGame("fort sill game");
                boolean isWon = minigame.play(usrSM);
                // if player won
                if (isWon) {
                    System.out.println("Your journey in Fort Sill is over soldier..");
                    usrSM.setPostName("Fort Bliss");
                    counter = 9000000;
//                    break;
                }
            }
            if (counter == 0) {
                System.out.println("Welcome to Fort Sill. Your Drill Instructor dropped you off at the gate.");
            } else if (counter == 9000000) {
                System.out.println("You beat the boss. You are now PCS'ed to Fort Bliss.");
            }
            System.out.println("Enter your action [format= verb + noun] for help type (help me)");
            userAction = input.nextLine();
            response = parser.receiveAction(userAction, usrSM.getPostName());
            if (!(response.getVerb().equals("")) || !(response.getNoun().equals(""))) {
                try {
                    switch (response.getVerb().trim()) {
                        case "go":
                            GameController.enteringBuildingController(response.getNoun(), usrSM);
                            break;
                        case "show":
                            GameController.displayBuildings(response.getNoun(), usrSM);
                            break;
                        case "talk":
                            GameController.interactWithNPC(response.getNoun(), usrSM);
                            break;
                        case "help":
                            interactHelpRequest(response.getNoun(), usrSM);
                            break;
                        case "request":
                            // method to retrieve all possible post
                            String pcsSelect = getPossibleBuildingsForPCS(response.getNoun(), usrSM);
                            usrSM.setPcsRequest(pcsSelect);
                            System.out.println("You submitted the pcs form to " + pcsSelect);


                            break;
                        case "jun":
                            System.out.println("Good job");
                            System.out.println("You WON");
                            System.exit(0);
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

    // private method to get possible buildings for pcs
    private static String getPossibleBuildingsForPCS(String noun, ServiceMember usrSM) {
        InputStream locationJSONFile = GameController.class.getResourceAsStream("/locations.json");
        JSONParser parserForLocation = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(locationJSONFile))) {
            JSONObject locationObj = (JSONObject) parserForLocation.parse(reader);
            String[] keysArrItem = Arrays.copyOf(locationObj.keySet().toArray(), locationObj.keySet().toArray().length, String[].class);
            ArrayList<String> pcsLocationList = new ArrayList<>();
            Scanner pcsInput = new Scanner(System.in);
            int whileInt = 0;
            while (whileInt == 0) {
                System.out.println("Please type in the number associated with the post you would like to move to: ");
                int x = 1;

                for (String key : keysArrItem) {
                    System.out.println(x + ". " + key);
                    x++;
                }

                try{
                    int pcsSelection = pcsInput.nextInt();

                    if(pcsSelection > 0 && pcsSelection < keysArrItem.length +1) {
                        pcsSelection--;
                        return keysArrItem[pcsSelection];
                    }
                }catch (InputMismatchException ignored){

                }
            }

        } catch (IOException | ParseException e) {}
        return "";
    }


    // generate random game
    private static String prepareRandomGame() {
        ArrayList<String> gameList = new ArrayList<>();
        gameList.add("rock paper scissors");
        // gameList.add("memorization game");
        final int min = 0;
        final int max = gameList.size();
        int randomIndex = (int) (Math.random() * (max - min));
        return gameList.get(randomIndex);
    }

    private static void interactWithNPC(String noun, ServiceMember usrSM) {
        if (noun == null || noun.length() == 0) {
            System.out.println("Invalid soldier");
            return;
        }
        String playerCurrentLocation = usrSM.getLocation();
        String playerCurrentPost = usrSM.getPostName();
        // check the soldier's existence
        ArrayList<Enlisted> existingSolider = null;
        ArrayList<SeniorEnlist> existingSeniorSolider = null;
        if (playerCurrentPost.equals("Fort Sill")) {
            existingSolider = fortSill.getSolider(playerCurrentLocation);
        } else {
            existingSeniorSolider = fortBliss.getSolider(playerCurrentLocation);
        }
        if (playerCurrentPost.equals("Fort Sill")) {
            if (existingSolider == null || existingSolider.size() == 0) {
                System.out.println("There is no one in the area... T_T");
                return;
            }
        }
        if (playerCurrentPost.equals("Fort Bliss")) {
            if (existingSeniorSolider == null || existingSeniorSolider.size() == 0) {
                System.out.println("There is no one in the area... T_T");
                return;
            }
        }
        String targetSoldierName = noun.substring(0, 1).toUpperCase() + noun.substring(1);
        Enlisted solider = null;
        SeniorEnlist senior = null;
        if (existingSolider != null) {
            for (Enlisted eachSolider : existingSolider) {
                if (eachSolider.getName().equals(targetSoldierName)) {
                    solider = eachSolider;
                    break;
                }
            }
        } else if (existingSeniorSolider != null){
            for (SeniorEnlist eachSolider : existingSeniorSolider) {
                if (eachSolider.getName().equals(targetSoldierName)) {
                    senior = eachSolider;
                    break;
                }
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
                if (usrSM.getPostName().equals("Fort Sill")){
                    // TODO: initialize item using JSON is done, work on store it in the inventory
                    usrSM.storeItemInVentory(fortSill.showItemInTheFacilityTest(usrSM.getLocation()));
                    boolean isWorthRank = PromoteHelper.isRankWorthItFortSill(usrSM, solider);
                    if (isWorthRank) {
                        usrSM.setRank(solider.getRank());
                        System.out.println("Congrats you won the interaction.\n" +
                                "Obtained item: " + fortSill.showItemInTheFacilityTest(usrSM.getLocation()) + "\n" +
                                "Player's current rank: " + usrSM.getRank() + "\nObtained item can be used during final" +
                                "combat during the Boss stage.");
                    }
                } else if (usrSM.getPostName().equals("Fort Bliss")){
//                    need to create items
                    System.out.println("No items available in Fort Bliss currently.");
                }
                else {
                    System.out.println("Congrats you won the interaction.");
                    System.out.println("You decided not to take their rank\n" + "It is lower than yours yuck!");
                }
            } else {
                System.out.println("You have lost. You maintain your rank but lost your dignity!!!");
                System.out.println("You lost 5 hp");
                if (usrSM.getHealth() <= 0) {
                    System.out.println("You got article 15..");
                    System.out.println("Separation package...\nBye");
                    System.exit(0);
                }
                usrSM.setHealth(5, false);
                if (usrSM.getHealPotion() > 0) {
                    System.out.println("Would you like to heal?");
                    // yes or no
                    Scanner healInput = new Scanner(System.in);
                    String healAnswer = healInput.nextLine().toLowerCase();
                    while (!healAnswer.equals("yes") && !healAnswer.equals("no") && !healAnswer.equals("y")
                            && !healAnswer.equals("n")) {
                        System.out.println("Please enter yes/y or no/n.");
                        healAnswer = healInput.nextLine().toLowerCase();
                    }
                    if (healAnswer.equals("yes") || healAnswer.equals("y")){
                        usrSM.setHealth(15, true);
                        System.out.println("You used the healing potion and healed by 15");
                        usrSM.setHealPotion();
                        System.out.println("Your current health is " + usrSM.getHealth() + "\nYou now have " + usrSM.getHealPotion() + " heal potion");

                    }
                } else {
                    System.out.println("You have no health potion");
                }
            }
            return;
        } else if (senior != null){
            System.out.println("Targeting:" + noun);
            System.out.println("You finally saw " + senior.getName() + "'s rank!\nIt is " + senior.getRank());
            // game start
            // boolean isWin = rockPaperScissors.play();
            minigame = gameFactory.playGame(prepareRandomGame());
            boolean isWin = minigame.play();
            System.out.println("Win or lose: " + isWin);
            if (isWin) {
                if (usrSM.getPostName().equals("Fort Bliss")){
                    // TODO: FortBliss is not implemented yet
                    // usrSM.storeItemInVentory(fortBliss.(usrSM.getLocation()));
                    boolean isWorthRank = PromoteHelper.isRankWorthItForBliss(usrSM, senior);
                    if (isWorthRank) {
                        usrSM.setRank(senior.getRank());
                        System.out.println("Congrats you won the interaction.\n" +
                                "Obtained item: " + fortBliss.getItemFromFacility(usrSM.getLocation()) + "\n" +
                                "Player's current rank: " + usrSM.getRank());
                    }
                } else if (usrSM.getPostName().equals("Fort Bliss")){
//                    need to create items
                    System.out.println("No items available in Fort Bliss currently.");
                }
                else {
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
                HelpmeHelper.printHelpRequestDataFromJSON(usrDep);
        }
    }

    private static void displayBuildings(String noun, ServiceMember usrDep) {
        if (usrDep.getPostName().equals("Fort Sill")) {
            switch (noun) {
                case "map":
                case "buildings":
                    System.out.println(fortSill.getBuildings());
                    return;
                case "building":
                case "location":
                    // System.out.println(fo)
                    System.out.println("You are currently in " + usrDep.getLocation() + ", at Fort Sill");
                    // System.out.println("You s");
                    return;
                case "status":
                    System.out.println("=".repeat(10) + " Status Report " + "=".repeat(10));
                    System.out.println("Player name: " + usrDep.getName());
                    System.out.println("Player rank: " + usrDep.getRank());
                    System.out.println("Player's special: " + usrDep.getSpecial());
                    System.out.println("Player health: " + usrDep.getHealth());
                    System.out.println("Player attack damage: " + usrDep.getStrength());
                    System.out.println("Player's inventory:");
                    System.out.println("Player's Permanent Change of Station request: " + usrDep.getPcsRequest());
                    usrDep.viewMyInventory();
                    return;
                case "inventory":
                    usrDep.viewMyInventory();
                    return;

                default:
                    System.out.println("These are the possible location you can go!!");
                    break;
            }
        } else if (usrDep.getPostName().equals("Fort Bliss")) {
            switch (noun) {
                case "map":
                case "buildings":
                    System.out.println(fortSill.getBuildings());
                    return;
                case "building":
                case "location":
                    // System.out.println(fo)
                    return;
                case "status":
                case "inventory":
                    return;
                default:
                    System.out.println("These are the possible location you can go!!");
                    break;
            }
        }
    }

    private static void enteringBuildingController(String noun, ServiceMember usrSM) {
        String lowerNoun = noun.toLowerCase();
        if (usrSM.getPostName().equals("Fort Sill")) {
            switch (lowerNoun) {
                case "dfac":
                case "px":
                case "church":
                case "gym":
                case "barracks":
                case "market":
                    System.out.println("Entering: " + noun + " building");
                    fortSill.enterToBuilding(noun);
                    usrSM.setLocation(noun);
                    System.out.println("Current " + usrSM.getName() + "'s location: " + usrSM.getLocation());
                    break;
                default:
                    System.out.println("These are the possible location you can go!!");
                    System.out.println(fortSill.getBuildings());
                    break;
            }
        } else if (usrSM.getPostName().equals("Fort Bliss")) {
            switch (lowerNoun) {
                case "gym":
                case "pizza":
                case "housing":
                case "starbucks":
                case "theater":
                case "mall":
                    System.out.println("Entering: " + noun + " building");
                    fortBliss.enterToBuilding(noun);
                    usrSM.setLocation(noun);
                    System.out.println("Current " + usrSM.getName() + "'s location: " + usrSM.getLocation());
                    break;

                default:
                    System.out.println("These are the possible location you can go!!");
                    System.out.println(fortSill.getBuildings());
                    break;
            }
        }
    }
}