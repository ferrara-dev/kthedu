public interface Queue<Item> extends Iterable<Item> {
    void enqueue(Item item);
    Item dequeue();
    void print();
    boolean isEmpty();
}
