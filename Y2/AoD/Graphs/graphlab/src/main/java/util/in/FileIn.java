package util.in;

import datastruct.list.ArrayList;
import datastruct.list.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class FileIn {
    private final Scanner fileScanner;
    private FileInputStream fileInputStream;

    public FileIn(File file) {
        initStream(file);
        fileScanner = new Scanner(fileInputStream);
    }

    public FileIn(String filePath)  {
        initStream(new File(filePath));
        fileScanner = new Scanner(fileInputStream);
    }

    private void initStream(File file){
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileInputException("Something went wrong when reading " + file.toString(), e);
        }
    }

    public ArrayList<String> getRows() {
        ArrayList<String> rows = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            rows.add(fileScanner.nextLine());
        }
        return rows;
    }

    public String nextRow(){
        if(fileScanner.hasNextLine()){
            return fileScanner.nextLine();
        }
        else {
            return null;
        }
    }

    public ArrayList<String> splitRow(){
        if(fileScanner.hasNextLine()){
            return new ArrayList<>(fileScanner.nextLine().split("\\s+"));
        } else {
            return null;
        }
    }

    public boolean hasNextLine(){
        return fileScanner.hasNextLine();
    }
}
