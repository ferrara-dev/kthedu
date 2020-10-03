package graph.edge;

import graph.Vertex;

public class DefaultEdge<T> extends Edge{
    public DefaultEdge(Vertex<T> from, Vertex <T> to){
        super.from = from;
        super.to = to;
    }

    @Override
    public Object getWeight() {
        return null;
    }
}
