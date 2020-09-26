package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rene Argento on 18/07/17.
 */
@SuppressWarnings("unchecked")
public class Exercise3 {

    private class SeparateChainingHashTableLinkedListWithDeleteK<Key, Value> {

        private class Node {
            Key key;
            Value value;
            int numberOfKeysAtTimeOfInsert;
            Node next;

            public Node(Key key, Value value, Node next, int numberOfKeysAtTimeOfInsert) {
                this.key = key;
                this.value = value;
                this.next = next;
                this.numberOfKeysAtTimeOfInsert = numberOfKeysAtTimeOfInsert;
            }
        }

        private List<Node> buckets;
        private int keysSize;
        private int numberOfBuckets;

        private int averageListSize;

        //The largest prime <= 2^i for i = 1 to 31
        //Used to distribute keys uniformly in the hash table after resizes
        //PRIMES[n] = 2^k - Ak where k is the power of 2 and Ak is the value to subtract to reach the previous prime number
        private final int[] PRIMES = {
                1, 1, 3, 7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
                32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
                8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
                536870909, 1073741789, 2147483647
        };

        //The lg of the hash table size
        //Used in combination with PRIMES[] to distribute keys uniformly in the hash function after resizes
        private int lgM;

        SeparateChainingHashTableLinkedListWithDeleteK(int numberOfBuckets, int averageListSize) {
            keysSize = 0;
            this.averageListSize = averageListSize;

            this.numberOfBuckets = numberOfBuckets;
            buckets = new ArrayList<>(numberOfBuckets);

            for (int i = 0; i < numberOfBuckets; i++) {
                buckets.add(null);
            }

            lgM = (int) (Math.log(numberOfBuckets) / Math.log(2));
        }

        private int hash(Key key) {
            int hash = key.hashCode() & 0x7fffffff;

            if (lgM < 26) {
                hash = hash % PRIMES[lgM + 5];
            }

            return hash % numberOfBuckets;
        }

        private double getLoadFactor() {
            return ((double) keysSize) / (double) numberOfBuckets;
        }

        public boolean isEmpty() {
            return keysSize == 0;
        }

        public int size() {
            return keysSize;
        }

        public boolean contains(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to contains() cannot be null");
            }

            return get(key) != null;
        }

        private void resize(int newBucketSize) {

            List<Node> tempBuckets = buckets;
            buckets = new ArrayList<>(newBucketSize);

            numberOfBuckets = newBucketSize;
            keysSize = 0;

            for (int i = 0; i < newBucketSize; i++) {
                buckets.add(null);
            }

            for (Node node : tempBuckets) {
                while (node != null) {
                    put(node.key, node.value, node.numberOfKeysAtTimeOfInsert);
                    node = node.next;
                }
            }
        }

        public Value get(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to get() cannot be null");
            }

            //Get node at the head of the bucket
            Node node = buckets.get(hash(key));

            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }

            return null;
        }

        public void put(Key key, Value value) {
            put(key, value, -1);
        }

        private void put(Key key, Value value, int numberOfKeysAtTimeOfInsert) {
            if (key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }

            if (value == null) {
                delete(key);
                return;
            }

            //Get node at the head of the bucket
            int bucketIndex = hash(key);
            Node node = buckets.get(bucketIndex);

            //Update key value if it already exists
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }

                node = node.next;
            }

            keysSize++;
            node = buckets.get(bucketIndex);
            Node newNode;

            if (numberOfKeysAtTimeOfInsert != -1) {
                //this is a resize operation
                newNode = new Node(key, value, node, numberOfKeysAtTimeOfInsert);
            } else {
                newNode = new Node(key, value, node, keysSize);
            }

            buckets.set(bucketIndex, newNode);

            if (getLoadFactor() > averageListSize) {
                resize(numberOfBuckets * 2);
                lgM++;
            }
        }

        public void delete(Key key) {
            if (key == null) {
                throw new IllegalArgumentException("Argument to delete() cannot be null");
            }

            if (isEmpty() || !contains(key)) {
                return;
            }

            int bucketIndex = hash(key);
            Node node = buckets.get(bucketIndex);

            keysSize--;

            if (node.key.equals(key)) {
                buckets.set(bucketIndex, node.next);
            } else {
                while (node != null) {
                    if (node.next.key.equals(key)) {
                        node.next = node.next.next;
                        break;
                    }

                    node = node.next;
                }
            }

            if (numberOfBuckets > 1 && getLoadFactor() <= averageListSize / (double) 4) {
                resize(numberOfBuckets / 2);
                lgM--;
            }
        }

    }

    public static void main(String[] args) {
        Exercise3 exercise3 = new Exercise3();
        SeparateChainingHashTableLinkedListWithDeleteK<Integer, Integer> separateChainingHashTableLinkedListWithDeleteK =
                exercise3.new SeparateChainingHashTableLinkedListWithDeleteK<>(5, 10);

        separateChainingHashTableLinkedListWithDeleteK.put(1, 1);
        separateChainingHashTableLinkedListWithDeleteK.put(2, 2);
        separateChainingHashTableLinkedListWithDeleteK.put(3, 3);
        separateChainingHashTableLinkedListWithDeleteK.put(4, 4);
        separateChainingHashTableLinkedListWithDeleteK.put(5, 5);
        separateChainingHashTableLinkedListWithDeleteK.put(6, 6);
        String s = "Aa";
        String s1 = "BB";
        System.out.println(s.hashCode() + "   " + s1.hashCode());
    }
}