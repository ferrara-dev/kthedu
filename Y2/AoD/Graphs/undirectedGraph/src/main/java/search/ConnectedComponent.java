package search;

import graph.Graph;
import graph.UndirectedGraph;
import list.Bag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This Graph client provides its clients with the ability to independently process a graph’s connected
 * components.
 *
 * The computation is based on a vertex-indexed array id[] such that id[v] is set to i if v is in the ith
 * connected component processed.
 *
 * The constructor finds an unmarked vertex and calls the recursive dfs() to mark and identify
 * all the vertices connected to it, continuing until all vertices have been marked and identified. Implementations
 * of the instance methods connected(), id(), and count() are immediate.
 */
public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponent(Graph G) {
        marked = new boolean[G.vertices()];
        id = new int[G.vertices()];
        for (int s = 0; s < G.vertices(); s++)
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }


    //are v and w connected?
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // number of connected components
    int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new UndirectedGraph(new Scanner(new FileInputStream(new File(args[0]))));
        ConnectedComponent cc = new ConnectedComponent(graph);
        int M = cc.count();
        System.out.println(M + " components");
        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<Integer>();
        for (int v = 0; v < graph.vertices(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++) {
            for (int v : components[i])
                System.out.print(v + " ");
            System.out.println();
        }
    }
}
