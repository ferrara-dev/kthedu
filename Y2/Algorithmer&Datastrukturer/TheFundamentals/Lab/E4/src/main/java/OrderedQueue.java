import java.util.Iterator;
import java.util.Objects;

public class OrderedQueue<Item extends Comparable<Item>> implements Queue<Item> {
    private Node head;
    private Node tail;

    public OrderedQueue() {
        head = null;
        tail = null;

    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Item dequeue() {
        return null;
    }

    @Override
    public void enqueue(Item item) {
        Node node = new Node(item);
        node.next = head;
        head = node;
    }

    public void insert(Item item) {
        if (head == null)
            head = new Node(item);

        Node tempNode = this.head;

        while (tempNode != null && tempNode.next != null && !isLess(item, tempNode.next.item)) {
            tempNode = tempNode.next;
        }

        Node newNode = new Node(item);
        /*Empty list,and index is 0*/
        if (tempNode == null) {
            this.head = newNode;
        } else {
            newNode.next = tempNode;
            head = newNode;
        }
}

    private boolean isLess(Item t, Item th){
        if(t.compareTo(th) < 0)
            return true;
        else
            return false;
    }

    @Override
    public void print(){
        System.out.println(toString());
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class Node{
        Item item;
        Node next = null;

        Node(){

        }

        Node(Item item){
            this.item = item;
        }
    }
    public static void main(String ...args){
        Queue<Integer> queue = new OrderedQueue<>();
        queue.insert(5);
        queue.insert(3);
        queue.insert(-2);
        queue.insert(0);
    }

}
