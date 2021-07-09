package com.welcomeToTheMilitary.json_pack;

import com.welcomeToTheMilitary.character.ServiceMember;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    private Reader reader = null;
    private InputStream inputFileOutputJSON = JsonReader.class.getResourceAsStream("/output.json");
    private InputStream inputFileSpecialJSON = JsonReader.class.getResourceAsStream("/specials.json");
    public ServiceMember returnSolder(){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ServiceMember soldierToReturn = null;

//        String path = JsonReader.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//        System.out.println("You are about to enter the JSon files" + path);
        // try (FileReader reader = new FileReader("jsonFiles/output.json"))
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileOutputJSON)))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
//            employeeList.forEach( emp -> parseSoldierObject( (JSONObject) emp ) );
//
            soldierToReturn = parseSoldierObject((JSONObject) employeeList.get(0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return soldierToReturn;
    }

    public HashMap<String,String> getSpecials(){
        HashMap<String,String> specialHash = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        // try (FileReader reader = new FileReader( "jsonFiles/specials.json"))
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFileSpecialJSON)))
        {
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            //Iterate over employee array
            obj.keySet().forEach( eachSpecial -> {
                JSONObject special = (JSONObject) obj.get(eachSpecial);
                specialHash.put(special.get("name").toString(),special.get("description").toString());
            } );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return specialHash;
    }

    public static void printHelpRequestDataFromJSON() {
        JSONParser parser = new JSONParser();
        InputStream inputFilePossibleVerbAndNounJSON = JsonReader.class.getResourceAsStream("/possibleVerbAndNoun.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFilePossibleVerbAndNounJSON, "UTF-8")))
        {
            Object helpObjectObj = parser.parse(reader);
            JSONObject helpObject = (JSONObject) helpObjectObj;
            // helpObject = (JSONObject) parser.parse(new FileReader("jsonFiles/possibleVerbandNoun.json"));
            helpObject.keySet().forEach(eachInstruction -> {
                System.out.println("=".repeat(5) + " " + eachInstruction + " " + "=".repeat(5));
                JSONObject instructionSet = (JSONObject) helpObject.get(eachInstruction);
                System.out.println("Supported " + eachInstruction + " verb " + instructionSet.get("verb").toString());
                System.out.println("Supported " + eachInstruction + " noun " + instructionSet.get("noun").toString());
                System.out.println("Example " + eachInstruction + " example " + instructionSet.get("example").toString() + "\n");
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();;
        }
    }

    private ServiceMember parseSoldierObject(JSONObject soldierObject){
//Get employee object within list
        JSONObject soldierobj = (JSONObject) soldierObject.get("soldier");

        //Get employee first name
        String name = (String) soldierobj.get("name");
        System.out.println(name);

        //Get employee last name
        String special = (String) soldierobj.get("special");
        System.out.println(special);

        //Get employee website name
        String location = (String) soldierobj.get("location");
        System.out.println(location);


        return new ServiceMember(name,special,location);
    }

}
