package com.welcomeToTheMilitary.attributes;

import com.welcomeToTheMilitary.textparser.TextParserHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;

public class RetrieveSpecialHelper {
    public static HashMap<String, String> getSpecialName() {
        JSONParser parser = new JSONParser();
        HashMap<String,String> specialHash = new HashMap<>();
        InputStream inputStreamVerbAndNounForParseJSON =
                TextParserHelper.class.getResourceAsStream("/specials.json");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamVerbAndNounForParseJSON)))
        {
            //Read JSON file
            JSONObject obj = (JSONObject) parser.parse(reader);
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

    public static int getSpecialDamage(String userSpecial) {
        JSONParser parser = new JSONParser();
        InputStream inputStreamVerbAndNounForParseJSON =
                TextParserHelper.class.getResourceAsStream("/specials.json");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamVerbAndNounForParseJSON))) {
            JSONObject listsOfSpecials = (JSONObject) parser.parse(reader);
            JSONObject special = (JSONObject) listsOfSpecials.get(userSpecial);
            int damange = Integer.parseInt(String.valueOf(special.get("damageIncrease")));
            return damange;
        } catch (FileNotFoundException fileNotFoundException) {
           System.out.println("Error on getSpecialhelper");
           fileNotFoundException.printStackTrace();
           return 0;
        } catch (ParseException parseException) {
            System.out.println("Error on getSpecialHelper");
            parseException.printStackTrace();
            return 0;
        } catch (IOException ioException) {
            System.out.println("Error on ioException");
            ioException.printStackTrace();
            return 0;
        }
    }
}
