package com.welcomeToTheMilitary.bases;

import com.welcomeToTheMilitary.character.Enlisted;
import com.welcomeToTheMilitary.textparser.ParseResponse;
import com.welcomeToTheMilitary.textparser.TextParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Fort_Sill_MapTest {
    // test cases
    private static TextParser textParser = new TextParser();
    private static ParseResponse response = null;
    private static Fort_Sill_Map fort_sill_map = new Fort_Sill_Map("Fort Sill", "");

    @Test
    public void getSoliderSuccessPath() {
        ArrayList<Enlisted> actualEnlist = fort_sill_map.getSolider("dfac");
        ArrayList<Enlisted> dfacSoldier = new ArrayList<>();
        Enlisted E1 = new Enlisted("Brad", "High and Thight. Forever skinny", "e-1");
        Enlisted E2 = new Enlisted("Jeremy", "Can dip a whole can of Skoal Wintergreen. Wears combat boots with his jeans", "e-2");
        Enlisted E3 = new Enlisted("Rogers", "Has an associates in Political Science. Wears Nine Line Apparel. Says Hooah a lot", "e-3");
        dfacSoldier.add(E1);
        dfacSoldier.add(E2);
        dfacSoldier.add(E3);
        assertArrayEquals(dfacSoldier.toArray(), actualEnlist.toArray());
    }

    @Test
    public void getSoliderFailPath() {
        ArrayList<Enlisted> actualEnlist = fort_sill_map.getSolider("dfac");
        ArrayList<Enlisted> dfacSoldier = new ArrayList<>();
        Enlisted E1 = new Enlisted("brad", "High and Thight. Forever skinny", "e-1");
        Enlisted E2 = new Enlisted("Jesadfremy", "Can dip a whole can of Skoal Wintergreen. Wears combat boots with his jeans", "e-2");
        Enlisted E3 = new Enlisted("Rogxzcvers", "Has an associates in Political Science. Wears Nine Line Apparel. Says Hooah a lot", "e-3");
        dfacSoldier.add(E1);
        dfacSoldier.add(E2);
        dfacSoldier.add(E3);
        for (int i = 0; i < actualEnlist.size(); i++) {
            assertEquals(dfacSoldier.get(i), actualEnlist.get(i));
        }
    }
}