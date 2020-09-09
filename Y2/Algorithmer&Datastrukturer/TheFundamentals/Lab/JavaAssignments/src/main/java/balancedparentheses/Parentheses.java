package balancedparentheses;

import util.ConsoleTable;
import util.TestUtil;
import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parentheses {
    protected String expression;
    protected final Map<Character, Character> bracketMap;

    public Parentheses() {
        bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put(']', '[');
        bracketMap.put('}', '{');
    }

    /**
     * Set the expression that is to be evaluated
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Check if an expression has balanced parentheses
     *
     * @return
     */
    public boolean checkBalanced() {
        if (isClosing(expression.charAt(0)))
            return false;
        CharStack stack = new CharStack();
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (isOpening(current)) {
                stack.push(current);
                continue;
            } else if (isClosing(current)) {
                Character openingAtTop = stack.peek();
                if (openingAtTop != Character.MIN_VALUE && openingAtTop == findMatchingOpening(current)) {
                    stack.pop();
                    continue;
                } else
                    return false;
            }

        }

        return stack.isEmpty();
    }

    /**
     * Check balanced with an recursive algorithm
     * The algorithm is not used, as it gives stack overflow for larger n.
     *
     * Not sure what the problem is, but i believe that the call stack overflows
     * when to many recursive calls are made.
     * @param n
     * @return
     */
    int checkBalanced(int n) {
        if (n > expression.length() - 1) {
            return n;
        }
        else if(isClosing(expression.charAt(n)))
            return n;

        else if (isOpening(expression.charAt(n))) {
            char c = findMatchingClosing(expression.charAt(n));
            int i = checkBalanced(n + 1);
            if (i < expression.length() && expression.charAt(i) == c) {
                return checkBalanced(i + 1);
            } else {
                return n + 1;
            }
        }

        return checkBalanced(n + 1);
    }

    /**
     * Check if a character is a closing bracket
     * @param c the character that is checked
     * @return true if <code> c </code> is a closing bracket.
     */
    protected boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    /**
     * Check if a character is an opening bracket
     * @param c the character that is checked
     * @return true if <code> c </code> is a opening bracket.
     */
    protected boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    /**
     * Find a matching opening for a closing bracket
     * @param c the character that is matched
     * @return the corresponding opening bracket, if <code> c </code>
     *         is not a closing bracket, '/u0000' is returned.
     */
    private char findMatchingOpening(char c) {
        if (c == ')')
            return '(';
        else if (c == ']')
            return '[';
        else if (c == '}')
            return '{';
        else
            return Character.MIN_VALUE;
    }

    /**
     * Find a closing opening for a opening bracket
     * @param c the character that is matched
     * @return the corresponding closing bracket, if <code> c </code>
     *         is not a opening bracket, '/u0000' is returned.
     */
    protected char findMatchingClosing(char c) {
        if (c == '(')
            return ')';
        else if (c == '[')
            return ']';
        else if (c == '{')
            return '}';
        else
            return Character.MIN_VALUE;
    }

    /**
     * Class used to run tests of the <code> Parentheses </code> member
     * functions.
     */
    private static class Test {

        public static void main(String... args) {
            Test test = new Test();
            ArrayList<ArrayList<String>> content = new ArrayList<>();
            for (int N = 250; N < 1000000000; N += N) {
                double timeHash = test.hashMatchTest(N);
                double timeMethod = test.methodMatchTest(N);
                ArrayList<String> row = new ArrayList<String>();
                row.add(String.valueOf(N));
                row.add(String.valueOf(timeMethod));
                row.add(String.valueOf(timeHash));
                if (timeHash > timeMethod)
                    row.add("Method was " + String.valueOf(timeHash - timeMethod) + " ms faster");
                else if (timeHash < timeMethod)
                    row.add("Hash was " + String.valueOf(timeMethod - timeHash) + " ms faster");
                else
                    row.add("tie");
                content.add(row);
            }
            ConsoleTable consoleTable = TestUtil.createOutputTable(content,
                    "N",
                    "Method match time (ms) ",
                    "Hashmap match time (ms) ",
                    "Fastest");
            consoleTable.printTable();
        }

        private double hashMatchTest(int N) {
            Parentheses parentheses = new Parentheses();
            Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
            for (int i = 0; i < N; i++) {
                parentheses.bracketMap.get('[');
            }
            return timer.elapsedTime();
        }

        private double methodMatchTest(int N) {
            Parentheses parentheses = new Parentheses();
            Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
            for (int i = 0; i < N; i++) {
                parentheses.findMatchingClosing('[');
            }
            return timer.elapsedTime();
        }

    }
}
