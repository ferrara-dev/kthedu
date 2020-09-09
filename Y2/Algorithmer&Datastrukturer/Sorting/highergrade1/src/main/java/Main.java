import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchFactory;

public class Main {
    private static final long maxIterations = 1000000000L;

    public static void main(String... args) {
        test(new A1.Parentheses());
    }

    private static void timedTest(){
        for (int N = 250; N < maxIterations ; N += N) { // Print time for problem size N.
            double time = timeTrial(N);
            System.out.printf("%7d %5.1f\n", N * 2, time);
        }
    }

    private static double timeTrial(int N) { // Time ThreeSum.count() for N random 6-digit ints.
        A1.Parentheses parentheses;
        parentheses = new A1.Parentheses();
        String expression = generateExpression(N);
        parentheses.setExpression(expression);
        Stopwatch timer = StopwatchFactory.MILLI.getStopwatch();
        boolean isBalanced = parentheses.checkBalanced();
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
    private static long testBigExpression(A1.Parentheses parentheses, int n) {
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

    private static long test(A1.Parentheses parentheses) {
        String[] testStrings = {"({[]})","()()()","a})","([)]{}", "2+2x(4-2)x(3x4)", "abc", "(([()]){})", "(([()]){)}", "( ()(", "()", "(())", "[]", "[", "[)", "[ {} ] () () ()"};
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
