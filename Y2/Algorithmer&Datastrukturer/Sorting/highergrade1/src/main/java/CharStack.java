import java.util.NoSuchElementException;

public class CharStack {
    private Node head;
    private Integer size;

    public CharStack() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack, if the stack is empty null is returned
     *
     */
    public char peek() {
        if (isEmpty())
            return Character.MIN_VALUE;
        return head.data;
    }

    /**
     * Push a new element to the top of the stack.
     *
     * @param data that is added to the stack.
     */
    public void push(char data) {
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
    public char pop() {
        if (isEmpty())
            throw new NoSuchElementException("The list is empty");

        char data = head.data;
        head = head.next;
        size -= 1;
        return data;
    }

    /**
     * Determine if the stack is empty or not
     *
     * @return true if the top of the stack
     * is pointing to <code> null </code>
     */
    public boolean isEmpty() {
        return head == null;
    }


    public int size() {
        return size;
    }


    private class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
        }

        Node() {

        }
    }

}
