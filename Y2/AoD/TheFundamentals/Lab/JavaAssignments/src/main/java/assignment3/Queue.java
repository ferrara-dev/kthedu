package assignment3;

import util.StdRandom;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 *
 * Solution to assignment 3
 *
 * @author Samuel Ferrara 19940412-1395
 * Doubly linked circular list Implementation of a generic queue enforcing
 * the First In First Out policy.
 * <p>
 * The doubly linked list is implemented using an empty sentinel node to denote the
 * start / beginning of the list.
 * <p>
 * enqueue --> [x]->[x][x][x][x]
 * [x][x][x][x]->[x] --> dequeue
 */
public class Queue<Item> implements Iterable<Item> {
    private Node sentinel;
    private boolean print = true;

    public Queue() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void disablePrint() {
        print = false;
    }

    /**
     * dequeue element at the back-end of the queue
     * <p>
     * The item that was added least recently will be returned
     * and removed from the queue.
     * <p>
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the element at the back of the queue
     */
    public Item dequeue() {
        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (print) {
            if (item != null)
                System.out.println("Dequeue [" + item.toString() + "]");
            print();
        }
        return item;
    }

    /**
     * Enqueue a new item to the front-end of the queue.
     * <p>
     * The <code>sentinel</code> node will have its
     * <code> next </code> reference to the element
     * that was most recently enqueued with this method
     * and the <code> prev </code> reference to the element
     * that was least recently enqueued with this method.
     *
     * @param item
     */
    public void enqueue(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next.prev = node;
        sentinel.next = node;
        if (print) {
            System.out.println("Enqueue [" + item.toString() + "]");
            print();
        }
    }

    /**
     * Print all elements currently in the queue
     */
    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return toStringRecursive(sentinel.prev, new StringBuilder());
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
     * @return a string representation of this queue in FIFO order
     */
    private String toStringRecursive(Node node, StringBuilder sb) {
        if (Objects.equals(sentinel, node))
            return sb.toString();
        else {
            return toStringRecursive(node.prev, sb.append("[ " + node.item.toString() + " ] "));
        }
    }

    private boolean isSentinel(Node node) {
        return Objects.equals(sentinel, node);
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
            boolean hasNext = Objects.equals(current,sentinel) || !Objects.equals(current.next,sentinel);
            return !Objects.equals(current, sentinel);
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
     * <p>
     * If <code>sentinel.next</code> OR <code>sentinel.prev </code> is pointing
     * back to the <code>sentinel</code> node, it means that the list is empty.
     *
     * @return true if sentinel == sentinel.next OR sentinel == sentinel.prev
     */
    public boolean isEmpty() {
        return isSentinel(sentinel.next);
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

    /**
     * Simple unit test of <code> Queue </code> class.
     * <p>
     * Test data is received from a text file containing a sequence of characters.
     * <p>
     *
     * If read string equals '-' dequeue() is called
     * If read string equals '+' a random integer is enqueued
     * The file is read until <code> scanner </code> obj does not have any
     * string left to read
     * <p>
     * The test will also test the iterator implementation.
     */
    public static class Test {
        private Queue<Integer> queue = new Queue<>();

        public static void main(String... args) throws IOException {
            Test test = new Test();
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(" ");
            test.test(input, 0);
            test.testIter();
        }

        private void test(String[] input, int n){
            if (n == input.length)
                return;
            if (input[n].equals("+")) {
                queue.enqueue(StdRandom.uniform(-10,10));
            }
            else if (input[n].equals("-")) {
                queue.dequeue();
            }
            test(input, n + 1);
        }

        private void testIter() {
            System.out.println("::::::::::::::: Testing iterator :::::::::::::::");
            System.out.print("Current queue state is : ");
            for (Integer i : queue)
                System.out.print("[ " + i + " ] ");
        }
    }
}
