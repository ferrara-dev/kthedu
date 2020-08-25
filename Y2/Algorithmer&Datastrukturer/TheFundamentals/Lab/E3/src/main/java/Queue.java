import java.util.Iterator;

public class Queue <Item> implements Iterable<Item> {
    private Node first;    // beginning of queue
    private Node last;     // end of queue
    private int n;               // number of elements on queue

    public Queue(){

    }

    public void enqueue(Item item){

    }

    public boolean isEmpty(){
        return false;
    }

    public Iterator<Item> iterator() {
        return null;
    }

    private class Node{
        Item item;
        Node next;

        Node(){

        }
    }
}
