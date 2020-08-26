package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Samuel Ferrara 19940412-1395 spof@kth.se
 *
 * Class used to return data from inputstreams.
 */
public class InputReader {
    private InputStream inputStream;

    public InputReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    /**
     * Read and return the next character from standard input stream
     *
     * @return returns the next character, if the end of the stream
     *         is reached, -1 is returned
     *
     * @throws IOException
     */
    public char readChar() throws IOException {
        int c = inputStream.read();
        return (char) c;
    }

    /**
     * Simple unit test, read input from stdin one char at a time
     * and print it to standard output.
     */
    public static void main(String... args) throws IOException {
        InputReader inputReader = new InputReader(System.in);
        char c = inputReader.readChar();
        while (c != (char) -1) {
            System.out.println(c);
            c = inputReader.readChar();
        }
    }
}
