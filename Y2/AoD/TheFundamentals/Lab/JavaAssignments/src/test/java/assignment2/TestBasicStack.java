package assignment2;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TestBasicStack {
    BasicStack<Integer> stack = new BasicStack<>();

    @Test(expected = IllegalArgumentException.class)
    public void testPushNull(){
        stack.push(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmpty(){
        stack.pop();
    }

    @Test
    public void testPeekEmpty(){
        Integer integer = stack.peek();
        Assert.assertNull(integer);
    }

    @Test
    public void testIsEmpty(){
        stack.push(1);
        stack.pop();
        boolean empty = stack.isEmpty();
        Assert.assertTrue(empty);
    }

}
