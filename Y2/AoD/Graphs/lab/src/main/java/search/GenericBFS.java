package search;

import graph.GenericGraph;
import list.FIFOQueue;
import list.HashTable;
import list.Stack;

public class GenericBFS<V> {
    protected final GenericGraph<V> graph;
    protected HashTable<V, Boolean> markedVertices;
    protected HashTable<V, V> edgeToVertex;
    protected V source;

    public GenericBFS(GenericGraph<V> graph, V source) {
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
     * SearchStrategy method Override to implement the
     * depth-first-graph.search algorithm.
     * <p>
     * Called only at instantiation
     *
     * @param
     * @param s
     */
    protected void search(V s) {
        FIFOQueue<V> queue = new FIFOQueue<V>();
        markedVertices.put(s, true); // Mark the source
        queue.enqueue(s); // put the marked source on the queue.

        while (!queue.isEmpty()) {
            V v = queue.dequeue(); // Remove next vertex from the queue.

            for (V w : graph.adjVertices(v)) // For every unmarked adjacent vertex,
                if (!markedVertices.get(w)) {
                    edgeToVertex.put(w, v); // save last edge on a shortest path,
                    markedVertices.put(w, true);
                    queue.enqueue(w); // and add it to the queue.
                }
        }
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
