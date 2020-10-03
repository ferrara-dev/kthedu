package graph;


import datastruct.list.ArrayList;
import datastruct.st.HashTable;
import util.in.FileIn;

public class BasicGraph<T> implements Graph<T> {
    protected HashTable<T, ArrayList<T>> adjVertices;
    protected int edgesCount;
    protected final boolean directed;

    public BasicGraph(boolean isDirected) {
        directed = isDirected;
        adjVertices = new HashTable<>();
    }

    @Override
    public int numberOfVertices() {
        return adjVertices.size();
    }

    @Override
    public int edges() {
        return edgesCount;
    }

    @Override
    public boolean addVertex(T t) {
        if (!hasVertex(t)) {
            adjVertices.put(t, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean hasVertex(T v) {
        return adjVertices.contains(v);
    }

    @Override
    public boolean hasVertices(T... v) {
        for (T w : v) {
            if (!hasVertex(w))
                return false;
        }
        return true;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addEdge(T v, T w) {
        addVertex(v);
        addVertex(w);
        if (!hasEdge(v, w))
            edgesCount++;
        adjVertices.get(v).add(w);
        if (!directed)
            adjVertices.get(w).add(v);
    }

    @Override
    public Iterable<T> vertices() {
        return adjVertices.keys();
    }

    public boolean hasEdge(T v, T w) {
        if (!hasVertex(v) || !hasVertex(w))
            return false;
        return adjVertices.get(v).contains(w);
    }

    @Override
    public Iterable<T> adjVertices(T t) {
        return adjVertices.get(t);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T v : adjVertices.keys()) {
            builder.append(v.toString() + ": ");
            for (T w : adjVertices.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private static class GraphTester {
        public static void main(String... args) {


            FileIn fileIn = new FileIn(args[0]);
            String directed = args[1];
            Graph<String> graph = null;
            if(directed.toLowerCase().equals("directed"))
                graph = new BasicGraph<>(true);
            else if(directed.toLowerCase().equals("undirected"))
                graph = new BasicGraph<>(false);
            else
                graph = new BasicGraph<>(false);

            while (fileIn.hasNextLine()) {
                String v[] = fileIn.nextRow().split(" ");
                String fromVertex = v[0];
                String toVertex = v[1];
                graph.addEdge(fromVertex, toVertex);
            }

            for (String v : graph.vertices()) {
                System.out.print(v + " : ");
                for (String w : graph.adjVertices(v)) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        }
    }
}
