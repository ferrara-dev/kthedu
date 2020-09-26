package sort;

import stopwatch.MilliStopwatch;
import stopwatch.StopwatchI;
import util.StdRandom;

import java.util.Arrays;

/**
 * Solution to higher grade assignment 2 & 3
 */
public class QuickSort implements Sort {
    private boolean randomized = true;

    public QuickSort(boolean randomized) {
        this.randomized = randomized;
    }

    public QuickSort() {

    }

    @Override
    public double time(int[] arr) {
        StopwatchI stopwatch = new MilliStopwatch();
        sort(arr);
        return stopwatch.elapsedTime();
    }

    @Override
    public void sort(int[] a) {
        if (randomized)
            StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int pivotIndex = partition(a, lo, hi);
        sort(a, lo, pivotIndex - 1);
        sort(a, pivotIndex + 1, hi);
    }

    private int partition(int[] a, int lower, int upper) {
        int pivot = a[lower];
        int i = lower;
        int j = upper + 1;
        while (true) {

            // find item on lo to swap
            while (a[++i] < pivot) {
                if (i == upper) break;
            }

            // find item on hi to swap
            while (pivot < a[--j]) {
                if (j == lower) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            swap(a, i, j);
        }
        swap(a, lower, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static class quickSortTester {
        private static Sort quickSort = new QuickSort(false);

        public static void main(String... args) {
            test();
        }

        private static void test() {
            int a[] = new int[5];
            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 5; i++) {
                    a[i] = StdRandom.uniform(0, 5);
                }
                System.out.print("Before sorting : ");
                printArray(a);
                quickSort.sort(a);
                if (assertSorted(a)) {
                    System.out.print("After sorting : ");
                    printArray(a);
                    System.out.println(" ");
                } else {
                    throw new AssertionError("Sorting function is not working properly");
                }
            }

        }

        private static void printArray(int array[]) {
            System.out.println(Arrays.toString(array));
        }

        private static boolean assertSorted(int array[]) {
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1])
                    return false;
            }
            return true;
        }
    }

    public String toString(){
        return this.getClass().getSimpleName();
    }

}
