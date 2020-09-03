package operation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import stack.BasicStack;
import stack.Stack;

public class RecursiveOperation implements ReadAndReverseOperation {
    private Stack<Character> stack = new BasicStack<>();

    public RecursiveOperation() {
        System.out.println("Recursive operation started !");
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
    public void readStdin() {
        if (!StdIn.isEmpty()){
            char c = StdIn.readChar();
            if (c != '\n') {
                stack.push(c);
                readStdin();
            }
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
    public void writeStdout() {
        if (stack.isEmpty())
            return;
        else {
            StdOut.print(stack.pop());
            writeStdout();
        }
    }

}
