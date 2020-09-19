package assignment6;

import common.AlgorithmCompare;
import common.Sort;
import mergesort.MergeSort;

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
    private static Set<Sort> sorts;
    private static Map<String, List<Double>> executionTimes = new LinkedHashMap<>();

    static {
        sorts = new LinkedHashSet<>();
        for (int i = 0; i < 30; i++) {
            Sort sort = new MergeSort(i + 1, false);
            sorts.add(sort);
            executionTimes.put(sort.toString(), new ArrayList<>());
        }
    }

    public static void main(String...args){
        run();
    }

    private static void run(){
        int trials = 10;
        for(int N = 100000; N < 1000000; N += 100000) {
            for (Sort sort : sorts) {
                double timeForTrials = AlgorithmCompare.timeRandomInputTotal(sort, N,trials);
                executionTimes.get(sort.toString()).add(timeForTrials);
            }
        }
    }

}
