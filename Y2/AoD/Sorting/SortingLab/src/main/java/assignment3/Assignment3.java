package assignment3;

import assignment1.Assignment1Driver;
import sort.InsertionSort;
import util.StdRandom;

import java.util.Arrays;
/**
 * @Author Samuel Ferrara spof@kth.se
 * Solution to assignment 3
 *
 * Todo for E part :
 * [x] create a method which counts the number of inversions in an array
 * [x] print the inversions as [i, a[i]], [j, a[j]]
 * []  calculate the time complexity
 *
 * use the input [1, 2, 4, 3, 5, 0]
 * ============================= README =============================
 *
 * Time complexity calculation :
 * The time complexity of BruteForceCount.count(arr []) is O(N^2).
 *
 * The algorithm performs N iterations in the outer loop, and the inner loops
 * performs N - Performed iterations in outer.
 *
 *
 */
public class Assignment3 {

    public static void main(String... args) {
        int a [] = Assignment1Driver.getUserInput();
        System.out.println(Arrays.toString(a));
        long inversions = new BruteForceCount().count(a);
        System.out.println("Inversions in array : " + inversions);
        System.out.println(Arrays.toString(a));
        new InsertionSort().sort(a);
        System.out.println(Arrays.toString(a));
    }


}
