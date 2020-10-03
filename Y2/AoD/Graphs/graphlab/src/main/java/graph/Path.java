package graph;

import datastruct.list.ArrayList;

public class Path<Vertex> {
    private final Vertex source;
    private final Vertex destination;
    private final ArrayList<Vertex> verticesOnPath;

    private Path(Vertex source, Vertex destination, ArrayList<Vertex> verticesOnPath){
        this.source = source;
        this.destination = destination;
        this.verticesOnPath = verticesOnPath;
    }

    public static Builder builder(){
        return new Builder();
    }

    public ArrayList<Vertex> getVerticesOnPath() {
        return verticesOnPath;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public static class Builder<Vertex>{
        private Vertex destination;
        private Vertex source;
        private ArrayList<Vertex> verticesOnPath;

        public Builder destination(Vertex destination){
            this.destination = destination;
            return this;
        }

        public Builder source(Vertex source){
            this.source = source;
            return this;
        }

        public Builder verticesOnPath(ArrayList<Vertex> verticesOnPath){
            this.verticesOnPath = verticesOnPath;
            return this;
        }

        public Path build(){
            return new Path(source,destination,verticesOnPath);
        }
    }
}
