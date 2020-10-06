package assignments;

import graph.undirected.SimpleGraph;
import search.BFSearch;
import util.FileIn;
import util.UserIn;

public class A2 {
    private UserIn userIn = new UserIn();
    private SimpleGraph<String> graph = new SimpleGraph<>();

    public static void main(String... args) {
        A2 a2 = new A2();
        a2.setUp(args[0]);
        boolean run = true;
        while (run)
            run = a2.run();
    }

    public boolean run() {
        String[] in = userIn.userInput();
        if (in.length != 0 && in[0].equals(queries.EXIT.s))
            return false;
        else if (in.length == 3 && in[0].equals(queries.PATH.s)) {
            StringBuilder sb = new StringBuilder();
            BFSearch<String> bfSearch = new BFSearch<>(graph, in[1]);
            for (String v : bfSearch.pathTo(in[2])) {
                sb.append("[" + v + "]");
                if (!v.equals(in[2])) {
                    sb.append("<-->");
                }
            }
            if (sb.length() == 0) {
                System.out.println("No path exists between" + in[1] + " and " + in[2]);
            } else
                System.out.println(sb.toString());
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
    }

    private enum queries {
        EXIT("exit"),
        PATH("path");

        String s;

        queries(String s) {
            this.s = s;
        }
    }
}
