package assignment3.in;

import assignment1.InsertionSort;
import common.Sort;

/**
 * (1) Divide the left half into sub arrays until its no longer possible
 */
public class InversionCounter {
    private static Sort sort = new InsertionSort();

    public static long count(int a[]) {
        int[] b = a.clone();
        int[] aux = new int[a.length];
        return count(a, b, aux, 0, a.length - 1);
    }

    public static long countBruteForce(int[] a) {
        long inversions = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[i]) {
                    //   System.out.println(inversionToString(i,j,a));
                    inversions++;
                }
        return inversions;
    }

    public static long countWCutoff(int a[]){
        int[] b = a.clone();
        int[] aux = new int[a.length];
        return countWithCutoff(a, b, aux, 0, a.length - 1);
    }

    private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid + 1, hi);
        inversions += mergeAndCount(b, aux, lo, mid, hi);
        printInversions(a,lo,hi);
        return inversions;
    }

    private static long countWithCutoff(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo + 7) {
            countBruteForce(aux, lo, hi);
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid + 1, hi);
        inversions += mergeAndCount(b, aux, lo, mid, hi);
        printInversions(a,lo,hi);
        return inversions;
    }

    private static long mergeAndCount(int a[], int aux[], int lowerLimit, int mid, int upperLimit) {
        long inversions = 0;
        int i = lowerLimit;
        int j = mid + 1;
        int k = lowerLimit;
        while (k <= upperLimit) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > upperLimit)
                aux[k] = a[i++];
            else if (a[i] > a[j]) {
                inversions += (mid - i + 1);
                aux[k] = a[j++];
            } else {
                aux[k] = a[i++];
            }
            k++;
        }
        return inversions;
    }

    // print inversions in a[lo..hi] via brute force
    private static void printInversions(int[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[i]){
              //      System.out.println(inversionToString(i,j,a));
                }
    }

    private static long countBruteForce(int[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[i]){
                    inversions ++;
                }
        return inversions;
    }

    private static String inversionToString(int i, int j, int [] a){
        return "[" + i + "," + a[i] + "] " + ", [" + j + "," + a[j] + "]";
    }
}
