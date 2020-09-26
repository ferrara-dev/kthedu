package util.algorithmtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScriptRunner
{
    public static void main (String...args) throws IOException {
        String scriptPath = getPath() + "src/main/resources/plot_script.py";
        String pyPath = "/Users/root/AppData/Local/Programs/Python/Python38/python.exe";
        String alt1 = scriptPath.replace("/", "\\");
        String alt2 = pyPath.replace("/","\\");
        System.out.println(alt1);
        Process process = Runtime.getRuntime().exec(
                new String[]{alt2, alt1});

        System.out.println(process.isAlive());
        process.destroy();
        System.out.println(process.isAlive());
    }

    public static String getPath(){
        File f = new File("temp");
        String absolutePath = f.getAbsolutePath().replace("temp","");
        return absolutePath;
    }
} 