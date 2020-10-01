package graph.search;

import datastruct.st.HashTable;
import graph.Graph;
import graph.GraphImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementation of abstract class {@link Search}
 * @param <T>
 */
/**
 * dfs(node u)
 *   for each node v connected to u :
 *     if v is not visited :
 *       visited[v] = true
 *       dfs(v)
 *
 *
 * for each node u:
 *   if u is not visited :
 *     visited[u] = true
 *     connected_component += 1
 *     dfs(u)
 */
public class DFSearch<T> extends Search<T>{
    private HashTable<T,T> id = new HashTable<>();
    private int count;

    public DFSearch(Graph<T> graph, T source) {
        super(graph,source);
    }

    /**
     * Search method Overridden to implement the
     * depth-first-graph.search algorithm.
     *
     * Called only at instantiation
     * @param graph the graph that is searched
     * @param v
     */
    @Override
    protected void search(Graph<T> graph, T v) {
        markedVertices.put(v,true);
        count++;
        for (T w : graph.adjVertices(v)){
            if(!markedVertices.get(w)){
                edgeToVertex.put(w,v);
                search(graph, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public static class DFSTester{
        public static void main(String...args) throws FileNotFoundException {
            Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
            Graph<Integer> graph = new GraphImpl<>();

            while (scanner.hasNextLine()){
                String in = scanner.nextLine();
                String [] nums = in.split(" ");
                int v = Integer.parseInt(nums[0]);
                int w = Integer.parseInt(nums[1]);
                graph.addVertex(v);
                graph.addVertex(w);
                graph.addEdge(v,w);
            }
            System.out.println(graph.toString());

            Integer source = 0;
            DFSearch<Integer> dfSearch = new DFSearch(graph,source);
            for(Integer i : dfSearch.pathTo(5)){
                if (i == source)
                    System.out.print(i);
                else
                    System.out.print("-" + i);
            }
        }
    }
}
