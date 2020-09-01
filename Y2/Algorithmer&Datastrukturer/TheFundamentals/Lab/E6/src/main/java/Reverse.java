import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Reverse {
    public static void main(String[] Args) {

        reverse();
    }



    private static void reverse () {

        Stack<Character> s = new Stack<Character>();
        while (!StdIn.isEmpty()) {
            char c = StdIn.readChar();
            s.push(c);
        }
        while(!s.isEmpty())
            StdOut.print(s.pop());
    }

}
