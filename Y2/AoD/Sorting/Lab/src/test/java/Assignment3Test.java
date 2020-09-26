import assignment3.MergeCount;
import assignment3.in.Inversions;
import org.junit.Assert;
import org.junit.Test;
import util.StdRandom;

public class Assignment3Test {

    @Test
    public void testInversions(){
        for(int i = 0; i < 1000000; i++){
            int [] a = StdRandom.randomInts(6,-5,5);
            Assert.assertEquals(Inversions.count(a), new MergeCount().count(a));
        }
    }
}
