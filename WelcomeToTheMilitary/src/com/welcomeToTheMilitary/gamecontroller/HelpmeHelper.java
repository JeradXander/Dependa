package com.welcomeToTheMilitary.gamecontroller;

import com.welcomeToTheMilitary.character.ServiceMember;
import com.welcomeToTheMilitary.json_pack.JsonReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelpmeHelper {
    public static void printHelpRequestDataFromJSON(ServiceMember usr) {
        JSONParser parser = new JSONParser();
        InputStream inputFilePossibleVerbAndNounJSON = JsonReader.class.getResourceAsStream("/possibleVerbAndNoun.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputFilePossibleVerbAndNounJSON, "UTF-8")))
        {
            Object helpObjectObj = parser.parse(reader);
            JSONObject helpObjectHelpme = (JSONObject) helpObjectObj;
            JSONObject helpObject = (JSONObject) helpObjectHelpme.get(usr.getPostName());
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
}
