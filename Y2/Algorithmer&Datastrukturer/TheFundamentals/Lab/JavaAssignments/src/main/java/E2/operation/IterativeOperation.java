package E2.operation;

import E2.BasicStack;
import datastructure.Stack;

import static util.IOUtil.readChar;
import static util.IOUtil.EOF;
import static util.IOUtil.putChar;

/**
 * Implementation of <code> ReadAndReverseOperation </code> template.
 *
 * Used to read characters from standard input and then print them
 * in reversed order using iteration.
 */
public class IterativeOperation extends ReadAndReverseOperation {
    private Stack<Character> stack = new BasicStack<>();

    public IterativeOperation(){
        super("Iterative");
    }

    /**
     * Read one character at a time from standard input.
     *
     * Performs the operation by iteration.
     */
    @Override
    public void readInput() {
            char c = readChar();
            while(charIsValid(c)){
                stack.push(c);
                c = readChar();
            }
    }

    /**
     * Write one character at a time to standard output
     *
     * Performs the operation by iteration
     */
    @Override
    public void writeOutput() {
        for(Character c: stack)
            putChar(c);
    }

    private boolean charIsValid(char c){
        return c != '\n' && c!=EOF;
    }
}
