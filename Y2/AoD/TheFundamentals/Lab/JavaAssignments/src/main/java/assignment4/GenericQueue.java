package assignment4;

import assignment3.Queue;
import util.Command;
import util.StdRandom;
import util.TestUtil;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Solution to assignment 4
 *
 * Implementation of generic iterable circular linked list which allows the user to insert and remove
 * elements to/from the front and back end of the queue.
 * @param <Item>
 */
public class GenericQueue<Item> implements Iterable<Item> {
    private int size;
    private Node sentinel;

    public GenericQueue() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Check if the list is currently empty.
     * If the size of the list is 0, the list is empty
     *
     * @return true if <code> int size </code> is currently 0
     */
    public boolean isEmpty() {
        return Objects.equals(sentinel, sentinel.next);
    }

    /**
     * Add a new element to the front of the list.
     *
     * @param item
     * @throws IllegalArgumentException if param <code> Item </code> is null.
     */
    public void addFirst(Item item) {
        checkNullArgument(item);
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size += 1;
        System.out.println("Adding [" + item.toString() + "] first");
        print();
    }

    /**
     * Method to add an element to the back-end of the queue.
     * @param item the item that is added to the list
     * @throws IllegalArgumentException if param <code> Item </code> is null.
     */
    public void addLast(Item item) {
        checkNullArgument(item);
        Node node = new Node(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
        System.out.println("Adding [" + item.toString() + "] last");
        print();
    }

    private void checkNullArgument(Item item){
        if(Objects.isNull(item))
            throw new IllegalArgumentException("Null items can not be added to the queue");
    }
    /**
     * Remove the element currently at the front of the queue
     *
     * @return
     */
    public Item removeFirst() {
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size != 0) {
            size -= 1;
        }
        if (item != null)
            System.out.println("Removing first element [" + item.toString() + "]");
        print();
        return item;
    }

    /**
     * Remove the element currently at the end of the queue
     *
     * @return <code> Item item </code> contained by the node
     * currently at the back of the queue.
     *
     * @return the item that is currently last OR null if the
     *         list is empty.
     */
    public Item removeLast() {
        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (size != 0) {
            size -= 1;
        }

        if (item != null)
            System.out.println("Removing last element [" + item.toString() + "]");
        print();
        return item;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return toString(sentinel.next, new StringBuilder());
    }

    private String toString(Node n, StringBuilder sb) {
        if (Objects.equals(n, sentinel))
            return sb.toString();

        return toString(n.next, sb.append("[ " + n.item + " ] "));
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
            return Objects.equals(current, sentinel);
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.prev;
            return item;
        }
    }

    public class Node {
        Node next = null;
        Node prev = null;

        Item item;

        Node() {

        }

        Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Simple unit test
     */
    private static class Test {
        private GenericQueue<Integer> queue = new GenericQueue<>();
        private Scanner scanner = new Scanner(System.in);

        public static void main(String... args) {
            Test test = new Test();
            test.test();
        }


        private void test() {
            Queue<String> commands = TestUtil.instructions();
            for (String cmd : commands) {
                if (cmd.equals(Command.ADD_FIRST.instr))
                    queue.addFirst(StdRandom.uniform(-100, 100));
                else if (cmd.equals(Command.ADD_LAST.instr))
                    queue.addLast(StdRandom.uniform(-100, 100));
                else if (cmd.equals(Command.REMOVE_FIRST.instr))
                    queue.removeFirst();
                else if (cmd.equals(Command.REMOVE_LAST.instr))
                    queue.removeLast();
            }
        }

    }
}
