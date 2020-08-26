package stack;


import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Samuel Ferrara 19940412-1395 spof@kth.se
 * <p>
 * Implementation of the <code> Stack </code> interface.
 * <p>
 * This implementation enforces a First In Last Out policy,
 * meaning that every insertion adds an element to the top of the stack
 * while every fetch removes an element from the top of the stack.
 */
public class BasicStack<Data> implements Stack<Data> {
    private Node head;
    private Integer size;

    public BasicStack() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Push a new element to the top of the stack.
     * The
     *
     * @param data that is contained by the element.
     */
    @Override
    public void push(Data data) {
        Node node = new Node(data);
        node.next = head;
        head = node;
        size += 1;
    }

    /**
     * Fetches data from element currently at the top of the stack,
     * removes the element and then returns the data.
     *
     * @return the data that is contained by the element
     * that is currently at the top of the stack.
     *
     * @throws NoSuchElementException if there is no element at the
     *                                top of the stack.
     */
    @Override
    public Data pop() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        }

        Data data = head.data;
        head = head.next;
        size -= 1;
        return data;
    }

    /**
     * Determine if the stack is empty or not
     *
     * @return true if the top of the stack
     *         is pointing to <code> null </code>
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Print the stacks current state to standard output
     */
    @Override
    public void print(){
        System.out.println(toString());
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the data
     * that is currently on the stack.
     *
     * @return the sequence of data in this stack in LIFO order
     * separated by spaces and framed by brackets :
     *
     * Example:
     * (1) toString() => [ ]
     * (2) push(-2)
     * (3) toString() => [-2]
     * (4) push(1)
     * (5) toString() => [1 -2]
     *     ...
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (Data data : this) {
            stringBuilder.append(data.toString());
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");
        String stringRepresentation = stringBuilder.toString();
        return stringRepresentation;
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Data> iterator() {
        return new LinkedIterator(head);
    }

    private class LinkedIterator implements Iterator<Data> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Data next() {
            if (!hasNext()) throw new NoSuchElementException();
            Data item = current.data;
            current = current.next;
            return item;
        }
    }

    private class Node {
        Data data;
        Node next;

        Node(Data data) {
            this.data = data;
        }

        Node() {

        }
    }

    /**
     * Simple unit test.
     * Test pushing integers to the stack and printing them and
     * then goes on to removing them.
     */
    private static class Test{
        public static void main(String ...args){
            Stack<Integer> stack = new BasicStack<>();
            Integer ints [] = {5,3,6,1,-6};

            System.out.println("Pushing elements to the stack...");
            for(Integer integer : ints){
                stack.push(integer);
                stack.print();
            }

            if(stack.size() == 5)
                System.out.println("The stack now has 5 elements ");
            else
                System.out.println("Something went wrong when updating the stack size ");

            System.out.println("Popping elements from the stack...");
            while (!stack.isEmpty()) {
                stack.pop();
                stack.print();
            }

            if(stack.isEmpty() && stack.size() == 0)
                System.out.println("The stack is now empty !");
            else
                System.out.println("Something went wrong, the stack is not empty");

            System.out.println("Testing the iterator.......");
            for(Integer integer : ints){
                stack.push(integer);
            }

            for(Integer integer: stack){
                System.out.println(integer);
            }
        }
    }

}
