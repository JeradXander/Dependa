package com.dependapot.textparser;


import java.util.ArrayList;

// this
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
    // @return ArrayList<String>
    public ParseResponse receiveAction(String userActionInput, String post) {
        // trim the user action input
        boolean isValidActionInput = trimUserInput(userActionInput);
        boolean isOperableCommend = operateUserAction(isValidActionInput, post);
        if (isOperableCommend) {
            // return verbAndNoun;
            return new ParseResponse(getNoun(), getVerb());
        }
        // placeholder
        return new ParseResponse("", "");
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
        } else {
            // some message
            System.out.println("Invalid input:\nDesired input format: verb + noun");
            return false;
        }
        return true;
    }

    // operate action
    private boolean operateUserAction(boolean isValidActionInput, String postType) {
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
                isOperable = moveAction(nounInput, postType);
                return isOperable;
            // utilizing
//            case "use":
//            case "apply":
//            case "utilize":
//                isOperable = utilizeAction(nounInput);
//                return isOperable;
            // pick up
//            case "get":
//            case "obtain":
//            case "gain":
//            case "pick":
//            case "pickup":
//                isOperable = getAction(nounInput);
//                return isOperable;
            default:
                System.out.println("Sorry, the verb: " + verbInput + " is not supported");
                return isOperable;
        }
    }

    // method for "go || move || drive || walk || run"
    private boolean moveAction(String nounInput, String postType) {
        System.out.println("Move action");
        if (postType.equals("Fort Sill")) {
            // switch case for fort sill
            switch (nounInput) {
                case "dfac":
                case "barrack":
                case "church":
                case "px":
                case "commissary":
                case "gym":
                    return true;
                default:
                    return false;
            }
        } else if (postType.equals("Fort Bliss")) {
            // switch case for fort bliss
            switch (nounInput) {
                case "starbucks":
                case "freedom crossing":
                case "house":
                case "restaurant":
                case "theater":
                    return true;
                default:
                    return false;
            }
        } else {
            // place holder for now
            return false;
        }
    }

    // method for "utilize item"
//    private boolean utilizeAction(String nounInput) {
//        System.out.println("Utilize Action");
//        // placeholder
//        return false;
//    }

    // method for "get || obtain || gain || pick || pickup"
//    private boolean getAction(String nounInput) {
//        System.out.println("get");
//        // placeholder
//        return false;
//    }

    // test method
    // this will be removed
    public static void main(String[] args) {
        TextParser text = new TextParser();
        // expected verb = MOVE
        // expected noun = DFAC
        // post.getName() <- damian
        ParseResponse response = text.receiveAction("Move to DFAC", "Fort Sill");
        // expected verb = ""
        // expected noun = ""
        System.out.println(response);
        String noun = response.getNoun();
        String verb = response.getVerb();
        System.out.println(noun);
        System.out.println(verb);

        response = text.receiveAction("go dfac", "Fort Sill");
        System.out.println(response);
        // expected verb = "go"
        //
        /*System.out.println(text.receiveAction("go DFAC"));

        System.out.println(text.receiveAction("run to the Barrack"));*/
    }

}
