package highergrade2;

import assignment5.Assignment5;
import sort.InsertionSort;
import sort.MergeSort;
import sort.QuickSort;
import sort.Sort;
import util.ArrayUtil;
import util.StdRandom;

/**
 * Todo for higher grade assignment 2 :
 * <p>
 * [] Compare the execution times for sorting large arrays of integers
 * with quicksort and merge sort.
 * <p>
 * Question : When should one select quicksort over mergesort?
 * <p>
 * ================================ README ======================================
 * Classes relevant to this assignment:
 * > {@code MedianOfThreeQuickSort} implementation of the {@code Sort} interface
 * > {@code MergeSort} implementation of the {@code Sort} interface
 *
 *  Results are found in QuickVSMerge.pdf.
 *
 *  The results confirms that the worst case scenario
 *  for quick sort is when the largest or smallest
 *  element is chosen as the pivot.
 *
 *  QuickSort was most effective on random arrays
 */
public class HigherGrade2Driver {
    private int problemSize = 100000;
    private int trials = 1;

    public void compare() {
        Sort quickSort = new QuickSort(false);
        Sort mergeSort = new MergeSort(21);
        int array[] = StdRandom.randomInts(problemSize, -2, 2);
        double time = 0.0;

        for (int i = 0; i < trials; i++) {
            time += mergeSort.time(array.clone());
        }

        double quickSortTime = 0.0;
        for (int i = 0; i < trials; i++) {
            quickSortTime += quickSort.time(array.clone());
        }
        System.out.println("With random Array :: ");
        System.out.println(quickSort.toString() + " N=" + problemSize + " --> " + quickSortTime / trials);
        System.out.println(mergeSort.toString() + " N=" + problemSize + " --> " + time / trials);

        System.out.println("With sorted Array :: ");
        int ascending[] = ArrayUtil.ascendingArray(problemSize);
        double timeasc = 0.0;
        for (int i = 0; i < trials; i++) {
            timeasc += quickSort.time(ArrayUtil.clone(ascending));
        }

        double timeasc2 = 0.0;

        for (int i = 0; i < trials; i++) {
            timeasc2 += mergeSort.time(ArrayUtil.clone(ascending));
        }

        System.out.println("With ascending Arrays :: ");
        System.out.println(quickSort.toString() + " N=" + problemSize + " --> " + timeasc / trials);
        System.out.println(mergeSort.toString() + " N=" + problemSize + " --> " + timeasc2 / trials);
    }

        public static void main (String...args){
            HigherGrade2Driver higherGrade2Driver = new HigherGrade2Driver();
            if (args.length > 0) {
                higherGrade2Driver.problemSize = Integer.parseInt(args[0]);
                if (args.length > 1) {
                    higherGrade2Driver.trials = Integer.parseInt(args[1]);
                }
            }
            higherGrade2Driver.compare();
        }
    }
