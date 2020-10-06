package DFSTest;

import graph.undirected.SimpleGraph;
import list.ArrayList;
import org.junit.Before;
import org.junit.Test;
import search.DFSearch;

public class TestUndirectedDFS {
    private String [][] edges;
    private SimpleGraph<String> graph;

    @Before
    public void setUp() {
        edges = new String[5][2];
        edges[0] = new String[]{"A", "B"};
        edges[1] = new String[]{"A", "C"};
        edges[2] = new String[]{"B", "C"};
        edges[3] = new String[]{"C", "D"};
        edges[4] = new String[]{"D", "A"};

        graph = new SimpleGraph<>();
        for(String [] vertPair : edges){
            graph.addEdge(vertPair[0],vertPair[1]);
        }
    }

    @Test
    public void testPathTo(){
        DFSearch<String> dfSearch = new DFSearch<>(graph,"A");
        ArrayList<String> arrayList = new ArrayList<>(dfSearch.pathTo("B"));
        for(String v : arrayList){
            System.out.print(v);
            if(!v.equals("B"))
                System.out.print("<-->");
        }
    }
}
