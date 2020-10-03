package graph;

import datastruct.list.ArrayList;
import datastruct.st.HashTable;
import graph.search.DFSearch;
import graph.search.SearchStrategy;

import java.util.Objects;

/**
 * PathFinder class.
 *
 * Class responsible for finding paths between
 * vertices in a graph.
 *
 * Implemented on top of SearchStrategy implementations
 * of Depth-First-Search, Breath-First-Search etc.
 *
 * {@code searches} is used to cache instances of {@link SearchStrategy} that have been
 *                  instantiated with a {@link Vertex} source, {@code graph} must contain the source
 *
 * @param <Vertex>
 */
public class PathFinder<Vertex>{
    private final Graph graph;
    private HashTable<Vertex, HashTable<SearchType, SearchStrategy>> searches; //

    /**
     * Initialized with implementation of {@link Graph<Vertex>}.
     *
     * @param graph
     */
    public PathFinder(Graph<Vertex> graph) {
        searches = new HashTable<>();
        this.graph = graph;
    }

    /**
     * Find path between <code> source </code> and <code> destination </code>
     * using Depth-First-Search algorithm.
     */
    public Path dfsPath(Vertex source, Vertex destination) {
        SearchStrategy strategy = strategy(SearchType.DFS,source);
        Path path = getPath(source,destination,strategy);
        return path;
    }

    /**
     * Find path between <code> source </code> and <code> destination </code>
     * using Breath-First-Search algorithm.
     * @param source
     * @param destination
     * @return
     */
    public Path bfsPath(Vertex source, Vertex destination) {
        SearchStrategy strategy = strategy(SearchType.BFS,source);
        Path path = getPath(source,destination,strategy);
        return path;
    }

    /**
     * Check if a path exists between <code> source </code> and
     * <code> destination </code>.
     *
     * @param source
     * @param destination
     * @return true if path exists.
     */
    public boolean pathExists(Vertex source, Vertex destination){
        SearchStrategy strategy = strategy(SearchType.BFS,source);
        return strategy.hasPathTo(destination);
    }

    /**
     * Get the path from <code> source </code> to <code> destination </code>
     * using <code> strategy </code>.
     *
     * @param source
     * @param destination TODO : implement case for when not in graph
     * @param strategy
     * @return
     */
    private Path getPath(Vertex source, Vertex destination,SearchStrategy strategy){
        nullCheck(source,destination);
        if(!graph.hasVertex(source))
            throw new IllegalArgumentException("Source must be in graph !");
        if(!graph.hasVertex(source))
            throw new IllegalArgumentException("destination must be in graph !");

        ArrayList<Vertex> lst = new ArrayList(strategy.pathTo(destination));
        Path path = Path.builder()
                .source(source)
                .destination(destination)
                .verticesOnPath(lst)
                .build();
        return path;
    }

    private SearchStrategy strategy(SearchType searchType, Vertex source){
        if (!searches.contains(source)) {
            searches.put(source, new HashTable<>());
            searches.get(source).put(searchType, new DFSearch(graph, source));
        }
        else if (!searches.get(source).contains((searchType))) {
            searches.get(source).put(searchType, new DFSearch(graph, source));
        }

        SearchStrategy searchStrategy;
        searchStrategy = searches.get(source).get(searchType);
        return searchStrategy;
    }

    /**
     * utility method used to check if input parameters are null
     *
     * @throws IllegalArgumentException if any of the params are null
     */
    private void nullCheck(Object... objects) {
        for (Object object : objects) {
            if (Objects.isNull(object)) {
                throw new IllegalArgumentException("PathFinder can not operate on null value vertices!");
            }
        }
    }

    enum SearchType{
        DFS,
        BFS;
    }
}
