public interface Queue<Item> extends Iterable<Item> {
    boolean isEmpty();
    void addFirst(Item item);
    void addLast(Item item);
    Item removeFirst();
    Item removeLast();
    void print();
}
