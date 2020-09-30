package datastruct.graph;


import datastruct.list.LinkedList;

public class Graph<T>{

    private static class Vertex<T>{
        T data;
        LinkedList<Vertex> connections;
    }
}
