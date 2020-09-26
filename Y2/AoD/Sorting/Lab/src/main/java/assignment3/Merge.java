package assignment3;

import java.util.Arrays;

public class Merge {

    /**
     * Merge left- and right array into result.
     *
     * @param leftArray
     * @param rightArray
     * @param result
     */
    public static void merge(int[] result, int[] leftArray, int[] rightArray) {
        int i = 0, j = 0, k = 0;
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int n = result.length;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] < rightArray[j])
                result[k++] = leftArray[i++];
            else
                result[k++] = rightArray[j++];
        }

        while (i < leftSize)
            result[k++] = leftArray[i++];

        while (j < rightSize)
            result[k++] = rightArray[j++];


        System.out.println(Arrays.toString(result));
    }

    /**
     * Left sub-array = a[lowerLimit...mid]
     * Right sub-array = a[mid+1...upperLimit]
     * Merges left sub-array and right sub-array defined above into <code>aux[]</code>
     *
     * @param a
     * @param aux
     * @param lowerLimit denotes the lower limit of the left sub-array
     * @param mid        denotes the breaking point between the left and right sub-array.
     * @param upperLimit denotes the upper limit of the right sub-array
     */
    public static void merge(int a[], int aux[], int lowerLimit, int mid, int upperLimit) {
        int i = lowerLimit;
        int j = mid + 1;
        int k = lowerLimit;
        while (k <= upperLimit) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > upperLimit)
                aux[k] = a[i++];
            else if (a[i] > a[j])
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
            k++;
        }
        System.out.println(Arrays.toString(aux));
    }

    public static class MergeTester {
        public static void main(String... args) {
            int[] rightArray = {2, 3, 4};
            int[] leftArray = {1, 5, 6};
            int[] result = new int[rightArray.length + leftArray.length];
            Merge.merge(result, leftArray, rightArray); // Should output [1,2,3,4,5,6]

            int a[] = {2, 3, 4, 1, 5, 6};
            int aux[] = a.clone();
            int lo = 0;
            int hi = a.length - 1;
            int mid = lo + (hi - lo) / 2;
            Merge.merge(a, aux, lo, mid, hi);
        }
    }
}
