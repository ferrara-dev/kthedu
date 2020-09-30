package search;

import graph.Graph;
import list.FIFOQueue;
import list.Stack;

public class BreadthFirstPaths implements Paths {
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.vertices()];
        edgeTo = new int[G.vertices()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        FIFOQueue<Integer> queue = new FIFOQueue<Integer>();
        marked[s] = true; // Mark the source
        queue.enqueue(s); // and put it on the queue.
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // Remove next vertex from the queue.
            for (int w : G.adj(v))
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
                }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public boolean marked(int w) {
        return marked[w];
    }

    @Override
    public int getSource() {
        return s;
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}