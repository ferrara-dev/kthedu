package graph.search;

import datastruct.list.ArrayList;
import datastruct.list.Stack;
import datastruct.st.HashTable;
import graph.Graph;
import graph.BasicGraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CC<V> extends SearchStrategy<V> {
    private HashTable<V, V> id;
    private Stack<V> count;

    public CC(Graph<V> graph, V source) {
        super(graph, source);
    }

    @Override
    protected void init() {
        markedVertices = new HashTable<>();
        edgeToVertex = new HashTable<>();

        for (V vertex : graph.vertices()) {
            markedVertices.put(vertex, false);
        }

        this.id = new HashTable<>();
        this.count = new Stack<>();
    }

    @Override
    protected void search(V s) {
        this.id = new HashTable<>();
        this.count = new Stack();

        for (V vertex : graph.vertices()) {
            if (!super.markedVertices.get(vertex)) {
                this.count.push(vertex);
                connectedComponents(graph, vertex);
            }
        }
    }

    private void connectedComponents(Graph<V> graph, V s) {
        super.markedVertices.put(s, true);
        id.put(s, count.peek());

        for (V w : graph.adjVertices(s)) {
            if (!super.markedVertices.get(w)) {
                connectedComponents(graph, w);
            }
        }
    }
    //are v and w connected?
    public boolean connected(V v, V w) {
        return id.get(v) == id.get(w);
    }

    public int count(){
        return count.size();
    }

    public V id(V v){
        return id.get(v);
    }

    public static void main(String... args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
        Graph<String> graph  = new BasicGraph<String>(false);

        while (scanner.hasNextLine()) {
            String [] in = scanner.nextLine().split(" ");
            graph.addVertex(in[0]);
            graph.addVertex(in[1]);
            graph.addEdge(in[0], in[1]);
        }

        CC<String> cc = new CC(graph, "0");

        System.out.println(cc.count());

        HashTable<String,ArrayList<String>> components = new HashTable<>();
        ArrayList<String> cmp = new ArrayList<>(cc.count);

        for (int i = 0; i < cc.count(); i++){
            cmp.get(i);
            components.put(cmp.get(i), new ArrayList<>());
        }

        for(String v: graph.vertices()){
            components.get(cc.id(v)).add(v);
        }

        for(String key : components.keys()){
            for(String v : components.get(key))
                System.out.print(v + " ");
            System.out.println();
        }
    }
}
