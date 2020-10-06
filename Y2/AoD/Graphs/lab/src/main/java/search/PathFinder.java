package search;

import graph.undirected.SimpleGraph;

import list.HashTable;
import list.Stack;

import java.util.NoSuchElementException;

/**
 * PathFinder class.
 * <p>
 * Class responsible for finding paths between
 * vertices in a graph.
 * <p>
 * Implemented on top of SearchStrategy implementations
 * of Depth-First-Search, Breath-First-Search etc.
 * <p>
 * {@code searches} is used to cache instances of {@link SearchStrategy} that have been
 * instantiated with a {@link V} source, {@code graph} must contain the source.
 *
 * @param <V>
 */
public class PathFinder<V> {
    private SimpleGraph<V> graph;
    private HashTable<V, HashTable<SearchType, SearchStrategy>> searches;

    public PathFinder(SimpleGraph<V> graph) {
        this.graph = graph;
        searches = new HashTable<>();
    }

    public Iterable<V> dfsPath(V source, V destination) {
        if(!graph.contains(source) || !graph.contains(destination))
            throw new NoSuchElementException();

        if (searches.contains(source)) {
            if (!searches.get(source).contains(SearchType.DFS))
                searches.get(source).put(SearchType.DFS, new DFSearch(graph, source));
        } else {
            searches.put(source, new HashTable<>());
            searches.get(source).put(SearchType.DFS, new DFSearch(graph, source));
        }
        if(searches.get(source).get(SearchType.DFS).hasPathTo(destination))
            return searches.get(source).get(SearchType.DFS).pathTo(destination);
        else
            return new Stack<>();
    }

    enum SearchType {
        DFS,
        BFS;
    }
}
