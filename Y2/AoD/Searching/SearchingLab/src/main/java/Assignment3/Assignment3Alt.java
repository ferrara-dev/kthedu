package Assignment3;

import Assignment1.TextFilter;

import datastruct.set.SET;
import datastruct.st.BinarySearchTree;
import datastruct.List;
import util.StdIn;

import java.util.Scanner;

/***
 * ============= ALTERNATIVE SOLUTION TO ASSIGNMENT 3 =============
 *
 * <Introduction>
 *    @author Samuel Ferrara spof@kth.se
 *
 *    This file serves as an alternative solution to assignment 3.
 *
 *    Program that shows how many of the words in a text that have a
 *    unique hash code.
 *
 * </Introduction>
 *
 * <Solution>
 *  1. Map all distinct words to a {@link SET} of strings.
 *  2. The hashcode of each distinct word is mapped
 *     in a {@link BinarySearchTree}, where the hashcode
 *     serves as key and a list of words that has that
 *     hashcode as value.
 *  3. How many of the distinct words that have a unique
 *     hashcode can now be determined.
 * </Solution>
 */
public class Assignment3Alt {
    private static BinarySearchTree<Integer, List<String>> hashCodeToWordMap = new BinarySearchTree<Integer, List<String>>();
    private static SET<String> distinctWords = new SET<>();
    private static TextFilter textFilter = new TextFilter();


    public static void main(String... args) {
        visualizeHashDistribution();
    }

    public static void visualizeHashDistribution() {
        String[] keys;
        int distinct = 0;
        int nrOfDistinctHashCodes = 0;

        while (!StdIn.isEmpty()) {
            keys = textFilter.filterText(StdIn.readLine()).split("\\s");
            for (String key : keys) {
                if (!distinctWords.contains(key)) {
                    distinctWords.add(key);
                    distinct++;
                }
            }
        }

        for (String word : distinctWords) {
            int hashCode = word.hashCode();
            if (!hashCodeToWordMap.contains(hashCode)) {
                hashCodeToWordMap.put(hashCode, new List<>());
                nrOfDistinctHashCodes++;
            }
            hashCodeToWordMap.get(hashCode).add(word);
        }

        int nrOfDistinctWords = distinctWords.size();
        int nrOfHashCodeCollisions = 0;

        for (Integer hashCode : hashCodeToWordMap.keys()) {
            List<String> words = hashCodeToWordMap.get(hashCode);
            if (words.size() > 1) {
                System.out.println(hashCodeToWordMap.get(hashCode).toString());
                nrOfHashCodeCollisions++;
            }
        }

        System.out.println(nrOfDistinctWords + " distinct strings ");
        System.out.println(nrOfDistinctHashCodes + " distinct hash codes");
        System.out.println(nrOfHashCodeCollisions + " hashcode collisions");
        System.out.println(((double) nrOfDistinctHashCodes/nrOfDistinctWords) + "% of the words have a distinct hashcode");
        System.out.println();
        System.out.println(distinct);
        System.out.println(distinctWords.size());
    }


}
