package graph.search;

import datastruct.list.FIFOQueue;
import graph.Graph;

public class BFSearch<V> extends Search<V> {

    public BFSearch(Graph<V> graph, V source) {
        super(graph,source);
    }

    /**
     * Search method Override to implement the
     * depth-first-graph.search algorithm.
     *
     * Called only at instantiation
     * @param graph
     * @param s
     */
    protected void search(Graph<V> graph, V s) {
        FIFOQueue<V> queue = new FIFOQueue<V>();
        markedVertices.put(s, true); // Mark the source
        queue.enqueue(s); // put the marked source on the queue.

        while (!queue.isEmpty()) {
            V v = queue.dequeue(); // Remove next vertex from the queue.

            for (V w : graph.adjVertices(v)) // For every unmarked adjacent vertex,
                if (!super.hasPathTo(w)) {
                    edgeToVertex.put(w, v); // save last edge on a shortest path,
                    markedVertices.put(w,true);
                    queue.enqueue(w); // and add it to the queue.
                }
        }
    }

}
