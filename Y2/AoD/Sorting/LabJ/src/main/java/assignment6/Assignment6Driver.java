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
        Sort mergeSort = new MergeSort(7,false);
        Sort cutoff9 = new MergeSort(50, false);
        List<String> headers = Arrays.asList("N", mergeSort.toString(),  cutoff9.toString());
        List<String> row = new ArrayList<>();
        ConsoleTable consoleTable = new ConsoleTable(headers);
        for (int N = 10000; N < 1000000; N +=N*0.5) {
            row = new ArrayList<>();
            row.add(String.valueOf(N));
            row.add("");
            row.add("");
            consoleTable.addRow(row);
        }
        int i = 0;
        for (int N = 10000; N < 1000000; N += N*0.5,i++) {
            double totalTime = 0.0;
            totalTime = AlgorithmCompare.timeRandomInputTotal(mergeSort, N, 5);
            consoleTable.updateField(i,1,String.valueOf(totalTime));
        }
        i = 0;

        for (int N = 10000; N < 1000000; N += N*0.5,i++) {
            double totalTime = 0.0;
            totalTime = AlgorithmCompare.timeRandomInputTotal(cutoff9, N, 5);
            consoleTable.updateField(i,2,String.valueOf(totalTime));
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
