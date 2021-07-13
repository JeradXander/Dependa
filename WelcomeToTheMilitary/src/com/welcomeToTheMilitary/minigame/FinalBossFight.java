package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.boss.FortBlissFinalBoss;
import com.welcomeToTheMilitary.boss.FortSillFinalBoss;
import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;

// testing
import com.welcomeToTheMilitary.boss.Boss;
// end of testing
import java.util.Scanner;

public class FinalBossFight implements iMinigame {
    private ServiceMember player = null;
    private FinalBoss boss = null;

    private Boss tempBoss = null;

    // for user input / action
    private static Scanner userAction = new Scanner(System.in);

    public FinalBossFight() {
        clearScreen();
        introduction();
    }

    private void introduction() {
        System.out.println("=".repeat(5) + " Final Stage " + "=".repeat(5));
        System.out.println("You finally met the boss");
        System.out.println("Developer: only the 'attack' command is working so far.\nSorry.. T_T");
    }

    private void setBoss(ServiceMember usr) {
        if (usr.getPostName().equals("Fort Sill")) {
            tempBoss = new FortSillFinalBoss("SFC", "Daniels", 1,100);
        }
        if (usr.getPostName().equals("Fort Bliss")) {
            tempBoss = new FortBlissFinalBoss("Master", "fort bliss boss", 1,100);
        }
    }

    // clear the screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public boolean play() {
        return false;
    }

    // it was finalBoss as param
    @Override
    public boolean play(ServiceMember usr) {
        // testing
        if (usr.getPostName().equals("Fort Sill")) {
            tempBoss = new FortSillFinalBoss("SFC", "Daniels", 1,100);
        }
        if (usr.getPostName().equals("Fort Bliss")) {
            tempBoss = new FortBlissFinalBoss("Master", "fort bliss boss", 500,100);
        }
        // end of testing
        String userCommand = "";
        while (usr.getHealth() > 0 && tempBoss.getVitality() > 0) {
            displayBothStatus(usr, tempBoss);
            System.out.println("Number of Special Remained: " + usr.getNumberOfUserSpecialOnEachFinalBoss());
            System.out.print("What is your move?\n> ");
            userCommand = userAction.nextLine();
            if (userCommand == null || userCommand.length() == 0) {
                System.out.println("Boss: This is your final mercy!!!");
                System.out.println("type 'attack' | ");
                userCommand = userAction.nextLine();
            }
            userFightBossAction(userCommand, usr, tempBoss);
            if (userCommand.equals("attack") || userCommand.equals("special")){
                bossFightUserRandomAction(usr, tempBoss);
            } else {
                System.out.println("SFC Daniels is looking at you menacingly!");
            }

        }
        if (usr.getHealth() <= 0 && boss.getVitality() <= 0) {
            System.out.println("You guys tied!");
            return false;
        }
        else if (usr.getHealth() <= 0) {
            System.out.println("You lose");
            return false;
        } else if (boss.getVitality() <= 0) {
            System.out.println("You won against final boss in Fort Sill");
        }
        return true;
    }

    private void userFightBossAction(String _userCommand, ServiceMember usr, Boss boss) {
        switch (_userCommand) {
            case "attack":
                int userHitDamage = usr.attack();
                System.out.println("attacking the boss");
                boss.setVitality(userHitDamage);
                System.out.println("You damaged SFC Daniels for "+ userHitDamage);
                return;
            case "use item":
                System.out.println("Trying to use item but not implemented yet! hahahah");
                // need to think
                return;
            case "inventory":
                System.out.println("What item would you like to use: ");
                usr.viewMyInventory();
                return;
            case "special":
                int userSpecialDamage = (usr.useSpecial());
                boss.setVitality(userSpecialDamage);
                System.out.println("You damaged SFC Daniels for "+ userSpecialDamage);
                return;
            default:
                System.out.println("??");
                return;
        }
    }

    private void bossFightUserRandomAction(ServiceMember usr, Boss boss) {
        String bossActionList[] = {"attack", "use spell"};
        int randomIndex = (int) (Math.random() * (bossActionList.length - 0));
        switch (bossActionList[randomIndex]) {
            case "attack":
                int bossHitDamage = boss.attack();
                usr.setHealth(usr.getHealth() - bossHitDamage, false);
                return;
            default:
                System.out.println(bossActionList[randomIndex] + " is not implemented yet");
                return;
        }
    }

    // not implemented yet
    private void displayBothStatus(ServiceMember usr, Boss boss) {
        // Set up the String
        String introTitle = "=".repeat(14) + " Status Report " + "=".repeat(14);
        String playerTitle = "Player";
        String playerFinalBoss = "Final Boss";
        String playerRank = usr.getRank();
        String bossRank = boss.getRank();
        String playerName = usr.getName();
        String bossTitle = boss.getName();
        String playerHealth = "Hp: " + String.valueOf(usr.getHealth());
        String bossHealth = "Hp: " + String.valueOf(boss.getVitality());

        System.out.println(introTitle);
        System.out.printf("%-20s %20s %n", playerTitle, playerFinalBoss);
        System.out.printf("%-20s %20s %n", playerName, bossTitle);
        System.out.printf("%-20s %20s %n", playerRank, bossRank);
        System.out.printf("%-20s %20s %n", playerHealth, bossHealth);
        System.out.println("=".repeat(22)  + "=".repeat(22));
    }

    public static void main(String[] args) {
        ServiceMember park = new ServiceMember("Park", "Dog Tags", "fort sill");
        park.setPostName("Fort Bliss");
        // FinalBoss ssg = new FinalBoss("SFC", "Daniels", 1,100);
        FinalBossFight fightBoss = new FinalBossFight();
        fightBoss.play(park);
    }
}

