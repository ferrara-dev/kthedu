package graph.undirected;

import graph.AbstractGraph;
import list.HashTable;

import java.util.NoSuchElementException;

/**
 * High level abstraction of SimpleGraph data structure.
 *
 * Offers graph client with generic type nodes
 * by utilizing implementation of {@link AbstractGraph}.
 *
 * Maps vertices of type {@link V} to indices in <code> abstractGraph </code>.
 *
 * @param <V>
 */
public class SimpleGraph<V> {
    private HashTable<V, Integer> st; // Vertex -> index
    private V[] keys; // index -> String
    private AbstractGraph abstractGraph;

    public SimpleGraph() {
        abstractGraph = new AbstractGraph();
        st = new HashTable<>();
        keys = (V[]) new Object[abstractGraph.getCapacity()];
    }

    /** Add a new vertex to the graph */
    public boolean addVertex(V toAdd) {
        if (!st.contains(toAdd)) {
            st.put(toAdd, st.size());
            keys[st.get(toAdd)] = toAdd;
            abstractGraph.addVertex(st.get(toAdd));
            return true;
        } else
            return false;
    }

    /** Check if graph has edge from to */
    public boolean hasEdge(V from, V to) {
        return abstractGraph.hasEdge(index(from), index(to));
    }

    /** Add a new edge between from and to */
    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);
        abstractGraph.addEdge(index(from), index(to), true);
    }

    /** Check if graph contains a vertex */
    public boolean contains(V v) {
        return st.contains(v);
    }

    /**
     * Get index that a vertex is mapped to
     * @throws NoSuchElementException if the graph
     *         does not contain the vertex.
     */
    public int index(V v) {
        if(v == null)
            throw new IllegalArgumentException("Null can not be mapped to an index");
        if(!st.contains(v))
            throw new NoSuchElementException();
        return st.get(v);
    }

    /**
     * Get name of vertex that is mapped to index v
     * @throws NoSuchElementException if the graph
     *         does not contain the vertex.
     */
    public V name(int v) {
        if(v >= keys.length || v < 0)
            throw new NoSuchElementException();

        V vert = keys[v];

        if(vert == null)
            throw new NoSuchElementException();

        return vert;
    }

    /** Get base graph with integer representation of vertices */
    public AbstractGraph graph() {
        return abstractGraph;
    }

    /** Get string representation of graph */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Number of vertices = ");
        sb.append(abstractGraph.vertices());
        sb.append("\n");
        sb.append("Number of edges = ");
        sb.append(abstractGraph.edges());
        sb.append("\n");
        for (V v : st.keys()) {
            sb.append(v.toString() + ": ");
            for (Integer w : abstractGraph.adj(index(v))) {
                sb.append(name(w) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
