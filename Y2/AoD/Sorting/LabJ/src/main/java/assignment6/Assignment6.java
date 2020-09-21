package assignment6;

import assignment3.Merge;
import common.AlgorithmCompare;
import common.Sort;
import mergesort.MergeSort;
import util.StdRandom;
import util.stopwatch.StopwatchUnit;

import java.util.*;

/**
 * @author Saumel Ferrara spof@kth.se
 * <p>
 * ============================= README ============================= *
 * Compare the execution times for sorting large arrays of integers
 * with different cutoffs.
 * <p>
 * Todo for assignment 6:
 * [] compare All cutoffs 0-30 for random arrays
 * [] Sort an array of length N for cut-offs 0-30
 * - repeat t times.
 * - Calculate what cut-off that gave the best
 * performance on average
 * - repeat for r different lengths N
 * - Plot in bar diagram
 * [] Perform doubling test for top performers
 * - plot execution time with regards to number of elements.
 * [] save result as csv file
 * [] Explain the code and how execution time was measured,
 * why your results are valid and show the results
 * <p>
 * <p>
 * ====================== Questions to answer ======================= *
 * <Question> How was execution time measured ? </Question>
 * <Answer>
 *
 * </Answer>
 */
public class Assignment6 {
    private static int problemSize = 10000;
    public static void main(String... args) {
        int[] randomArray = StdRandom.randomInts(problemSize, -1, 1);
        Sort merge = new MergeSort(0);
        Sort[] mergeSorts = new MergeSort[2];
        mergeSorts[0] = merge;
        mergeSorts[1] = new MergeSort(11);

        for (Sort sort : mergeSorts) {
            double time = 0.0;
            for (int i = 0; i < 100; i++) {
                time += AlgorithmCompare.time(sort, randomArray.clone(), StopwatchUnit.MILLI);
            }
            System.out.println(sort.toString() + " N=" + problemSize + " --> " + time + "ms");
        }
    }


}
