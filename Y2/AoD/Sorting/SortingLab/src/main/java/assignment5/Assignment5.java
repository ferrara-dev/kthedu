package assignment5;

import sort.InsertionSort;
import sort.MergeSort;
import sort.Sort;
import util.StdRandom;

/**
 * @author Saumel Ferrara spof@kth.se
 * <p>
 * ============================= README ============================= *
 * Compare the execution times for sorting large arrays of integers
 * with insertionsort and merge sort.
 * <p>
 * Todo for assignment 5:
 * [X] compare insertion sort VS Merge sort for random arrays
 * [] Explain the code and how execution time was measured,
 *    and why the presented results are valid.
 * ========================= Solution =================================
 *
 *
 *
 * ====================== Questions to answer ======================= *
 * <Question> How was execution time measured ? </Question>
 * <Answer>
 * Every implementation of the Sort interface has a time(arr[]) method
 * that returns the time that i took to sort the array in milliseconds.
 *
 * It internally uses an implementation of the StopWatchI interface
 * which will return the elapsed time.
 *
 * Execution time is measured for one problem size at a time as the
 * average out of a number of trials.
 *
 * Both problem size and number of trials can be given as command
 * line arguments, with problems size at index 0 and trials at index 1.
 * </Answer>
 *
 * <Question> what parameters in the input could be relevant? </Question>
 *
 * <Answer>
 *      what matters most is if the array is sorted or not, the merge sort
 *      algorithm has a nLog(n) time complexity in the worst case and the
 *      insertion sort has n^2.
 *
 *      However insertion sort would sort already sorted arrays faster than
 *      mergesort, but as the result sugests in the InsertionVSMerge.pdf.
 *      Merge sort is superior insertion sort in random trials.
 * </Answer>
 */

public class Assignment5 {
    private int problemSize = 10000;
    private int trials = 1;

    public void compare() {
        Sort insertionSort = new InsertionSort(false);
        Sort mergeSort = new MergeSort();
        int array [] = StdRandom.randomInts(problemSize,-2,2);

        double time = 0.0;
        for(int i = 0; i < trials; i++) {
            time += mergeSort.time(array.clone());
        }

        double timeInsertionSort = 0.0;
        for(int i = 0; i < trials; i++) {
            timeInsertionSort += insertionSort.time(array.clone());
        }

        System.out.println(mergeSort.toString() + " N=" + problemSize + " --> " + time/trials);
        System.out.println(insertionSort.toString() + " N=" + problemSize + " --> " + timeInsertionSort/trials);
    }

    public static void main(String...args){
        Assignment5 assignment5 = new Assignment5();
        if(args.length > 0){
            assignment5.problemSize = Integer.parseInt(args[0]);
            if(args.length > 1){
                assignment5.trials = Integer.parseInt(args[1]);
            }
        }
        assignment5.compare();
    }
}
