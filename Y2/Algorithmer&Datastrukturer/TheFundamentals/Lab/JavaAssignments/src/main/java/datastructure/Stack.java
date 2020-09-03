package datastructure;

public interface Stack<Data> extends Iterable<Data>{
    void push(Data item);
    Data pop();
    Data peek();
    boolean isEmpty();
    void print();
    int size();
}
