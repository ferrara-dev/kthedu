package assignment3;

import common.InversionCount;

public class BruteForceCount implements InversionCount {
    private boolean debugPrint = true;

    public BruteForceCount(boolean debugPrint) {
        this.debugPrint = debugPrint;
    }

    public BruteForceCount() {

    }

    @Override
    public long count(int[] a) {
        long inversions = 0;
        for (int i = 0; i < a.length; i++)
            for (int j = i + 1; j < a.length; j++)
                if (a[j] < a[i]) {
                    if (debugPrint)
                        System.out.println(inversionToString(i, j, a));
                    inversions++;
                }
        return inversions;
    }

}
