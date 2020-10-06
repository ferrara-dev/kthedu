import list.HashSet;
import org.junit.Test;

public class TestHashSet {
    @Test
    public void testContains(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Aa");
        hashSet.add("BB");
        hashSet.contains("BB");
        hashSet.contains("B");
    }
}
