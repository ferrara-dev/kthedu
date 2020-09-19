package assignment3;

import common.InversionCount;

public class MergeCount implements InversionCount {
    private boolean debugPrint = true;
    private int[] array;

    public MergeCount(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public MergeCount() {

    }

    /**
     * Count the number of inversions in an
     * Use clone of a[] to avoid side effect of being rearranged in ascending order.
     *
     * @param a
     *
     * @return
     */
    @Override
    public long count(int[] a) {
        this.array = a;
        int[] b = a.clone();
        int[] aux = b.clone();
        long inversions = count(aux, b, 0, a.length - 1);
        return inversions;
    }

    private long count(int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo)
            return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(aux, b, lo, mid);
        inversions += count(aux, b, mid + 1, hi);
        inversions += mergeAndCount(b, aux, lo, mid, hi);
        return inversions;
    }

    /**
     * Merge two sorted arrays and return the number of inversions between them.
     *
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
                if (debugPrint)
                    printInversions(k, j);
                aux[k] = a[j++];
                inversions += (mid - i + 1);
            } else {
                aux[k] = a[i++];
            }
        }
        return inversions;
    }

    private void printInversions(int k, int j) {
        for (int l = k; l < j; l++)
            System.out.println(inversionToString(l, j, array));
    }
}
