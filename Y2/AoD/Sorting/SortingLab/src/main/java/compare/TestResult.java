package compare;

public class TestResult implements Comparable<TestResult> {
    private final String sort;
    private final int problemSize;
    private final double executionTime;

    public TestResult(String sort, int problemSize, double executionTime) {
        this.sort = sort;
        this.problemSize = problemSize;
        this.executionTime = executionTime;
    }

    @Override
    public int compareTo(TestResult other) {
        if (this.executionTime < other.executionTime)
            return -1;
        else if(this.executionTime > other.executionTime)
            return 1;
        else
            return 0;
    }

    public String toString(){
        return sort.toString() + " N=" + problemSize + "  -->" + " : " + executionTime;
    }
}
