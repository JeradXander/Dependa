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

    @Test
    public void receiveActionSuccessForBliss() {
        String successResponse = "move starbucks";
        response = textParser.receiveAction(successResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        System.out.println(actualVerb);
        assertEquals("move", actualVerb);
        assertEquals("starbucks", actualNoun);
        System.out.println("receiveActionSuccessForBliss Pass");
    }

    @Test
    public void receiveActionEmptyInputFortSill() {
        // it returns an empty string when we get empty string from user
        String emptyResponse = "";
        response = textParser.receiveAction(emptyResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
        System.out.println("receiveActionEmptyInput Pass");
    }

    @Test
    public void receiveActionEmptyInputFortBliss() {
        String emptyResponse = "";
        response = textParser.receiveAction(emptyResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionNullInputFortSill() {
        // if the input is null somehow, it should return empty string
        String nullResponse = null;
        response = textParser.receiveAction(nullResponse, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionNullInputFortBliss() {
        // testcase for null
        // it should return empty string for both verb and noun
        String nullResponse = null;
        response = textParser.receiveAction(nullResponse, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidActionFortSill() {
        // invalid verb
        // invalid verb should return empty string
        String invalidActionVerb = " ge eez";
        response = textParser.receiveAction(invalidActionVerb, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidActionFortBliss() {
        // invalid verb
        // should return both verb and noun with empty string
        String invalidActionVerb = "asdfasdf asdf";
        response = textParser.receiveAction(invalidActionVerb, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidNounFortSill() {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "move asdf";
        response = textParser.receiveAction(invalidActionNoun, FORTSILL);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }

    @Test
    public void receiveActionInvalidNounFortBliss() {
        // invalid noun
        // should return both verb and noun with empty string
        String invalidActionNoun = "drive zxbzx";
        response = textParser.receiveAction(invalidActionNoun, FORTBLISS);
        String actualVerb = response.getVerb();
        String actualNoun = response.getNoun();
        assertEquals("", actualVerb);
        assertEquals("", actualNoun);
    }
}