package common;

import java.util.List;

public interface AlgorithmTest<T>{
    List<T> getAlgorithms();
    List<TestResult> getResult();
    void addResult(TestResult...testResult);
    String getDescription();
    void setProblemRange(int lo, int hi);
}
