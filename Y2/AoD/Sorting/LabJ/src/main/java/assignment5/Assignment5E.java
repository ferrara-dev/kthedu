package assignment5;

import assignment1.InsertionSort;
import common.AlgorithmCompare;
import common.ComparisonService;
import common.Sort;
import common.TestResult;
import mergesort.MergeSort;
import util.ConsoleTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Saumel Ferrara spof@kth.se
 *
 * ============================= README ============================= *
 *  Compare the execution times for sorting large arrays of integers
 *  with insertionsort and merge sort.
 *
 * Todo for assignment 5:
 *    [X] compare insertion sort VS Merge sort for random arrays
 *    [] compare insertion sort VS Merge sort for descending arrays
 *    [] compare insertion sort vs Merge sort for ascending arrays
 *    [] compare the different cases for insertion sort
 *    [] compare the different cases for merge sort
 *    [] print result of comparisons to the console
 *    [] save result as csv file
 *    [] Explain the code and how execution time was measured,
 *       why your results are valid and show the results
 *
 *
 * ====================== Questions to answer ======================= *
 *  <Question> How was execution time measured ? </Question>
 *  <Answer>
 *      Static methods in the <code>AlgorithmCompare</code> class are
 *      responsible for measuring total time to perform t number of
 *      operations on an array of length n.
 *
 *      It takes an implementation of the <code> Sort </code> interface
 *      as parameter, and measures the execution time using <code>System.nanoTime()</code>
 *  </Answer>
 *
 *  <Question> what parameters in the input could be relevant? </Question>
 *      <Answer>
 *
 *      </Answer>
 *
 *
 */
public class Assignment5E {

    public void insertionVSMergeRandomArrays(int lowerBounds, int upperBounds, int trials){

        Sort insertionSort = new InsertionSort(false);
        Sort mergeSort = new MergeSort(false);

        TestResult res = ComparisonService.runAlgorithmTest(lowerBounds, upperBounds, trials, insertionSort,mergeSort);
        res.setDescription("Insertion sort VS Merge sort 5 trials");
        ComparisonService.printResult(res);
        ComparisonService.saveResult(res);

/*
        List<String> headers = Arrays.asList("N", insertionSort.toString(), mergeSort.toString());
        ConsoleTable consoleTable = new ConsoleTable(headers);

        for(int N = lowerBounds; N < upperBounds; N += N){
            List<String> resultTableRow = new ArrayList<>();
            double insertionSortTime = AlgorithmCompare.timeRandomInputTotal(insertionSort, N, trials);
            double mergeSortTime = AlgorithmCompare.timeRandomInputTotal(mergeSort, N, trials);
            resultTableRow.add(String.valueOf(N));
            resultTableRow.add(String.valueOf(insertionSortTime));
            resultTableRow.add(String.valueOf(mergeSortTime));
            consoleTable.addRow(resultTableRow);
        }
        consoleTable.printTable();

 */
    }

    public void insertionDescendingVSInsertionAscending(int lowerBounds, int upperBounds,int trials){
        for(int i = lowerBounds; i < upperBounds; i++){

        }
    }
}
