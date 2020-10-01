package graph;

import search.BFSearch;
import search.DFSearch;
import search.Search;

public interface Graph<T> extends Iterable<T>{
    int numberOfVertices();

    int edges();

    boolean addVertex(T t);

    void addEdge(T t1, T t2);

    Iterable<T> vertices();

    Iterable<T> adjVertices(T t);

    boolean hasEdge(T v, T w);

    boolean hasVertex(T v);

    Class<?> getVertexType();

    Search<T> depthFirstSearch(T source);

}
