package common;

import util.StdRandom;
import util.stopwatch.StopwatchI;
import util.stopwatch.StopwatchFactory;
import util.stopwatch.StopwatchUnit;
import util.timer.Stopwatch;
import util.timer.TimeUnit;

public class AlgorithmCompare {

    private AlgorithmCompare() {
    }

    public static AlgorithmCompare getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Get median time to perform
     * Use <code> algorithm </code> to perform operation on random
     * array of length n for number of times given by <code> t </code>.
     *
     * @param algorithm
     * @param n
     * @param t
     *
     * @return median time out of <code> t </code> trials
     * that it took to complete operation on random integer array of size n.
     */
    public static double timeRandomInputMedian(Object algorithm, int n, int t) {
        double total = 0.0;
        int[] a;
        for (int trial = 0; trial < t; trial++) {
            a = StdRandom.randomInts(n, -100, 100);
            total += time(algorithm, a, StopwatchUnit.NANO);
        }
        double median = total / t;
        return median;
    }

    /**
     * Get total time for <code> algorithm </code> to perform operation
     * on t random arrays of length n
     *
     * @param algorithm
     * @param n
     * @param t
     *
     * @return total time, in milli seconds, to perform operation for <code> t </code> trials.
     */
    public static double timeRandomInputTotal(Sort algorithm, int n, int t) {
        double total = 0.0;
        int[] a;
        for (int trial = 0; trial < t; trial++) {
            a = StdRandom.randomInts(n, -100, 100);
            total += time(algorithm, a, StopwatchUnit.MILLI);
        }
        return total;
    }


    /**
     * Get the execution time of <code> algorithm </code> to perform
     * operation on <code> input </code>  array of length N.
     *
     * @param algorithm
     * @param input
     *
     * @return execution time for <code> algorithm </code> operation.
     */
    public static double time(Object algorithm, int[] input, StopwatchUnit unit) {
        if(algorithm instanceof Sort)
            return time((Sort) algorithm,input, unit);
        else if(algorithm instanceof InversionCount)
            return time((InversionCount) algorithm,input,unit);
        else
            throw new IllegalArgumentException();
    }

    private static double time(Sort algorithm, int[] input, StopwatchUnit unit) {
        StopwatchI stopwatch = StopwatchFactory.getStopwatch(unit);
        algorithm.sort(input);
        return stopwatch.elapsedTime();
    }

    private static double time(InversionCount algorithm, int[] input, StopwatchUnit unit) {
        StopwatchI stopwatch = StopwatchFactory.getStopwatch(unit);
        algorithm.count(input);
        return stopwatch.elapsedTime();
    }

    private static class InstanceHolder {
        private static final AlgorithmCompare INSTANCE = new AlgorithmCompare();
    }
}
