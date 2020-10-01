package search;


import datastruct.list.Stack;
import datastruct.st.HashTable;
import graph.Graph;

public abstract class Search<V>{
    protected final HashTable<V, Boolean> markedVertices;
    protected final HashTable<V, V> edgeToVertex;
    protected final V source;

    public Search(Graph<V> graph, V source){
        this.source = source;
        markedVertices = new HashTable<>();
        edgeToVertex = new HashTable<>();

        for (V vertex : graph.vertices()) {
            markedVertices.put(vertex, false);
        }

        search(graph, source);
    }

    abstract protected void search(Graph<V> graph, V s);

    /** Check if <code> source </code> vertex has a path to <code> destination </code>*/
    public boolean hasPathTo(V destination) {
        return markedVertices.get(destination);
    }

    /** return path from <code> source </code> to <code> destination </code> if exists */
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination))
            return null;
        Stack<V> path = new Stack<V>();
        for (V x = destination; x != source; x = edgeToVertex.get(x))
            path.push(x);
        path.push(source);
        return path;
    }

    /** Get source vertex */
    public V getSource() {
        return source;
    }

    public enum SearchType{
        BFS,
        DFS;
    }

}
