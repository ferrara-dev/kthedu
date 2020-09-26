package src;

/**
 * LIFO - Last In First Out implementation of a linked-list.
 */
public class LIFOLinkedList extends LinkedList<Integer> {
    private Node head;

    public LIFOLinkedList(){
        head = null;
    }

    public LIFOLinkedList(LinkedList linkedList){

    }

    public void insertAscending(Integer item){
        Node temp = head;
        if(temp == null){
            insert(item);
            return;
        }

        Node node = new Node(item);
        while (temp.next != null){
            if(temp.item < item){
                node.next = temp;
                head = node;
                return;
            }
            temp = temp.next;
        }

        insert(item, temp);
        print();
    }

    /**
     * Linked list used to insert element of
     * generic type <code> T </code>
     * @param element that is inserted at to top
     *                of the list
     */
    public void insert(Integer element) {
        Node node = new Node(element);
        node.next = head;
        head = node;
    }

    /**
     * Get the value in the current node
     * and remove it from the list.
     * @return the value pointed to by the
     *         node on top of the queue.
     */
    public Integer get() {
        if(head == null)
            throw new IllegalOperationException("The list is empty");
        else{
            Integer item = head.item;
            head = head.next;
            return item;
        }
    }

    /**
     * Check if the list is empty
     *
     * @return true if the current head is pointing to another <code> Node </code>
     */
    public boolean isEmpty() {
        if(head == null)
            return true;
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node temp = head;
        while (temp != null){
            sb.append(temp.item);
            temp = temp.next;
            if(temp != null)
                sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }

    private void insert(Integer item, Node p){
        Node n = new Node(item);
        n.next = p.next;
        p.next = n;
    }
}
