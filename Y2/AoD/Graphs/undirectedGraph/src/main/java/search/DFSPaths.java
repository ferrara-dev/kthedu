package search;

import graph.Graph;
import graph.UndirectedGraph;
import list.Stack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFSPaths implements Paths {
    private boolean[] marked;
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s;
    private int count;

    public DFSPaths(Graph G, int s) {
        marked = new boolean[G.vertices()];
        edgeTo = new int[G.vertices()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int getSource() {
        return s;
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static class DFSSearchTester {
        /**
         * Unit tests the {@code DepthFirstSearch} data type.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) throws FileNotFoundException {
            Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
            Graph G = new UndirectedGraph(scanner);
            System.out.println(G.toString());
            int s = Integer.parseInt(args[1]);
            DFSPaths search = new DFSPaths(G, s);

            for (int v = 0; v < G.vertices(); v++) {
                if (search.marked(v)) {
                    System.out.print(v + " ");
                }
            }
            System.out.println();
            if (search.count() != G.vertices())
                System.out.println("Not connected");
            else
                System.out.println("Connected");
        }
    }
}