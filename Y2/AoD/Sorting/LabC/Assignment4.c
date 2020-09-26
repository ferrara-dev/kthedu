#include <stdio.h>

/**
 * @author Samuel Ferrara spof@kth.se
 *
 * ========================== README ==========================
 * Todo for E Assignment 4 :
 *      Write an algorithm that rearranges the
 *      elements in a array of integers so that
 *      all negative elements comes before positives.
 *      The algorithm can not use more than O(1)
 *      extra memory.
 *      Further more, the it is not allowed to
 *      sort the array, only collect all negative
 *      values first.
 *
 * ================ Solution for E Assigment 4  ===============
 *
 * ------------ Modified insertion sort solution --------------
 * The solution solves the task with O(N^2) time complexity and
 * O(1) auxiliary space.
 *
 * Order of appearance in the array is kept.
 *
 *
 * ---------------- Steps of the algorithm --------------------
 * <1> Iterate through the elements from i = 1 to n - 1.
 * <2> If the current integer is positive, do nothing.
 * <3> If the current element is negative
 *    <3.1> shift all positive element from 0 to i- 1 one step to the right.
 *    <3.2> put the negative integer in the correct place>
 *
 *
 * ------------------------ Pseudo-code ------------------------
 *     rearrange(a[],n):
 *          i = 1
 *          while( i < n ):
 *               if a[i] is positive
 *                  continue;
 *               while (j >= 0 AND a[j] is positive):
 *                  shift a[j] one step to the right
 *                  j = j - 1
 *              end inner while;
 *
 *              a[j+1] = currentInt
 *          end outer while;
 *
 * ---------------------- End pseudo-code ---------------------
 *
 * * ------------ Modified partition sort solution --------------
 * The solution solves the task with O(N) time complexity and
 * O(1) auxiliary space.
 *
 * Because of the fact that the array is only traversed once,
 * the time complexity must be of order N.
 *
 * Sets the pivot element as 0 and swaps
 * all elements that are less than pivot
 * to currentIndex.
 *
 *
 *

 */
void separatePosNeg(int arr[], int n);

void printArr(int arr[], int n);
void swap(int array[], int i , int j);
void partition(int array[], int n);

int main() {
    int arr[] = {-7, 9, -3, 6, 0, -1};
    int n = sizeof(arr) / sizeof(arr[0]);
    printArr(arr, n);
    partition(arr, n);
    printArr(arr, n);
    return 0;
}
/**
 * Performs partition in O(N) time
 * Does NOT keep order of appearance
 */
void partition(int array[], int n){
    int currentIndex = 0;
    for(int i = 0; i < n; i++){
        if(array[i] < 0){
            swap(array,i,currentIndex);
            currentIndex += 1;
        }
    }
}
/**
 * Performs partition in O(N^2) time
 * Does keep order of appearance
 */
void separatePosNeg(int arr[], int n) {
    int currentInt, j;
    for (int i = 1; i < n; i++) {
        currentInt = arr[i];

        if (currentInt > 0)
            continue;

        j = i - 1;
        while (j >= 0 && arr[j] >= 0) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = currentInt;
    }

}

void swap(int array [], int i , int j){
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

void printArr(int arr[], int n) {
    for (int i = 0; i < n; i++) {
        printf("[%d] ", arr[i]);
    }
    printf("\n");
}