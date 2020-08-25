package datastructure;

import java.util.Iterator;

/**
 * Last In First Out implementation.
 * The element that is pushed in last is the first to
 * be removed on pop()
 *
 * @param <Data>
 */
public class Stack<Data> implements Iterable<Data> {
    private Node head;

    public Stack() {
        head = null;
    }

    public void push(Data data) {
        Node n = new Node(data);
        n.next = head;
        head = n;
    }

    public Data pop() {
        if (head != null) {
            Data data = head.data;
            head = head.next;
            return data;
        } else
            throw new UnsupportedOperationException("The list is empty");
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<Data> iterator() {
        return null;
    }

    public void print(){
        System.out.println(toString());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node temp = head;
        while (temp != null){
            sb.append(temp.data);
            temp = temp.next;
            if(temp != null)
                sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }
    private class Node {
        Data data;
        Node next;

        Node() {

        }

        Node(Data data) {
            this.data = data;
            this.next = null;
        }
    }
}
