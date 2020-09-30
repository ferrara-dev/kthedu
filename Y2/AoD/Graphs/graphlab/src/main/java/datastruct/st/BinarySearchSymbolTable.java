package datastruct.st;


import util.Compare;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static util.Compare.largerThan;
import static util.Compare.lessThan;

/**
 * An the ordered array symbol table (algorithm 3.2)
 */
public class BinarySearchSymbolTable<Key extends Comparable<Key>, Value> implements ST<Key,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    private int capacity;

    public BinarySearchSymbolTable(int capacity) {
        this.capacity = capacity;
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }


    /**
     * @return the number of key-value pairs
     */
    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty())
            return null;
        int index = rank((Key) key);
        if (index < N && Compare.equals((Comparable) key, keys[index])) {
            return values[index];
        } else
            return null;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * returns the number of keys smaller than a given key.
     * <p>
     * Used to tell the get(Key key) method where to find
     * the searched key.
     * <p>
     * Used to tell the put(Key key, Val val) method where
     * to update.
     * ■ If key is in the table, rank() returns its index in the table, which is the same as
     * the number of keys in the table that are smaller than key.
     *
     * ■ If key is not in the table, rank() also returns the number of keys in the table
     * that are smaller than key.
     *
     * @param key
     *
     * @return
     */
    public int rank(Key key) {
        return rank(key, 0, N - 1);
    }

    private int rank(Key key, int lower, int upper) {
        if (upper < lower)
            return lower;
        int mid = lower + (upper - lower) / 2;

        int compare = key.compareTo(keys[mid]);

        if (lessThan(key, keys[mid])) {
            return rank(key, lower, mid - 1);

        } else if (largerThan(key, keys[mid])) {
            return rank(key, mid + 1, upper);
        } else {
            return mid;
        }
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        // insert new key-value pair
        if (N == keys.length)
            resize(2*keys.length);

        // move all elements after i one step to the right
        for (int j = N; j > i; j--)  {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < N-1; j++)  {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }

        N--;
        keys[N] = null;  // to avoid loitering
        values[N] = null;

        // resize if 1/4 full
        if (N > 0 && N == keys.length/4) resize(keys.length/2);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[N-1];
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between {@code lo}
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        LinkedList<Key> queue = new LinkedList<>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.add(keys[i]);
        if (contains(hi)) queue.add(keys[rank(hi)]);
        return queue;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }
        values = tempv;
        keys = tempk;
    }
}
