package util;

import common.AlgorithmTest;
import common.Sort;
import common.TestResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortingTest implements AlgorithmTest<Sort> {
    private String testDescription;
    private int lowerProblemSize;
    private int upperProblemSize;
    private Set<Sort> algorithms;
    private List<TestResult> results;
    public static void main(String...args){

    }

    public SortingTest(String testDescription, Sort...sorts){
        this.testDescription = testDescription;
        algorithms = new HashSet<>();
        for(Sort sort: sorts)
            algorithms.add(sort);

        results = new ArrayList<>();

    }

    @Override
    public List<Sort> getAlgorithms() {
        return new ArrayList<>(algorithms);
    }

    @Override
    public List<TestResult> getResult() {
        return results;
    }

    @Override
    public void addResult(TestResult...testResults) {
        for(TestResult testResult: testResults)
            results.add(testResult);
    }


    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setProblemRange(int lo, int hi) {
        this.lowerProblemSize = lo;
        this.upperProblemSize = hi;
    }
}
