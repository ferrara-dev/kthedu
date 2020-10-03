package graph;

import graph.search.SearchStrategy;

public interface Graph<T>{
    int numberOfVertices();

    int edges();

    boolean addVertex(T t);


    void addEdge(T t1, T t2);

    Iterable<T> vertices();

    Iterable<T> adjVertices(T t);

    boolean hasEdge(T v, T w);

    boolean hasVertex(T v);

    boolean hasVertices(T...v);

    boolean isDirected();
}
