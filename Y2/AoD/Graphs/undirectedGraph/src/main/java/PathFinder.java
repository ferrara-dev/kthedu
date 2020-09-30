import graph.Graph;
import graph.UndirectedGraph;
import list.LinkedList;
import search.BreadthFirstPaths;
import search.DFSPaths;
import search.Paths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PathFinder {
    private final Paths paths;

    public PathFinder(Paths paths) {
        this.paths = paths;
    }

    /**
     * Returns list of vertices in path from <code> source </code> to <code> destination </code>,
     * including the source and destination vertices.
     *
     * @param destination
     *
     * @return {@link LinkedList<Integer>} with vertices or null if the path does not exist.
     */
    private LinkedList pathsTo(int destination) {
        LinkedList<Integer> traversedVertices = null;
        int from = paths.getSource();
        System.out.print(from + " to " + destination + ": ");
        boolean pathExists = paths.hasPathTo(destination);
        if (pathExists) {
            traversedVertices = new LinkedList<>();
            traversedVertices.addAll(paths.pathTo(destination));
            for (int x : traversedVertices)
                if (x == from)
                    System.out.print(x);
                else
                    System.out.print("-" + x);
            System.out.println();
        }
        return traversedVertices;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new UndirectedGraph(new Scanner(new FileInputStream(new File(args[0]))));
        int source = Integer.parseInt(args[1]);
        int destination = Integer.parseInt(args[2]);
        String searchImpl = args[3].toUpperCase().toLowerCase();
        Paths search = null;

        switch (searchImpl) {
            case "bfs":
                search = new BreadthFirstPaths(graph,source);
                break;
            default:
                search = new DFSPaths(graph, source);
        }

        PathFinder paths = new PathFinder(search);
        LinkedList<Integer> path = paths.pathsTo(destination);
        System.out.println(path.toString());
    }


}
