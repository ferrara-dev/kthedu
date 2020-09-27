package Assignment2;

import datastruct.st.BinarySearchSymbolTable;
import datastruct.st.BinarySearchTree;
import datastruct.st.ST;
import datastruct.st.SequentialSearchST;
import util.Stopwatch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * TODO:
 * Theory comparison of the two implementations.
 *
 *
 * @author Samuel Ferrara spof@kth.se
 *
 * This file serves as solution to programming assignment 2
 * and Theory question 2.
 *
 *  ============== PROGRAMMING ASSIGNMENT 2 ===============
 *
 * Program to compare running time for counting word frequency
 * in a text using :
 * 1. OrderedArraySymbolTable
 * 2. BinarySearchTree (BST)
 *
 * Method:
 * The total accumulated time that it takes to perform the operation
 * in measured in the static frequencyCount method located in this
 * class.
 *
 * The accumulated running time for executing all symbol table
 * put() and get() method calls are measured and added up
 * by the method.
 *
 * This is repeated 10 000 times to get an average running time.
 *
 * Theory :
 *
 *
 */
public class Assignment2 {
    public static void main(String... args) throws FileNotFoundException {
        FrequencyCounter frequencyCounter;
        if (args.length > 2) {
            Scanner in = new Scanner(new FileReader(args[0]));
            int N = Integer.parseInt(args[1]);
            String impl = args[2];
            String[] input = readInput(in, N);
            double time = 0.0;
            if (impl.toLowerCase().equals("bin")) {
                System.out.println("Testing BinarySearch implementation");
                for(int i = 0; i < 10000; i++) {
                    String inp[] = input.clone();
                    time += frequencyCount(new BinarySearchSymbolTable(N),inp, false);
                }
                frequencyCount(new BinarySearchSymbolTable(N),input, true);
            } else if (impl.toLowerCase().equals("seq")) {
                System.out.println("Testing Sequential implementation");
                for(int i = 0; i < 10000; i++) {
                    String inp[] = input.clone();
                    time += frequencyCount(new SequentialSearchST(),inp, false);
                }

            } else if (impl.toLowerCase().equals("bst")) {
                System.out.println("Testing BST implementation");
                for(int i = 0; i < 10000; i++) {
                    String inp[] = input.clone();
                    time += frequencyCount(new BinarySearchTree(),inp, false);
                }
                frequencyCount(new BinarySearchTree(),input, true);
            } else {
                throw new IllegalArgumentException("Program argument must contain path to input file, " +
                        "N amount of words to be read " +
                        "and symbol table implementation\n" +
                        "for BinarySearchSymbolTable enter bin as last program argument\n" +
                        "for SequentialSearchST enter seq as last program argument");
            }
            System.out.println("N = " + N);
            System.out.println("Word frequency counted in " + time/10000+ " ms");
        }
    }


    public static double frequencyCount(ST<String, Integer> st, String[] words, boolean print) {
        double time = 0.0;
        Stopwatch stopwatch;
        int distinct = 0, wordCount = 0;
        int counter = 0;
        for (String key : words) {
            wordCount++;
            if (st.contains(key)) {
                stopwatch = new Stopwatch();
                int count = st.get(key);
                time += stopwatch.elapsedMilli();

                stopwatch = new Stopwatch();
                st.put(key, count + 1);
                time += stopwatch.elapsedMilli();
            } else {
                stopwatch = new Stopwatch();
                st.put(key, 1);
                distinct++;
                time+= stopwatch.elapsedMilli();
            }
            counter++;
        }

        stopwatch = new Stopwatch();
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        time += stopwatch.elapsedMilli();

        for (String word : st.keys()) {
            stopwatch = new Stopwatch();
            int wordFreq = st.get(word);
            time += stopwatch.elapsedMilli();

            stopwatch = new Stopwatch();
            int maxWordFreq = st.get(max);
            time += stopwatch.elapsedMilli();

            if (wordFreq > maxWordFreq)
                max = word;
        }

        stopwatch = new Stopwatch();
        int maxFrequency = st.get(max);
        time += stopwatch.elapsedMilli();

        if (print) {
            System.out.println(max + " " + maxFrequency);
            System.out.println("distinct = " + distinct);
            System.out.println("words    = " + wordCount);
        }

        return time;
    }

    private static String[] readInput(Scanner in, int N) {
        int counter = 0;
        String input[] = new String[N];

        while (in.hasNext() && counter < N) {
            input[counter] = in.next();
            counter += 1;
        }

        return input;
    }
}
