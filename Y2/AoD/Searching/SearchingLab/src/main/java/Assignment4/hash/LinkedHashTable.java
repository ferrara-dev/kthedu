package Assignment4.hash;

import Assignment2.ST;

public class LinkedHashTable<Key, Value> implements ST<Key, Value> {
    private int M; // number of chains
    private int N = 0; // number of key-value pairs
    private Entry[] entries = new Entry[M];

    private static class Entry {
        private Object key;
        private Object value;
        private Entry next;
        int numberOfKeysAtTimeOfInsert;

        Entry(Object key, Object value, Entry node,int numberOfKeysAtTimeOfInsert) {
            this.numberOfKeysAtTimeOfInsert = numberOfKeysAtTimeOfInsert;
            this.key = key;
            this.value = value;
            next = node;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LinkedHashTable() {
        this(97);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     *
     * @param M the initial number of chains
     */
    public LinkedHashTable(int M) {
        this.M = M;
        entries = new Entry[M];
    }

    /**
     * resize the hash table to have the given number of chains,
     * rehashing all of the keys
     *
     * @param chains
     */
    private void resize(int chains) {
        LinkedHashTable<Key, Value> temp = new LinkedHashTable<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Entry x = entries[i]; x != null; x = x.next) {
                temp.put((Key) x.key, (Value) x.value);
            }
        }

        this.M = temp.M;
        this.N = temp.N;
        this.entries = temp.entries;
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
     * @param key
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
        entries[hash] = new Entry(key, value, entries[hash], count);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @return
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
        return null;
    }

    private boolean hasEntry(int hashKey) {
        return entries[hashKey] != null;
    }

    private int hash(Key x) {
        return (x.hashCode() & 0x7fffffff) % M;
    }

    private static class LinkedHashTableTester {
        public static void main(String... args) {
            LinkedHashTable<String, Integer> hashTable = new LinkedHashTable<>();
            System.out.println("Aa".hashCode());
            System.out.println("BB".hashCode());

            hashTable.put("Aa", 5);
            hashTable.put("BB", 10);
            hashTable.delete("BB");

        }
    }
}