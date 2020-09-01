import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class GenericQueue<Item extends Comparable<Item>> implements Queue<Item> {
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
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add a new element to the front of the list.
     *
     * @param item
     */
    @Override
    public void addFirst(Item item) {
        Node node = new Node(item);
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
        size += 1;
        System.out.println("Adding [" + item.toString() + "] first");
        print();
    }

    @Override
    public void addLast(Item item) {
        Node node = new Node(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
        System.out.println("Adding [" + item.toString() + "] last");
        print();
    }

    /**
     * Remove the element currently at the front of the queue
     *
     * @return
     */
    @Override
    public Item removeFirst() {
        Item item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if(size != 0){
            size -= 1;
        }
        if(item != null)
            System.out.println("Removing first element [" + item.toString() + "]");
        print();
        return item;
    }

    /**
     * Remove the element currently at the end of the queue
     *
     * @return <code> Item item </code> contained by the node
     *          currently at the back of the queue.
     */
    @Override
    public Item removeLast() {
        Item item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        if(size != 0){
            size -= 1;
        }

        if(item != null)
            System.out.println("Removing last element [" + item.toString() + "]");
        print();
        return item;
    }

    @Override
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
    public static void main(String... args) {
        Queue<Integer> queue = new GenericQueue<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            String s = scanner.nextLine();
            if(s.equals("rm first")){
                queue.removeFirst();
            }
            else if(s.equals("rm last")){
                queue.removeLast();
            }
            else if(s.equals("add first")){
                queue.addFirst(i);
            }
            else if(s.equals("add last")){
                queue.addLast(i);
            }
            else{
                System.out.println("Illegal command, try again");
                i -= 1;
            }
        }
    }
}
