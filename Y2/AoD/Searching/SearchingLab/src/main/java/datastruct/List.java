package datastruct;


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
public class List<Item> implements Iterable<Item> {
    private int size;
    private Node sentinel;

    public List() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public int size() {
        return size;
    }

    public List(Item[] items) {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (Item item : items) {
            this.add(item);
        }
    }

    public boolean contains(Item item) {
        for (Item item1 : this)
            if (item1.equals(item))
                return true;
        return false;
    }

    public static List listOf(Object... items) {
        List lst = new List();
        for (Object item : items) {
            lst.add(item);
        }
        return lst;
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
     * <p>
     * If given index equals to the number of elements, the last element is removed immediately
     * <p>
     * If given index is larger that half the number of elements, the nodes will be traversed backwards
     * until the index is reached.
     * <p>
     * else the nodes will be traversed forwards until the index is reached.
     *
     * @param index
     *
     * @return <code> Item </code> located at index given by param.
     *
     * @throws NoSuchElementException If list is empty or given index is larger than number of elements
     */
    public Item delete(int index) {
        return get(index,true);
    }

    /**
     * Get item at <code> index </code> without removing
     * the element.
     * @param index
     * @return
     */
    public Item get(int index){
        return get(index,false);
    }

    private Item get(int index, boolean getAndRemove) {
        if (index >= 1 && index <= size) {
            Item item;
            Node node;
            if (index == 1) {
                node = sentinel.next;
            } else if (index == size) {
                node = sentinel.prev;
            } else {
                int midSize = size / 2;
                if (index > midSize)
                    node = traverseBackwards(size - index);
                else
                    node = traverseForwards(index);
            }
            item = node.item;
            if (getAndRemove)
                delete(node);

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

    /**
     * Helper method used to remove a node
     *
     * @param toRemove the node that is to be removed
     */
    private void delete(Node toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        if (size != 0) {
            size -= 1;
        }
    }

    /**
     * dequeue element at the back of the queue
     * <p>
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the back of the queue
     */
    public Item get() {
        Item item = sentinel.prev.item;
        delete(sentinel.prev);
        return item;
    }


    /**
     * Enqueue a new item to the front of the list.
     *
     * @param item
     */
    public void add(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size += 1;
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
        private List<Integer> queue = new List();

        public static void main(String... args) throws IOException {
            Test test = new Test();
            test.test();
        }

        private void test() {
            List<Integer> queue = new List<>();
            for (int i = 0; i < 10; i++) {
                queue.add(i);
                if (i == 3) {
                    queue.get();
                }
            }
            queue.delete(4);
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