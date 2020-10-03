package graph.edge;

import graph.Vertex;

public abstract class Edge<T,W> {
    protected Vertex<T> from;
    protected Vertex<T> to;
    protected W weight;

    public Vertex getFrom(){
        return from;
    }

    public Vertex getTo(){
        return to;
    }

    public abstract W getWeight();

}
