package com.dependapot.bases;

import com.dependapot.character.LowerEnlist;
import com.dependapot.textparser.ParseResponse;
import com.dependapot.textparser.TextParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Fort_Sill_Map {
    public String name = "Fort Sill Map";
    private final String description;

//    private final static Set<String> building_Seen = new HashSet<String>();
//    private final static HashMap<String, ArrayList<LowerEnlist>> NUM_BLDGS = null;

    // private -> updated CODE WITH ME
    private HashMap<String, ArrayList<LowerEnlist>> buildings = null;
    private String currentDependaLocation = null;
    // END OF CODE WITH ME

    public Fort_Sill_Map(String _name, String description){
        this.name = _name;
        this.description = description;
        setUpFacility();
    }

    // method for enterToDfac
    public void enterToBuilding(String building) {
        if (building == null || building.length() == 0) {
            System.out.println("EnterToDfac method: invalid");
            return;
        }
        // need a method to get the solider from dfact
        ArrayList<LowerEnlist> foundedSolider = getSolider(building);
        System.out.println("You are entering to the " + building);
        System.out.println("You find " + foundedSolider.size() + " attractive soldiers");
        System.out.println("You saw their ranks!\nYou saw...");
        for (LowerEnlist eachSoldier : foundedSolider) {
            System.out.println("Rank: " + eachSoldier.getRank());
        }
        return;
    }

    //

    // going to get all the solider based on the building that is passed by parameter
    private ArrayList<LowerEnlist> getSolider(String building) {
        HashMap<String, ArrayList<LowerEnlist>> data = this.buildings;
        ArrayList<LowerEnlist> enlists = data.get(building);
        return enlists;
    }

    // put all created soldiers into facility
    private void setUpFacility() {
        this.buildings = new HashMap<>();
        this.buildings.put("dfac", prepareSoldierInDfac());
        this.buildings.put("px", prepareSoldierInPX());
        this.buildings.put("commissary", prepareSoldierInCommissary());
        this.buildings.put("church", prepareSoldierInChurch());
        this.buildings.put("gym", prepareSoldierInGym());
        this.buildings.put("barracks", prepareSoldierInBarracks());
    }

    // helper method to set up the place with correct soldiers for the dfac
    private ArrayList<LowerEnlist> prepareSoldierInDfac() {
        ArrayList<LowerEnlist> dfacSoldier = new ArrayList<>();
        LowerEnlist E1 = new LowerEnlist("e-1");
        LowerEnlist E2 = new LowerEnlist("e-2");
        LowerEnlist E3 = new LowerEnlist("e-3");
        dfacSoldier.add(E1);
        dfacSoldier.add(E2);
        dfacSoldier.add(E3);
        return dfacSoldier;
    }
    // helper method to set up the place with correct soldiers for the PX
    private ArrayList<LowerEnlist> prepareSoldierInPX(){
        ArrayList<LowerEnlist> pxSoldier = new ArrayList<>();
        LowerEnlist E6 = new LowerEnlist("e-6");
//        WarrantOfficer WO1 = new WarrantOfficer()
        pxSoldier.add(E6);
// add Warrant Officer
        return pxSoldier;
    }
    // helper method to set up the place with correct soldiers for the Commissary
    private ArrayList<LowerEnlist> prepareSoldierInCommissary(){
        ArrayList<LowerEnlist> commSoldier = new ArrayList<>();
        LowerEnlist E6 = new LowerEnlist("e-6");
        commSoldier.add(E6);
        // add Warrant Officer
        return commSoldier;
    }

    // helper method to set up the place with correct soldiers for the church
    private ArrayList<LowerEnlist> prepareSoldierInChurch(){
        ArrayList<LowerEnlist> churchSoldier = new ArrayList<>();
        LowerEnlist E4 = new LowerEnlist("e-4");
        LowerEnlist E6 = new LowerEnlist("e-6");
        churchSoldier.add(E4);
        churchSoldier.add(E6);
        return churchSoldier;
    }

    // helper method to set up the place with correct soldiers for the gym
    private ArrayList<LowerEnlist> prepareSoldierInGym(){
        ArrayList<LowerEnlist> gymSoldier = new ArrayList<>();
        LowerEnlist E5 = new LowerEnlist("e-5");
// CID
        gymSoldier.add(E5);
        return gymSoldier;
    }
    // helper method to set up the place with correct soldiers for the Barracks
    private ArrayList<LowerEnlist> prepareSoldierInBarracks(){
        ArrayList<LowerEnlist> barracksSoldier = new ArrayList<>();
        LowerEnlist E1 = new LowerEnlist("e-1");
        LowerEnlist E2 = new LowerEnlist("e-2");
        LowerEnlist E3 = new LowerEnlist("e-3");
        LowerEnlist E4 = new LowerEnlist("e-4");
        barracksSoldier.add(E1);
        barracksSoldier.add(E2);
        barracksSoldier.add(E3);
        barracksSoldier.add(E4);
        return barracksSoldier;
    }
    // it used to be static
    public void enterBuildingController(ParseResponse response){
        String noun = response.getNoun();
        String verb = response.getVerb();
        boolean is_Dependa_in_DFAC;
        boolean is_Dependa_in_Barracks;
        boolean is_Dependa_in_Church;
        boolean is_Dependa_in_Gym;
        boolean is_Dependa_in_PX;
        boolean is_Dependa_in_Commisary;
        System.out.println(noun);
        currentDependaLocation = "Fort Sill";
//        need to pull from text parser
        String _bldg_name;
//        do{
//            System.out.println("You are in Beautiful Fort Sill, Oklahoma. You are standing at the gate ready to explore what this base has to offer!!! ");
//        }while (building_Seen.contains(_bldg_name));
//        building_Seen.add(_bldg_name);
//        String building_description = null;
        if (noun.equals("dfac")){
            is_Dependa_in_DFAC = true;
            is_Dependa_in_Barracks = false;
             is_Dependa_in_Church = false;
             is_Dependa_in_Gym = false;
             is_Dependa_in_PX = false;
             is_Dependa_in_Commisary = false;
            // ding_description = "A buffet style Dinning Facility with powedered eggs, turkey bacon, and all the water you can have";
            enterToBuilding("dfac");
        }else if (noun.equals("px")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = true;
            is_Dependa_in_Commisary = false;
            enterToBuilding("px");
            // building_description = "You do not pay taxes here. Come purchase all your unneccessary needs!!!";
        }else if (noun.equals("commissary")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = true;
            enterToBuilding("commissary");
            // building_description = "Tax free grocery with all the hot chips you can buy";
        }else if(noun.equals("barracks")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = true;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("barracks");
            // building_description = "Standing still since 42', the mold in these barracks are older than your grandfather";
        }else if(noun.equals("gym")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = false;
            is_Dependa_in_Gym = true;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("gym");
//            building_description = "You can sell LuLaRoe leggings here and only do lower body exercises.";
        }else if(noun.equals("church")){
            is_Dependa_in_DFAC = false;
            is_Dependa_in_Barracks = false;
            is_Dependa_in_Church = true;
            is_Dependa_in_Gym = false;
            is_Dependa_in_PX = false;
            is_Dependa_in_Commisary = false;
            enterToBuilding("church");
//            building_description = "You've been here before and you will be back. ";
        }else{

        }
//      return  enterBuildingController( ParseResponse);
//    return new Fort_Sill_Map(building_description,false);
    }
//    public boolean isDIBLDG(){
//        return is_Dependa_in_Building;
//    }
//
//    @Override
//    public String toString() {
//        return description;
//    }

//    public void enter(Player player) throws IOException {
//        System.out.println("You are in " + description);
//        if (monster.isAlive()) {
//            new Battle(player, monster);
//        }
//    }

    public static void main(String[] args) {
        // pre-setup
        ParseResponse response = null;
        TextParser textParser = new TextParser();
        response = textParser.receiveAction("go to dfac", "Fort Sill");


        Fort_Sill_Map fort_sill_map = new Fort_Sill_Map("Fort Sill", "");
//        fort_sill_map.enterToBuilding("dfac");
//        fort_sill_map.enterToBuilding("church");
        fort_sill_map.enterBuildingController(response);
    }
}
