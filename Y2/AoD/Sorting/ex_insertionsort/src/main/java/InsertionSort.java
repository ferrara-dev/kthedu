public class InsertionSort {

    public static void InsertionSort(Comparable [] arr){
        int i = 1;
        int j;
        int n = arr.length;
        while(i < n){
            j = i - 1;
            Comparable current = arr[i];
            while (j >= 0 && largerThan(arr[j], current)){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = current;
            i += 1;
        }
    }

    private static void swap(Comparable [] arr, int j){
        Comparable temp = arr[j-1];
        arr[j-1] = arr[j];
        arr[j] = temp;
    }


    private static boolean lessThan(Comparable dis, Comparable dat){
        return (dis.compareTo(dat) < 0);
    }

    private static boolean lessOrEquals(Comparable dis, Comparable dat){
        return dis.compareTo(dat) <= 0;
    }

    private static boolean largerThan(Comparable dis, Comparable dat){
        return dis.compareTo(dat) > 0;
    }

    private static boolean largerOrEquals(Comparable dis, Comparable dat){
        return dis.compareTo(dat) >= 0;
    }

}
