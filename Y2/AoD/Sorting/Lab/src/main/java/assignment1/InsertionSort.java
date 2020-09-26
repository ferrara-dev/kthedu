package assignment1;

import common.Sort;

import java.util.Arrays;

/**
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

    private void swap(int[] arr, int j) {
        int temp = arr[j - 1];
        arr[j - 1] = arr[j];
        arr[j] = temp;
    }

    private void printArr(int[] a) {
        System.out.println(Arrays.toString(a));
    }

}
