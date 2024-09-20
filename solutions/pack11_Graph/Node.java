package solutions.pack11_Graph;

import java.util.ArrayList;

public class Node {
    int id;
    ArrayList<Edge> edges;
    ArrayList<EdgeWithBandwidth> edgesWithBandwidth;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
        this.edgesWithBandwidth = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void addEdgeWithBandwidth(EdgeWithBandwidth edge) {
        edgesWithBandwidth.add(edge);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<EdgeWithBandwidth> getEdgesWithBandwidth() {
        return edgesWithBandwidth;
    }

    public String toString() {
        return "Node " + id;
    }
}