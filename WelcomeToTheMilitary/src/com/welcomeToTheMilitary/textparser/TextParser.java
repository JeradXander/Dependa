package com.welcomeToTheMilitary.textparser;



public class TextParser {
    private String noun = null;
    private String verb = null;
    private TextParserHelper parserHelper = new TextParserHelper();
    public TextParser() {
        // empty constructor
        noun = "";
        verb = "";
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
        // remove for testing for purpose
        // boolean isOperableCommend = operateUserAction(isValidActionInput, post);
        boolean isOperableCommend = testOperateUserAction(isValidActionInput, post);
        if (isOperableCommend) {
            // return verbAndNoun;
            return new ParseResponse(getNoun(), getVerb());
        }

        // placeholder
        // reset the noun and verb
        this.verb = "";
        this.noun = "";
        return new ParseResponse(getNoun(), getVerb());
    }

    // for boss stage
    // utilize the idea of method overloading to avoid logic changes
    public ParseResponse receiveAction(String userActionInput, String post, String typeOfBossStage) {
        // trim the user action input
        boolean isValidActionInput = trimUserInput(userActionInput);
        boolean isOperableCommend = testOperateUserAction(isValidActionInput, post, typeOfBossStage);
        if (isOperableCommend) {
            // return verbAndNoun;
            return new ParseResponse(getNoun(), getVerb());
        }
        // placeholder
        // reset the noun and verb
        this.verb = "";
        this.noun = "";
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
            this.verb = "exit";
            this.noun = "game";
        } else {
            // some message
            this.verb = "";
            this.noun = "";
            System.out.println("Invalid input:\nDesired input format: verb + noun\n for help type (help me)");
            return false;
        }
        return true;
    }

    private boolean testOperateUserAction(boolean isValidActionInput, String postType) {
        if (!isValidActionInput) {
            System.out.println("Formatting Error");
            return false;
        }
        boolean isOperable = false;
        String verbInput = this.verb;
        String nounInput = this.noun;
        if (postType.equals("Fort Sill")) {
            isOperable = parserHelper.getCoreInstructionHelper(verbInput, nounInput, "Fort Sill");
        } else if (postType.equals("Fort Bliss")) {
            isOperable = parserHelper.getCoreInstructionHelper(verbInput, nounInput, "Fort Bliss");
        }
        if (isOperable) {
            this.verb = parserHelper.getVerbFromHelper();
            this.noun = parserHelper.getNounFromHelper();
            return isOperable;
        }
        this.verb = parserHelper.getVerbFromHelper();
        this.noun = parserHelper.getNounFromHelper();
        return false;
    }

    // user action for the boss stage
    private boolean testOperateUserAction(boolean isValidActionInput, String postType, String typeOfBossStage) {
        if (!isValidActionInput) {
            System.out.println("Formatting Error");
            return false;
        }
        boolean isOperable = false;
        String verbInput = this.verb;
        String nounInput = this.noun;
        if (postType.equals("Fort Sill")) {
            isOperable = parserHelper.getBossInstructionHelper(verbInput, nounInput, typeOfBossStage);
        } else if (postType.equals("Fort Bliss")) {
            isOperable = parserHelper.getBossInstructionHelper(verbInput, nounInput, typeOfBossStage);
        }
        if (isOperable) {
            this.verb = parserHelper.getVerbFromHelper();
            this.noun = parserHelper.getNounFromHelper();
            return isOperable;
        }
        this.verb = parserHelper.getVerbFromHelper();
        this.noun = parserHelper.getNounFromHelper();
        return false;
    }
}
