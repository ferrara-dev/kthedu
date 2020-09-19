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
    /*
    public static void main(String...args){
        runSmallProbSize(args[1]);
        runLargeProbSize(args[1]);
    }

    private static void runSmallProbSize(String arg){
        final String testDescription = "Small problem sizes : Insertionsort vs Mergesort";
        TestResult testResult = AlgorithmCompare.algorithmComparison(testDescription,
                1,100,10,
                new MergeSort(false),
                new InsertionSort(false));
        ComparisonService.printComparison(testResult);
        String fileName = ComparisonService.saveComparison(testResult);
        try {
            ComparisonPlotter.createGraphs("C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\testresults\\insertionVSmergeSmall.csv",
                    plotName(testDescription));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runLargeProbSize(String arg){
        final String testDescription = "Large problem sizes : Insertionsort vs Mergesort";
        TestResult testResult = AlgorithmCompare.algorithmComparison(testDescription,
                250,100000,5,
                new MergeSort(false),
                new InsertionSort(false));
        ComparisonService.printComparison(testResult);
        if(arg.equals("plot")) {
            try {
                ComparisonPlotter.createGraphs("C:\\Users\\root\\Desktop\\kthedu\\Y2\\AoD\\Sorting\\LabJ\\src\\main\\resources\\testresults\\insertionVSmergeSmall.csv",
                        plotName(testDescription));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String plotName(String name){
       return name.replace(" ", "").replace(":", "");
    }

 */
}
