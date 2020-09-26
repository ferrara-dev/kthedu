package util.algorithmtest;

import java.util.*;

public class AlgorithmTestResult {
    private List<String> headers;
    private List<List<String>> rows;
    private Map<Object, Map<Integer,Double>> algorithmExecutionTimeMap;

    AlgorithmTestResult(){
        headers = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public void setHeaders(List<String> headers){
        this.headers = headers;
    }

    public void addHeader(String header){
        headers.add(header);
    }

    public void addRow(List<String> row){
        rows.add(row);
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
