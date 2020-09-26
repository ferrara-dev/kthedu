package util;

import java.util.ArrayList;

public class ArrayUtil {

    private ArrayUtil(){

    }

    public static int [] clone(int [] src){
        int [] clone = new int[src.length];
        for(int i = 0; i < clone.length; i++)
            clone[i] = src[i];
        return src;
    }
}
