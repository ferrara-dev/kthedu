package list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList <Item> implements Iterable<Item>{
    private int size;
    private Node sentinel;

    public LinkedList() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public int size() {
        return size;
    }

    public LinkedList(Item[] items) {
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

    public static LinkedList listOf(Object... items) {
        LinkedList lst = new LinkedList();
        for (Object item : items) {
            lst.add(item);
        }
        return lst;
    }

    public static LinkedList listOf(Iterable items) {
        LinkedList lst = new LinkedList();
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
     * dequeue element at the front of the queue
     * <p>
     * If the list is empty and <code> sentinel.prev </code>
     * is pointing back to the sentinel element, null is returned.
     *
     * @return Item contained in the back of the queue
     */
    public Item dequeue() {
        Item item = sentinel.next.item;
        delete(sentinel.next);
        return item;
    }

    /**
     * Enqueue a new item to the back of the list.
     *
     * @param item
     */
    public void add(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node node = new Node(item);
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        node.next = sentinel;
        size += 1;
    }

    public void addAll(LinkedList<Item> lst) {
        if (lst == null)
            throw new IllegalArgumentException();
        for(Item item : lst){
            add(item);
        }
    }

    public void addAll(Iterable<Item> lst) {
        if (lst == null)
            throw new IllegalArgumentException();
        for(Item item : lst){
            add(item);
        }
    }

    public void add(Item...items) {
        if (items == null)
            throw new IllegalArgumentException();
        for(Item item : items){
            add(item);
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

    public String toStringReversed() {
        return toStringReversed(sentinel.prev, new StringBuilder());
    }

    public String toStringReversed(Node node, StringBuilder sb){
        if (Objects.equals(sentinel, node))
            return sb.toString();
        else {
            return toStringRecursive(node.prev, sb.append("[ " + node.item.toString() + " ] "));
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


    public static void main(String...args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.print();
        int firstIn = linkedList.dequeue();
        System.out.println(firstIn);

    }
}
