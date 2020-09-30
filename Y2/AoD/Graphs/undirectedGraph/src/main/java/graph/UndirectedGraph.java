package graph;

import list.Bag;
import util.Input;

import java.util.Scanner;

public class UndirectedGraph implements Graph {
    private final int v;
    private int e; // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    public UndirectedGraph(int v) {
        this.v = v;
        this.e = 0;
        init();
    }

    public UndirectedGraph(Scanner input) {
        this(input.nextInt());
        e = input.nextInt();
        int c = e;
        for (int i = 0; i < c; i++) { // Add an edge.
            int v = input.nextInt(); // Read a vertex,
            int w = input.nextInt(); // read another vertex,
            addEdge(v, w); // and add edge connecting them.
        }
    }

    /**
     * initialize the adjacency list
     */
    private void init() {
        adj = (Bag<Integer>[]) new Bag[v]; // Create array of lists.
        for (int i = 0; i < v; i++) // Initialize all lists
            adj[i] = new Bag<Integer>(); // to empty.
    }

    @Override
    public int vertices() {
        return v;
    }

    @Override
    public int edges() {
        return e;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Add v to w’s list.
        e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
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
}
