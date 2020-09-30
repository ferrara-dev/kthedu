package search;


public interface Paths <Vertex>{
    //is v connected to s?
    boolean marked(Vertex v);

    int getSource();

    Iterable<Vertex> pathTo(Vertex v);

    boolean hasPathTo(Vertex v);
}
