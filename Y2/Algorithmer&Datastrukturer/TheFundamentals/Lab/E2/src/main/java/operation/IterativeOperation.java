package operation;

import io.InputReader;
import io.OutputWriter;
import operation.Operation;
import stack.BasicStack;
import stack.Stack;

import java.io.IOException;

public class IterativeOperation implements Operation {
    private Stack<Character> stack = new BasicStack<>();

    /**
     * Read one character at a time from standard input.
     *
     * Performs the operation by iteration.
     * @param inputReader
     */
    @Override
    public void readStdin(InputReader inputReader) {
        try {
            char c = inputReader.readChar();
            while (charIsValid(c)) {
                stack.push(c);
                c = inputReader.readChar();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write one character at a time to standard output
     *
     * Performs the operation by iteration
     * @param outputWriter
     */
    @Override
    public void writeStdout(OutputWriter outputWriter) {
        for (Character c : stack) {
            try {
                outputWriter.putChar(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean charIsValid(char c) {
        if (c == (char) -1 || c == '\n')
            return false;
        return true;
    }
}
