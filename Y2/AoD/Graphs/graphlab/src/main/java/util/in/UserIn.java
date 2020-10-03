package util.in;

import java.util.ArrayList;
import java.util.Scanner;

public class UserIn {
    private Scanner scanner;
    private ArrayList<String> queries;

    public UserIn(String... queries) {
        this.queries = new ArrayList<>();
        for (String query : queries)
            this.queries.add(query.toLowerCase());

        this.scanner = new Scanner(System.in);
    }

    public String[] userInput() {
        System.out.print(" >>> ");
        String[] query = scanner.nextLine().split("\\s+");
        boolean queryValid = validateQuery(query);
        if (queryValid) {
            return query;
        } else {
            return userInput();
        }
    }

    public String[] getInput(String delimiter) {
        return scanner.nextLine().toLowerCase().split(delimiter);
    }

    public String getLine() {
        return scanner.nextLine();
    }

    private boolean validateQuery(String query []) {
        if(!this.queries.contains(query[0]) || query.length < 3){
            System.err.println("Invalid query, please try again");
            return false;
        }
        else
            return true;
    }
}
