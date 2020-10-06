package BFSTest;

import graph.directed.DiGraph;
import graph.undirected.SimpleGraph;
import org.junit.*;
import search.BFSearch;
import search.DFSearch;

public class DirectedBFSTest {
    private String [][] edges;
    private SimpleGraph<String> graph;

    @Before
    public void setUp() {
        edges = new String[5][2];
        edges[0] = new String[]{"A", "B"};
        edges[1] = new String[]{"A", "C"};
        edges[2] = new String[]{"B", "C"};
        edges[3] = new String[]{"D", "C"};
        edges[4] = new String[]{"D", "A"};

        graph = new DiGraph<>();

        for(String [] vertPair : edges){
            graph.addEdge(vertPair[0],vertPair[1]);
        }

        System.out.println(graph.toString());

    }

    @Test
    public void testAHasPathToB(){
        BFSearch<String> dfs = new BFSearch<>(graph,"A");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testAHasPathToC(){
        BFSearch<String> dfs = new BFSearch<>(graph,"A");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testAHasPathToD(){
        BFSearch<String> dfs = new BFSearch<>(graph,"A");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testBHasPathToA(){
        BFSearch<String> dfs = new BFSearch<>(graph,"B");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testBHasPathToC(){
        BFSearch<String> dfs = new BFSearch<>(graph,"B");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testBHasPathToD(){
        BFSearch<String> dfs = new BFSearch<>(graph,"B");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToA(){
        BFSearch<String> dfs = new BFSearch<>(graph,"C");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToB(){
        BFSearch<String> dfs = new BFSearch<>(graph,"C");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToD(){
        BFSearch<String> dfs = new BFSearch<>(graph,"C");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testDHasPathToA(){
        BFSearch<String> dfs = new BFSearch<>(graph,"D");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testDHasPathToB(){
        BFSearch<String> dfs = new BFSearch<>(graph,"D");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testDHasPathToC(){
        BFSearch<String> dfs = new BFSearch<>(graph,"D");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testPathTo(){
        System.out.println(graph.toString());
        BFSearch<String> dfs = new BFSearch<>(graph, "A");
        boolean pathExist = dfs.hasPathTo("C");
        for (String v : dfs.pathTo("C")) {
            System.out.print(v);
            if (!v.equals("C"))
                System.out.print("-->");
        }
        Assert.assertTrue(pathExist);
    }
}
