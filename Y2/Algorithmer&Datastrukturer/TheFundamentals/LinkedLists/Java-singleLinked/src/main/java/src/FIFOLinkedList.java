package src;

public class FIFOLinkedList <T> extends LinkedList<T> {
    Node head = null;
    Node tail = null;

    /**
     * Insert a new element in the list.
     * Inserts a new element and to end
     * of the list.
     * If <code> head </code> is null,
     * a new node that is pointed to
     * by the head is created.
     *
     * else
     * tail.next is set to point to
     * a new node;
     *
     * @param item
     */
    @Override
    public void insert(T item) {
        Node n = new Node(item);
        if(head == null)
            head = tail = n;
        else{
            tail.next = n;
        }
    }

    @Override
    public T get() {
        if(head == null)
            throw new IllegalOperationException("The list is empty");
        T item = head.item;
        head = head.next;
        if(head == null)
            tail = null;
        return item;
    }

    @Override
    public void insertAscending(T element) {

    }

    @Override
    public boolean isEmpty() {
        if(head == null)
            return true;
        return false;
    }

    @Override
    public void print() {

    }

    @Override
    public String toString() {
        return null;
    }
}
