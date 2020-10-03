package graph;

import datastruct.list.ArrayList;
import datastruct.st.HashTable;

/**
 * TODO for higher grade assignment
 * @param <Vertex>
 * @param <Weight>
 */
public class WeightedGraph<Vertex, Weight> implements Graph<Vertex>{
    protected HashTable<Vertex, ArrayList<Edge>> adjVertices;
    protected int edgesCount;
    protected boolean directed;

    private static class Edge<Vertex, Weight> {
        Vertex from;
        Vertex to;
        Weight weight;

        public Edge(Vertex from, Vertex to, Weight weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    @Override
    public int numberOfVertices() {
        return 0;
    }

    @Override
    public int edges() {
        return 0;
    }

    @Override
    public boolean addVertex(Vertex vertex) {
        return false;
    }

    @Override
    public void addEdge(Vertex t1, Vertex t2) {

    }

    @Override
    public Iterable<Vertex> vertices() {
        return null;
    }

    @Override
    public Iterable<Vertex> adjVertices(Vertex vertex) {
        return null;
    }

    @Override
    public boolean hasEdge(Vertex v, Vertex w) {
        return false;
    }

    @Override
    public boolean hasVertex(Vertex v) {
        return false;
    }

    @Override
    public boolean hasVertices(Vertex... v) {
        return false;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }


}
