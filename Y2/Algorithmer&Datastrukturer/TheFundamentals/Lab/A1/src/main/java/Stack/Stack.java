package Stack;

public interface Stack <Item> extends Iterable<Item>{
    void push(Item item);
    Item pop();
    Item peek();
    boolean isEmpty();
    void print();
    int size();
}
