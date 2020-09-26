package assignment3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.StdRandom;

public class QueueTest {
    Queue <Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();
    }

    /**
     * Test the FIFO-policy
     *
     * Assert that the element that was least recently enqueued is
     * the first to get removed on dequeue.
     */
    @Test
    public void testFIFOPolicy(){
        int firstItem = 1;
        queue.enqueue(firstItem);
        for(int i = 0; i < 3; i++){
            int nextItem = StdRandom.uniform(2,10);
            queue.enqueue(nextItem);
        }
        int dequeueItem = queue.dequeue();
        Assert.assertEquals(firstItem, dequeueItem);
    }

    @Test
    public void testIsEmpty(){
        boolean isEmpty = queue.isEmpty();
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void testIsEmpty2(){
        queue.enqueue(2);
        boolean notEmpty = queue.isEmpty();
        for(int i = 0; i < 5; i++){
            queue.enqueue(StdRandom.uniform(0,5));
        }
        boolean isNotEmpty = queue.isEmpty();

        Assert.assertEquals(notEmpty,isNotEmpty);
    }


    /**
     * Test enqueue of null element
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullEnqueue(){
        queue.enqueue(null);
    }

    /**
     * Test if null is returned when performing dequeue on empty Queue
     */
    @Test
    public void testEmptyDequeue(){
        Assert.assertNull(queue.dequeue());
    }

}
