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
        A1.CharStack stack = new A1.CharStack();
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

    protected boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    protected boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

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
            A1.Parentheses parentheses = new A1.Parentheses();
            Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
            for (int i = 0; i < N; i++) {
                parentheses.bracketMap.get('[');
            }
            return timer.elapsedTime();
        }


        private double methodMatchTest(int N) {
            A1.Parentheses parentheses = new A1.Parentheses();
            Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
            for (int i = 0; i < N; i++) {
                parentheses.findMatchingClosing('[');
            }
            return timer.elapsedTime();
        }

    }
}
