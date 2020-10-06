package util;

import java.util.Scanner;

public class UserIn {
    private Scanner scanner;


    public UserIn() {
        this.scanner = new Scanner(System.in);
    }

    public String[] userInput() {
        System.out.print(" >>> ");
        String[] query = scanner.nextLine().split("\\s+");
        return query;
    }

    public String[] getInput(String delimiter) {
        return scanner.nextLine().toLowerCase().split(delimiter);
    }

    public String getLine() {
        return scanner.nextLine();
    }

}
