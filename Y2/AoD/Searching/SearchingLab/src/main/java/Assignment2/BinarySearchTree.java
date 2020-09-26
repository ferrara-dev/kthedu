package Assignment2;

import util.Compare;
import util.List;

import static util.Compare.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BinarySearchTree<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        Node left, right;
        private int N;

        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

        @Override
        public String toString(){
            return key.toString();
        }
    }

    public Value get(Key key) {
        return get(key, root);
    }

    /**
     * Search the tree for a given key.
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
     * @return value that matches to {@link Key} or null
     * if the search fails.
     */
    private Value get(Key key, Node x) {
        if (Objects.isNull(x))
            return null;
        if (lessThan(key, x.key))
            return get(key, x.left);
        else if (largerThan(key, x.key))
            return get(key, x.right);
        else
            return x.value;
    }

    public void put(Key key, Value value) {
        if (Objects.isNull(key) || Objects.isNull(value))
            throw new IllegalArgumentException("Null values are not accepted in key-value pairs");

        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node x) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        if (lessThan(key, x.key))
            x.left = put(key, value, x.left);
        else if (largerThan(key, x.key))
            x.right = put(key, value, x.right);
        else
            x.value = value;
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
        return get(key) != null;
    }

    public void printInOrder() {
        printInorder(root);
    }

    /* Given a binary tree, print its nodes in inorder*/
    private void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    /* Given a binary tree, print its nodes according to the
     "bottom-up" postorder traversal. */
    private void printPostOrder(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostOrder(node.left);

        // then recur on right subtree
        printPostOrder(node.right);

        // now deal with the node
        System.out.print(node.key + " ");
    }

    public void printPreOrder() {
        printPreorder(root);
    }

    /* Given a binary tree, print its nodes in preorder*/
    private void printPreorder(Node node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
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

    @Override
    public Iterable<Key> keys() {
        List<Key> keys = new List<>();
        if (!isEmpty())
            keys(root, keys, min(), max());
        return keys;
    }

    private void keys(Node x, List<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        if (Compare.lessThan(lo, hi))
            keys(x.left, queue, lo, hi);
        if (lessOrEquals(lo, x.key) && largerOrEquals(hi, x.key))
            queue.enqueue(x.key);
        if (largerThan(hi, x.key))
            keys(x.right, queue, lo, hi);
    }

    private int size(Node x) {
        if (Objects.isNull(x))
            return 0;
        else
            return x.N;
    }

    public void print(){
        new BTreePrinter().printNode(root);
    }


    class BTreePrinter {
        public void printNode(Node root) {
            int maxLevel = maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private void printNodeInternal(java.util.List<Node> nodes, int level, int maxLevel) {
            StringBuilder sb = new StringBuilder();

            if (nodes.isEmpty() || isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            printWhitespaces(firstSpaces);

            java.util.List<Node> newNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        printWhitespaces(1);

                    printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        printWhitespaces(1);

                    printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private String getWhitespaces(int count) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++)
                sb.append(" ");
            return sb.toString();
        }
        private int maxLevel(Node node) {
            if (node == null)
                return 0;

            return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
        }

        private <T> boolean isAllElementsNull(java.util.List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}
