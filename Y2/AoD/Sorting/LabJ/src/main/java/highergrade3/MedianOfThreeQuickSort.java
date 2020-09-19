package highergrade3;

import common.Sort;
import util.StdRandom;

import java.util.Arrays;

/**
 * Median of three implementation of quick sort
 */
public class MedianOfThreeQuickSort implements Sort {
    @Override
    public void sort(int[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lower, int upper) {
        if (upper <= lower)
            return;
        int lt = lower;
        int i = lower + 1;
        int gt = upper;
        int v = a[lower];
        while (i <= gt) {
            if (a[i] < v)
                swap(a, lt++, i++);
            else if (a[i] > v)
                swap(a, i, gt--);
            else
                i++;
        }
        sort(a, lower, lt - 1);
        sort(a, gt + 1, upper);
    }


    private int partition(int[] a, int lower, int upper) {
        return 0;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String...args){
        int [] ints = StdRandom.randomInts(5,-5,5);
        Sort quickSort = new MedianOfThreeQuickSort();
        System.out.println(Arrays.toString(ints));
        quickSort.sort(ints);
        System.out.println(Arrays.toString(ints));
    }
}
