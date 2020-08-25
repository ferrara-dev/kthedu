package recursion.max;

public class ArrayUtil {
    public static int [] asArray(String ...strings){
        return asArray(0, new int[strings.length], strings);
    }

    private static int [] asArray(int index, int [] arr, String...strings){
        if(index < arr.length){
            arr[index] = Integer.parseInt(strings[index]);
            return asArray(index + 1, arr,strings);
        }
        else
            return arr;
    }


    public static int findMax(int array[]){
        return findMax(array, 0, array[0]);
    }

    /**
     * Find the largest integer in an array recursively
     * @param array the array that is searched
     * @param index of the current element
     * @param max the element that is currently considered the largest
     * @return
     */
    private static int findMax(int array[], int index, int max){
        if(index < array.length){
            if(max < array[index])
                max = array[index];

            return findMax(array, index +1, max);   // recursi
        }

        else{
            return max;
        }
    }
}
