package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.json_pack.JsonReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Post {
    private String name = "Fort Drum";
    private ServiceMember sm;
    private HashMap<String, ArrayList<Enlisted>> buildingsAndSoldiers = new HashMap<>();
    private JsonReader jsonReader = new JsonReader();

    public Post(String _name, ServiceMember _sm){

        name = _name;
        sm = _sm;
        buildingsAndSoldiers = buildingNSoldiersBuilder();
    }

    private ArrayList<Enlisted> prepareSoldiers() {
        ArrayList<Enlisted> allSoldiers = new ArrayList<>();
        Enlisted E1 = new Enlisted("Brad Berry", "High and Thight. Forever skinny", "E-1");
        Enlisted E2 = new Enlisted("Jermaine Cool", "Can dip a whole can of Skoal Wintergreen. Wears combat boots with his jeans", "E-2");
        Enlisted E3 = new Enlisted("Roger Rodriguez", "Has an associates in Political Science. Wears Nine Line Apparel. Says Hooah a lot", "E-3");
        Enlisted E6 = new Enlisted("Shad Grunloh", "Grumpy with gray hairs. Walks with a purpose.", "E-6");
        Enlisted E6a = new Enlisted("Arturo Gomez", "Always having a cookout. Dances with products as he puts them into his cart.", "E-6");
        Enlisted E4 = new Enlisted("Mason Psefzereie", "Hide n Seek Champion from his unit. Barracks Lawyer.", "E-4");
        Enlisted E6b = new Enlisted("John Goodman", "Will pray with you and take you to choir practice.", "E-6");
        Enlisted E5 = new Enlisted("Brandon Rivers", "Do you even lift, Bro.", "E-5");
        Enlisted E1a = new Enlisted("Laginus Slow", "Got a DUI last week and avoids extra duty.", "E-1");
        Enlisted E2a = new Enlisted("Soko Malfunction", "Pot Belly. Always dirty because he's a mechanic. Never fixes anything tho.", "E-2");
        Enlisted E3a = new Enlisted("David Stephens", "Just passed Ranger School. Lives off coffee and MRE's. Listens to Classic Rock.", "E-3");
        Enlisted E4a = new Enlisted("Stephen David", "Has a low-fade. Always preparing for SFAS. Won't tell you what it means.", "E-4");
        Enlisted E7 = new Enlisted("Gary Wilson", "12 deployments in 10 years. Loves to shoot guns", "E-7");
        Enlisted E8 = new Enlisted("Steve Floerchinger", "Senior Jump Master. GWOT Traplord with amazing humor. 11/10 can you make you laugh", "E-8");
        Enlisted E9 = new Enlisted("Frank Tank", "Rangers Lead the Way. Have you gone to Ranger School yet?", "E-9");
        Enlisted E5a = new Enlisted("Jose Gooseman", "Hasn't deployed because he has medical reason. Nobody knows what is wrong with him", "E-5");
        Enlisted E6c = new Enlisted("Roger Spradley", "Deployed for one month and was sent back for a injury sustained at the gym", "E-6");
        Enlisted E7a = new Enlisted("Chip Woodworth", "Zero deployments and complains on how weak Warriors are now days", "E-7");
        Enlisted E8a = new Enlisted("Dylan Harper", "Hides from all deployments and schools. Works strictly in Training Environments", "E-8");
        Enlisted E9a = new Enlisted("Ryan Stevens", "Motivator and places soldiers first.", "E-9");
        Enlisted E7b = new Enlisted("Danny Trejo", "Does TikTok videos of his calisthenic workouts. Always works out in uniform.", "E-8");
        Enlisted E5b = new Enlisted("Cody Parkins", "Walks laps around the mall to lose weight but ends up taking a nap at Spencer's.", "E-5");
        Enlisted E7c = new Enlisted("Justin Fitch", "Low Fade and works behind a brown fence", "E-7");
        Enlisted E8b = new Enlisted("Lambert Andrew", "Believes that Warriors should be at work until 1900. Sends everybody to Airborne school.", "E-8");
        Enlisted E9b = new Enlisted("Andy Cohen", "Runs Marathons and you can always see him helping Warriors get in shape", "E-9");
        Enlisted E6d = new Enlisted("Stephen Collins", "Is always having a pizza party in the Admin office. Hasn't filed paperwork in three years. ", "E-6");


        allSoldiers.add(E6);
        allSoldiers.add(E6a);;
        allSoldiers.add(E4);
        allSoldiers.add(E6b);
        allSoldiers.add(E5);
        allSoldiers.add(E1a);
        allSoldiers.add(E2a);
        allSoldiers.add(E3a);
        allSoldiers.add(E4a);
        allSoldiers.add(E1);
        allSoldiers.add(E2);
        allSoldiers.add(E3);
        allSoldiers.add(E5a);
        allSoldiers.add(E5b);
        allSoldiers.add(E6c);
        allSoldiers.add(E6d);
        allSoldiers.add(E7);
        allSoldiers.add(E7a);
        allSoldiers.add(E7b);
        allSoldiers.add(E7c);
        allSoldiers.add(E8);
        allSoldiers.add(E8a);
        allSoldiers.add(E8b);
        allSoldiers.add(E9);
        allSoldiers.add(E9a);
        allSoldiers.add(E9b);
        return allSoldiers;
    }


    private HashMap<String, ArrayList<Enlisted>> buildingNSoldiersBuilder(){
//    Building and soldiers and return hashmap HashMap<String, ArrayList<Character>>
        HashMap<String, ArrayList<Enlisted>> tempBuildingsAndSoldiers = new HashMap<>();
        ArrayList<String> buildings = jsonReader.getbuilStrings(name);
        ArrayList<Enlisted> solders = prepareSoldiers();

            for (int i = 0; i < buildings.size();i++){
                ArrayList<Enlisted> buildignSold = new ArrayList<>();
                for(int j = 0; j < 2;j++){

                    Random rand = new Random();
                    solders.get(rand.nextInt(solders.size()));
                    solders.remove(rand.nextInt(solders.size()));
                }
                tempBuildingsAndSoldiers.put(buildings.get(i),buildignSold);
            }
        return tempBuildingsAndSoldiers;
    }

    public HashMap<String, ArrayList<Enlisted>> getBuildingNSoldiersBuilder() {
        return buildingsAndSoldiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceMember getSm() {
        return sm;
    }

    public void setSm(ServiceMember sm) {
        this.sm = sm;
    }

    public HashMap<String, ArrayList<Enlisted>> getBuildingsAndSoldiers() {
        return buildingsAndSoldiers;
    }

    public void setBuildingsAndSoldiers(HashMap<String, ArrayList<Enlisted>> buildingsAndSoldiers) {
        this.buildingsAndSoldiers = buildingsAndSoldiers;
    }

}