package Assignment2;

import util.List;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {
    private Node first; // first node in the linked list
    private int n;

    private class Node { // linked-list node
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) { // Search for key, return associated value.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val; // search hit
        return null; // search miss
    }

    @Override
    public int size() {
        return 0;
    }

    public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            } // Search hit: update val.
        first = new Node(key, val, first); // Search miss: add new node.
        n++;
    }

    @Override
    public int rank(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keys = new List<>();
        for (Node x = first; x != null; x = x.next)
            keys.enqueue(x.key);
        return keys;
    }
}