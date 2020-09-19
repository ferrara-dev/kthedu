package common;

public interface InversionCount {
    long count(int a[]);

    default String inversionToString(int i, int j, int[] a){
        return "[" + i + "," + a[i] + "]" + ", " + "[" + j + "," + a[j] + "]";
    }
}
