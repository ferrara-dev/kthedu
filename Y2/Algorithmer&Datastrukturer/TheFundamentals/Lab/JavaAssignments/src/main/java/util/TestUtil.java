package util;

import assignment3.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Scanner;

public class TestUtil{
    private static TestUtil instance;
    private Scanner scanner = new Scanner(System.in);
    private InputInterpreter inputInterpreter = new InputInterpreter();
    private HashMap<String, ConsoleTable> tables = new HashMap<>();

    private TestUtil(){

    }

    public static TestUtil getInstance() {
        if (instance == null) {
            synchronized (TestUtil .class) {
                if (instance == null) {
                    instance = new TestUtil();
                }
            }
        }
        return instance;
    }

    public static Queue<String> instructions(){
        return getInstance().getInstructions();
    }

    public Queue<String> getInstructions(){
       return inputInterpreter.readInput(scanner);
    }

    public static TestUtil commands(Command...commands){
        getInstance().setCommands(commands);
        return instance;
    }

    public static ConsoleTable createOutputTable(ArrayList<ArrayList<String>> content, String...headers){
        ConsoleTable ct = new ConsoleTable(Arrays.asList(headers), content);
        return ct;
    }

    public void setCommands(Command...commands){
        inputInterpreter.setCommands(commands);
    }


}
