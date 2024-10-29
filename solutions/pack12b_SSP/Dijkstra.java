package solutions.pack12b_SSP;

import java.util.*;

public class Dijkstra {

    int[][] adjMatrix;
    int[] previous, distance;
    boolean[] visited;
    int source;

    public Dijkstra(int[][] adjMatrix, int source) {
        this.adjMatrix = adjMatrix;
        this.source = source;
        previous = new int[adjMatrix.length];
        distance = new int[adjMatrix.length];
        visited = new boolean[adjMatrix.length];
    }

    public void findSSP() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        Arrays.fill(previous, -1); // Initialize to -1 for no predecessor

        distance[source] = 0;

        for (int count = 0; count < adjMatrix.length - 1; count++) {
            int u = minDistance(distance, visited);
            if (u == -1) break;

            visited[u] = true;
            for (int v = 0; v < adjMatrix.length; v++) {
                if (!visited[v] && adjMatrix[u][v] != -1 && distance[u] != Integer.MAX_VALUE &&
                    distance[u] + adjMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjMatrix[u][v];
                    previous[v] = u;
                }
            }
        }
        System.out.println("Distances: " + Arrays.toString(distance));
        System.out.println("Previous nodes: " + Arrays.toString(previous));
    }

    int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < adjMatrix.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void printAllSSP() {
        for (int i = 0; i < distance.length; i++) {
            System.out.print("Shortest path from " + source + " to " + i + " is: ");
            printPath(i);
            System.out.println("\nwith a distance of " + distance[i] + "\n");
        }
    }

    private void printPath(int vertex) {
        if (vertex == -1) return;
        if (vertex == 0) {
            System.out.print(vertex);
            return;
        }
        printPath(previous[vertex]);
        System.out.print(" -> " + vertex);
    }

    // Breadth-First Search (BFS)
    public void bfs() {
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        
        System.out.print("BFS Order: ");
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (int v = 0; v < adjMatrix.length; v++) {
                if (adjMatrix[u][v] != -1 && !visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }
        System.out.println();
    }

    // Depth-First Search (DFS) Recursive
    public void dfs() {
        Arrays.fill(visited, false);
        System.out.print("DFS Order (Recursive): ");
        dfsRecursive(source);
        System.out.println();
    }

    private void dfsRecursive(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int w = 0; w < adjMatrix.length; w++) {
            if (adjMatrix[v][w] != -1 && !visited[w]) {
                dfsRecursive(w);
            }
        }
    }

    // Depth-First Search (DFS) Iterative
    public void dfsIterative() {
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        visited[source] = true;

        System.out.print("DFS Order (Iterative): ");
        while (!stack.isEmpty()) {
            int u = stack.pop();
            System.out.print(u + " ");
            for (int v = 0; v < adjMatrix.length; v++) {
                if (adjMatrix[u][v] != -1 && !visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
        System.out.println();
    }
}
