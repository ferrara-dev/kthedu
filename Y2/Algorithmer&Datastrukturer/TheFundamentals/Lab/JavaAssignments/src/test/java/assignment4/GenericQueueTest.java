package assignment4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericQueueTest {
    GenericQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new GenericQueue<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullFirst(){
        queue.addFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullLast(){
        queue.addLast(null);
    }

    @Test
    public void testAddLast(){
        queue.addLast(1);
        queue.addLast(2);
        queue.addFirst(3);
        int lastElement = queue.removeLast();
        Assert.assertEquals(2,lastElement);
    }

    @Test
    public void testAddFirst(){
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addLast(5);
        int firstElement = queue.removeFirst();
        Assert.assertEquals(2,firstElement);
    }


}
