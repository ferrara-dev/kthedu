package graph;


import datastruct.list.ArrayList;
import datastruct.st.HashTable;
import search.DFSearch;
import search.Search;

import java.util.Iterator;

public class GraphImpl<T> implements Graph<T> {
    private HashTable<T, ArrayList<T>> adjVertices;
    private int edgesCount;

    public GraphImpl() {
        adjVertices = new HashTable<>();
    }

    @Override
    public int numberOfVertices() {
        return adjVertices.size();
    }

    @Override
    public int edges(){
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

    public boolean hasVertex(T v) {
        return adjVertices.contains(v);
    }

    @Override
    public void addEdge(T v, T w) {
        addVertex(v);
        addVertex(w);
        if (!hasEdge(v, w))
            edgesCount++;
        adjVertices.get(v).add(w);
        adjVertices.get(w).add(v);
    }

    @Override
    public Iterable<T> vertices() {
        return adjVertices.keys();
    }

    public boolean hasEdge(T v, T w) {
        if(!hasVertex(v) || !hasVertex(w))
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
        for (T v: adjVertices.keys()) {
            builder.append(v.toString() + ": ");
            for (T w: adjVertices.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public Class<?> getVertexType() {
        Iterator it = adjVertices.keys().iterator();
        if (it != null && it.hasNext()) {
            return it.next().getClass();
        } else
            return null;
    }

    @Override
    public Search<T> depthFirstSearch(T source) {
        Search<T> search = new DFSearch<T>(this,source);
        return search;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


    private static class GraphTester {
        public static void main(String... args) {
            GraphImpl<String> graph = new GraphImpl<>();

            graph.addEdge("A", "B");
            graph.addEdge("A", "C");
            graph.addEdge("C", "D");
            graph.addEdge("D", "E");
            graph.addEdge("D", "G");
            graph.addEdge("E", "G");
            graph.addVertex("H");

            System.out.println(graph.getVertexType());
            System.out.println(graph);

            System.out.println("Vertices: " + graph.numberOfVertices());
            System.out.println("Edges: " + graph.edges());

        }
    }
}
