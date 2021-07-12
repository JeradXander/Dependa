package com.welcomeToTheMilitary.bases;


import com.welcomeToTheMilitary.attributes.Item;
import com.welcomeToTheMilitary.character.SeniorEnlist;
import com.welcomeToTheMilitary.textparser.ParseResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Fort_Bliss_Map {
    private Random random_WO_Generator = new Random();
    public String name = "Fort Bliss";
    private final String description;

    private HashMap<String, ArrayList<SeniorEnlist>> buildings = null;
    private String currentDependaLocation = null;
    private HashMap<String, String> itemBasedOnFacility = null;

//    Constructor
    public Fort_Bliss_Map(String _name, String description){
        this.name = _name;
        this.description = description;
        setUpFacility();
        setUpItems();
    }



    // getter for item associated with the facility
    // param: building name
    public String getItemFromFacility(String facilityName) {
        return itemBasedOnFacility.get(facilityName);
    }

    // method to prepare items associated to the facility
    // only being used in the fort bliss map class
    private void setUpItems() {
        this.itemBasedOnFacility = new HashMap<>();
        itemBasedOnFacility.put("Housing", new Item("BBQ").getName());
        itemBasedOnFacility.put("Starbucks", new Item("Grande Caramel Frappe's").getName());
        itemBasedOnFacility.put("Theater", new Item("Twizzlers").getName());
        itemBasedOnFacility.put("Gym", new Item("Workout gloves").getName());
        itemBasedOnFacility.put("Freedom Crossing (Mall)", new Item("Ray Bans sunglasses").getName());
        itemBasedOnFacility.put("Restaurant", new Item("Buffalo Wings").getName());
    }

    // going to get all the solider based on the building that is passed by parameter
    public ArrayList<SeniorEnlist> getSolider(String building) {
        HashMap<String, ArrayList<SeniorEnlist>> data = this.buildings;
        ArrayList<SeniorEnlist> enlists = data.get(building);
        return enlists;
    }
    // get buildings for
    public ArrayList<String> getBuildings(){
        ArrayList<String> bldgs = new ArrayList<>();
        for (String eachBuilding: buildings.keySet()){
            bldgs.add(eachBuilding);
        }
        return bldgs;
    }
    // method for enterToBuilding
    public void enterToBuilding(String building) {
        if (building == null || building.length() == 0) {
            System.out.println("Enter To Building method: invalid");
            return;
        }
        // need a method to get the solider from building
        ArrayList<SeniorEnlist> foundedSolider = getSolider(building);
        System.out.println("You are entering the " + building);
        System.out.println("You find " + foundedSolider.size() + " soldier's from another company");
        System.out.println("You saw...");
        for (SeniorEnlist eachSoldier : foundedSolider) {
            System.out.println("Name: " + eachSoldier.getName());
            System.out.println("Attribute: " + eachSoldier.getAttribute());
        }
        return;
    }


    // get solider's name
    public ArrayList<String> getSoldierNameBasedOnBuilding(String building) {
        // check if the user is currently at the gate
        if (building.equals("gate")) {
            System.out.println("You are currently at the gate.\nNo one to talk to...T_T");
            return new ArrayList<String>();
        }
        String targetBuilding = building.trim();
        ArrayList<String> nameOfSoldiersInBuilding = new ArrayList<>();
        ArrayList<SeniorEnlist> soldiersInBuilding = buildings.get(targetBuilding);
        for (SeniorEnlist eachSoldier : soldiersInBuilding) {
            nameOfSoldiersInBuilding.add(eachSoldier.getName());
        }
        return nameOfSoldiersInBuilding;
    }

    // put all created soldiers into facility
    private void setUpFacility() {
        this.buildings = new HashMap<>();
        this.buildings.put("housing", prepareSoldierInHousing());
        this.buildings.put("starbucks", prepareSoldierInStarbucks());
        this.buildings.put("theater", prepareSoldierInTheater());
        this.buildings.put("gym", prepareSoldierInGym());
        this.buildings.put("mall", prepareSoldierInMall());
        this.buildings.put("pizza", prepareSoldierInAntPizza());
    }

    // helper method to set up the place with correct soldiers for housing
    private ArrayList<SeniorEnlist> prepareSoldierInHousing() {
        ArrayList<SeniorEnlist> housingSoldier = new ArrayList<>();
        SeniorEnlist E7 = new SeniorEnlist("Gary", "12 deployments in 10 years. Loves to shoot guns", "E-7");
        SeniorEnlist E8 = new SeniorEnlist("Steve", "Senior Jump Master. GWOT Traplord with amazing humor. 11/10 can you make you laugh", "E-8");
        SeniorEnlist E9 = new SeniorEnlist("Frank", "Rangers Lead the Way. Have you gone to Ranger School yet?", "E-9");
        housingSoldier.add(E7);
        housingSoldier.add(E8);
        housingSoldier.add(E9);
        return housingSoldier;
    }


    // helper method to set up the place with correct soldiers for Starbucks
    private ArrayList<SeniorEnlist> prepareSoldierInStarbucks() {
        ArrayList<SeniorEnlist> starbucksSoldier = new ArrayList<>();
        SeniorEnlist E5 = new SeniorEnlist("Jose", "Hasn't deployed because he has medical reason. Nobody knows what is wrong with him", "E-5");
        SeniorEnlist E6 = new SeniorEnlist("Roger", "Deployed for one month and was sent back for a injury sustained at the gym", "E-6");
        SeniorEnlist E7 = new SeniorEnlist("Chip", "Zero deployments and complains on how weak Warriors are now days", "E-7");
        starbucksSoldier.add(E5);
        starbucksSoldier.add(E6);
        starbucksSoldier.add(E7);
        return starbucksSoldier;
    }

    // helper method to set up the place with correct soldiers for Theater
    private ArrayList<SeniorEnlist> prepareSoldierInTheater() {
        ArrayList<SeniorEnlist> theaterSoldier = new ArrayList<>();
        SeniorEnlist E8 = new SeniorEnlist("Dylan", "Hides from all deployments and schools. Works strictly in Training Environments", "E-8");
        SeniorEnlist E9 = new SeniorEnlist("Ryan", "Motivator and places soldiers first.", "E-9");
        theaterSoldier.add(E8);
        theaterSoldier.add(E9);
        return theaterSoldier;
    }

    // helper method to set up the place with correct soldiers for Gym
    private ArrayList<SeniorEnlist> prepareSoldierInGym() {
        ArrayList<SeniorEnlist> gymSoldier = new ArrayList<>();
        SeniorEnlist E7 = new SeniorEnlist("Danny", "Does TikTok videos of his calisthenic workouts. Always works out in uniform.", "E-8");
        gymSoldier.add(E7);
        return gymSoldier;
    }

    // helper method to set up the place with correct soldiers for Freedom Crossing (Mall)
    private ArrayList<SeniorEnlist> prepareSoldierInMall() {
        ArrayList<SeniorEnlist> mallSoldier = new ArrayList<>();
        SeniorEnlist E5 = new SeniorEnlist("Cody", "Walks laps around the mall to lose weight but ends up taking a nap at Spencer's.", "E-5");
        SeniorEnlist E7 = new SeniorEnlist("Justin", "Low Fade and works behind a brown fence", "E-7");
        SeniorEnlist E8 = new SeniorEnlist("Lambert", "Believes that Warriors should be at work until 1900. Sends everybody to Airborne school.", "E-8");
        SeniorEnlist E9 = new SeniorEnlist("Andy", "Runs Marathons and you can always see him helping Warriors get in shape", "E-9");
        mallSoldier.add(E5);
        mallSoldier.add(E7);
        mallSoldier.add(E8);
        mallSoldier.add(E9);
        return mallSoldier;
    }

    // helper method to set up the place with correct soldiers for Anthony's
    private ArrayList<SeniorEnlist> prepareSoldierInAntPizza() {
        ArrayList<SeniorEnlist> pizzaSoldier = new ArrayList<>();
        SeniorEnlist E6 = new SeniorEnlist("Stephen", "Is always having a pizza party in the Admin office. Hasn't filed paperwork in three years. ", "E-6");
        pizzaSoldier.add(E6);
        return pizzaSoldier;
    }

    // it used to be static
    public void enterBuildingController(ParseResponse response){
        String noun = response.getNoun();
        String verb = response.getVerb();
        boolean is_Dependa_in_Housing;
        boolean is_Dependa_in_Starbucks;
        boolean is_Dependa_in_Theater;
        boolean is_Dependa_in_Gym;
        boolean is_Dependa_in_Mall;
        boolean is_Dependa_in_Anthonys;
        System.out.println(noun);
        currentDependaLocation = "Fort Bliss";
//        need to pull from text parser
        String _bldg_name;
//        do{
//            System.out.println("You are in Beautiful Fort Sill, Oklahoma. You are standing at the gate ready to explore what this base has to offer!!! ");
//        }while (building_Seen.contains(_bldg_name));
//        building_Seen.add(_bldg_name);
//        String building_description = null;
        if (noun.equals("housing")){
            is_Dependa_in_Housing = true;
            is_Dependa_in_Starbucks = false;
            is_Dependa_in_Theater = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_Mall = false;
            is_Dependa_in_Anthonys = false;
//             building_description = "A buffet style Dinning Facility with powdered eggs, turkey bacon, and all the water you can have";
            enterToBuilding("housing");
        }else if (noun.equals("mall")){
            is_Dependa_in_Housing = false;
            is_Dependa_in_Starbucks = false;
            is_Dependa_in_Theater = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_Mall = true;
            is_Dependa_in_Anthonys = false;
            enterToBuilding("mall");
            // building_description = "You do not pay taxes here. Come purchase all your unnecessary needs!!!";
        }else if (noun.equals("theater")){
            is_Dependa_in_Housing = false;
            is_Dependa_in_Starbucks = false;
            is_Dependa_in_Theater = true;
            is_Dependa_in_Gym = false;
            is_Dependa_in_Mall = false;
            is_Dependa_in_Anthonys = false;
            enterToBuilding("theater");
            // building_description = "Tax free grocery with all the hot chips you can buy";
        }else if(noun.equals("starbucks")){
            is_Dependa_in_Housing = false;
            is_Dependa_in_Starbucks = true;
            is_Dependa_in_Theater = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_Mall = false;
            is_Dependa_in_Anthonys = false;
            enterToBuilding("starbucks");
            // building_description = "Standing still since 42', the mold in these barracks are older than your grandfather";
        }else if(noun.equals("gym")){
            is_Dependa_in_Housing = false;
            is_Dependa_in_Starbucks = false;
            is_Dependa_in_Theater = false;
            is_Dependa_in_Gym = true;
            is_Dependa_in_Mall = false;
            is_Dependa_in_Anthonys = false;
            enterToBuilding("gym");
//            building_description = "You can find rusted gym equipment and listen to 80's rock.";
        }else if(noun.equals("pizza")){
            is_Dependa_in_Housing = false;
            is_Dependa_in_Starbucks = false;
            is_Dependa_in_Theater = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_Mall = false;
            is_Dependa_in_Anthonys = true;
            enterToBuilding("pizza");
//            building_description = "You've been here before and you will be back. ";
        }else{

        }
//      return  enterBuildingController( ParseResponse);
//    return new Fort_Sill_Map(building_description,false);
    }

}
