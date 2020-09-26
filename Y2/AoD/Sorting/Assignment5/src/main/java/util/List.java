package util;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class List<Item> implements Iterable<Item> {
    private int size = 0;
    private Node sentinel;
    private boolean print = true;

    public List() {
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
        if (index >= 0 && index < size) {
            Item item;
            if (index == 0) {
                item = sentinel.next.item;
                remove(sentinel.next);
            } else if (index == size - 1) {
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

    public void addAt(int i, Item item){
        if( i >= 1 && i < size){
            Node temp = traverseForwards(i);
            add(new Node(item),temp);
        }
    }

    /**
     * Add an item last
     * @param item
     */
    public void add(Item item){
        add(new Node(item),sentinel);
    }

    public void addFirst(Item item){
        add(new Node(item),sentinel.prev);
    }

    private void add(Node node, Node toMoveForward){
        node.next = toMoveForward;
        node.prev = toMoveForward.prev;
        toMoveForward.prev = node;
        node.prev.next = node;
        size++;
    }

    private Node traverseBackwards(int index) {
        Node temp = sentinel.prev;
        for (int i = 0; i < index; i++)
            temp = temp.prev;
        return temp;
    }

    private Node traverseForwards(int index) {
        Node temp = sentinel.next;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp;
    }

    /**
     * Helper method used to remove a node
     * @param toRemove the node that is to be removed
     */
    private void remove(Node toRemove) {
        if (size != 0) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
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
    public Item dequeue() {
        Item item = sentinel.prev.item;
        remove(sentinel.prev);
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

    public Item peekAt(int i){
        if(i > size || i < 1){
          return null;
        }
        Node temp = traverseForwards(i);
        return temp.item;
    }

    public Item get(int i){
        if(i > size || i < 0){
            return null;
        }
        Node temp = traverseForwards(i);
        return temp.item;
    }

    public int getSize() {
        return size;
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

    public void set(int i, Item item){
       Node temp = traverseBackwards(i);
       temp.item = item;
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
     * Simple unit test of <code> List </code> class.
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
                queue.enqueue(i);
                if (i == 3) {
                    queue.dequeue();
                }
            }
            queue.addAt(4,55);
            queue.add(100);
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