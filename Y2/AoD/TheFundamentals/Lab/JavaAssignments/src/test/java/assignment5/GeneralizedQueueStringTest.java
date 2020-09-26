package assignment5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.StdRandom;

import java.util.NoSuchElementException;

public class GeneralizedQueueStringTest {
    GeneralizedQueue<String> queue;
    @Before
    public void setUp() throws Exception {
        queue = new GeneralizedQueue<String>();
    }

    @Test
    public void testRemoveLast(){
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        String lastElement = queue.remove(3);
        Assert.assertEquals("a", lastElement);
    }

    @Test
    public void testRemoveFirst(){
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        String firstElement =  queue.remove(1);
        Assert.assertEquals("c",firstElement);
    }

    /**
     * Ensure that the last element is the next node to
     * be removed with dequeue
     */
    @Test
    public void testDequeue(){
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        String lastElement = queue.dequeue();
        Assert.assertEquals("a",lastElement);
    }

    @Test
    public void testRemoveAt(){
        for(int i = 0; i < 10; i++){
            if(i == 4){
                queue.enqueue("a");
                continue;
            }
            queue.enqueue(StdRandom.randomString(5));
        }

        String elementAt =  queue.remove(6);
        Assert.assertEquals("a",elementAt);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveAtNegative(){
        try{
            queue.remove(-5);
        } catch (NoSuchElementException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }
}
