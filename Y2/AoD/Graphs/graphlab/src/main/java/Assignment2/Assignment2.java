package Assignment2;

import Assignment1.Input;
import datastruct.list.ArrayList;
import graph.Graph;
import graph.BasicGraph;
import util.in.FileIn;

/**
 * Get the path between two vertices X and Y
 * in a graph.
 *
 * First element in program argument must contain path
 * to file with input.
 *
 */
public class Assignment2 {
    private static String delimiter = " ";

    public static void main(String...args){
        FileIn fileIn = new FileIn(args[0]);
        ArrayList<String> rows = fileIn.getRows();
        Graph<String> graph = new BasicGraph<>(false);
        initGraph(graph, rows);

        String [] input = Input.userInput();


    }

    private static void initGraph(Graph<String> graph, ArrayList<String> rows) {
        for (String row : rows) {
            String[] vertices = row.split(delimiter);
            if (vertices.length == 1)
                graph.addVertex(vertices[0]);
            else
                graph.addEdge(vertices[0], vertices[1]);
        }
    }
}
