package solutions.pack11_Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class NetworkOptimizer {

    public int optimizeNetwork(int N, int M, int[][] input) {

        int totalLatency = 0;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < input.length; i++) {
            int node1Id = input[i][0];
            int node2Id = input[i][1];
            int latency = input[i][2];
            Edge edge = new Edge(latency, nodes[node1Id - 1], nodes[node2Id - 1]);
            nodes[node1Id - 1].addEdge(edge);
            nodes[node2Id - 1].addEdge(edge);
        }

        Set<Node> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());

        Node startNode = nodes[0];
        visited.add(startNode);

        pq.addAll(startNode.getEdges());

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();

            Node nextNode = !visited.contains(currentEdge.node1) ? currentEdge.node1 : currentEdge.node2;

            if (!visited.contains(nextNode)) {
                totalLatency += currentEdge.getLatency();
                visited.add(nextNode);

                for (Edge edge : nextNode.getEdges()) {
                    if (!visited.contains(edge.node1) || !visited.contains(edge.node2)) {
                        pq.add(edge);
                    }
                }
            }
        }

        return totalLatency;
    }

    public int optimizeNetwork(int N, int M, int T, int[][] input) {
        int totalLatency = 0;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }

        for (int i = 0; i < input.length; i++) {
            int node1Id = input[i][0];
            int node2Id = input[i][1];
            int latency = input[i][2];
            int bandwidth = input[i][3];
            EdgeWithBandwidth edge = new EdgeWithBandwidth(latency, bandwidth, nodes[node1Id - 1], nodes[node2Id - 1]);
            nodes[node1Id - 1].addEdgeWithBandwidth(edge);
            nodes[node2Id - 1].addEdgeWithBandwidth(edge);
        }

        Set<Node> visited = new HashSet<>();
        PriorityQueue<EdgeWithBandwidth> pq = new PriorityQueue<>(new EdgeWithBandwidthComparator());

        Node startNode = nodes[0];
        visited.add(startNode);

        for (EdgeWithBandwidth edge : startNode.getEdgesWithBandwidth()) {
            if (edge.getBandwidth() >= T) {
                pq.add(edge);
            }
        }

        while (!pq.isEmpty()) {
            EdgeWithBandwidth currentEdge = pq.poll();

            Node nextNode = !visited.contains(currentEdge.node1) ? currentEdge.node1 : currentEdge.node2;

            if (!visited.contains(nextNode)) {
                totalLatency += currentEdge.getLatency();
                visited.add(nextNode);

                for (EdgeWithBandwidth edge : nextNode.getEdgesWithBandwidth()) {
                    if ((!visited.contains(edge.node1) || !visited.contains(edge.node2)) && edge.getBandwidth() >= T) {
                        pq.add(edge);
                    }
                }
            }
        }

        if (visited.size() != N) {
            return -1;
        }

        return totalLatency;
    }

}
