package Assignment1;

import datastruct.list.LinkedList;
import graph.Graph;
import graph.GraphImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    private static String delimiter = " ";
    private static Scanner userIn = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        Graph<String> graph = new GraphImpl<>();
        LinkedList<String> input = getInput(filename);
        initGraph(graph,input);
        String source = userIn();
        while (!source.toLowerCase().equals("exit")) {
            for (String w : graph.adjVertices(source)) {
                System.out.println(" " + w);
            }
            source = userIn();
        }
    }

    private static String userIn(){
        System.out.print(" >>> ");
        return userIn.nextLine();
    }

    private static void initGraph(Graph<String> graph, LinkedList<String> rows) {
        for (String row : rows) {
            String[] vertices = row.split(delimiter);
            if (vertices.length == 1)
                graph.adjVertices(vertices[0]);
            else
                graph.addEdge(vertices[0], vertices[1]);
        }
    }

    private static LinkedList<String> getInput(String fileName) throws FileNotFoundException {
        LinkedList<String> rows = new LinkedList<>();
        Scanner scanner = new Scanner(new FileInputStream(new File(fileName)));
        while (scanner.hasNextLine()) {
            rows.add(scanner.nextLine());
        }
        return rows;
    }
}
