package Assignment1;

import java.util.Map;

/**
 * ============================== PROGRAMING ASSIGNMENT 1 ============================
 *
 * @author Samuel Ferrara spof@kth.se
 * <Intro>
 * This file serves as solution to programming assignment 1
 *
 * Program to filter text by replacing all non-alphabetic char with white-space characters.
 * </Intro>
 *
 * <Solution>
 * The assignment is solved using the {@link TextFilter class}.
 *
 * A String object is filtered by calling filterText(String s) method, which runs an
 * algorithm consiting of the following steps:
 * 1. Go through the text character by character.
 * 2. If character is alpha, new-line or white-space, add it to the output string
 * Else add a white-space character to the output string
 *
 * The solution iterates through the whole text and has a time complexity of O(N) order.
 * Where N is the number of character in the text.
 * </Solution>
 */
public class Assignment1 {
    private static TextFilter textFilter = new TextFilter();
}
