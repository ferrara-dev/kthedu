package operation;

import io.InputReader;
import io.OutputWriter;
import operation.Operation;
import stack.BasicStack;
import stack.Stack;

import java.io.IOException;

public class RecursiveOperation implements Operation {
    private Stack<Character> stack = new BasicStack<>();

    public RecursiveOperation(){
        System.out.println("Recursive operation started !");
    }

    /**
     * Read one character at a time from standard input
     *
     * Performs the operation by recursion:
     *
     *  Base case : The char that is read from the stdin
     *              equals -1 OR '\n' (new line character)
     *
     * @param inputReader
     */
    @Override
    public void readStdin(InputReader inputReader) throws IOException {
        char c = inputReader.readChar();
        if(charIsValid(c)){
            stack.push(c);
            readStdin(inputReader);
        }
        else
            return;
    }
    /**
     * Write one character at a time to standard output
     *
     * Performs the operation by recursion:
     *
     *  Base case : The stack that holds the character is empty
     *
     * @param outputWriter
     */
    @Override
    public void writeStdout(OutputWriter outputWriter) throws IOException {
        if(stack.isEmpty())
            return;
        else{
            char c = stack.pop();
            outputWriter.putChar(c);
            writeStdout(outputWriter);
        }
    }

    private boolean charIsValid(char c) {
        if (c == (char) -1 || c == '\n')
            return false;
        return true;
    }
}
