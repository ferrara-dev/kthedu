package assignment2;

/**
 * Solution for assignment 2 is run from this class.
 */
public class Assignment2Main {
    /**
     * Main method that the application is run from.
     * If the first element in the command line argument equals
     * "rec", the operation will be performed recursively.
     *
     * Else it will perform the operation by iteration.
     *
     * @param args Command line argument
     */
    public static void main(String... args) {
        if (args.length > 0 && args[0].equals("rec"))
            new Assignment2Main().runRecursive();
        else
            new Assignment2Main().runIterative();
    }

    private void runRecursive(){
        readAndReverseRecursive(new BasicStack<>());
    }

    private void runIterative(){
        readAndReverseIterative(new BasicStack<>());
    }

    /**
     *  * Used to read characters from standard input and then print them
     *  * in reversed order using recursion.
     * @param stack
     */
    private void readAndReverseRecursive(BasicStack <Character> stack) {
        char c = IOUtil.readChar();
        if(charIsValid(c)){
            stack.push(c);
            readAndReverseRecursive(stack);
            IOUtil.putChar(stack.pop());
        }
    }

    /**
     * Used to read characters from standard input and then print them
     * in reversed order using iteration.
     */
    private void readAndReverseIterative(BasicStack <Character> stack) {
        char c = IOUtil.readChar();
        while(charIsValid(c)){
            stack.push(c);
            c = IOUtil.readChar();
        }

        for(Character ch: stack)
            IOUtil.putChar(ch);

    }

    /**
     * helper method used to check if a character is valid
     * @param c
     * @return true if the is not Character.MIN_VALUE or new line charcter
     */
    private boolean charIsValid(char c){
        return c != '\n' && c!=(char) -1;
    }
}
