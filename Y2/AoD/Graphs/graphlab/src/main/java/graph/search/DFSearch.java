package graph.search;

import datastruct.st.HashTable;
import graph.Graph;
import graph.BasicGraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementation of abstract class {@link }
 *
 * @param <V>
 */

public class DFSearch<V> extends SearchStrategy<V> {
    private HashTable<V, V> id = new HashTable<>();
    private int count;

    public DFSearch(Graph<V> graph, V source) {
        super(graph, source);
    }

    @Override
    protected void init() {
        markedVertices = new HashTable<>();
        edgeToVertex = new HashTable<>();

        for (V vertex : graph.vertices()) {
            markedVertices.put(vertex, false);
        }
    }

    /**
     * SearchStrategy method Overridden to implement the
     * depth-first-graph.search algorithm.
     * <p>
     * Called only at instantiation
     */
    @Override
    protected void search(V v) {
        markedVertices.put(v, true);
        count++;
        for (V w : graph.adjVertices(v)) {
            if (!markedVertices.get(w)) {
                edgeToVertex.put(w, v);
                search(w);
            }
        }

    }

    public int count() {
        return count;
    }

    public static class DFSTester {
        public static void main(String... args) throws FileNotFoundException {
            Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
            Graph<Integer> graph = new BasicGraph<Integer>(false);

            while (scanner.hasNextLine()) {
                String in = scanner.nextLine();
                String[] nums = in.split(" ");
                int v = Integer.parseInt(nums[0]);
                int w = Integer.parseInt(nums[1]);
                graph.addVertex(v);
                graph.addVertex(w);
                graph.addEdge(v, w);
            }

            System.out.println(graph.toString());

            Integer source = 0;
            DFSearch<Integer> dfSearch = new DFSearch(graph, source);
            for (Integer i : dfSearch.pathTo(5)) {
                if (i == source)
                    System.out.print(i);
                else
                    System.out.print("-" + i);
            }
        }
    }
}
