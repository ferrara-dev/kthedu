package assignment3.in;

public class Inversions {

        // do not instantiate
        private Inversions() { }

        // merge and count
        private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
            long inversions = 0;

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }

            // merge back to a[]
            int i = lo, j = mid+1;
            for (int k = lo; k <= hi; k++) {
                if      (i > mid)
                    a[k] = aux[j++];
                else if (j > hi)
                    a[k] = aux[i++];
                else if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                    inversions += (mid - i + 1); }
                else
                    a[k] = aux[i++];
            }
            return inversions;
        }

        // return the number of inversions in the subarray b[lo..hi]
        // side effect b[lo..hi] is rearranged in ascending order
        private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
            long inversions = 0;
            if (hi <= lo) return 0;
            int mid = lo + (hi - lo) / 2;
            inversions += count(a, b, aux, lo, mid);
            inversions += count(a, b, aux, mid+1, hi);
            inversions += merge(b, aux, lo, mid, hi);
            return inversions;
        }


        /**
         * Returns the number of inversions in the integer array.
         * The argument array is not modified.
         * @param  a the array
         * @return the number of inversions in the array. An inversion is a pair of
         *         indicies {@code i} and {@code j} such that {@code i < j}
         *         and {@code a[i] > a[j]}.
         */
        public static long count(int[] a) {
            int[] b   = new int[a.length];
            int[] aux = new int[a.length];
            for (int i = 0; i < a.length; i++)
                b[i] = a[i];
            long inversions = count(a, b, aux, 0, a.length - 1);
            return inversions;
        }

}
