package Assignment4;

import Assignment4.indexprogram.IndexApp;
import Assignment4.indexprogram.WordIndexer;
import Assignment4.indexprogram.WordIndices;
import datastruct.st.BinarySearchTree;
import datastruct.st.LinkedHashTable;
import datastruct.st.ST;
import util.Stopwatch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
 *  {@link WordIndexer} is the class responsible for indexing
 *  each of the words in the text.
 *
 *  The idexing is done by mapping distinct words to a list of
 *  indicies using either {@link BinarySearchTree} or {@link LinkedHashTable}
 *  which are both implementations of the {@link ST} interface.
 *
 *  The datastructures are built by the following algorithm :
 *  1. Load the text, filter it and save each line in a list.
 *  2
 *
 * </Solution>
 */
public class assignment4 {

    public static void main(String...args) throws FileNotFoundException {
        IndexApp indexApp = new IndexApp(new FileInputStream(args[0]));
        while (indexApp.runApplication());
    }
}
