package solutions.pack11_Graph;

public class Edge {
    int latency;
    Node node1;
    Node node2;

    public Edge(int ltc, Node n1, Node n2) {
        this.latency = ltc;
        this.node1 = n1;
        this.node2 = n2;
    }

    public int getLatency(){
        return latency;
    }

    public Node getNode1() {
        return node1;
    }
}
