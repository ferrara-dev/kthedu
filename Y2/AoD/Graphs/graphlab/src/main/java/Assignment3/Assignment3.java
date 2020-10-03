package Assignment3;


import graph.BasicGraph;
import graph.Graph;
import graph.PathFinder;
import util.in.FileIn;
import util.in.UserIn;

/**
 * Program that can answer if there is a path between any two vertices
 * in a directed graph.
 *
 * The query below will tell if a direct path exists from vertex X to vertex Y :
 * "exists X Y"
 *
 */
public class Assignment3 {

    public static void main(String... args) {
        UserIn userIn = new UserIn("exists","stop");
        FileIn fileIn = new FileIn(args[0]);
        Graph<String> graph = new BasicGraph<>(true);

        while (fileIn.hasNextLine()){
            String vertices [] = fileIn.nextRow().split(" ");
            graph.addEdge(vertices[0],vertices[1]);
        }
        System.out.print("");
        PathFinder<String> pathFinder = new PathFinder<>(graph);
        String [] query = userIn.userInput();
        while (!query[0].equals("stop")){

            String source = query[1];
            String destination = query[2];

            try {
                boolean exists = pathFinder.pathExists(source, destination);
                printRes(exists,source,destination);
            } catch (IllegalArgumentException e){
                e.printStackTrace();
                return;
            }

            query = userIn.userInput();
        }


    }

    private static void printRes(boolean res, String source, String destination){
        if(res){
            System.out.println("A path exists between " + source + " and " + destination);
        }
        else {
            System.out.println("No path exists between " + source + " and " + destination);
        }
    }

}
