package src;

public abstract class LinkedList <E> {

    public abstract void insert(E element);
    public abstract E get();
    public abstract void insertAscending(E element);
    public abstract boolean isEmpty();

    public void print() {
        System.out.println(toString());
    }


    @Override
    public abstract String toString();

    protected class Node {
        Node next = null;
        E item;

        Node(){

        }

        Node(E item){
            this.item = item;
        }
    }

}
