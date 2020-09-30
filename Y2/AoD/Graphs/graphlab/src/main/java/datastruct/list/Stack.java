package datastruct.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> head;
    private int size;

    private static class Node<Item> {
        Node<Item> next;
        Item item;

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Add an <code> Item </code> to the front
     * of the bag.
     *
     * @param item
     */
    public void push(Item item) {
        if (this.head == null) {
            this.head = new Node<>(item);
        } else {
            Node<Item> node = new Node<>(item, head);
            head = node;
        }
        size++;
    }

    /**
     * Pop element from front of stack
     * @return
     */
    public Item pop(){
        if (this.head == null)
            throw new NoSuchElementException();
        else {
            Item data = head.item;
            head = head.next;
            size -= 1;
            return data;
        }
    }

    public int size() {
        return size;
    }

    /**
     * Return LIFO iterator
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new StackIterator<>(head);
    }

    private static class StackIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public StackIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
