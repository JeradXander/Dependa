package com.bases;

import java.util.HashSet;
import java.util.Random;

public class Fort_Sill_Map {
    private final String description;
    private final Enemies _enemies;
    private final Boolean is_Dependa_in_Building;
    private final static Random _random = new Random();
    private final static Set<Integer> building_Seen = new HashSet<Integer>();
    private final static int NUM_BLDGS = 6;
    private Fort_Sill_Map(String description, Enemies _enemies, Boolean is_Dependa_in_Building){
        this.description = description;
        this._enemies = enemies;
        this.is_Dependa_in_Building = isDIBLDG;
    }
    public static Fort_Sill_Map new_Bldg(){
        if(building_Seen.size() == NUM_BLDGS){
            building_Seen.clear();
        }
        int i;
    }
}
