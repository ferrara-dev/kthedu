package E2.operation;

import E2.BasicStack;
import datastructure.Stack;

/**
 * Template method used to read some input and print it in reverse order.
 *
 */
public abstract class ReadAndReverseOperation {
    protected Stack<Character> stack = new BasicStack<>();
    private final String operationType;

    protected ReadAndReverseOperation(String operationType) {
        this.operationType = operationType;
    }

    public void doOperation(){
        System.out.println(operationType + " operation started");
        readInput();
        writeOutput();
    }

    protected abstract void readInput();
    protected abstract void writeOutput();
}
