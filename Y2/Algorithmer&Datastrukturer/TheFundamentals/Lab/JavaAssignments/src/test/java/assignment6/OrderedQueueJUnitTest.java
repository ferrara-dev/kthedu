package assignment6;

import org.junit.Assert;
import org.junit.Test;
import util.StdRandom;

public class OrderedQueueJUnitTest {
    OrderedQueue orderedQueue = new OrderedQueue();

    @Test(expected = IllegalArgumentException.class)
    public void testNullArgument(){
        orderedQueue.enqueue(null);
    }

    @Test
    public void testAdd(){
        for(int i = 0; i < 15; i++){
            orderedQueue.enqueue(StdRandom.uniform(-15,15*i));
        }
    }

    @Test
    public void testIterator(){
        for(int i = 0; i < 15; i++){
            orderedQueue.enqueue(StdRandom.uniform(-15,15));
        }
        for(Integer i: orderedQueue)
            System.out.print("[ " + i + " ] ");
    }

    @Test
    public void testRemoveEmpty(){
        Integer integer = orderedQueue.dequeue();
        Assert.assertNull(integer);
    }


}
