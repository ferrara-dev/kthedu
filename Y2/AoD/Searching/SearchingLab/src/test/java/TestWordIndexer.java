import datastruct.st.LinkedHashTable;
import Assignment4.indexprogram.WordIndexer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestWordIndexer {
    private WordIndexer wordIndexer;
    @Before
    public void setUp() throws Exception {
        String text = "abc abc a a b";
        wordIndexer = new WordIndexer(new LinkedHashTable<>());
        wordIndexer.loadText(text);
    }

    @Test
    public void testHasWord(){
        boolean has = wordIndexer.hasWord("abc");
        Assert.assertTrue(has);
    }

    @Test
    public void testHasWord2(){
        boolean has = wordIndexer.hasWord("a");
        Assert.assertTrue(has);
    }

    @Test
    public void testHasWord3(){
        boolean has = wordIndexer.hasWord("b");
        Assert.assertTrue(has);
    }

    @Test
    public void testHasWord4(){
        boolean has = wordIndexer.hasWord("c");
        Assert.assertFalse(has);
    }

    @Test
    public void testGetWordIndexes(){
        List<Integer> indexes = wordIndexer.getIndices("abc");
        Assert.assertTrue(!indexes.isEmpty());
    }


    @Test
    public void testGetWordIndexes2(){
        List<Integer> indexes = wordIndexer.getIndices("abc");
        System.out.println(indexes.toString());
        boolean containsIndices = indexes.containsAll(Arrays.asList(0,4));
        Assert.assertTrue(containsIndices);
    }


    @Test
    public void testGetWordIndexes3(){
        List<Integer> indexes = wordIndexer.getIndices("a");
        System.out.println(indexes.toString());
        boolean containsIndices = indexes.containsAll(Arrays.asList(8,10));
        Assert.assertTrue(containsIndices);
    }


    @Test
    public void testGetWordIndexes4(){
        List<Integer> indexes = wordIndexer.getIndices("b");
        System.out.println(indexes.toString());
        boolean containsIndices = indexes.containsAll(Arrays.asList(12));
        Assert.assertTrue(containsIndices);
    }
}
