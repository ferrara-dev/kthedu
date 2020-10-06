package assignments;

import graph.undirected.SimpleGraph;
import search.DFSearch;
import util.FileIn;
import util.UserIn;

/**
 * @author Samuel Ferrara spof@kth.se
 *
 * ========================= ASSIGNMENT 1 README =========================
 * <Introduction>
 *     Program based on DFS which can answer questions of the type:
 *     "Find the a path from X to Y"
 *     Which should result in a list of vertices traversed from X to Y if there is a path.
 *     If the path does not exist, the program will make it that clear.
 *
 *     <startup>
 *         1. The first element in program arguments should contain a file path to the graph data.
 *         2. Each line in the input file should contain vertex pairs that defines a two-way edge.
 *     </startup>
 *
 * </Introduction>
 */
public class A1 {
    private UserIn userIn = new UserIn();
    private SimpleGraph<String> graph = new SimpleGraph<>();


    public static void main(String...args){
        A1 a1 = new A1();
        a1.setUp(args[0]);
        boolean run = true;
        while (run)
            run = a1.run();
    }

    public boolean run(){
        String [] in = userIn.userInput();
        if(in.length != 0 && in[0].equals(queries.EXIT.s))
            return false;
        else if(in.length == 3 && in[0].equals(queries.PATH.s)){
            StringBuilder sb = new StringBuilder();
            DFSearch<String> dfSearch = new DFSearch<>(graph,in[1]);
            for (String v : dfSearch.pathTo(in[2])){
                sb.append("["+v+"]");
                if(!v.equals(in[2])){
                    sb.append("<-->");
                }
            }
            if(sb.length() == 0){
                System.out.println("No path exists between" + in[1] + " and " + in[2]);
            } else
                System.out.println(sb.toString());
            return true;
        }
        else{
            System.out.println("Invalid query, please try again");
            return run();
        }
    }

    private void setUp(String filePath){
        FileIn fileIn = new FileIn(filePath);
        while (fileIn.hasNextLine()){
            String in [] = fileIn.nextRow().split(" ");
            graph.addEdge(in[0],in[1]);
        }
    }

    private enum queries{
        EXIT("exit"),
        PATH("path");

        String s;
        queries(String s) {
            this.s = s;
        }
    }

}
