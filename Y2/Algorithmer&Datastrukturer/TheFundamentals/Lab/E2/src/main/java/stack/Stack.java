package stack;

public interface Stack <Data> extends Iterable<Data>{
    void push(Data item);
    Data pop();
    boolean isEmpty();
    void print();
    int size();
}
