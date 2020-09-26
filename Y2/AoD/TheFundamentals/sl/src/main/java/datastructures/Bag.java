package datastructures;

import java.util.Iterator;

public class Bag<Item> extends LinkedList<Item>{
    void add(Item item){
        Node node = new Node(item);
        node.next = head.next;
        head = node;
        size += 1;
    }
    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
