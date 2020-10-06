package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Solution to Assignment 6
 *
 * Doubly linked list implementation of ordered queue.
 *
 * The element with the highest value is returned first
 * on dequeue operation.
 *
 */
public class OrderedQueue implements Iterable<Integer> {
    private Node sentinel;

    public OrderedQueue() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Enqueue an item to the ordered queue.
     * All elements are inserted in ascending order.
     * <p>
     * In case list is empty, the element is simply added after
     * the sentinel element.
     * <p>
     * Before starting to traverse the list until an element that is smaller
     * is found, the method checks if the <code> item </code> that is to
     * be added is larger than the element currently in the back of the
     * queue.
     *
     * @param item that is added before
     *
     * @throws IllegalArgumentException if param <code> item </code> is null
     */
    public void enqueue(Integer item) {
        if(item == null)
            throw new IllegalArgumentException();

        Node node = new Node(item);
        if (isEmpty())
            addBefore(node, sentinel.next);
        else if (lessOrEquals(sentinel.prev.item, item))
            addBefore(node, sentinel);
        else{
            add(sentinel.next, item);
        }

        System.out.println("Enqueue [" + item.toString() + "]");
        print();
    }

    /**
     * Traverses the list nodes recursively and checks
     * if the value stored in the current node is less than
     * the value of <code> item </code>
     *
     * @param current
     * @param item
     */
    private void add(Node current, Integer item) {
        if(!isSentinel(current)) {
            if (item.compareTo(current.item) < 0) {
                Node toAdd = new Node(item);
                addBefore(toAdd, current);
            } else {
                add(current.next, item);
            }
        }
    }

    /**
     * Add a node before the node given by the second param <code> current </code>
     *
     * @param toAdd   the node that is to be added
     * @param current the node that <code> toAdd </code> is to be added in front of
     */
    private void addBefore(Node toAdd, Node current) {
        toAdd.next = current;
        toAdd.prev = current.prev;
        toAdd.next.prev.next = toAdd;
        toAdd.next.prev = toAdd;
    }

    /**
     * dequeue element with the highest value
     *
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item currently at the back of the queue
     */
    public Integer dequeue() {
        Integer item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if(item != null)
            System.out.println("Dequeue [" + item.toString() + "]");
        print();
        return item;
    }

    public void print() {
        System.out.println(toString());
    }

    /**
     * Check if the list is currently empty
     *
     * If <code> next </code> or <code> prev </code> in the sentinel
     * element is pointing back to itself, the list is considered empty.
     * @return
     */
    public boolean isEmpty() {
        return Objects.equals(sentinel, sentinel.next) || Objects.equals(sentinel, sentinel.prev);
    }

    @Override
    public String toString() {
        return toStringRecursive(sentinel.next, new StringBuilder());
    }

    /**
     * Appends string representation of list <code> Node </code>
     * to <code> StringBuilder </code> object recursively.
     * <p>
     * Base case:
     * the current node equals the <code>sentinel</code> node
     *
     * @param node The node that is represented as a string
     * @param sb   The <code> StringBuilder </code> object that the
     *             string representation is appended to
     *
     * @return a string representation of this queue
     */
    private String toStringRecursive(Node node, StringBuilder sb) {
        if (isSentinel(node))
            return sb.toString();
        else {
            return toStringRecursive(node.next, sb.append("[ " + node.item.toString() + " ] "));
        }
    }

    private boolean isSentinel(Node node) {
        return Objects.equals(sentinel, node);
    }

    private boolean lessOrEquals(Integer thisItem, Integer thatItem) {
        if (thisItem.compareTo(thatItem) <= 0)
            return true;
        return false;
    }
    /**
     * Returns an iterator to this stack that iterates through the items in FIFO order.
     *
     * @return an iterator to this stack that iterates through the items in FIFO order
     */
    public Iterator<Integer> iterator() {
        return new LinkedIterator(sentinel.next);
    }

    private class LinkedIterator implements Iterator<Integer> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return !Objects.equals(current, sentinel);
        }

        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Integer item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String... args) {
        OrderedQueue queue = new OrderedQueue();
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.dequeue();
        queue.enqueue(12);
        queue.enqueue(12);
        queue.enqueue(0);
        queue.enqueue(-35);
        queue.enqueue(4);
        queue.dequeue();


    }

    private class Node {
        Integer item;
        Node next;
        Node prev;

        Node() {

        }

        Node(Integer item) {
            this.item = item;
        }

        boolean lessOrEquals(Integer item) {
            if (this.item.compareTo(item) <= 0)
                return true;
            return false;
        }

    }
}