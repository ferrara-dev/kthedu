package Assignment4.indexprogram;

import Assignment4.hash.LinkedHashTable;
import util.Stopwatch;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * "index"-program which allows the user to ask for
 * the position of a word in a text.
 *
 * The text is read from the {@link FileInputStream} that is given
 * as constructor argument.
 */
public class IndexApp {
    private Scanner scanner;
    private WordIndexer wordIndexer;

    public IndexApp(FileInputStream fileInputStream) {
        scanner = new Scanner(new BufferedInputStream(fileInputStream));
        wordIndexer = new WordIndexer(new LinkedHashTable<>());
        init();
        scanner = new Scanner(System.in);
    }

    /**
     * This method is called from main in a loop,
     * it will return true until the user enters "exit".
     *
     * @return true until the user enters "exit".
     */
    public boolean runApplication() {
        System.out.println("Enter a word to get its position...");
        String userInput =  scanner.nextLine();
        if (userInput.toLowerCase().equals("exit"))
            return false;
        else {
            WordIndices wordIndices = wordIndexer.wordIndices(userInput);
            showResult(wordIndices);
            return true;
        }
    }

    private void showResult(WordIndices wordIndices) {
        if (wordIndices.getIndexes() == null) {
            System.out.println(
                    "There was no word in the text that matched the word "
                            + "\"" + wordIndices.getWord()
                            + "\""
            );
        } else {
            System.out.println("The word " + "\"" + wordIndices.getWord() + "\"" +
                    " was found on the following position(s) " +
                    wordIndices.getIndexes().toString());
        }
        System.out.print("Press any key to continue . . . ");
        scanner.nextLine();
        for (int i = 0; i < 5; i++)
            System.out.println("");
    }

    private void init() {
        System.out.println("Index application started !");
        System.out.println("Loading the text...");
        Stopwatch stopwatch = new Stopwatch();
        wordIndexer.loadText(scanner);
        double time = stopwatch.elapsedMilli();
        System.out.println("Application started in " + (time / 1000.0) + " seconds");
    }


}
