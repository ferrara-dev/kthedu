package graph;

import list.HashSet;
import list.HashTable;


public class GenericGraph<T> {
    protected HashTable<T, HashSet<T>> adjVertices;
    protected int edgesCount;
    protected final boolean directed;

    public GenericGraph(boolean isDirected) {
        directed = isDirected;
        adjVertices = new HashTable<>();
    }


    public int numberOfVertices() {
        return adjVertices.size();
    }


    public int edges() {
        return edgesCount;
    }


    public boolean addVertex(T t) {
        if (!hasVertex(t)) {
            adjVertices.put(t, new HashSet<>());
            return true;
        }
        return false;
    }


    public boolean hasVertex(T v) {
        return adjVertices.contains(v);
    }


    public boolean hasVertices(T... v) {
        for (T w : v) {
            if (!hasVertex(w))
                return false;
        }
        return true;
    }


    public boolean isDirected() {
        return directed;
    }

    public void addEdge(T v, T w) {
        addVertex(v);
        addVertex(w);
        if (!hasEdge(v, w))
            edgesCount++;
        adjVertices.get(v).add(w);
        if (!directed)
            adjVertices.get(w).add(v);
    }


    public Iterable<T> vertices() {
        return adjVertices.keys();
    }

    public boolean hasEdge(T v, T w) {
        if (!hasVertex(v) || !hasVertex(w))
            return false;
        return adjVertices.get(v).contains(w);
    }

    public Iterable<T> adjVertices(T t) {
        return adjVertices.get(t).keys();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T v : adjVertices.keys()) {
            builder.append(v.toString() + ": ");
            for (T w : adjVertices.get(v).keys()) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
