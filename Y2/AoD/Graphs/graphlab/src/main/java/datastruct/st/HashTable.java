package datastruct.st;

import Assignment3.Assignment3;
import datastruct.List;
import datastruct.set.SET;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

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
        List<Key> keys = new List<>();
        for(Entry entry : entries()){
            while (entry != null){
                keys.add((Key) entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }


    public Iterable<Entry> entries() {
        List<Entry> entries = new List<>();
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

    private static class LinkedHashTableTester {
        public static void main(String... args) {
            HashTable<String, Integer> map = new HashTable<>();
            datastruct.set.SET<String> distinctWords = new datastruct.set.SET<>();
            Assignment3.collectWords(map);
            System.out.println(map.N);
            long sum = sum(map);


            double mean = (double) sum / (double) map.entries.length;
            System.out.println(sum);
            System.out.println(map.size());

            System.out.println("Mean: " + mean);

            double variance = 0;

            for (int i = 0; i < map.entries.length; i++) {
                if (map.entries[i] != null)
                    variance += Math.pow((double) map.entries[i].currentChainSize - mean, 2.0);
            }

            double std = Math.sqrt(variance / (double) map.entries.length);
            System.out.println("Standard Deviation: " + std);
            plot(map);

        }

        private static void plot(HashTable<String, Integer> map){
            XYChart chart = new XYChartBuilder().width(800).
                    height(600).
                    theme(Styler.ChartTheme.Matlab).
                    title("Hash distribution").
                    xAxisTitle("hash indices").
                    yAxisTitle("chain length/collisions").
                    build();
            chart.getStyler().setPlotGridLinesVisible(false);
            chart.getStyler().setXAxisTickMarkSpacingHint(100);

            java.util.List<Integer> xAxis = new ArrayList<>(); // hashes
            java.util.List<Integer> yAxis = new ArrayList<>(); // chainLength

            for (int i = 0; i < map.M; i++) {
                xAxis.add(i);
                yAxis.add(map.chainLengthAt(i));
            }

            chart.addSeries("Gaussian 1", xAxis, yAxis);
            // XYChart chart = QuickChart.getChart("Hash function spread", "Hash", "Collisions", "spread", hashes, collisions); // Create Chart
            new SwingWrapper(chart).displayChart(); // Show it
        }

        private static long sum(HashTable<String, Integer> map) {
            long sum = 0;
            for (int i = 0; i < map.entries.length; i++) {
                    sum += map.chainLengthAt(i);
                    System.out.println("Bucket " + i + ": " + map.chainLengthAt(i));
            }
            return sum;
        }

    }
}