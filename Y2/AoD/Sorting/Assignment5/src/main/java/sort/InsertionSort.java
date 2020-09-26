package sort;

import util.StdRandom;
import util.stopwatch.MilliStopwatch;
import util.stopwatch.StopwatchI;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Samuel Ferrara
 * Solution to assignment 1 and 2
 * <p>
 * <p>
 * Performs insertionSort on an array of integers
 * <p>
 * Uses swaps
 */
public class InsertionSort implements Sort {
    private boolean debugPrint = true;

    public InsertionSort(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public InsertionSort() {

    }

    @Override
    public void sort(int[] a) {
        int n = a.length;
        int i = 1;
        int nrOfSwaps = 0;
        while (i < n) {
            int j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                swap(a, j);
                nrOfSwaps++;
                if (debugPrint)
                    printArr(a);
                j--;
            }
            i++;
        }
        if (debugPrint)
            System.out.println("Total number of swaps : " + nrOfSwaps);
    }

    @Override
    public double time(int[] arr) {
        StopwatchI stopwatch = new MilliStopwatch();
        sort(arr);
        return stopwatch.elapsedTime();
    }

    private void swap(int[] arr, int j) {
        int temp = arr[j - 1];
        arr[j - 1] = arr[j];
        arr[j] = temp;
    }

    private void printArr(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

    public static void main(String... args) {
        boolean withPrint = false;
        if (args.length > 1 && (args[0].toLowerCase().equals("a2") || args[0].toLowerCase().equals("assignment2")))
            withPrint = true;
        Sort insertionSort = new InsertionSort(withPrint);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Give the number of elements that is to be sorted : ");
        int n = scanner.nextInt();
        int arr[] = new int[n];

        System.out.println("Give " + n + " number of integers that is to be sorted");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(Arrays.toString(arr));
        insertionSort.sort(arr);
    }

    private static class InsertionSortTester{
        public static void main(String...args){
            InsertionSort insertionSort = new InsertionSort(false);
            int [] a = StdRandom.randomInts(10000,-5,5);
            StopwatchI stopwatchI = new MilliStopwatch();
            insertionSort.sort(a);
            System.out.println(stopwatchI.elapsedTime());
        }
    }
}
