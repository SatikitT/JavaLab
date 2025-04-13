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
                    System.out.println(distance[u] + " + " + adjMatrix[u][v]);
                    distance[v] = distance[u] + adjMatrix[u][v];
                    previous[v] = u;
                }
            }
            System.out.println(Arrays.toString(distance));
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
        System.out.println(minIndex);
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

    public void findMST() {
        int[] key = new int[adjMatrix.length];
        int[] parent = new int[adjMatrix.length];
        boolean[] inMST = new boolean[adjMatrix.length];
    
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[source] = 0;
    
        for (int count = 0; count < adjMatrix.length - 1; count++) {
            int u = minKey(key, inMST); // Pick the minimum key vertex not yet included in MST
            inMST[u] = true;
    
            for (int v = 0; v < adjMatrix.length; v++) {
                // Update key and parent only if adjMatrix[u][v] is a valid edge
                // and v is not yet in MST and the weight of edge u-v is less than key[v]
                if (adjMatrix[u][v] != -1 && !inMST[v] && adjMatrix[u][v] < key[v]) {
                    key[v] = adjMatrix[u][v];
                    parent[v] = u;
                }
            }
        }
        printMST(parent);
    }
    
    private int minKey(int[] key, boolean[] inMST) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < adjMatrix.length; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    
    public void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < adjMatrix.length; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + "\t" + adjMatrix[i][parent[i]]);
            }
        }
    }
    
    void findMST2() {
        int[] parent = new int[adjMatrix.length];
        int[] dist = new int[adjMatrix.length];
        boolean[] vstd = new boolean[adjMatrix.length];

        Arrays.fill(vstd, false);
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        for (int count = 0; count < adjMatrix.length - 1; count++) {
            int u = findMin(dist, vstd);

            vstd[u] = true;

            for (int v = 0; v < adjMatrix.length; v++) {
                if (!vstd[v] && adjMatrix[u][v] != -1 && adjMatrix[u][v] < dist[v]) {
                    dist[v] = adjMatrix[u][v];
                    parent[v] = u;
                }
            }
        }
    }

    int findMin(int[] dist, boolean[] vstd){
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (!vstd[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}
