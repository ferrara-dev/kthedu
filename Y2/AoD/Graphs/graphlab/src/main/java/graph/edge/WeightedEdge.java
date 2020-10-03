package graph.edge;

import graph.Vertex;

public class WeightedEdge<T,W> extends Edge<T,W> {
    private Vertex<T> from;
    private Vertex<T> to;
    private W weight;

    public WeightedEdge(Vertex from, Vertex to, W weight){
        super.from = from;
        super.to = to;
        super.weight = weight;
    }

    @Override
    public W getWeight() {
        return weight;
    }
}
