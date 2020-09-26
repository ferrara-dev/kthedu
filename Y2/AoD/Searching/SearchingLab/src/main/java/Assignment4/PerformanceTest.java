package Assignment4;

import Assignment2.BinarySearchTree;
import Assignment2.ST;
import Assignment4.hash.LinkedHashTable;
import Assignment4.indexprogram.WordIndexInterface;
import Assignment4.indexprogram.WordIndexer;
import Assignment4.indexprogram.JavaWordIndexer;
import util.Stopwatch;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class PerformanceTest {
    public static void main(String... args) {
        WordIndexInterface wordIndexer = null;
        if (args.length > 0) {
            String impl = args[0];
            if (impl.equals("bst")) {
                wordIndexer = new WordIndexer(new BinarySearchTree<String, List<Integer>>());
                System.out.println("<=== Running performance test with BST implementation ===>");
            } else if (impl.equals("hash")) {
                wordIndexer = new WordIndexer(new LinkedHashTable<>());
                System.out.println("<=== Running performance test with LinkedHashTable implementation ===>");
            } else if (impl.equals("javabst")) {
                wordIndexer = new JavaWordIndexer(new TreeMap<>());
                System.out.println("<=== Running performance test with Java BST implementation ===>");
            } else if (impl.equals("javahash")) {
                wordIndexer = new JavaWordIndexer(new HashMap<>());
                System.out.println("<=== Running performance test with Java hashmap implementation ===>");
            }

            System.out.println("Reading text.....");
            Stopwatch stopwatch2 = new Stopwatch();
            wordIndexer.loadText(new Scanner(System.in));
            double loadTime = stopwatch2.elapsedMilli();
            System.out.println("Took " + loadTime / 1000 + " seconds to load the file");
            List<Integer> indices = null;
            Stopwatch stopwatch = new Stopwatch();

            for (int i = 0; i < 1000000; i++) {
                indices = wordIndexer.getIndices("the");
            }

            double time = stopwatch.elapsedMilli();
            System.out.println();
            //    System.out.println("\"the\" is found at position(s) " + indices.toString() + " in the text");
            System.out.println("100 0000 accesses took " + time + " milli seconds");
        }
    }
}
