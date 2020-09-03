package E5;

import datastructure.Queue;

public interface GeneralizedQueue<Item> extends Queue<Item> {
    public Item remove(int index);
}
