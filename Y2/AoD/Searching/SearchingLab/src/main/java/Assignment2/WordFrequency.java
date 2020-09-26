package Assignment2;

public class WordFrequency {
    private final String word;
    private final int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getWord() {
        return word;
    }
}
