package assignment1;

import common.Sort;

import java.util.Arrays;

public class OptimizedInsertionSort implements Sort {

    public OptimizedInsertionSort(){

    }

    @Override
    public void sort(int[] arr) {
        int i = 1;
        int j;
        int n = arr.length;
        while(i < n){
            j = i - 1;
            int current = arr[i];
            while (j >= 0 && arr[j] > current){
                arr[j+1] = arr[j];
                j--;

            }
            arr[j+1] = current;
            i += 1;
        }
    }

    private void printArr(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
