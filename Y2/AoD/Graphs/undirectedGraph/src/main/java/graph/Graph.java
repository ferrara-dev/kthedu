package graph;

public interface Graph {

    /** @return number of vertices in the graph*/
    int vertices();

    /** @return number of edges in the graph*/
    int edges();

    /** Add a new edge to the graph */
    void addEdge(int v, int w);

    /** @return Iterable of vertices adjacent to v */
    Iterable<Integer> adj(int v);

    /** @return String representation of the graph */
    String toString();
}
