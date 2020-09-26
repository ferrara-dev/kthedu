package Assignment4;

import Assignment4.indexprogram.IndexApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * ====================== PROGRAMMING ASSIGNMENT 4 ======================
 * <Introduction>
 * @author Samuel Ferrara spof@kth.se
 *
 * This file serves as solution to programming assignment 4.
 *
 * "index"-program which allows the user to ask on which position a
 * given word can be found in a text.
 *
 * The program lists the position of all occurences of the word
 * that is asked for.
 * </Introduction>
 *
 * <Solution>
 *
 * </Solution>
 */
public class assignment4 {

    public static void main(String...args) throws FileNotFoundException {
        IndexApp indexApp = new IndexApp(new FileInputStream(args[0]));
        while (indexApp.runApplication());
    }
}
