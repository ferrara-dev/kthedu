package graph;

import list.Bag;

public class AbstractGraph {
    private int v;
    private int e; // number of edges
    private Bag<Integer>[] adj; // adjacency lists
    private int capacity;

    public AbstractGraph() {
        capacity = 100;
        adj = (Bag<Integer>[]) new Bag[capacity]; // Create array of lists.
    }

    /**
     * get number of vertices in the graph
     */
    public int vertices() {
        return v;
    }

    /**
     * get number of edges in the graph
     */
    public int edges() {
        return e;
    }

    /**
     * check if graph has edge between v and w
     */
    public boolean hasEdge(int v, int w) {
        return adj[v].contains(w);
    }

    /**
     * add a new vertex v to the graph
     */
    public void addVertex(int v) {
        if (adj[v] == null) {
            if (this.v == capacity - 1)
                resize(capacity * 2);
            adj[v] = new Bag<>();
            this.v++;
        }
    }

    /**
     * add a new edge from v to w
     */
    public void addEdge(int v, int w, boolean bilateral) {
        addVertex(v);
        addVertex(w);
        adj[v].add(w);
        if(bilateral)
            adj[w].add(v);
        e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * Get string representation of graph
     */
    public String toString() {
        String s = v + " vertices, " + e + " edges\n";
        for (int i = 0; i < v; i++) {
            s += i + ": ";
            for (int w : this.adj(i))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    /**
     * Get current max capacity for the graph
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * resize the underlying array
     */
    private void resize(int capacity) {
        Bag[] copy = new Bag[capacity];
        for (int i = 0; i < v; i++) {
            copy[i] = adj[i];
        }
        adj = copy;
        this.capacity = capacity;
    }

}
