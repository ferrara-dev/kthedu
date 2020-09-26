package stack;

import datastructures.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPush {
    private Stack<Integer> integerStack;
    @Before
    public void setUp() throws Exception {
        integerStack = new Stack<>();
    }

    @Test
    public void testPush_1(){
        integerStack.push(1);
        integerStack.print();
        integerStack.push(2);
        integerStack.print();
        integerStack.push(3);
        integerStack.print();
        assertFalse(integerStack.isEmpty());
    }

    @Test
    public void testPush_2(){
        integerStack.push(1);
        integerStack.print();
        integerStack.push(2);
        integerStack.print();
        integerStack.push(3);
        integerStack.print();
        assertEquals(integerStack.size(), 3);
    }

}
