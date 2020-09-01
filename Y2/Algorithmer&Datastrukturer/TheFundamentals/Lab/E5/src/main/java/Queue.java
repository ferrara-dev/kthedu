public interface Queue<Item> extends Iterable<Item> {
    boolean isEmpty();
    Item remove(int index);
    void enqueue(Item item);
    Item dequeue();
    void print();
}
