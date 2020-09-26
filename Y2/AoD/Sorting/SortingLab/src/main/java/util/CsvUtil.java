package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {
    private static final String DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private CsvUtil() {

    }

    public static List<List<String>> read(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                records.add(new ArrayList<>(Arrays.asList(values)));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void write(String fileName, List<List<String>> records) {
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

    public static void appendColumns(String dstFile, List<List<String>> columns) {
        if (!exists(dstFile)) {
            write(dstFile, columns);
        } else {
            for (List<String> column : columns)
                appendColumn(dstFile, column);
        }
    }

    public static boolean appendColumn(String dstFile, List<String> column) {
        List<List<String>> records = read(dstFile);
        if (!column.isEmpty())
            for (int i = 0; i < records.size(); i++) {
                String newColumnCell = column.get(i);
                records.get(i).add(newColumnCell);
            }
        write(dstFile, records);
        return true;
    }


    public static boolean updateColumn(String dstFile, List<String> column, int col) {
        List<List<String>> records = read(dstFile);
        if (records != null && !records.isEmpty())
            if (!column.isEmpty())
                if (col < records.size() && col >= 0) {
                    if (column.size() <= records.size()) {
                        String cell = "";
                        List<String> headers = records.get(0);
                        String columnHeader = column.get(0);
                        if (headers.contains(columnHeader)) {
                            for (int i = 0; i < column.size() - 1; i++) {
                                cell = column.get(i);
                                records.get(i).set(col, cell);
                            }
                        }

                        addColumn(records, column, col);
                    }
                }
        write(dstFile, records);
        return false;
    }

    private static List<String> getColumn(List<List<String>> records, int col) {
        List<String> column = new ArrayList<>();
        for (int i = 0; i < column.size(); i++) {
            column.add(records.get(col).get(i));
        }
        return column;
    }

    private static void addColumn(List<List<String>> records, List<String> column, int col) {
        for (int i = 0; i < records.size(); i++) {
            records.get(i).add(col, column.get(i));
        }
    }

    public static String asString(String filePath) {
        if (exists(filePath)) {
            List<List<String>> records = read(filePath);
            StringBuilder sb = new StringBuilder();
            for (List<String> record : records) {
                sb.append(String.join(DELIMITER, record));
                sb.append("\n");
            }
            return sb.toString();
        }
        return "";
    }

    public static boolean exists(String filePath) {
        if (!isCsv(filePath))
            return false;
        File f = new File(filePath);
        boolean exists = f.isFile();
        return f.exists();
    }

    public static boolean isCsv(String filePath) {
        if (filePath.endsWith(".csv"))
            return true;
        return false;
    }

    public static void deleteRecord(String fileName) {
        File file = new File(fileName);
        file.delete();
    }

    private static boolean hasHeader(String fileName, String header) {
        List<List<String>> records = read(fileName);
        if (records.get(0).contains(header))
            return true;
        return false;
    }


    public static String getPath() {
        File f = new File("temp");
        String absolutePath = f.getAbsolutePath().replace("temp", "");
        return absolutePath;
    }

    public void addRow(List<List<String>> records, List<String> row) {
        int i = 0;
        for (i = 0; i < records.size() - 1; i++) {
            int N = Integer.parseInt(records.get(i).get(0));
            if (N < Integer.parseInt(records.get(i + 1).get(0))) {
                records.add(i, records.get(i + 1));
                return;
            }
        }
        records.add(row);
    }
}
