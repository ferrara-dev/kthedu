package balancedparentheses;

import util.ConsoleTable;
import util.TestUtil;
import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchFactory;

import java.util.ArrayList;

public class ParenthesesCheckMain {
    private static final long maxIterations = 100000000L;

    public static void main(String... args) {
        Parentheses parentheses = new Parentheses();
        test(parentheses);
        timedTest();
    }

    private static void timedTest(){
        ArrayList<ArrayList<String>> content = new ArrayList<>();
        System.out.println("=============== TIMED TEST ===============");
        for (int N = 250; N < maxIterations ; N += N) { // Print time for problem size N.
            double time = timeTrial(N);
            ArrayList<String> row = new ArrayList<String>();
            row.add(String.valueOf(2*N));
            row.add(String.valueOf(time));
            content.add(row);
        }

        ConsoleTable consoleTable = TestUtil.createOutputTable(content,
                "N (amount of characters)",
                " Operation time (ms)"
        );
        consoleTable.printTable();

    }

    private static double timeTrial(int N) {
        Parentheses parentheses;
        parentheses = new Parentheses();
        String expression = generateExpression(N);
        parentheses.setExpression(expression);
        Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
        parentheses.checkBalanced();
        return timer.elapsedTime();
    }

    /**
     * Create an expression with 2*n characters
     * @return
     */
    private static String generateExpression(int n) {
        StringBuilder bigExpression = new StringBuilder();
        for (int i = 0; i < n; i++) {
            bigExpression.append("()");
        }
        return bigExpression.toString();
    }

    /**
     * Test an expression with 100000 characters
     */
    private static long testBigExpression(Parentheses parentheses, int n) {
        parentheses.setExpression(generateExpression(n));
        long start = System.currentTimeMillis();
        boolean isBalanced = parentheses.checkBalanced();
        long stop = System.currentTimeMillis();
        if (isBalanced)
            System.out.println("Expression is balanced !");
        else
            System.out.println("Expression is NOT balanced");
        long elapsedTime = stop - start;
        return elapsedTime;
    }

    private static long test(Parentheses parentheses) {
        String[] testStrings = {
                "({[]})","()()()","a})","([)]{}",
                "2+2x(4-2)x(3x4)", "abc", "(([()]){})", "(([()]){)}",
                "( ()(", "()", "(())", "[]", "[", "[)",
                "[ {} ] () () ()"
        };
        long start = System.nanoTime();
        for (int i = 0; i < testStrings.length; i++) {
            parentheses.setExpression(testStrings[i]);
            boolean isBalanced = parentheses.checkBalanced();
            if (isBalanced)
                System.out.println(testStrings[i] + " is balanced !");
            else
                System.out.println(testStrings[i] + " is NOT balanced");
        }
        return System.nanoTime() - start;
    }
}
