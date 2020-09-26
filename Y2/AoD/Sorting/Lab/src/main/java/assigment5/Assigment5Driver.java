package assigment5;

import assignment1.InsertionSort;
import assignment1.OptimizedInsertionSort;
import common.Sort;
import mergesort.MergeSort;
import util.algorithmtest.AlgorithmTester;
import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchUnit;

import java.io.IOException;

/**
 * Driver class for assignment 5
 */
public class Assigment5Driver {
    private static AlgorithmTester algorithmTester = new AlgorithmTester();

    public static void main(String...args){
        runAssignmentE();
    }

    private static void runAssignmentE(){
        Sort mergeSort = new MergeSort(false);
        Sort insertionSort = new InsertionSort(false);
        Sort optimizedInsertion = new OptimizedInsertionSort();
        algorithmTester.setUpTest(mergeSort, insertionSort, optimizedInsertion);
        algorithmTester.setStopwatchUnit(StopwatchUnit.NANO);
        algorithmTester.setMaxCapacity(100000);
        algorithmTester.runAlgorithmTest();
        algorithmTester.printResult();
        algorithmTester.saveData();
    }
}
