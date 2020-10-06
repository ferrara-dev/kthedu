package graph.directed;

import graph.undirected.SimpleGraph;

/**
 * This class is part of solution to assignment 3
 *
 * Extends {@link SimpleGraph} so that it does not
 * add bilateral edges to its <code> abstractGraph </code>
 *
 * @param <V>
 */
public class DiGraph<V> extends SimpleGraph<V> {

    public DiGraph(){
        super();
    }

    /** Add a new edge between from and to */
    @Override
    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);
        super.graph().addEdge(index(from), index(to), false);
    }

}
