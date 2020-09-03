package E2.operation;

import util.IOUtil;

/**
 * Implementation of <code> ReadAndReverseOperation </code> template.
 *
 * Used to read characters from standard input and then print them
 * in reversed order using recursion.
 */
public class RecursiveOperation extends ReadAndReverseOperation {
    public RecursiveOperation() {
        super("Recursive");
    }

    /**
     * Read one character at a time from standard input
     * <p>
     * Performs the operation by recursion:
     * <p>
     * Base case : Stdin dosent have any more tokens to read OR '\n' (new line character)
     *             is read.
     */
    @Override
    public void readInput() {
        char c = IOUtil.readChar();
        if(charIsValid(c)){
            stack.push(c);
            readInput();
        }
    }

    /**
     * Write one character at a time to standard output
     * <p>
     * Performs the operation by recursion:
     * <p>
     * Base case : The stack that holds the characters is empty
     */
    @Override
    public void writeOutput() {
        if (!stack.isEmpty()){
            IOUtil.putChar(stack.pop());
            writeOutput();
        }
    }

    private boolean charIsValid(char c){
        return c != '\n' && c!=(char) -1;
    }
}
