package datastructures;

import java.util.Iterator;

/**
 * @author Samuel Ferrara spof@kth.se
 *
 * Implementation of a FIFO Queue.
 *
 * The First In First Out policy is implemented in the following way:
 *
 * Elements are enqued to the back-end of the queue.
 *
 * Elements are dequeued from the front-end of the queue.
 * @param <Item>
 */
public class Queue<Item> extends LinkedList<Item>{

    /**
     * Enqueues a new item to the back of the list.
     * @param item the item that is enqueued.
     */
    public void enqueue(Item item){
        Node node = new Node(item);
        if(head != null) {
            tail.next = node;
            tail = tail.next;
        }
        else
            head = tail = node;

        size += 1;
    }

    /**
     * Remove and return the element that
     * has been in the queue for the longest.
     * @return
     */
    public Item dequeue(){
        Item item = head.item;
        head = head.next;
        size -= 1;
        return item;
    }

    /**
     * Check if the head is pointing anywhere to determine
     * if the list is empty or not.
     * @return true if the head is pointing to null
     */
    public boolean isEmpty(){
        return head == null;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
