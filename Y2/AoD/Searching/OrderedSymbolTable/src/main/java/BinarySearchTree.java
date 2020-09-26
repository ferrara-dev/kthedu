public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    class Node {
        private Key key; // key
        private Value val; // associated value
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * If key < x.key :
     *
     * Change key’s value to val if key in subtree rooted at x.
     * Otherwise, add new node to subtree associating key with val.
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * Traverse the tree recursively
     * if(key < x):
     * Step down to the left.
     * if(key > x):
     * step down to hte right
     * else
     * the right node is found
     *
     * @param x
     * @param key
     *
     * @return
     */
    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    private static class TreeTester{
        public static void main(String...args){
            BinarySearchTree<String, Integer> binarySearchTree = new BinarySearchTree<>();
            String input = "Abc Abc Abc aa a";
            String [] splitInput = input.split(" ");
            for(String word : splitInput){
                if(binarySearchTree.get(word) == null){
                    binarySearchTree.put(word,1);
                }
                else {
                   int occurances =  binarySearchTree.get(word);
                   binarySearchTree.put(word,occurances+1);
                }
            }

            int a  = 0;
        }
    }
}
