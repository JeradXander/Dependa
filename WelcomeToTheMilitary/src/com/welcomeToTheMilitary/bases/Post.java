package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.character.LowerEnlist;
import com.welcomeToTheMilitary.character.ServiceMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Post {
 private String name;
 private ServiceMember sm;


 HashMap<String, ArrayList<String>> buildingsAndSoldiers = new HashMap<>();


 private ArrayList<LowerEnlist> prepareSoldierInDfac() {
        ArrayList<LowerEnlist> dfacSoldier = new ArrayList<>();
        LowerEnlist E1 = new LowerEnlist("Brad", "High and Thight. Forever skinny", "E-1");
        LowerEnlist E2 = new LowerEnlist("Jeremy", "Can dip a whole can of Skoal Wintergreen. Wears combat boots with his jeans", "E-2");
        LowerEnlist E3 = new LowerEnlist("Rogers", "Has an associates in Political Science. Wears Nine Line Apparel. Says Hooah a lot", "E-3");
        dfacSoldier.add(E1);
        dfacSoldier.add(E2);
        dfacSoldier.add(E3);
        return dfacSoldier;
    }

}


