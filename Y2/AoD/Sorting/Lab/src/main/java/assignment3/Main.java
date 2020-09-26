package assignment3;

import assignment1.Assignment1Main;
import assignment3.in.InversionCounter;
import assignment3.in.Inversions;
import common.InversionCount;
import util.ConsoleTable;
import util.StdRandom;
import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchFactory;
import util.stopwatch.StopwatchUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Samuel Ferrara spof@kth.se
 * Solution to assignment 3
 * Todo for E part :
 * [x] create a method which counts the number of inversions in an array
 * [x] print the inversions as [i, a[i]], [j, a[j]]
 * []  calculate the time complexity
 */
public class Main {
    public static void main(String... args) {
        int a [] = StdRandom.randomInts(5,-5,5);
        System.out.println(Arrays.toString(a));
        long inversions = new MergeCount().count(a);
        System.out.println("Inversions in array : " + inversions);
        System.out.println(Arrays.toString(a));
    }

    private static void timedTest() {
        List<List<String>> content = new ArrayList<>();
        System.out.println("=============== TIMED TEST ===============");
        for (int N = 250; N < 100000; N += N) { // Print time for problem size N.
            int[] randomInts = Assignment1Main.randomInts(N);
            double time1 = timedInversionCount(new MergeCount(), randomInts);
            double time2 = timedInversionCount(new CutOffMergeCount(), randomInts);
            double time3 = timedInversionCount(new BruteForceCount(), randomInts);
            List<String> row = new ArrayList<String>();
            row.add(String.valueOf(N));
            row.add(String.valueOf(time1));
            row.add(String.valueOf(time2));
            row.add(String.valueOf(time3));
            content.add(row);
        }

        List<String> headers = new ArrayList<>();
        headers.add("Number of elements (N)");
        headers.add("Time for merge in milliseconds");
        headers.add("Time for merge with cutoff milliseconds");
        headers.add("Time for brute in milliseconds");

        ConsoleTable consoleTable = new ConsoleTable(headers, content);
        consoleTable.printTable();
    }

    public static double timedInversionCount(InversionCount inversionCount, int[] arr) {
        Stopwatch stopwatch = StopwatchFactory.getStopwatch(StopwatchUnit.MILLI);
        inversionCount.count(arr);
        return stopwatch.elapsedTime();
    }
}
