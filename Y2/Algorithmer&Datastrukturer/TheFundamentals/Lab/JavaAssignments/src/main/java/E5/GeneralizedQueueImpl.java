package E5;

import datastructure.Queue;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class GeneralizedQueueImpl<Item> implements GeneralizedQueue<Item> {
    private int size;
    private Node sentinel;

    public GeneralizedQueueImpl() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * Check if the list is empty.
     * <p>
     * If <code>sentinel.next</code> OR <code>sentinel.prev </code> is pointing
     * back to the <code>sentinel</code> node, it means that the list is empty.
     *
     * @return true if sentinel == sentinel.next OR sentinel == sentinel.prev
     */
    @Override
    public boolean isEmpty() {
        return Objects.equals(sentinel, sentinel.next) || Objects.equals(sentinel, sentinel.prev);
    }

    @Override
    public Item remove(int index) {
        if (index >= 1 && index <= size) {
            if (index == 1)
                return removeFirst();

            else if (index == size)
                return removeLast();

            else {
                Node temp = sentinel;
                for (int i = 0; i < index; i++)
                    temp = temp.next;

                Item item = temp.item;
                remove(temp);
                return item;
            }
        }
        throw new NoSuchElementException("");
    }

    private void remove(Node toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        if (size != 0) {
            size -= 1;
        }
    }

    /**
     * Remove the element currently at the front of the queue
     *
     * @return
     */
    private Item removeFirst() {
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
     */
    private Item removeLast() {
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

    /**
     * dequeue element at the back of the queue
     * <p>
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
        if (size != 0)
            size -= 1;
        if (item != null)
            System.out.println("Dequeue [" + item.toString() + "]");
        print();
        return item;
    }

    /**
     * Print all elements currently in the queue
     */
    @Override
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

    /**
     * Enqueue a new item to the top of the list.
     *
     * @param item
     */
    public void enqueue(Item item) {
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size += 1;
        System.out.println("Enqueue [" + item.toString() + "]");
        print();
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        Node(Item item) {
            this.item = item;
        }

        Node() {

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

    /**
     * Simple unit test of <code> QueueImpl </code> class.
     * <p>
     * Test data is received from a text file containing a sequence of characters.
     * <p>
     * If read string equals '-' dequeue() is called
     * If read string is a digit, it is enqueued by calling enqueue(Item item)
     * The file is read until <code> scanner </code> obj does not have any
     * string left to read
     * <p>
     * The test will also test the iterator implementation.
     */
    public static class Test {
        private Queue<Integer> queue = new GeneralizedQueueImpl<>();

        public static void main(String... args) throws IOException {
            Test test = new Test();
            test.test();
        }

        private void test() {
            GeneralizedQueue<Integer> queue = new GeneralizedQueueImpl<>();
            for (int i = 0; i < 10; i++) {
                queue.enqueue(i);
                if (i == 3) {
                    queue.dequeue();
                }
            }

            queue.remove(5);
            queue.print();
        }

        /**
         * Test method used to iterate through the queue recursively
         *
         * @param iterator
         */
        private void iterate(Iterator<Integer> iterator) {
            if (iterator.hasNext()) {
                int i = iterator.next();
                System.out.println("[ " + i + " ]");
                iterate(iterator);
            } else
                return;
        }
    }
}
