package E4;

import datastructure.Queue;

import java.util.*;

public class GenericQueue<Item> {
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
        return size == 0;
    }

    /**
     * Add a new element to the front of the list.
     *
     * @param item
     */
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
            test.testFile();
        }

        private void testKeyboard() {
                for (int i = 0; i < 10; i++) {
                    String s = scanner.nextLine();
                    if (s.equals("rm first")) {
                        queue.removeFirst();
                    } else if (s.equals("rm last")) {
                        System.out.println("Enter an integer to remove last : ");
                        queue.removeLast();
                    } else if (s.equals("add first")) {
                        String integer = scanner.nextLine();
                        try {
                            queue.addFirst(Integer.parseInt(integer));
                        } catch (NumberFormatException e) {
                            System.out.println(" input " + integer + " is not an integer");
                        }
                    } else if (s.equals("add last")) {
                        System.out.println("Enter an integer to add last : ");
                        String integer = scanner.nextLine();
                        try {
                            queue.addFirst(Integer.parseInt(integer));
                        } catch (NumberFormatException e) {
                            System.out.println(" input " + integer + " is not an integer");
                        }
                    } else {
                        System.out.println("Illegal command, try again");
                        i -= 1;
                    }
                }
            }


        private void testFile() {
            if (scanner.hasNext()) {
                String [] s = scanner.nextLine().split(" ");
                boolean isNumeric = s[0].chars().allMatch(Character::isDigit);
                if (isNumeric) {
                    String cmd = s[1];
                    if (cmd.equals("+l"))
                        queue.addLast(Integer.parseInt(s[0]));

                    else if (cmd.equals("+f"))
                        queue.addFirst(Integer.parseInt(s[0]));

                } else if (s[0].equals("-l"))
                    queue.removeLast();
                else if (s[0].equals("-f"))
                    queue.removeFirst();
                testFile();
            }
            return;
        }


    }
}
