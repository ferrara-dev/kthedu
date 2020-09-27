package datastruct.st;

import java.util.Map;

public interface ST<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    int size();
    int rank(Key key);
    boolean contains(Key key);
    Iterable<Key> keys();
}
