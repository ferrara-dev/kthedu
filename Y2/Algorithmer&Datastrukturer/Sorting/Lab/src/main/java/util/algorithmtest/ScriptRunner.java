package util.algorithmtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InterpreterExample
{
    public static void main (String...args) throws IOException {
        System.out.println(getPath());
    }

    public static String getPath(){
        File f = new File("temp");
        String absolutePath = f.getAbsolutePath().replace("temp","");
        return absolutePath;
    }
} 