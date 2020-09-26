import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import src.LIFOLinkedList;
import src.LinkedList;

public class TestLifo {
    private LinkedList<Integer> linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LIFOLinkedList();
    }

    @Test
    public void testAdd(){
        Integer e1 = 1;
        linkedList.insert(e1);
        assertEquals(e1, e1);
    }

    @Test
    public void testRemove_1(){
        Integer e1 = 1;
        linkedList.insert(e1);
        Integer e3 = 3;
        linkedList.insert(e3);
        Integer e2 = linkedList.get();
        assertEquals(e2, e3);
    }

    @Test
    public void testIsEmpty(){
        Integer e1 = 1;
        linkedList.insert(e1);
        boolean isNotEmpty = linkedList.isEmpty();
        Integer e2 = linkedList.get();
        boolean isEmptyAgain = linkedList.isEmpty();
        assertNotEquals(isNotEmpty, isEmptyAgain);
    }

    @Test
    public void testToString(){
        linkedList.insert(4);
        linkedList.insert(5);
        linkedList.print();
        linkedList.insert(34);
        linkedList.get();
        linkedList.print();
    }

    @Test
    public void testInsertAscending(){
        linkedList.insertAscending(3);
        linkedList.insertAscending(5);
        linkedList.insertAscending(2);
        linkedList.insertAscending(0);
    }


}
