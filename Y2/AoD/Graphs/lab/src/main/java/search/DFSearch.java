package search;

import graph.undirected.SimpleGraph;
import list.Stack;

public class DFSearch<V> extends SearchStrategy<V> {
    private boolean[] marked; // Has dfs() been called for this vertex?
    private int[] edgeTo; // last vertex on known path to this vertex

    public DFSearch(SimpleGraph<V> graph, V source) {
        super(graph, source);
    }

    @Override
    protected void init(int s) {
        marked = new boolean[abstractGraph.vertices()];
        edgeTo = new int[abstractGraph.vertices()];
        dfs(s);
    }

    /**
     * Steps of the algorithm :
     * 1. set v as visited
     * 2. Iterate through vertices adjacent to v
     * 3. Continue until adjacent vertex w that has not been visited is encountered
     * 4. Set edge to w as v.
     * 4. Repeat for w.
     * @param v
     */
    private void dfs(int v) {
        marked[v] = true;
        for (int w : abstractGraph.adj(v))
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);
            }
    }

    @Override
    public boolean hasPathTo(V v) {
        return marked[graph.index(v)];
    }

    @Override
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
