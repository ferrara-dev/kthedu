package mergesort;

import common.Sort;

import java.util.Arrays;

/**
 * Merge sort is memory expensive and wont work if the
 * array that needs to be sorted barley fits in memory.
 * <p>
 * - UpperBound and lowerBound shows the range currently being considered by the algorithm,
 * and mid shows the middle of this range.
 * <p>
 * - The range starts as the whole array, and is halved each time mergeSort() method calls itself.
 * - When when the range is one, the method will return.
 */
public class MergeSort implements Sort {
    private boolean debugPrint = true;
    private int cutoff = 0;

    public MergeSort(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public MergeSort() {
    }

    public MergeSort(int cutoff) {
        this.cutoff = cutoff;
    }

    public MergeSort(int cutoff, boolean debugPrint) {
        this.cutoff = cutoff;
        this.debugPrint = debugPrint;
    }

    public void sort(int[] a) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private void sort(int[] arrayToSort, int[] aux, int lowerBound, int upperBound) {
        if (upperBound <= lowerBound + cutoff) {
            insertionSort(aux, lowerBound, upperBound);
            return;
        }

        int mid = lowerBound + (upperBound - lowerBound) / 2; // Find middle point of range
        sort(aux, arrayToSort, lowerBound, mid); // Sort the left half
        sort(aux, arrayToSort, mid + 1, upperBound); // Sort the right half
        merge(arrayToSort, aux, lowerBound, mid, upperBound); // merge the arrays
    }

    /**
     * Left sub-array = a[lowerLimit...mid]
     * Right sub-array = a[mid+1...upperLimit]
     * Merges left sub-array and right sub-array defined above into <code>aux[]</code>
     *
     * @param arrayToSort
     * @param aux
     * @param lowerBound denotes the lower limit of the left sub-array
     * @param mid        denotes the breaking point between the left and right sub-array.
     * @param upperBound denotes the upper limit of the right sub-array
     */
    private void merge(int[] arrayToSort, int[] aux, int lowerBound, int mid, int upperBound) {
        int i = lowerBound;
        int j = mid + 1;
        int k = lowerBound;
        while (k <= upperBound) {
            if (i > mid)
                aux[k] = arrayToSort[j++];
            else if (j > upperBound)
                aux[k] = arrayToSort[i++];
            else if (arrayToSort[j] < arrayToSort[i])
                aux[k] = arrayToSort[j++];
            else {
                aux[k] = arrayToSort[i++];
            }
            k++;
        }
    }

    private void insertionSort(int[] a, int lowerBound, int upperBound) {
        for (int i = lowerBound; i <= upperBound; i++)
            for (int j = i; j > lowerBound && a[j] < a[j - 1]; j--)
                swap(a, j, j - 1);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public String toString() {
        if (cutoff != 0)
            return getClass().getSimpleName() + "(with Cut off =" + cutoff + ")";
        else
            return getClass().getSimpleName() + "(with NO Cut off)";
    }
}
