package datastruct.st;


import datastruct.list.LinkedList;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.HashMap;


/**
 * A symbol table implemented with a separate-chaining hash table.
 *
 * @param <Key>
 * @param <Value>
 */
public class HashTable<Key, Value> implements ST<Key, Value> {
    private int M; // number of chains
    private int N = 0; // number of key-value pairs
    private Entry[] entries = new Entry[M];

    protected static class Entry {
        private Object key;
        private Object value;
        private Entry next;
        int currentChainSize;

        Entry(Object key, Object value, Entry node, int currentChainSize) {
            this.currentChainSize = currentChainSize;
            this.key = key;
            this.value = value;
            next = node;
        }

        public int getChainSize() {
            return currentChainSize;
        }
    }

    protected int getM() {
        return M;
    }

    /**
     * Initializes an empty symbol table.
     */
    public HashTable() {
        this(97);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     *
     * @param M the initial number of chains
     */
    public HashTable(int M) {
        this.M = M;
        entries = new Entry[M];
    }

    /**
     * resize the hash table to have the given number of chains,
     * rehashing all of the keys
     *
     * @param chains
     */
    protected void resize(int chains) {
        HashTable<Key, Value> temp = new HashTable<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Entry x = entries[i]; x != null; x = x.next) {
                temp.put((Key) x.key, (Value) x.value);
            }
        }

        this.M = temp.M;
        this.N = temp.N;
        this.entries = temp.entries;
    }

    public boolean putIfAbsent(Key key, Value value){
        if (key == null)
            throw new IllegalArgumentException("First argument key can not be null");

        int hash = hash(key);
        Entry x = entries[hash];

        if(!contains(key)){
            put(key,value);
            return true;
        }
        return false;
    }

    /**
     * (1) Get the hash code of the key.
     * <p>
     * (2) If the hash code has an entry,
     * traverse the nodes until the
     * key is found and then return
     * the value.
     * <p>
     * (3) If the entry does not exist
     * or the key is not present
     * null is returned.
     *
     * @param key
     *
     * @return
     */
    public Value get(Object key) {
        int hash = hash((Key) key);
        Entry x = entries[hash];
        while (x != null) {
            if (key.equals(x.key))
                return (Value) x.value;
            x = x.next;
        }
        return null;
    }

    public int size() {
        return N;
    }

    /**
     * Returns the hash code for this key
     *
     * @param key
     *
     * @return
     */
    @Override
    public int rank(Key key) {
        throw new UnsupportedOperationException();
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("First argument key can not be null");

        if (N >= 10 * M)
            resize(2 * M);

        int hash = hash(key);
        Entry x = entries[hash];

        int count = 0;
        while (x != null) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
            x = x.next;
            count++;
        }

        N++;
        entries[hash] = new Entry(key, value, entries[hash], count + 1);
    }

    private void put(Key key, Value value, boolean onlyIfAbsent) {
        if (key == null)
            throw new IllegalArgumentException("First argument key can not be null");

        if (N >= 10 * M)
            resize(2 * M);

        int hash = hash(key);
        Entry x = entries[hash];

        int count = 0;
        while (x != null) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
            x = x.next;
            count++;
        }

        N++;
        entries[hash] = new Entry(key, value, entries[hash], count + 1);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     *
     * @return
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void remove(Object key) {
        if (key == null) throw new IllegalArgumentException("argument to remove() is null");

        int i = hash((Key) key);
        entries[i] = this.remove(entries[i], (Key) key);

        // halve table size if average length of list <= 2
        if (M > 97 && N <= 2 * M)
            resize(M / 2);
    }

    // remove key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Entry remove(Entry x, Key key) {
        if (x == null)
            return null;
        if (key.equals(x.key)) {
            N--;
            return x.next;
        }
        x.next = remove(x.next, key);
        return x;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("First argument key can not be null");
        int hash = hash(key);
        if (hasEntry(hash)) {
            Entry x = entries[hash];
            Entry prev = null;
            if (x.key.equals(key)) {
                entries[hash] = x.next;
            }

            while (x != null && !x.key.equals(key)) {
                prev = x;
                x = x.next;
            }
            prev.next = x.next;
            N--;
        }

        // halve table size if average length of list <= 2
        if (M > 97 && N <= 2 * M)
            resize(M / 2);

    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public Iterable<Key> keys() {
        LinkedList<Key> keys = new LinkedList<>();
        for(Entry entry : entries()){
            while (entry != null){
                keys.add((Key) entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }


    public Iterable<Entry> entries() {
        LinkedList<Entry> entries = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            if (this.entries[i] != null)
                entries.add(this.entries[i]);
        }
        return entries;
    }

    private boolean hasEntry(int hashKey) {
        return entries[hashKey] != null;
    }

    private int hash(Key x) {
        return (x.hashCode() & 0x7fffffff) % M;
    }

    protected int chainLengthAt(int index){
        if(entries[index] != null)
            return entries[index].getChainSize();
        else
            return 0;
    }

}