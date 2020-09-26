
public class LinkedHashTable<Key, Value> {
    private int M = 97;
    private Entry[] nodes = new Entry[M];

    private static class Entry {
        private Object key;
        private Object value;
        private Entry next;

        Entry(Object key, Object value, Entry node) {
            this.key = key;
            this.value = value;
            next = node;
        }
    }

    /**
     * (1) Get the hash code of the key.
     *
     * (2) If the hash code has an entry,
     *     traverse the nodes until the
     *     key is found and then return
     *     the value.
     *
     * (3) If the entry does not exist
     *     or the key is not present
     *     null is returned.
     *
     * @param key
     * @return
     */
    public Value get(Key key){
        int hash = hash(key);
        Entry x = nodes[hash];
        while (x != null){
            if(key.equals(x.key))
                return (Value) x.value;
            x = x.next;
        }
        return null;
    }

    public void put(Key key, Value value){
        if(key == null)
            throw new IllegalArgumentException("First argument key can not be null");

        int hash = hash(key);
        Entry x = nodes[hash];
        while (x != null){
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }
            x = x.next;
        }
        nodes[hash] = new Entry(key, value,nodes[hash]);
    }

    public boolean delete(Key key){
        if(key == null)
            throw new IllegalArgumentException("First argument key can not be null");
        int hash = hash(key);
        if(hasEntry(hash)) {
            Entry x = nodes[hash];
            Entry prev = null;

            if(x.key.equals(key)) {
                nodes[hash] = x.next;
                return true;
            }

            while (x != null && !x.key.equals(key))
            {
                prev = x;
                x = x.next;
            }

            if(x == null)
                return false;

            prev.next = x.next;
            return true;
        }

        return false;
    }

    private boolean hasEntry(int hashKey){
        return nodes[hashKey] != null;
    }
    private int hash(Key x) {
        return (x.hashCode() & 0x7fffffff) % M;
    }

    private static class LinkedHashTableTester{
        public static void main(String...args){
            LinkedHashTable<String,Integer> hashTable = new LinkedHashTable<>();
            System.out.println("Aa".hashCode());
            System.out.println("BB".hashCode());

            hashTable.put("Aa",5);
            hashTable.put("BB",10);
            hashTable.delete("BB");

        }
    }
}
