package performance;

import graph.GenericGraph;
import graph.undirected.SimpleGraph;
import search.*;
import util.FileIn;
import util.StopWatch;

public class GraphPerformance {

    public static void main(String... args) {
        switch (args[0].toLowerCase()){
            case "gen":
                testGenericGraph();
                return;
            case "def":
                testGraph();
                return;
        }
    }

    private static void testGraph(){
        FileIn fileIn = new FileIn("./src/main/resources/testData.dat");
        SimpleGraph<String> graph = new SimpleGraph<>();
        while (fileIn.hasNextLine()) {
            String ed[] = fileIn.nextRow().split(" ");
            graph.addEdge(ed[0], ed[1]);
        }

        double time = 0.0;
        BFSearch<String> bfSearch = null;

        for(int i = 0; i < 100000; i++){
            StopWatch stopWatch = new StopWatch();
            bfSearch = new <String>BFSearch(graph, "TX");
            time += stopWatch.elapsedTime();
        }

        for(String v : bfSearch.pathTo("CA")){
            System.out.print("[ "  + v + " ]");
            if(!v.equals("CA"))
                System.out.print("<-->");
        }

        System.out.println();
        System.out.println("Basic graph : ");
        printResult(time,100000);
    }

    private static void testGenericGraph() {
        FileIn fileIn = new FileIn("./src/main/resources/testData.dat");
        GenericGraph<String> genericGraph = new GenericGraph<>(false);
        while (fileIn.hasNextLine()) {
            String ed[] = fileIn.nextRow().split(" ");
            genericGraph.addEdge(ed[0], ed[1]);
        }
        double time = 0.0;
        GenericBFS<String> bfSearch = null;

        for(int i = 0; i < 100000; i++){
            StopWatch stopWatch = new StopWatch();
            bfSearch = new GenericBFS<>(genericGraph,"TX");
            time += stopWatch.elapsedTime();
        }

        for(String v : bfSearch.pathTo("CA")){
                System.out.print("[ "  + v + " ]");
            if(v != "CA")
                System.out.print("<-->");
        }
        System.out.println();
        System.out.println("Generic graph : ");
        printResult(time,100000);
    }

    private static void printResult(double time, int trials){
        System.out.println("Total time for " + trials + " executions was " + time/1000 + " s ");
    }
}
