package Assignment3;

import Assignment1.TextFilter;

import datastruct.set.SET;
import datastruct.st.BinarySearchTree;
import datastruct.List;
import util.StdIn;

import java.util.Scanner;

/***
 * TODO:
 * Write a program that shows how evenly the built-in hashcode() function for strings in
 * Java distributes the hashcodes for the words found in the text. (Hint it may be hard to use the hashcodes directly...)
 *
 *  ========================= FRÅN LÄRAREN  ===========================
 * Tanken är att du ska använda hashcode, problemet är att hashcode kan returenera väligt stora värden vilket
 * gör att den inte lämpar sig som indexerings funktion.
 * Jag recommenderar dig att läsa i boken om hashfunktioner.
 * Syftet med uppgiften är att illustrerar distributionen av värdena från hashfunktionen,
 * ifall man modifierar den så bör det tas med i din analys av programmet.
 */
public class Assignment3 {
    private static BinarySearchTree<Integer, List<String>> hashCodeToWordMap = new BinarySearchTree<Integer, List<String>>();
    private static BinarySearchTree<Integer, Integer> hashCodeDistributionMap = new BinarySearchTree<>();
    private static SET<String> distinctWords = new SET<>();
    private static TextFilter textFilter = new TextFilter();


    public static void main(String... args) {
        visualizeHashDistribution();
    }

    public static void visualizeHashDistribution() {
        Scanner scanner = new Scanner(System.in);
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
