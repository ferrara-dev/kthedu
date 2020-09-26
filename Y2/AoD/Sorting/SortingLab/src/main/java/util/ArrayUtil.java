package util;

import java.util.Arrays;

public class ArrayUtil {
    public static void printArray(int [] array){
        System.out.println(Arrays.toString(array));
    }

    public static int [] ascendingArray(int N){
            int[] a = new int[N];
            for(int i = 0; i < N; i++){
                a[i] = (i+1)*i;
            }
            return a;
    }

    public static int [] descendingArray(int N){
        int[] a = new int[N];
        for(int i = 0; i < N; i++){
            a[i] = (i+1)*i;
        }
        return a;
    }

    public static int [] randomArray(int N, int lo,int hi){
        return StdRandom.randomInts(N,lo,hi);
    }

    public static int [] randomArray(int N){
        return StdRandom.randomInts(N,-1,1);
    }

    public static int [] clone(int a []){
        int [] copy = new int [a.length];
        System.arraycopy(a,0,copy,0,a.length -1);
        return copy;
    }

    public static boolean isSorted(int a []){
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
