package search;

import graph.undirected.SimpleGraph;
import list.FIFOQueue;
import list.Stack;

public class BFSearch<V> extends SearchStrategy<V>{
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; //


    public BFSearch(SimpleGraph<V> graph, V source) {
       super(graph,source);
    }

    protected void init(int s) {
        marked = new boolean[abstractGraph.vertices()];
        edgeTo = new int[abstractGraph.vertices()];
        bfs(s);
    }

    private void bfs(int s) {
        FIFOQueue<Integer> queue = new FIFOQueue<Integer>();
        marked[s] = true; // Mark the source
        queue.enqueue(s); // and put it on the queue.
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // Remove next vertex from the queue.
            for (int w : abstractGraph.adj(v))
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
                }
        }
    }

    public boolean hasPathTo(V v) {
        return marked[graph.index(v)];
    }

    public Iterable<V> pathTo(V v) {
        Stack<V> path = new Stack<V>();
        if (!hasPathTo(v))
            return path;
        for (int x = graph.index(v); x != source; x = edgeTo[x])
            path.push(graph.name(x));
        path.push(graph.name(source));
        return path;
    }

}
