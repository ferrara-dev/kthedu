package datastructures;

public abstract class LinkedList<Item>  implements Iterable<Item>{
    protected Node head;
    protected Node tail;
    protected int size = 0;

    protected class Node{
        Item item;
        Node next;

        Node (Item item){
            this.item = item;
        }

        Node(){

        }
    }

    public abstract boolean isEmpty();

    public void print(){
        System.out.println(toString());
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

    public int size(){
        return this.size;
    }
}
