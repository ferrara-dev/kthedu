public class Main {

    public static void main(String...args){
        System.out.println("Input integers to be sorted");
        Integer [] arr = IOUtil.readIntArray();
        printArr(arr);
        InsertionSort.InsertionSort(arr);
        printArr(arr);
    }

    private static void printArr(Comparable [] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i].toString());
            sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
