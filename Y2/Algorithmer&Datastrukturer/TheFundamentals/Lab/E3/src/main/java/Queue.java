public interface Queue<Item> extends Iterable<Item> {
    boolean isEmpty();
    Item dequeue();
    void enqueue(Item item);
    void print();
}
