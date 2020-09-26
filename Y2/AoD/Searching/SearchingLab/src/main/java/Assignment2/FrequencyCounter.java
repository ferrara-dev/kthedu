package Assignment2;

import java.util.Scanner;

public class FrequencyCounter {
    private final ST<String, Integer> symbolMap;
    private final String[] words;
    private String mostFrequentWord;
    private int wordCount;
    private int distinct;

    public FrequencyCounter(ST symbolMap, String[] words) {
        this.symbolMap = symbolMap;
        this.words = words;
    }

    public void countWordFrequency() {
        wordCount = 0;
        distinct = 0;
        for (String word : words) {
            wordCount++;
            if (symbolMap.contains(word)) {
                int count = symbolMap.get(word);
                symbolMap.put(word, count + 1);
            } else {
                symbolMap.put(word, 1);
                distinct++;
            }
        }

        String max = "";
        symbolMap.put("", 0);

        for (String word : symbolMap.keys()) {
            if (symbolMap.get(word) > symbolMap.get(max))
                max = word;
        }
        mostFrequentWord = max;
        System.out.println(max + " " + symbolMap.get(max));
        System.out.println("distinct = " + distinct);
        System.out.println("words    = " + wordCount);
    }

    private void reset() {
        wordCount = 0;
    }

    public WordFrequency wordFrequency(String word) {
        Integer frequency = symbolMap.get(word);
        if (frequency == null) {
            return new WordFrequency(word, 0);
        } else {
            return new WordFrequency(word, frequency);
        }
    }

    public int getDistinct() {
        return distinct;
    }

    public int getWordCount() {
        return wordCount;
    }

    public String mostFrequentWord() {
        return mostFrequentWord;
    }

    private static class FrequencyCountTester {
        public static void main(String... args) {
            String[] testInput = {"abc", "abc", "abc", "aaa"};
            BinarySearchTree<String, Integer> bst = new BinarySearchTree<String, Integer>();
            FrequencyCounter frequencyCounter = new FrequencyCounter(bst, testInput);

        }
    }
}
