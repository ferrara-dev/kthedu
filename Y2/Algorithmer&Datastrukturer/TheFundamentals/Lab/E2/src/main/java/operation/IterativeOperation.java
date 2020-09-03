package operation;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import stack.BasicStack;
import stack.Stack;

public class IterativeOperation implements ReadAndReverseOperation {
    private Stack<Character> stack = new BasicStack<>();

    public IterativeOperation(){
        System.out.println("Performing operation by iteration !");
    }

    /**
     * Read one character at a time from standard input.
     *
     * Performs the operation by iteration.
     */
    @Override
    public void readStdin() {
            char c;
            while (!StdIn.isEmpty()) {
                c = StdIn.readChar();
                if(c =='\n')
                    break;
                stack.push(c);
            }
    }

    /**
     * Write one character at a time to standard output
     *
     * Performs the operation by iteration
     */
    @Override
    public void writeStdout() {
        for(Character c: stack)
            StdOut.print(c);
    }
}
