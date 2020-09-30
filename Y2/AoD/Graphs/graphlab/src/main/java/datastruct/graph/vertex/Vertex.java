package datastruct.graph.vertex;

public class Vertex<T> {
    private T name;
    private boolean wasVisited;

    public Vertex(T name){
        this.name = name;
        wasVisited = false;
    }

    public T getName() {
        return name;
    }

    public boolean isWasVisited() {
        return wasVisited;
    }
}
