package util;

import java.util.*;

public class CSVFile<T> {
    private List<List<String>> content;
    private int columns = 0;
    private int rows = 0;

    public CSVFile() {
        this.content = new ArrayList<>();
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    public void setHeaders(List<String> headers) {
        if (headers.isEmpty())
            content.add(headers);
        else content.set(0, headers);
    }
}
