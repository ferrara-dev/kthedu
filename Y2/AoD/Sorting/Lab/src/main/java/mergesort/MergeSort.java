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

    public MergeSort(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public MergeSort() {

    }

    public void sort(int[] a) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private void sort(int[] arrayToSort, int[] aux, int lowerBound, int upperBound) {
        if (upperBound > lowerBound) {  // Base case : if range is 1, return
            if (debugPrint)
                printSubArray(arrayToSort, lowerBound, upperBound);
            int mid = lowerBound + (upperBound - lowerBound) / 2; // Find middle point of range
            if (debugPrint)
                printDiv(arrayToSort, lowerBound, upperBound, mid);
            sort(aux, arrayToSort, lowerBound, mid); // Sort the left half
            sort(aux, arrayToSort, mid + 1, upperBound); // Sort the right half
            merge(arrayToSort, aux, lowerBound, mid, upperBound); // merge the arrays
        }
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

    private void printSubArray(int[] array, int lowerBound, int upperBound) {
        System.out.println("sub array to divide > " +
                "index " + lowerBound + "-" + upperBound +
                " -> " + Arrays.toString(Arrays.copyOfRange(array, lowerBound, upperBound + 1)));
    }

    private void printDiv(int[] array, int lowerBound, int upperBound, int mid) {
        System.out.print("index " + lowerBound + "-" + (mid) + " -> ");
        System.out.println(Arrays.toString(Arrays.copyOfRange(array, lowerBound, mid + 1))
                + " || " + "index " + (mid + 1) + "-" + (upperBound) + " -> " + Arrays.toString(Arrays.copyOfRange(array, mid + 1, upperBound + 1)));
    }


}
