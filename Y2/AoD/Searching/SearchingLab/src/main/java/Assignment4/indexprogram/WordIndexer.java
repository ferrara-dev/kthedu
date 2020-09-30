package Assignment4.indexprogram;

import Assignment1.TextFilter;
import datastruct.st.ST;
import datastruct.st.LinkedHashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordIndexer implements WordIndexInterface {
    private ST<String, List<Integer>> wordPositionMap; // Table of words and corresponding positions in a text
    private List<String> lines;
    private int charCount;
    private TextFilter textFilter;

    public WordIndexer(ST<String, List<Integer>> map) {
        this.wordPositionMap = map;
        lines = new ArrayList<>();
        this.textFilter = new TextFilter();
    }

    public void loadText(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toUpperCase().toLowerCase();
            lines.add(textFilter.filterText(line + "\n"));
        }
        setUp();
    }

    public void loadText(String text) {
        text = textFilter.filterText(text);
        String[] lines = text.split("\\r?\\n");
        for (String line : lines)
            this.lines.add(line.toUpperCase().toLowerCase());
        setUp();

    }

    public boolean hasWord(String word) {
        return wordPositionMap.contains(word.toLowerCase());
    }

    @Override
    public Object getMap() {
        return this.wordPositionMap;
    }

    public List<Integer> getIndices(String word) {
        return wordPositionMap.get(word.toLowerCase());
    }

    public WordIndices wordIndices(String word) {
        return new WordIndices(wordPositionMap.get(word.toLowerCase()), word);
    }

    public void printText() {
        for (String line : lines)
            System.out.println(line);
    }

    private void setUp() {
        String word = "";
        String [] words;
        for (String line : lines) {
            words = line.split("\\b");
            int len = 0;
            for (int i = 0; i < words.length; i++) {
                word = words[i];
                if (!isWhitespace(word)) {
                    int wordIndex = line.indexOf(word, len);
                    mapWord(word, wordIndex);
                }
                len += word.length();
            }
            charCount += line.length();
        }
    }

    private boolean isWhitespace(String s){
        return s.matches("\\s*");
    }

    private int mapWord(String word, int pos) {
        if (!wordPositionMap.contains(word))
            wordPositionMap.put(word, new ArrayList<>());

        wordPositionMap.get(word).add(pos + charCount);

        return word.length();
    }

    private static class WordIndexerTester {
        public static void main(String... args) {
            Scanner scanner = new Scanner(System.in);
            WordIndexer wordIndexer = new WordIndexer(new LinkedHashTable<>());
            wordIndexer.loadText(scanner);
            wordIndexer.printText();

            WordIndices indices = wordIndexer.wordIndices("blabla");
            System.out.println(indices.toString());

            WordIndices indices2 = wordIndexer.wordIndices("abc");
            System.out.println(indices2.toString());

            WordIndices indices3 = wordIndexer.wordIndices("aaa");
            System.out.println(indices3.toString());

            WordIndices indices4 = wordIndexer.wordIndices("nonting");
            System.out.println(indices4.toString());

        }
    }
}

