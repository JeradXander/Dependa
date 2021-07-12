package com.welcomeToTheMilitary.json_pack;

import com.welcomeToTheMilitary.bases.Post;
import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.character.ServiceMember;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWriter implements iDataToRead {


    @Override
    public void dataToPass(Object _dataToPass) {//Creating a JSONObject object

        ServiceMember myObj = (ServiceMember)_dataToPass;
        ServiceMember soldierObject = (ServiceMember)_dataToPass;
        JSONObject jsonObject = new JSONObject();



        JSONArray jArray = new JSONArray();
        jsonObject.put("name",soldierObject.getName());
        jsonObject.put("special",soldierObject.getSpecial());
        jsonObject.put("location",soldierObject.getLocation());

        JSONObject soldierjobj = new JSONObject();
        soldierjobj.put("soldier", jsonObject);

        jArray.add(soldierjobj);

        try {
            FileWriter file = new FileWriter("jsonFiles/output.json");
            file.write(jArray.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);

    }



}




class testMain {




    public static void main(String[] args) {
//        Object testDependa = new ServiceMember("Jerad", "Dog Tags", "Fort Sill");
//        JsonWriter writer = new JsonWriter();
//        writer.dataToPass(testDependa);

        JsonReader jReader = new JsonReader();

        ServiceMember readServiceMember = jReader.returnSolder();



        System.out.println("This was read" + readServiceMember.getLocation() + "Name: " + readServiceMember.getName() + "special: " + readServiceMember.getSpecial());
        jReader.printHelpRequestDataFromJSON();


        ArrayList<String> testlist = jReader.getbuilStrings("Fort Bliss");

        for (String name : testlist) {
            System.out.printf("building name" + name);
        }

        Post fortBliss = new Post("Fort Drum", readServiceMember);
        System.out.println("\nYou " + fortBliss.getName() );

        HashMap<String,ArrayList<Enlisted>> blissHash = fortBliss.getBuildingsAndSoldiers();

        blissHash.keySet().forEach( eachSpecial -> {
            System.out.println("\n" + eachSpecial.toString());

    });
    }


}