package highergrade1;

import sort.InsertionSort;
import sort.Sort;

import java.util.Arrays;

/**
 * Todo for higher grade assignment 1 :
 *
 *  [x] Augment the test code from assignment 1 so that the array
 *     is sorted in descending order instead of ascending order.
 *
 *
 * ================================ README ======================================
 * Classes relevant to this assignment:
 *   > {@code InsertionSort} implementation of the {@code Sort} interface
 *   > util classes
 *
 * =============================== Solution =====================================
 * Steps of the algorithm:
 *  1. multiply all elements by -1, making the largest element the smallest and
 *     vice versa.
 *  2. sort the array using insertion sort
 *  3. multiply all elements by -1
 */
public class HigherGrade1Driver {

    public static void main(String... args) {
        runHigherGradeAssignment();
    }

    /**
     * Sort an array of integers in ascending order by adding O(N) operations.
     * <p>
     * 1. multiply all elements by -1
     * 2. sort the array using insertion sort
     * 3. multiply all elements by -1
     */
    private static void runHigherGradeAssignment() {
        Sort insertionSort = new InsertionSort(true);
        int[] input = {1, 3, 2, 5, 4};
        System.out.println(Arrays.toString(input));

        for (int i = 0; i < input.length; i++) {
            input[i] = input[i] * (-1);
        }

        insertionSort.sort(input);

        for (int i = 0; i < input.length; i++) {
            input[i] = input[i] * (-1);
        }
        System.out.println(Arrays.toString(input));
    }

}
