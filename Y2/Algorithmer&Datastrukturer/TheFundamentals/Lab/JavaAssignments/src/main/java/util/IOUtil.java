package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Util class used to read from
 * standard input and write characters to standard output
 */
public class IOUtil {
    private static InputStream inputStream = System.in;
    private static PrintStream printStream = System.out;
    private static Scanner scanner = new Scanner(inputStream);
    public static char EOF = (char) -1;

    /**
     * Read and return the next character from standard input stream
     *
     * @return returns the next character, if the end of the stream
     *         is reached, -1 is returned
     *
     * @throws IOException
     */
    public static char readChar(){
        int c = -1;
        try {
            c = inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) c;
    }

    /**
     * Write a character to standard output.
     * @param c the character that is written to standard output.
     */
    public static void putChar(char c) {
        printStream.print(c);
    }

    /**
     *
     * @return the next token as an integer, throws number format exception if the next
     *         token can not be read.
     */
    public static int readInt(){
        scanner.useDelimiter(" ");
        if(scanner.hasNext()) {
            String s = scanner.next();
            boolean isNumeric = s.chars().allMatch(Character::isDigit);
            if (isNumeric) {
                return Integer.parseInt(s);
            }
            else{
                throw new NumberFormatException("Error parsing " + s);
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Simple unit test, read input from stdin one char at a time
     * and print it to standard output.
     */
    public static void main(String... args) throws IOException {
        char c = readChar();
        while (c != (char) -1) {
            putChar(c);
            c = readChar();
        }
    }

}
