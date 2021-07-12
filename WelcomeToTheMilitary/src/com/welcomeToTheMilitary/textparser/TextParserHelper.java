package com.welcomeToTheMilitary.textparser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class TextParserHelper {
    private HashMap<String, String> verbAndNoun = null;

    public TextParserHelper() {
        verbAndNoun = new HashMap<>();
    }

    public HashMap<String, String> getHashMapData() {
        return this.verbAndNoun;
    }

    public String getVerbFromHelper() {
        return this.verbAndNoun.get("verb");
    }

    public String getNounFromHelper() {
        return this.verbAndNoun.get("noun");
    }

    public boolean getCoreInstructionHelper(String userActionVerb, String userActionNoun, String postType) {
        JSONParser jsonParser = new JSONParser();
        InputStream inputStreamVerbAndNounForParseJSON =
                TextParserHelper.class.getResourceAsStream("/verbAndNounForParse.json");
        // read file
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreamVerbAndNounForParseJSON))) {
            JSONObject helpObject = (JSONObject) jsonParser.parse(reader);
            JSONObject fortSillData = (JSONObject) helpObject.get(postType);
            // System.out.println("TextParser Data: " + fortSillData);
            JSONObject matchingAction = (JSONObject) fortSillData.get(userActionVerb);
            if (matchingAction != null) {
                // found matching key
                // do something
                String isValidNoun = checkUserEnterNounValid(matchingAction, userActionNoun);
                if (isValidNoun != null) {
                    verbAndNoun.put("verb", userActionVerb);
                    verbAndNoun.put("noun", isValidNoun);
                    return true;
                }
            } else {
                // check all synonym
                String possibleMatchedSynonym = null;
                String possibleMatchedKey = null;
                String[] keysArr = Arrays.copyOf(fortSillData.keySet().toArray(), fortSillData.keySet().toArray().length, String[].class);
                // System.out.println(Arrays.toString(keysArr));
                for (int i = 0; i < keysArr.length; i++) {
                    JSONObject synonymAndNoun = (JSONObject) fortSillData.get(keysArr[i]);
                    // System.out.println("syn and noun: " + synonymAndNoun);
                    JSONArray synonym = (JSONArray)synonymAndNoun.get("synonym");
                    for (Object eachSynonymValue : synonym) {
                        if (String.valueOf(eachSynonymValue).equals(userActionVerb)) {
                            // System.out.println(eachSynonymValue);
                            possibleMatchedSynonym = String.valueOf(eachSynonymValue);
                            possibleMatchedKey = keysArr[i];
                            break;
                        }
                    }
                    if (possibleMatchedSynonym != null && possibleMatchedKey != null) {
                        break;
                    }
                }
                // System.out.println("Possible key: " + possibleMatchedKey);
                // System.out.println("Outside possible matched synonym: " + possibleMatchedSynonym);
                // if not null then go for noun, see if it matches
                if (possibleMatchedSynonym != null) {
                    JSONObject jsonObject = (JSONObject) fortSillData.get(possibleMatchedKey);
                    String isUserActionNounExist = checkUserEnterNounValid(jsonObject, userActionNoun);
                    // System.out.println(isUserActionExist);
                    // if isUserActionExist is not null then it exist
                    if (isUserActionNounExist != null) {
                        verbAndNoun.put("verb", possibleMatchedKey);
                        verbAndNoun.put("noun", isUserActionNounExist);
                        return true;
                    }
                    // if user noun is null then false
                    verbAndNoun.put("", "");
                    return false;
                } else {
                    // it means synonym is not found (verb error)
                    verbAndNoun.put("", "");
                    return false;
                }
            }
            verbAndNoun.put("", "");
            return false;
        } catch (IOException | ParseException io) {
            verbAndNoun.put("", "");
            System.out.println("Error on reading file: TextParserHelper");
            io.printStackTrace();
            return false;
        } catch (NullPointerException nullPointerException) {
            verbAndNoun.put("", "");
            System.out.println("Error on text parser helper");
            nullPointerException.printStackTrace();
            return false;
        }
    }

    // method to get appropriate noun
    private String checkUserEnterNounValid(JSONObject _jsonObject, String userActionNoun) {
        JSONArray possibleNounArray = (JSONArray) _jsonObject.get("noun");
        // System.out.println(possibleNounArray);
        for (int i = 0; i < possibleNounArray.size(); i++) {
            if (String.valueOf(possibleNounArray.get(i)).equals(userActionNoun)) {
                return String.valueOf(possibleNounArray.get(i));
            }
        }
        return null;
    }
}
