package datastruct.st;

import datastruct.list.LinkedList;
import util.Compare;

import java.util.NoSuchElementException;
import java.util.Objects;

import static util.Compare.*;

public class TreeSet <Key extends Comparable<Key>>{
    private Node root;

    private class Node {
        private Key key;
        Node left, right;
        private int N;

        Node(Key key, int N) {
            this.key = key;
            this.N = N;
        }

    }

    public void put(Key key) {
        if (Objects.isNull(key))
            throw new IllegalArgumentException("Null values are not accepted as keys");

        root = put(key, root);
    }

    private Node put(Key key, Node x) {
        if (x == null) {
            return new Node(key,1);
        }
        if (lessThan(key, x.key))
            x.left = put(key, x.left);
        else if (largerThan(key, x.key))
            x.right = put(key, x.right);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     *
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return contains(key,root) != null;
    }

    /**
     * SearchStrategy the tree for a given key.
     * <p>
     * If searched key is less than key of
     * current node, move to the left.
     * <p>
     * If searched key is less than key of
     * current node, move to the left.
     *
     * @param key searched key
     * @param x   current node
     *
     * @return null if the key is not found.
     */
    private Key contains(Key key, Node x) {
        if (Objects.isNull(x))
            return null;
        if (lessThan(key, x.key))
            return contains(key, x.left);
        else if (largerThan(key, x.key))
            return contains(key, x.right);
        else
            return x.key;
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param key the key
     *
     * @return the number of keys in the symbol table strictly less than {@code key}
     *
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Iterable<Key> keys() {
        LinkedList<Key> keys = new LinkedList<>();
        if (!isEmpty())
            keys(root, keys, min(), max());
        return keys;
    }

    private void keys(Node x, LinkedList<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        if (Compare.lessThan(lo, hi))
            keys(x.left, queue, lo, hi);
        if (lessOrEquals(lo, x.key) && largerOrEquals(hi, x.key))
            queue.add(x.key);
        if (largerThan(hi, x.key))
            keys(x.right, queue, lo, hi);
    }

    private int size(Node x) {
        if (Objects.isNull(x))
            return 0;
        else
            return x.N;
    }

    public static void main(String...args){
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.put(1);
        treeSet.put(2);
        treeSet.put(1);
        treeSet.put(3);
    }
}
