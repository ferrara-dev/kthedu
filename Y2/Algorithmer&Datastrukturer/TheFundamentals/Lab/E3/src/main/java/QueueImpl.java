import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Samuel Ferrara 19940412-1395
 * Doubly linked circular list Implementation of a generic queue enforcing
 * the First In First Out policy.
 *
 * enqueue --> [x]->[x][x][x][x]
 *             [x][x][x][x]->[x] --> dequeue
 * @param <Item>
 */
public class QueueImpl<Item> implements Queue<Item> {
    Node sentinel;

    public QueueImpl(){
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
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
        System.out.println("Enqueue [" + item.toString() + "]");
        print();
    }

    private class Node{
        Item item;
        Node next;
        Node prev;

        Node(Item item){
            this.item = item;
        }

        Node(){

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
     * Simple unit test of <code> QueueImpl </code> class.
     *
     * Test data is received from a text file containing a sequence of characters.
     *
     * If read string equals '-' dequeue() is called
     * If read string is a digit, it is enqueued by calling enqueue(Item item)
     * The file is read until <code> scanner </code> obj does not have any
     * lines left to read
     *
     * The test will also test the iterator implementation.
     */
    public static class Test{
        private Queue<Integer> queue = new QueueImpl<>();

        public static void main(String ...args) throws IOException {
            Test test = new Test();
            test.test(new Scanner(System.in));
            test.testIter();
        }

        private void test (Scanner scanner){
            scanner.useDelimiter(" ");
            if(scanner.hasNext()){
                String s = scanner.next();
                boolean isNumeric = s.chars().allMatch( Character::isDigit );
                if(isNumeric){
                    queue.enqueue(Integer.parseInt(s));
                }

                else if(s.equals("-"))
                    queue.dequeue();

                test(scanner);
            }
        }

        private void test(InputStream inputStream) throws IOException {

            char c  = (char) inputStream.read();
            if(c == '\n' || c == (char) -1)
                return;

            else if(Character.isDigit(c))
                queue.enqueue(Character.getNumericValue(c));

            else if(c == '-')
                queue.dequeue();

            test(inputStream);
        }

        /**
         * Test method used to test the
         */
        private void testIter(){
            System.out.println("::::::::::::::: Testing iterator :::::::::::::::");
            System.out.println("Current queue state is : " + queue.toString());
            for(Integer i :queue)
                System.out.println("[ " + i + " ]");
        }

        /**
         * Test method used to iterate through the queue recursively
         * @param iterator
         */
        private void iterate(Iterator<Integer> iterator){
            if(iterator.hasNext()){
                int i = iterator.next();
                System.out.println("[ " + i + " ]");
                iterate(iterator);
            }
            else
                return;
        }
    }
}
