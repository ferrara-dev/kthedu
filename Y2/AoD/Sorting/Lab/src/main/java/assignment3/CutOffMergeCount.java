package assignment3;

import common.InversionCount;

public class CutOffMergeCount implements InversionCount {
    public boolean debugPrint = true;
    private int [] array;
    public CutOffMergeCount(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public CutOffMergeCount() {

    }

    @Override
    public long count(int[] a) {
        array = a;
        int[] b = a.clone();
        int[] aux = b.clone();
        return countWithCutoff(b, aux, 0, a.length - 1);
    }


    private long countWithCutoff(int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo + 7) {
            countBruteForce(aux, lo, hi);
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        inversions += countWithCutoff(aux, b, lo, mid);
        inversions += countWithCutoff(aux, b, mid + 1, hi);
        inversions += mergeAndCount(b, aux, lo, mid, hi);
        if (debugPrint)
            printInversions(array, lo, hi);
        return inversions;
    }


    private long mergeAndCount(int a[], int aux[], int lowerLimit, int mid, int upperLimit) {
        long inversions = 0;
        int i = lowerLimit;
        int j = mid + 1;
        for (int k = lowerLimit; k <= upperLimit; k++) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > upperLimit)
                aux[k] = a[i++];
            else if (a[i] > a[j]) {
                aux[k] = a[j++];
                inversions += (mid - i + 1);
            } else {
                aux[k] = a[i++];
            }
        }
        return inversions;
    }
    private long countBruteForce(int[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[i]) {
                    inversions++;
                }
        return inversions;
    }

    private void printInversions(int[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j] < a[i]) {
                    System.out.println(inversionToString(i, j, a));
                }
    }
}
