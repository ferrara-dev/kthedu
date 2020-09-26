import assignment1.InsertionSort;
import assignment3.BruteForceCount;
import assignment3.MergeCount;
import mergesort.MergeSort;
import org.junit.Before;
import org.junit.Test;
import util.algorithmtest.AlgorithmTester;
import util.stopwatch.StopwatchUnit;

public class TestCommon {
    private AlgorithmTester algorithmTester;
    @Before
    public void setUp(){
        this.algorithmTester = new AlgorithmTester();
    }

    @Test
    public void testSorts(){
        algorithmTester.setUpTest(new InsertionSort(false), new MergeSort(false));
        algorithmTester.runAlgorithmTest();
    }

    @Test
    public void testInversionCounts(){
        algorithmTester.setUpTest(new BruteForceCount(false), new MergeCount(false));
        algorithmTester.setStopwatchUnit(StopwatchUnit.NANO);
        algorithmTester.runAlgorithmTest();
    }
}
