package com.welcomeToTheMilitary.textparser;



public class TextParser {
    private String noun = null;
    private String verb = null;

    public TextParser() {
        // empty constructor
    }

    // private getter
    private String getVerb() {
        return this.verb;
    }

    private String getNoun() {
        return this.noun;
    }

    // this method will return an arraylist that contains verb and noun order
    // @return ParseResponse obj
    public ParseResponse receiveAction(String userActionInput, String post) {
        // trim the user action input
        boolean isValidActionInput = trimUserInput(userActionInput);
        boolean isOperableCommend = operateUserAction(isValidActionInput, post);
        if (isOperableCommend) {
            // return verbAndNoun;
            return new ParseResponse(getNoun(), getVerb());
        }

        // placeholder
        return new ParseResponse(getNoun(), getVerb());
    }

    // helper method to validate user input
    // delete word "The" or "the"
    // set up the verb and noun
    private boolean trimUserInput(String userInput) {
        if (userInput == null || userInput.length() == 0) {
            this.noun = "";
            this.verb = "";
            return false;
        }
        String trimmedInput = userInput.trim().toLowerCase();
        // split out to check if the sentence contains more then 2 words
        String splitTrimmedInput[] = trimmedInput.split(" ");
        if (splitTrimmedInput.length == 4) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[3];
        } else if (splitTrimmedInput.length == 3) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[2];
        } else if (splitTrimmedInput.length == 2) {
            this.verb = splitTrimmedInput[0];
            this.noun = splitTrimmedInput[1];
        } else if (splitTrimmedInput[0].equals("exit") || splitTrimmedInput[0].equals("quit")) {
            System.out.println("Exiting game");
            this.verb = "";
            this.noun = "";
        } else {
            // some message
            this.verb = "";
            this.noun = "";
            System.out.println("Invalid input:\nDesired input format: verb + noun\n for help type (help me)");
            return false;
        }
        return true;
    }

    // operate action
    private boolean operateUserAction(boolean isValidActionInput, String post) {
        if (!isValidActionInput) {
            System.out.println("Invalid action");
            return false;
        }
        boolean isOperable = false;
        String verbInput = this.verb;
        String nounInput = this.noun;
        switch (verbInput) {
            // instruction for movement
            case "go":
            case "move":
            case "drive":
            case "walk":
            case "run":
                isOperable = moveAction(nounInput, post);
                return isOperable;
            case "talk":
            case "approach":
            case "interact":
                isOperable = interactNPCAction(nounInput, post);
                return isOperable;
            case "show":
            case "display":
                isOperable = displayMapAction(nounInput, post);
                return isOperable;
            case "help":
                isOperable = helpAction(nounInput, post);
                return isOperable;
            case "exit":
            case "quit":
                return isOperable;
            default:
                this.verb = "";
                this.noun = "";
                System.out.println("Sorry, the verb: " + verbInput + " is not supported");
                return isOperable;
        }
    }

    // method for "help me"
    private boolean helpAction(String nounInput, String postType) {
        System.out.println("Help Action");
        if (postType.equals("Fort Sill")) {
            switch (nounInput) {
                case "me":
                    return true;
                default:
                    System.out.println("Were you trying to type the word 'help me'");
                    this.noun = "";
                    return false;
            }
        }
        return false;
    }
    
    // method for "go || move || drive || walk || run"
    private boolean moveAction(String nounInput, String postType) {
        System.out.println("Move action");
        if (postType.equals("Fort Sill")) {
            // switch case for fort sill
            System.out.println(nounInput);
            switch (nounInput) {
                case "dfac":
                case "barracks":
                case "church":
                case "px":
                case "market":
                case "gym":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else if (postType.equals("Fort Bliss")) {
            // switch case for fort bliss
            switch (nounInput) {
                case "starbucks":
                case "mall":
                case "housing":
                case "pizza":
                case "theater":
                case "gym":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else {
            // place holder for now
            return false;
        }
    }

    // method for talking or interact with the NPC
    // method for "go || move || drive || walk || run"
    private boolean interactNPCAction(String nounInput, String postType) {
        System.out.println("interact action");
        if (postType.equals("Fort Sill")) {
            // switch case for fort sill
            switch (nounInput) {
                case "brad":
                case "jeremy":
                case "rogers":
                case "shad":
                case "arturo":
                case "mason":
                case "john":
                case "brandon":
                case "laginus":
                case "soko":
                case "david":
                case "stephen":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else if (postType.equals("Fort Bliss")) {
            // switch case for fort bliss
            switch (nounInput) {
                case "gary":
                case "steve":
                case "frank":
                case "jose":
                case "roger":
                case "chip":
                case "dylan":
                case "ryan":
                case "danny":
                case "cody":
                case "justin":
                case "lambert":
                case "andy":
                case "stephen":
                    return true;
                default:
                    this.noun = "";
                    return false;
            }
        } else {
            // place holder for now
            return false;
        }
    }

    private boolean displayMapAction(String nounInput, String postType) {
        if (postType.equals("Fort Sill")) {
            switch (nounInput) {
                case "map":
                case "location":
                case "buildings":
                case "building":
                    return true;
                default:
                    this.noun = "";
                    System.out.println("possible noun=>\nmap | location | buildings| buildings");
                    return false;
            }
        }
        return false;
    }

}
