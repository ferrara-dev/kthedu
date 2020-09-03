package datastructure;

import E3.QueueImpl;
import E6.OrderedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class CircularQueue <Item> implements Queue<Item>{
    protected Node sentinel;

    public CircularQueue() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    /**
     * dequeue element at the back of the queue
     *
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the element at the back of the queue
     */
    @Override
    public Item dequeue() {
        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if(item != null)
            System.out.println("Dequeue [" + item.toString() + "]");
        print();
        return item;
    }

    /**
     * Enqueue a new item to the front-end of the list.
     * @param item
     */
    public void enqueue(Item item) {
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        System.out.println("Enqueue [" + item.toString() + "]");
        print();
    }

    public boolean isSentinel(Node node){
        return Objects.equals(sentinel,node);
    }

    /**
     * Print all elements currently in the queue
     */
    @Override
    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        return toStringRecursive(sentinel.prev, new StringBuilder());
    }

    /**
     * Appends string representation of list <code> Node </code>
     * to <code> StringBuilder </code> object recursively.
     *
     * Base case:
     *  the current node equals the <code>sentinel</code> node
     *
     * @param node The node that is represented as a string
     *
     * @param sb The <code> StringBuilder </code> object that the
     *           string representation is appended to
     *
     * @return a string representation of this queue in FIFO order
     */
    private String toStringRecursive(Node node, StringBuilder sb){
        if(Objects.equals(sentinel,node))
            return sb.toString();
        else {
            return toStringRecursive(node.prev, sb.append("[ " + node.item.toString() + " ] "));
        }
    }


    /**
     * Returns an iterator to this stack that iterates through the items in FIFO order.
     *
     * @return an iterator to this stack that iterates through the items in FIFO order
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(sentinel.prev);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return Objects.equals(current,sentinel);
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    /**
     * Check if the list is empty.
     *
     * If <code>sentinel.next</code> OR <code>sentinel.prev </code> is pointing
     * back to the <code>sentinel</code> node, it means that the list is empty.
     *
     * @return true if sentinel == sentinel.next OR sentinel == sentinel.prev
     */
    @Override
    public boolean isEmpty() {
        return Objects.equals(sentinel,sentinel.next) || Objects.equals(sentinel,sentinel.prev);
    }

    protected class Node {
        Item item;
        Node next;
        Node prev;

        Node() {

        }

        Node(Item item) {
            this.item = item;
        }
    }
}
