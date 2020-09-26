package Assignment4.indexprogram;

import java.util.List;

public class WordIndices {
    private final List<Integer> indexes;
    private final String word;

    public WordIndices(List<Integer> indexes, String word) {
        this.indexes = indexes;
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        sb.append(" is found at ->");
        sb.append(indexes.toString());
        return sb.toString();
    }
}
