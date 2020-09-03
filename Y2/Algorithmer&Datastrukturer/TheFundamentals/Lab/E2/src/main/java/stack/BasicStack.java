package stack;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;


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
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Data peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return head.data;
    }

    /**
     * Push a new element to the top of the stack.
     *
     * @param data that is added to the stack.
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
     * goes on to remove the element and then returns the data.
     *
     * @return the element that was added most recently to the stack.
     *
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public Data pop() {
        if (isEmpty())
            throw new NoSuchElementException("The list is empty");

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
     * Test pushing integers to the stack by entering "+" and
     * popping them from the stack by entering "-"
     */
    private static class Test{
        public static void main(String ...args){
            Stack<Integer> stack = new BasicStack<>();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter + to push an element to the stack and - to pop an element from the stack");
            for(int i = 0; i < 10; i++){
                String line = scanner.nextLine();

                if(line.equals("+")){
                    System.out.println(i + " pushed to the stack");
                    stack.push(i);
                }

                else if(line.equals("-")){
                    if(stack.isEmpty()){
                        System.out.println("You can not pop from an empty stack, try again");
                        i -= 1;
                        continue;
                    }
                    else {
                        int p = stack.pop();
                        System.out.println(p + " popped from the stack");
                    }
                }
                stack.print();
            }
        }
    }

}
