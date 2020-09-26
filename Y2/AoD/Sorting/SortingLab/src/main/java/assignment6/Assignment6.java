package assignment6;

import compare.TestResult;
import datastructure.BasicStack;
import datastructure.OrderedQueue;
import sort.MergeSort;
import sort.Sort;
import util.ArrayUtil;
import util.StdRandom;

/**
 * @author Saumel Ferrara spof@kth.se
 * <p>
 * ============================= README ============================= *
 * Compare the execution times for sorting large arrays of integers
 * with different cutoffs.
 * <p>
 * Todo for assignment 6:
 * [x] compare All cutoffs 0-30 for random arrays
 * [x] Sort an array of length N for different cut-offs
 * - Calculate what cut-off that gave the best
 *   performance on average
 * [x] save result as csv file
 * [x] Explain the code and how execution time was measured,
 *     why your results are valid and show the results
 *
 *
 * =========================== Solution ===================================
 * MergeSort is an implementation of the Sort interface that can be set
 * up to change sorting strategy for arrays of sizes smaller than its
 * cutoff.
 *
 * The cutoff is set at instantation.
 *
 * ====================== Questions to answer ======================= *
 * <Question> How was execution time measured ? </Question>
 * <Answer>
 *  Every implementation of the Sort interface has a time(arr[]) method
 *  that returns the time that i took to sort the array in milliseconds.
 *
 *  It internally uses an implementation of the StopWatchI interface
 *  which will return the elapsed time.
 *
 *  Execution time is measured for one problem size at a time as the
 *  average out of a number of trials.
 *
 *  This program can be run in two different ways,
 *
 *  (1) if commandline arguments are given as " Assignment6 1000000 all"
 *      it will measure the time for cutoff between 1 and 30 on a random
 *      array with size 1000000 and print the most efficient cutoff to
 *      the console.
 *
 * (2) if commmalndline arguements are empty, or give as "Assignment6 1000000 6"
 *     it will measure the time for cuttoff 6 on a random array of size 1000000.
 *
 * </Answer>
 *
 * By looking at the result displayed in cutoffComparison.pdf the optimal cutoff
 * seems to be somewhere around 7 and 20 for random arrays.
 *
 * However the outcome of the result is dictated by the arrangement of elements
 * in the array as well as the machine that it is run on.
 *
 */
public class Assignment6 {

    public static void main(String... args) {
        int cutoff = 0;
        int problemSize = 100000;
        if (args.length > 0) {
            problemSize = Integer.parseInt(args[0]);
            if (args.length > 1)
                if (args[1].equals("all"))
                    testAll(problemSize);
                else
                    cutoff = Integer.parseInt(args[1]);
        }

        testsMergeSort(cutoff, problemSize);
    }

    public static void testsMergeSort(int cutoff, int problemSize) {
        Sort mergeSort = new MergeSort(cutoff);
        int[] randomArray = StdRandom.randomInts(problemSize, -1, 1);
        double time = 0.0;

        // warm up for good measure
        for (int i = 0; i < 10; i++) {
            mergeSort.time(randomArray.clone());
        }

        for (int i = 0; i < 10; i++) {
            time += mergeSort.time(StdRandom.randomInts(problemSize, -1, 1));
        }
        System.out.println(mergeSort.toString() + " N = " + problemSize + " ---> " + "Sorted in"  + time / 10 + " milliseconds");
    }


    public static void testAll(int problemSize) {
        BasicStack<Sort> sorts = new BasicStack<>();

        OrderedQueue<TestResult> results = new OrderedQueue<>();

        for (int i = 0; i <= 30; i++) {
            sorts.push(new MergeSort(30 - i));
        }

        for (int i = 0; i < 100; i++) {
            for (Sort sort : sorts) {
                sort.sort(ArrayUtil.randomArray(problemSize));
            }
        }

        while (!sorts.isEmpty()) {
            double time = 0.0;
            Sort sort = sorts.pop();
            for (int i = 0; i < 10; i++) {
                time += sort.time(ArrayUtil.randomArray(problemSize));
            }
            System.out.println(sort.toString() + " N = " + problemSize + " ---> " + "Sorted in " + time/10 + " milliseconds");
            results.enqueue(new TestResult(sort.toString(), problemSize, time));
        }

        System.out.println("");
        System.out.println("");
        OrderedQueue<TestResult> topResults = new OrderedQueue<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Best performance " + (i + 1) + " :: " + results.removeFirst().toString());
        }

        System.out.println("Worst performance :: " + results.dequeue().toString());
    }
}
