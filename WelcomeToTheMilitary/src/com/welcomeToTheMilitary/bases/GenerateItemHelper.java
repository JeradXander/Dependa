package com.welcomeToTheMilitary.bases;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GenerateItemHelper {
    private HashMap<String, HashMap<String, String>> itemFromJSON = null;

    public GenerateItemHelper() {
        itemFromJSON = new HashMap<>();
    }
    // read json file and create item
    public JSONObject getItemsFromJSONFile() {
        JSONParser parser = new JSONParser();
        InputStream inputStreamForItemJSON =
                GenerateItemHelper.class.getResourceAsStream("/item.json");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamForItemJSON))) {
            JSONObject itemObj = (JSONObject) parser.parse(reader);
            JSONObject postItem = (JSONObject) itemObj.get("Fort Sill");
            // TODO: Need Fort Bliss as well
            return postItem;
        } catch(NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
            return null;
        } catch (ParseException | IOException parseException) {
            System.out.println("Parse or IO exception: GenerateItemHelper");
            parseException.printStackTrace();
            return null;
        }
        // it should return HashMap<String, String>
    }
}
