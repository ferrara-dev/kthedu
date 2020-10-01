package datastruct.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item>{
    private Node<Item> head;
    private int size;

    private static class Node<Item>{
        Node<Item> next;
        Item item;

        Node(Item item, Node next){
            this.item = item;
            this.next = next;
        }

        Node(Item item){
            this.item = item;
        }
    }

    /**
     * Add an <code> Item </code> to the front
     * of the bag.
     * @param item
     */
    public void add(Item item){
        Node<Item> oldfirst = head;
        head = new Node<Item>(item, oldfirst);
        size++;
    }

    public int size(){
        return size;
    }

    public boolean contains(Item item){
        for(Item i : this){
            if(i.equals(item))
                return true;
        }
        return false;
    }

    /**
     * Return LIFO iterator
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new BagIterator<>(head);
    }

    private static class BagIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public BagIterator(Node first) {
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
