package com.welcomeToTheMilitary.minigame;

import com.welcomeToTheMilitary.attributes.Weapons;
import com.welcomeToTheMilitary.character.FinalBoss;
import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.Scanner;

public class FinalBossFight implements iMinigame {
    private ServiceMember player = null;
    private FinalBoss boss = null;
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

    // clear the screen
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public boolean play() {
        return false;
    }

    @Override
    public boolean play(ServiceMember usr, FinalBoss boss) {
        String userCommand = "";
        while (usr.getHealth() > 0 && boss.getVitality() > 0) {
            displayBothStatus(usr, boss);
            System.out.print("What is your move?\n> ");
            userCommand = userAction.nextLine();
            if (userCommand == null || userCommand.length() == 0) {
                System.out.println("Boss: This is your final mercy!!!");
                System.out.println("type 'attack' | ");
                userCommand = userAction.nextLine();
            }
            userFightBossAction(userCommand, usr, boss);
            BossFightUserRandomAction(usr, boss);
        }
        if (usr.getHealth() == 0) {
            System.out.println("You lose");
            return false;
        } else if (boss.getVitality() == 0) {
            System.out.println("You won against final boss in Fort Sill");
        }
        return true;
    }

    private void userFightBossAction(String _userCommand, ServiceMember usr, FinalBoss boss) {
        switch (_userCommand) {
            case "attack":
                int userHitDamage = usr.attack();
                System.out.println("attacking the boss");
                boss.setVitality(userHitDamage);
                return;
            case "use item":
                System.out.println("Trying to use item but not implemented yet! hahahah");
                // need to think
                return;
            default:
                System.out.println("??");
                return;
        }
    }

    private void BossFightUserRandomAction(ServiceMember usr, FinalBoss boss) {
        String bossActionList[] = {"attack", "use spell"};
        int randomIndex = (int) (Math.random() * (bossActionList.length - 0));
        switch (bossActionList[randomIndex]) {
            case "attack":
                int bossHitDamage = boss.attack();
                usr.setHealth(usr.getHealth() - bossHitDamage);
                return;
            default:
                System.out.println(bossActionList[randomIndex] + " is not implemented yet");
                return;
        }
    }

    // not implemented yet
    private void displayBothStatus(ServiceMember usr, FinalBoss boss) {
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
        String bossWeapon = "Weapon: " + boss.getCidWeapon();

        System.out.println(introTitle);
        System.out.printf("%-20s %20s %n", playerTitle, playerFinalBoss);
        System.out.printf("%-20s %20s %n", playerName, bossTitle);
        System.out.printf("%-20s %20s %n", playerRank, bossRank);
        System.out.printf("%-20s %20s %n", playerHealth, bossHealth);
        System.out.printf("%-20s %20s %n", "",bossWeapon);
        System.out.println("=".repeat(22)  + "=".repeat(22));
    }

//    public static void main(String[] args) {
//        ServiceMember park = new ServiceMember("Park", "a", "fort sill");
//        FinalBoss ssg = new FinalBoss("SFC", "Daniels", 20,30,new Weapons("Fists",5,5,5));
//        FinalBossFight fightBoss = new FinalBossFight();
//        fightBoss.play(park, ssg);
//    }
}

