package graph.search;


import datastruct.list.Stack;
import datastruct.st.HashTable;
import graph.Graph;


public abstract class SearchStrategy<V> {
    protected final Graph<V> graph;
    protected HashTable<V, Boolean> markedVertices;
    protected HashTable<V, V> edgeToVertex;
    protected V source;

    public SearchStrategy(Graph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        init();
        search(source);
    }

    abstract protected void init();

    abstract protected void search(V s);

    /**
     * Check if <code> source </code> vertex has a path to <code> destination </code>
     */
    public boolean hasPathTo(V destination) {
        return markedVertices.get(destination);
    }

    /**
     * return path from <code> source </code> to <code> destination </code> if exists
     */
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination))
            return null;
        Stack<V> path = new Stack<V>();
        for (V x = destination; x != source; x = edgeToVertex.get(x))
            path.push(x);
        path.push(source);
        return path;
    }

    /**
     * Get source vertex
     */
    public V getSource() {
        return source;
    }

    public boolean sourceEquals(V source) {
        return this.source == source;
    }

    public void setNewSource(V newSource) {
        this.source = newSource;
        search(source);
    }


}
