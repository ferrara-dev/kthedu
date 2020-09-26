package recursion.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String ...args){
        Main main = new Main();
        //  main.testCase1();
        //  main.testCase2(args);
        main.testCase3();
    }

    /**
     * Use a predefined array
     */
    private void testCase1(){
        int [] arr = {3,5,6,1,12,56};
        System.out.println(ArrayUtil.findMax(arr));
    }

    /**
     * Take data from commandline args to
     * create the array.
     * @param arr
     */
    private void testCase2(String ...arr){
        int [] array = new int [arr.length];
        for(int i = 0; i < arr.length; i++)
            array[i] = Integer.parseInt(arr[i]);
        System.out.println(ArrayUtil.findMax(array));
    }

    /**
     * Take data from standard input to create the array
     */
    private void testCase3(){
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            int [] arr = ArrayUtil.asArray(bufferedReader.readLine().split(" "));
            System.out.println(ArrayUtil.findMax(arr));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
