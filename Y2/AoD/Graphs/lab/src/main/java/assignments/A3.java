package assignments;

import graph.directed.DiGraph;
import graph.undirected.SimpleGraph;
import search.BFSearch;
import search.DFSearch;
import util.FileIn;
import util.UserIn;

/**
 * =================== ASSIGNMENT 3 README ===================
 *
 * <Introduction>
 *     Program to determine if a path exists between any two
 *     vertices A and B in directed graph.
 * </Introduction>
 */
public class A3 {
    private UserIn userIn = new UserIn();
    private DiGraph<String> graph = new DiGraph<>();

    public static void main(String... args) {
        A3 a3 = new A3();
        a3.setUp(args[0]);
        boolean run = true;
        while (run)
            run = a3.run();
    }

    public boolean run() {
        String[] in = userIn.userInput();
        if (in.length != 0 && in[0].equals(queries.EXIT.s))
            return false;
        else if (in.length == 3 && in[0].equals(queries.EXISTS.s)) {
            StringBuilder sb = new StringBuilder();
            DFSearch<String> bfSearch = new DFSearch<>(graph, in[1]);
            for (String v : bfSearch.pathTo(in[2])) {
                sb.append("[" + v + "]");
                if (!v.equals(in[2])) {
                    sb.append("-->");
                }
            }
            if (sb.length() == 0) {
                System.out.println("No path exists between " + in[1] + " and " + in[2]);
            } else {
                System.out.println("Path exists between " + in[1] + " and " + in[2]);
                System.out.println(sb.toString());
            }
            return true;
        } else {
            System.out.println("Invalid query, please try again");
            return run();
        }
    }

    private void setUp(String filePath) {
        FileIn fileIn = new FileIn(filePath);
        while (fileIn.hasNextLine()) {
            String in[] = fileIn.nextRow().split(" ");
            graph.addEdge(in[0], in[1]);
        }
        System.out.println(graph.toString());
    }

    private enum queries {
        EXIT("exit"),
        EXISTS("exists");

        String s;

        queries(String s) {
            this.s = s;
        }
    }
}
