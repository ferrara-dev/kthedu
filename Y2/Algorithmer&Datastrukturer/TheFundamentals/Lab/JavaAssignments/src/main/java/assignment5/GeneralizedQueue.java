package assignment5;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Solution to assignment 5
 * author : Samuel Ferrara spof@kth.se
 * Implementation of a generalized queue that allows elements to be removed
 * at a specific index.
 * <p>
 * The enqueue operation will add a new item to the first position (index = 1)
 * <p>
 * The dequeue operation will remove the item that was least recently enqueued (index = size)
 * <p>
 * The remove(index) will remove the element that is currently located at the given index.
 *
 * @param <Item>
 */
public class GeneralizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Node sentinel;
    private boolean print = true;

    public GeneralizedQueue() {
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
    public boolean isEmpty() {
        return Objects.equals(sentinel, sentinel.next) || Objects.equals(sentinel, sentinel.prev);
    }

    /**
     * Method to remove and return element of given index.
     * If given index equals to 1, the first element is removed immediately
     *
     * If given index equals to the number of elements, the last element is removed immediately
     *
     * If given index is larger that half the number of elements, the nodes will be traversed backwards
     * until the index is reached.
     *
     * else the nodes will be traversed forwards until the index is reached.
     *
     * @param index
     *
     * @return <code> Item </code> located at index given by param.
     * @throws NoSuchElementException If list is empty or given index is larger than number of elements
     *
     */
    public Item remove(int index) {
        if (index >= 1 && index <= size) {
            Item item;
            if (index == 1) {
                item = sentinel.next.item;
                remove(sentinel.next);
            } else if (index == size) {
                item = sentinel.prev.item;
                remove(sentinel.prev);
            } else {
                Node temp;
                int midSize = size/2;
                if (index > midSize)
                    temp = traverseBackwards(size - index);
                else
                    temp = traverseForwards(index);
                item = temp.item;
                remove(temp);
            }
            if (print) {
                System.out.println("removed [ " + item.toString() + " ] at index " + index);
                print();
            }
            return item;
        }
        throw new NoSuchElementException("index " + index + " is out of bounds");
    }

    private Node traverseBackwards(int index) {
        Node temp = sentinel.prev;
        for (int i = 0; i < index; i++)
            temp = temp.prev;
        return temp;
    }

    private Node traverseForwards(int index) {
        Node temp = sentinel;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp;
    }

    private void remove(Node toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        if (size != 0) {
            size -= 1;
        }
    }

    private Item removeLastItem() {
        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (size != 0) {
            size -= 1;
        }
        return item;
    }

    /**
     * dequeue element at the back of the queue
     * <p>
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the back of the queue
     */
    public Item dequeue() {
        Item item = removeLastItem();
        if (print) {
            if (item != null)
                System.out.println("Dequeue [" + item.toString() + "]");
            print();
        }
        return item;
    }


    /**
     * Enqueue a new item to the front of the list.
     *
     * @param item
     */
    public void enqueue(Item item) {
        if(item == null)
            throw new IllegalArgumentException();
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size += 1;
        if (print) {
            System.out.println("Enqueue [" + item.toString() + "]");
            print();
        }
    }

    /**
     * Print all elements currently in the queue
     * Prints the most recently added element first.
     */
    public void print() {
        System.out.println(toString());
    }


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
     * @return a string representation of this queue in FIFO order
     */
    private String toStringRecursive(Node node, StringBuilder sb) {
        if (Objects.equals(sentinel, node))
            return sb.toString();
        else {
            return toStringRecursive(node.next, sb.append("[ " + node.item.toString() + " ] "));
        }
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
     * Simple unit test of <code> Queue </code> class.
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
        private GeneralizedQueue<Integer> queue = new GeneralizedQueue();

        public static void main(String... args) throws IOException {
            Test test = new Test();
            test.test();
        }

        private void test() {
            GeneralizedQueue<Integer> queue = new GeneralizedQueue<>();
            for (int i = 0; i < 10; i++) {
                queue.enqueue(i);
                if (i == 3) {
                    queue.dequeue();
                }
            }

            queue.remove(4);
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
