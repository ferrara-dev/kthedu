package assignment5;

import assignment1.InsertionSort;
import common.AlgorithmCompare;
import common.ComparisonService;
import mergesort.MergeSort;
import common.TestResult;
import plotting.ComparisonPlotter;

import java.io.IOException;

/**
 * Driver class for assignment 5
 *
 * Creates plots from python script if command line arg at index 1 equals "plot"
 */
public class Assigment5Driver {
    private static final String PLOT_SCRIPT_PATH = "C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\scripts\\plot_script.py";
    private static final String CSV_PATH = "C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\testresults\\";

    public static void main(String...args){
        Assignment5E assignment5E = new Assignment5E();
        assignment5E.insertionVSMergeRandomArrays(1000,100000,3);
    }
}
