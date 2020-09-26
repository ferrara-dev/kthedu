package datastructure;

public abstract class Queue <T> {
    public abstract void enqueue(T t);
    public abstract T dequeue();
    public abstract boolean isEmpty();
}
