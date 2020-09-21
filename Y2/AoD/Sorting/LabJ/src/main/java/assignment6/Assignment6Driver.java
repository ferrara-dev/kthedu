package assignment6;

import assignment1.InsertionSort;
import assignment3.MergeCount;
import common.AlgorithmCompare;
import common.ComparisonService;
import common.Sort;
import common.TestResult;
import mergesort.MergeSort;
import plotting.ComparisonPlotter;
import util.ConsoleTable;
import util.StdRandom;
import util.csv.CsvUtil;
import util.stopwatch.StopwatchUnit;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assignment6Driver {
    public static void main(String... args) {
        doublingTest();
    }

    private static void doublingTest() {
        Sort mergeSort = new MergeSort(30,false);
        Sort cutoff30 = new MergeSort(13,false);
        Sort noCutoff = new MergeSort(0,false);
        Sort cutoff50 = new MergeSort(1000, false);

        List<String> headers = Arrays.asList("N", mergeSort.toString(), cutoff30.toString(), cutoff50.toString(), noCutoff.toString());
        List<String> row = new ArrayList<>();
        ConsoleTable consoleTable = new ConsoleTable(headers);
        for (int N = 0; N < 1; N++) {
            row = new ArrayList<>();
            row.add(String.valueOf(10000000));
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            consoleTable.addRow(row);
        }

        int i = 0;
        for (int N = 0; N < 1; N ++,i++) {
            double totalTime = 0.0;
            totalTime = AlgorithmCompare.timeRandomInputTotal(mergeSort, 10000000 , 1);
            consoleTable.updateField(i,1,String.valueOf(totalTime));
        }

        i = 0;

        for (int N = 0; N < 1; N ++,i++) {
            double totalTime = 0.0;
            totalTime = AlgorithmCompare.timeRandomInputTotal(cutoff30, 10000000 , 1);
            consoleTable.updateField(i,2,String.valueOf(totalTime));
        }

        i = 0;

        for (int N = 0; N < 1; N ++,i++) {
            double totalTime = 0.0;
            totalTime = AlgorithmCompare.timeRandomInputTotal(cutoff50, 10000000 , 1);
            consoleTable.updateField(i,3,String.valueOf(totalTime));
        }

        
        consoleTable.printTable();
    }

    private static void run() {
        Sort[] sorts = new Sort[30];
        for (int i = 0; i < sorts.length; i++) {
            sorts[i] = new MergeSort((i + 1), false);
        }

        TestResult res = ComparisonService.runAlgorithmTest(1000, 1000000, 5, sorts);
        res.setDescription("Cutoff comparison with 5 trials");
        ComparisonService.printResult(res);
        ComparisonService.saveResult(res);
    }

    private static void runStepsOfFive() {
        TestResult res = ComparisonService.runAlgorithmTest(250, 1000000, 100,
                new MergeSort(false),
                new MergeSort(5, false),
                new MergeSort(10, false),
                new MergeSort(15, false),
                new MergeSort(20, false),
                new MergeSort(25, false),
                new MergeSort(30, false));
        res.setDescription("Compare cutoff in steps of five");
        ComparisonService.printResult(res);
        ComparisonService.saveResult(res);
    }

    private static String plotName(String name) {
        return name.replace(" ", "").replace(":", "");
    }
}
