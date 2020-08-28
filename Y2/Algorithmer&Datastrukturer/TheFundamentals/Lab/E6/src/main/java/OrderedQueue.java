import java.util.Iterator;
import java.util.Objects;

/**
 * Ordered queue.
 * <p>
 * Object types stored in this queue need to implement the
 * <code> Comparable </code> interface.
 *
 * @param <Item>
 */
public class OrderedQueue<Item extends Comparable<Item>> implements Queue<Item> {
    private Node sentinel;

    public OrderedQueue() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    @Override
    public void enqueue(Item item) {
        Node node = new Node(item);
        if (Objects.equals(sentinel, sentinel.next) || sentinel.next.isSmaller(item))
            addFirst(node);
        else if(!sentinel.prev.isSmaller(item)){
            addLast(node);
        }

    }

    private void addLast(Node node) {
        node.next = sentinel;
    }

    private void addFirst(Node node) {
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;
        node.next.prev = node;
    }

    @Override
    public Item dequeue() {
        return null;
    }

    @Override
    public void print() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        Node() {

        }

        Node(Item item) {
            this.item = item;
        }

        boolean isSmaller(Item item) {
            if (this.item.compareTo(item) < 0)
                return true;
            return false;
        }

    }
}
