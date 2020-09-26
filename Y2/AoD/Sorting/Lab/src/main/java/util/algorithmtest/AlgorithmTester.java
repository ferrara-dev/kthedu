package util.algorithmtest;

import common.InversionCount;
import common.Sort;
import util.ConsoleTable;
import util.StdRandom;
import util.csv.CsvFile;
import util.stopwatch.Stopwatch;
import util.stopwatch.StopwatchFactory;
import util.stopwatch.StopwatchUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AlgorithmTester {
    private ConsoleTable consoleTable;
    private boolean isSetup = false;
    private boolean withResponseBody = false;
    private int maxCapacity = 100000;
    private HashMap<String, Object> sortingAlgorithmMap;
    private StopwatchUnit stopwatchUnit = StopwatchUnit.MILLI;
    private List<String> headers = new ArrayList<>();


    private AlgorithmTestResult algorithmTestResult = new AlgorithmTestResult();

    public AlgorithmTester() {
        consoleTable = new ConsoleTable();
        sortingAlgorithmMap = new HashMap<>();
    }

    public AlgorithmTester setUpTest(Object... algorithms) {
        for (Object algorithm : algorithms) {
            sortingAlgorithmMap.putIfAbsent(algorithm.getClass().getSimpleName(), algorithm);
        }
        return this;
    }

    public AlgorithmTester setStopwatchUnit(StopwatchUnit stopwatchUnit) {
        this.stopwatchUnit = stopwatchUnit;
        return this;
    }

    public AlgorithmTester withResponseBody(boolean with) {
        this.withResponseBody = with;
        return this;
    }

    public AlgorithmTester setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        return this;
    }

    private void setHeaders(List<String> headers) {
        headers.add("Number of elements (N)");
        sortingAlgorithmMap.forEach((key, val) -> {
                    headers.add("Time for " + key + " algorithm " + "(" + stopwatchUnit.getUnit() + ")");
                }
        );
    }

    public void runAlgorithmTest() {
        System.out.println("=============== TIMED TEST ===============");
        for (int N = 2; N < maxCapacity; N += N) { // Print time for problem size N.
            compareAlgorithms(N);
        }
    }

    /**
     * Compare execution time of two algorithms performing their operation
     * on an integer array of size N
     *
     * @param N
     */
    public void compareAlgorithms(int N) {
        int[] randomInts = StdRandom.randomInts(N, -10000, 10000);
        List<String> row = new ArrayList<String>();
        row.add(String.valueOf(N));
        sortingAlgorithmMap.forEach((key, algorithm) -> {
                    int[] ints = randomInts.clone();
                    double time = timedTest(algorithm, ints);
                    row.add(String.valueOf(time));
                }
        );
        algorithmTestResult.addRow(row);
    }

    /**
     * Print result of test to the console.
     */
    public void printResult() {
        List<String> headers = new ArrayList<>();
        setHeaders(headers);
        consoleTable.setHeaders(headers);
        consoleTable.setContent(algorithmTestResult.getRows());
        consoleTable.printTable();
    }

    /**
     * Save the test result to a csv file
     *
     * @throws IOException
     */
    public void saveData() {
        Path file = Paths.get("./src/main/java/util/algorithmtest/the-file-name.csv");
        CsvFile csvFile = new CsvFile(file);
        List<String> headers = new ArrayList<>();
        setHeaders(headers);
        csvFile.setHeaders(headers);
        List<List<String>> lst = algorithmTestResult.getRows();
        lst.forEach((row) -> {
            csvFile.addRow(row);
        });
        csvFile.write();
    }

    private double timedTest(Object algorithm, int a[]) {
        if (algorithm instanceof Sort)
            return timedSorting((Sort) algorithm, a);
        else if (algorithm instanceof InversionCount)
            return timedInversionCount((InversionCount) algorithm, a);
        else
            throw new IllegalArgumentException();
    }

    private double timedInversionCount(InversionCount inversionCount, int[] arr) {
        Stopwatch stopwatch = StopwatchFactory.getStopwatch(stopwatchUnit);
        inversionCount.count(arr);
        return stopwatch.elapsedTime();
    }

    private double timedSorting(Sort sorter, int[] a) {
        Stopwatch stopwatch = StopwatchFactory.getStopwatch(stopwatchUnit);
        try {
            sorter.sort(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stopwatch.elapsedTime();
    }

    public class AlgorithmTestConfig {
        private boolean withResponseBody;
        private boolean isSetup = false;

        AlgorithmTestConfig() {

        }
    }

    public static void main(String... args) throws IOException {
        AlgorithmTester algorithmTester = new AlgorithmTester();
        algorithmTester.saveData();
    }
}
