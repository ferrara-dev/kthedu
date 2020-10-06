package DFSTest;

import graph.directed.DiGraph;
import graph.undirected.SimpleGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import search.DFSearch;

public class TestDirectedDFS {
    private String[][] edges;
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

        for (String[] vertPair : edges) {
            graph.addEdge(vertPair[0], vertPair[1]);
        }


    }

    @Test
    public void testAHasPathToB() {
        DFSearch<String> dfs = new DFSearch<>(graph, "A");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testAHasPathToC() {
        DFSearch<String> dfs = new DFSearch<>(graph, "A");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testAHasPathToD() {
        DFSearch<String> dfs = new DFSearch<>(graph, "A");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testBHasPathToA() {
        DFSearch<String> dfs = new DFSearch<>(graph, "B");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testBHasPathToC() {
        DFSearch<String> dfs = new DFSearch<>(graph, "B");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testBHasPathToD() {
        DFSearch<String> dfs = new DFSearch<>(graph, "B");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToA() {
        DFSearch<String> dfs = new DFSearch<>(graph, "C");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToB() {
        DFSearch<String> dfs = new DFSearch<>(graph, "C");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testCHasPathToD() {
        DFSearch<String> dfs = new DFSearch<>(graph, "C");
        boolean pathExist = dfs.hasPathTo("D");
        Assert.assertFalse(pathExist);
    }

    @Test
    public void testDHasPathToA() {
        DFSearch<String> dfs = new DFSearch<>(graph, "D");
        boolean pathExist = dfs.hasPathTo("A");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testDHasPathToB() {
        DFSearch<String> dfs = new DFSearch<>(graph, "D");
        boolean pathExist = dfs.hasPathTo("B");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testDHasPathToC() {
        DFSearch<String> dfs = new DFSearch<>(graph, "D");
        boolean pathExist = dfs.hasPathTo("C");
        Assert.assertTrue(pathExist);
    }

    @Test
    public void testPathTo() {
        System.out.println(graph.toString());
        DFSearch<String> dfs = new DFSearch<>(graph, "A");
        boolean pathExist = dfs.hasPathTo("C");
        for (String v : dfs.pathTo("C")) {
            System.out.print(v);
            if (!v.equals("C"))
                System.out.print("-->");
        }

        Assert.assertTrue(pathExist);
    }
}
