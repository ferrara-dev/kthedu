package graph.edge;

public class EdgeFactory<V, E> {
    private final Class<? extends E> edgeClass;


    public EdgeFactory(Class<? extends E> edgeClass) {
        this.edgeClass = edgeClass;
    }

    public E createEdge(V source, V target) {
        try {
            return this.edgeClass.newInstance();
        } catch (Exception var4) {
            throw new RuntimeException("Edge factory failed", var4);
        }
    }
}
