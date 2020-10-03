package datastruct.st;


import datastruct.list.LinkedList;

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

    public Value get(Key key) { // SearchStrategy for key, return associated value.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val; // graph.search hit
        return null; // graph.search miss
    }

    @Override
    public int size() {
        return n;
    }

    public void put(Key key, Value val) { // SearchStrategy for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            } // SearchStrategy hit: update val.
        first = new Node(key, val, first); // SearchStrategy miss: add new node.
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
        LinkedList<Key> keys = new LinkedList<>();
        for (Node x = first; x != null; x = x.next)
            keys.add(x.key);
        return keys;
    }
}