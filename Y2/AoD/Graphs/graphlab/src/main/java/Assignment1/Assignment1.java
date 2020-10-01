package Assignment1;

import datastruct.list.Bag;
import datastruct.list.LinkedList;
import datastruct.st.SET;
import graph.Graph;
import graph.GraphImpl;
import search.BFSearch;
import search.DFSearch;
import search.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment1 {
    private static String delimiter = " ";
    private static Scanner userIn = new Scanner(System.in);

    /**
     * Must have path to file with input as first
     * element in command line argument.
     * <p>
     * On each line of the input file,
     * Vertices must be ordered either as two vertices
     * that form an edge, or a vertex that is added alone.
     * <p>
     * Delimiter in file is optional and is set to whitespace
     * as default.
     *
     * @param args
     */
    public static void main(String... args) {
        LinkedList<String> rows = null;
        Graph<String> graph = new GraphImpl<>();

        try {
            rows = getInput(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Program exited see cause below : ");
            System.err.println(e.getMessage());
            return;
        }

        if (args.length > 1)
            delimiter = args[1];

        initGraph(graph, rows);
        while (true) {
            String userInput[] = Input.userInput();
            String command = userInput[0];

            if (command.toLowerCase().equals("stop"))
                break;

            String source = userInput[1];
            String destination = userInput[2];
            Search<String> search = new DFSearch<>(graph, source);
            boolean pathExists = search.hasPathTo(destination);
            if (command.toLowerCase().equals("path")) {
                if (pathExists) {
                    LinkedList<String> path = LinkedList.listOf(search.pathTo(destination));
                    String p = "";
                    for (String vertex : path) {
                        if (!vertex.equals(destination) || !vertex.equals(source)) {
                            p += "[" + vertex + "]" + "-";
                        }
                    }
                    System.out.println("Path from " + "[" + source + "]" + " to " + "[" + destination + "] : " + p);
                } else {
                    System.out.println("No path exists between " + source + " and " + destination);
                }


            }

            else if (command.toLowerCase().equals("exists")){
                if(pathExists){
                    System.out.println("Path from " + "[" + source + "]" + " to " + "[" + destination + "] : " + " Exists !");
                }
                else {
                    System.out.println("Path from " + "[" + source + "]" + " to " + "[" + destination + "] : " + " Does not Exist !");
                }

            }
        }

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

    private static String userInput() {
        System.out.print(" >>> ");
        String input = userIn.nextLine();
        return input;
    }


}
