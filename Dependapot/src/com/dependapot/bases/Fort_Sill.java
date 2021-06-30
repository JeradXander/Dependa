package com.dependapot.bases;

public class Fort_Sill {
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
        String _bldg_name;
        do{
            System.out.println("You are in Beautiful Fort Sill, Oklahoma. You are standing at the gate ready to explore what this base has to offer!!! ");
        }while (building_Seen.contains(_bldg_name));
        building_Seen.add(_bldg_name);
        String building_description = null;
        if (_bldg_name == "DFAC"){
            building_description = "A buffet style Dinning Facility with powedered eggs, turkey bacon, and all the water you can have";

        }else if (_bldg_name == "PX"){

        }
    }
}
