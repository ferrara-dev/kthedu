package util.csv;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvUtil {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static List<List<String>> read(String filePath){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void write(String fileName, List<List<String>> records){
        try {
            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

            // write all records
            for (List<String> record : records) {
                writer.write(String.join(",", record));
                writer.newLine();
            }

            //close the writer
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String toCsvFormat(List<List<String>> records){
        StringBuilder sb = new StringBuilder();
        for(List<String> record : records){
            sb.append(String.join(",",record));
            sb.append("\n");
        }
        return sb.toString();
    }
}
