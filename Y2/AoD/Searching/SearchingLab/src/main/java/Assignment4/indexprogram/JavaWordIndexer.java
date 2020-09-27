package Assignment4.indexprogram;

import datastruct.st.LinkedHashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JavaWordIndexer implements WordIndexInterface {
    private Map<String, List<Integer>> wordPositionMap; // Table of words and corresponding positions in a text
    private List<String> lines;
    private int charCount;

    public JavaWordIndexer(Map<String, List<Integer>> map) {
        this.wordPositionMap = map;
        lines = new ArrayList<>();
    }

    public void loadText(Scanner scanner) {
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toUpperCase().toLowerCase());
        }
        setUp();
    }

    public void loadText(String text) {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines)
            this.lines.add(line.toUpperCase().toLowerCase());
        setUp();
    }

    public boolean hasWord(String word) {
        return wordPositionMap.containsKey(word.toLowerCase());
    }

    @Override
    public Object getMap() {
        return this.wordPositionMap;
    }

    public List<Integer> getIndices(String word) {
        return wordPositionMap.get(word.toLowerCase());
    }

    public WordIndices wordIndices(String word){
        return new WordIndices(wordPositionMap.get(word.toLowerCase()), word);
    }

    public void printText(){
        for(String line : lines)
            System.out.println(line);
    }

    private void setUp() {
        for (String line : lines) {
            String [] words = line.split("\\b");
            int len = 0;
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if(!word.matches("\\s*")){
                    int wordIndex = line.indexOf(word,len);
                    mapWord(word, wordIndex);
                }
                len += word.length();
            }
            charCount += line.length();
        }

    }

    private int mapWord(String word, int pos) {
        if (!wordPositionMap.containsKey(word))
            wordPositionMap.put(word, new ArrayList<>());

        wordPositionMap.get(word).add(pos + charCount);

        return word.length();
    }

    private static class WordIndexerTester{
        public static void main(String...args){
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
