package assignment1;

import util.StdRandom;

import java.util.Scanner;

/**
 * Solution to assignment 1
 * Todo for E part :
 * [x] Implement insertion sort.
 * <p>
 * [x] Augment the sorting process so that all the content of the array that is being sorted is
 * printed after each inner loop iteration.
 * <p>
 * [x] Write a unit test in main() which allows the user to define the size of the input (N)
 * and then input (N) integers from stdin which is to be sorted.
 *
 * Solution to assignment 2
 * Todo for E part :
 * [x] Augment the implementation so that it prints the number of swaps performed when sorting the array
 *
 */
public class Assignment1Main {

    public static void main(String... args) {

    }

    public static int[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the number of elements that is to be sorted : ");
        int n = scanner.nextInt();
        int arr[] = new int[n];
        System.out.println("Give " + n + " number of integers that is to be sorted");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }
    public static int[] randomInts(int N){
        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(-10,10);
        }
        return arr;
    }
}
