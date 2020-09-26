package util.csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFile {
    private Path file = Paths.get("./src/main/resources/the-file-name.csv");
    private String delimiter = ";";
    private int nrOfColumns;
    private int nrOfRows;
    private List<String> headers;
    private List<String> rows;

    public CsvFile(Path file) {
        this.file = file;
        headers = new ArrayList<>();
        rows = new ArrayList<>();
    }


    public void write() {
        try {
            Files.write(file, rows, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setHeaders(String... heads) {
        nrOfColumns = heads.length;
        for (String header : heads)
            headers.add(header);
    }

    public void setHeaders(List<String> heads) {
        nrOfColumns = heads.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nrOfColumns - 1; i++) {
            sb.append(heads.get(i)+delimiter);
        }
        sb.append(heads.get(nrOfColumns-1));
        rows.add(0,sb.toString());
    }

    public void addRow(List<String> row) {
        StringBuilder rowBuilder = new StringBuilder();
        for (int i = 0; i < nrOfColumns - 1; i++) {
            if (i > row.size())
                break;
            rowBuilder.append(row.get(i) + delimiter);
        }
        if (row.size() <= nrOfColumns)
            rowBuilder.append(row.get(nrOfColumns - 1));
        rows.add(rowBuilder.toString());
    }
}
