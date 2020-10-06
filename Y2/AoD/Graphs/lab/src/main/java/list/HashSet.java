package list;

public class HashSet<Key>{
    private int M; // number of chains
    private int N = 0; // number of key-value pairs
    private Entry [] entries;

    private static class Entry<Key>{
        Key key;
        Entry<Key> next;

        Entry(Key key, Entry<Key> next){
            this.key = key;
            this.next = next;
        }
    }


    protected int getM() {
        return M;
    }

    /**
     * Initializes an empty symbol table.
     */
    public HashSet() {
        this(97);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     *
     * @param M the initial number of chains
     */
    public HashSet(int M) {
        this.M = M;
        entries = new Entry[M];
    }

    /**
     * resize the hash set to have the given number of chains,
     * rehashing all of the keys
     *
     * @param chains
     */
    private void resize(int chains) {
        HashSet<Key> temp = new HashSet<Key>(chains);
        for (int i = 0; i < M; i++) {
            for (Entry x = entries[i]; x != null; x = x.next) {
                temp.add((Key) x.key);
            }
        }

        this.M = temp.M;
        this.N = temp.N;
        this.entries = temp.entries;
    }

    public void add(Key key) {
        if (key == null)
            throw new IllegalArgumentException("First argument key can not be null");

        if (N >= 10 * M)
            resize(2 * M);

        int hash = hash(key);
        Entry x = entries[hash];

        int count = 0;
        while (x != null) {
            if (key.equals(x.key)) {
                return;
            }
            x = x.next;
            count++;
        }

        N++;
        entries[hash] = new Entry(key, entries[hash]);
    }

    public boolean contains(Key key){
        int hash = hash(key);
        Entry x = entries[hash];
        while (x != null) {
            if (key.equals(x.key)) {
                break;
            }
            x = x.next;
        }

        return x != null;
    }

    private int hash(Key x) {
        return (x.hashCode() & 0x7fffffff) % M;
    }

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
}
