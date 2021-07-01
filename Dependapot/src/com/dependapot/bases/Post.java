package com.dependapot.bases;

import com.dependapot.character.Dependa;

import java.util.HashMap;
import java.util.Map;

public class Post {
private final Map<String, Map<String, Fort_Sill_Map>> map = new HashMap<String, Map<String, Fort_Sill_Map>>();
private Fort_Sill_Map currentMap;
//    Grab user input to build the base
private Post(){
}
private void putPost(String noun, String verb, Fort_Sill_Map bldg){
    if(!map.containsKey(noun)){
        map.put(noun, new HashMap<String, Fort_Sill_Map>());
    }
    map.get(noun).put(verb, bldg);
}

private Fort_Sill_Map getPost(String noun, String verb){
    return map.get(noun).get(verb);
}

private boolean bldg_Exists(String noun, String verb){
    if(!map.containsKey(noun)){
        return  false;
    }
    return map.get(noun).containsKey(verb);
}
public void move_Dependa(Dependa dependa){

}

}


