package com.dependapot.textparser;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextParserTest {
    private static TextParser textParser = new TextParser();
    private static ParseResponse response = null;
    private final String FORTSILL = "Fort Sill";
    private final String FORTBLISS = "Fort Bliss";

    @Test
    public void receiveActionSuccessFortSill() {
        // test for success path for fort sill
        String successResponse = "go dfac";
        response = textParser.receiveAction(successResponse,FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("go", actualVerb);
        assertEquals("dfac", actualNoun);
        System.out.println("receiveActionSuccessForSill Pass");
    }
}