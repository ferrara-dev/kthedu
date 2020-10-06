package search;

import graph.AbstractGraph;
import graph.undirected.SimpleGraph;

public abstract class SearchStrategy<V> {
    protected final int source;
    protected final AbstractGraph abstractGraph;
    protected final SimpleGraph<V> graph;

    public SearchStrategy(SimpleGraph<V> graph, V source) {
        if(!graph.contains(source))
            throw new IllegalArgumentException("The graph must contain the source vertex");

        this.source = graph.index(source);
        this.abstractGraph = graph.graph();
        this.graph = graph;
        init(this.source);
    }

    protected abstract void init(int s);

    public abstract boolean hasPathTo(V v);

    public abstract Iterable<V> pathTo(V v);
}
