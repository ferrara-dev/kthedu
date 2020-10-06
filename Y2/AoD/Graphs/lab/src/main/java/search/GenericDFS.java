package search;

import graph.GenericGraph;
import list.HashTable;
import list.Stack;

public class GenericDFS<V> {
    protected final GenericGraph<V> graph;
    protected HashTable<V, Boolean> markedVertices;
    protected HashTable<V, V> edgeToVertex;
    protected V source;

    public GenericDFS(GenericGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        markedVertices = new HashTable<>();
        edgeToVertex = new HashTable<>();

        for (V vertex : graph.vertices()) {
            markedVertices.put(vertex, false);
        }

        search(source);
    }

    /**
     * SearchStrategy method Overridden to implement the
     * depth-first-graph.search algorithm.
     * <p>
     * Called only at instantiation
     *
     * if visited[at] = true
     *      return
     * visited[at] = true
     *
     * neighbours = graph.adj(v)
     * for vertex in neighbours
     *  dfs(vertex)
     *
     */
    protected void search(V v) {
        if(markedVertices.get(v))
            return;
        markedVertices.put(v, true);
        for(V w : graph.adjVertices(v))
            search(w);
    }


    public boolean hasPathTo(V v) {
        return markedVertices.get(v);
    }

    public Iterable<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        Stack<V> path = new Stack<V>();
        for (V x = v; x != source; x = edgeToVertex.get(x))
            path.push(x);
        path.push(source);
        return path;
    }
}
