package Assignment1;

import java.util.Scanner;

public class Input {
    private static Scanner userIn = new Scanner(System.in);

    public static String [] userInput() {
        System.out.print(" >>> ");
        String[] input = userIn.nextLine().split("\\s+");
        boolean inputValid = validateInput(input);
        if (inputValid) {
            return input;
        } else {
            return userInput();
        }
    }

    private static boolean validateInput(String[] input) {
        if (input.length < 3) {
            if (input.length == 0)
                System.err.println("Invalid input : input line was empty");
            else if(input.length == 1 && !input[0].toLowerCase().equals(Query.STOP.q))
                System.err.println("Invalid input : no vertex was specified in the query");
            else
                System.err.println("Invalid input : at least 2 vertexes must be specified in the query");

            return false;

        } else if (!validateCommand(input[0])) {
            System.err.println("Invalid input : " + input[0] + " is not a command");
            return false;
        }
        return true;
    }

    private static boolean validateCommand(String command) {
        return command.toLowerCase().equals("path") || command.toLowerCase().equals("exists") || command.toLowerCase().equals("stop");
    }


    public enum Query {
        PATH("path"),
        EXISTS("exists"),
        STOP("stop");
        String q;

        Query(String q) {
            this.q = q;
        }
    }
}
