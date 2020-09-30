package search;


import list.Stack;

public interface Paths {
    //is v connected to s?
    boolean marked(int v);

    int getSource();

    Iterable<Integer> pathTo(int v);


    boolean hasPathTo(int v);
}
