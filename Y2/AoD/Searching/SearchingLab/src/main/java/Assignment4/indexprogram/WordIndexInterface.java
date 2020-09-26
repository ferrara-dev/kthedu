package Assignment4.indexprogram;

import Assignment4.indexprogram.WordIndices;

import java.util.List;
import java.util.Scanner;

public interface WordIndexInterface {

    void printText();
    void loadText(Scanner scanner);
    void loadText(String text);
    WordIndices wordIndices(String word);
    List<Integer> getIndices(String word);
    boolean hasWord(String word);
    Object getMap();
}
