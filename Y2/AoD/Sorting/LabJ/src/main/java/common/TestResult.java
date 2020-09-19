package common;


import java.util.*;

public class TestResult {
    private String description;
    private List<List<String>> results = new ArrayList<>();
    private List<String> headers = new ArrayList<>();
    private int len;

    public TestResult() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void add(List<String> res) {
            results.add(res);
    }

    public List<List<String>> getResults() {
        return results;
    }

    public void addHeader(String header) {
        headers.add(header);
        len = headers.size();
    }

    public List<String> getHeaders() {
        return headers;
    }
}
