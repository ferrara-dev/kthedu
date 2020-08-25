package datastructures;

import java.util.Iterator;

/**
 * Implementation of LIFO stack.
 *
 * The Last In First Out policy is enforced in the following way:
 *
 * Elements are pushed to the top of the stack.
 *
 * Elements are popped from the top of the stack.
 * @param <Item>
 */
public class Stack<Item> extends LinkedList<Item>{

    public void push(Item item){
        Node n = new Node(item);
        n.next = head;
        head = n;
        size += 1;
    }

    public Item pop(){
        Item item = head.item;
        head = head.next;
        size -= 1;
        return item;
    }
    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }
}
