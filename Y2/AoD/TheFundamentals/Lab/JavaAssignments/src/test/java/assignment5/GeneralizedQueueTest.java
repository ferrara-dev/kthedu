package assignment5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.StdRandom;

public class GeneralizedQueueTest {
    GeneralizedQueue queue;
    @Before
    public void setUp() throws Exception {
        queue = new GeneralizedQueue<Integer>();
    }

    @Test
    public void testRemoveLast(){
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        int lastElement = (int) queue.remove(3);
        Assert.assertEquals(2, lastElement);
    }

    @Test
    public void testRemoveFirst(){
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(4);
        int firstElement = (int) queue.remove(1);
        Assert.assertEquals(4,firstElement);
    }

    /**
     * Ensure that index 1 is the next node to
     * be removed with dequeue
     */
    @Test
    public void testRemoveFirst2(){
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(4);
        int firstElement = (int) queue.remove(1);
        Assert.assertEquals(4,firstElement);
    }

    @Test
    public void testRemoveAt(){
        for(int i = 0; i < 10; i++){
            if(i == 4){
                queue.enqueue(99);
                continue;
            }
            queue.enqueue(StdRandom.uniform(-5,5));

        }

        int elementAt = (int) queue.remove(6);
        Assert.assertEquals(99,elementAt);
    }

    @Test
    public void testRemoveAt2(){
        for(int i = 0; i < 10; i++){
            if(i == 6){
                queue.enqueue(99);
                continue;
            }
            queue.enqueue(StdRandom.uniform(-5,5));

        }

        int elementAt = (int) queue.remove(4);
        Assert.assertEquals(99,elementAt);
    }

    @Test
    public void testRemoveAt3(){
        for(int i = 0; i < 20; i++){
           queue.enqueue(i);
        }

        int elementAt = (int) queue.remove(4);
        Assert.assertEquals(16,elementAt);
    }


    @Test
    public void testRemoveAt4(){
        for(int i = 0; i < 20; i++){
            queue.enqueue(i);
        }

        int elementAt = (int) queue.remove(15);
        Assert.assertEquals(5,elementAt);
    }
}
