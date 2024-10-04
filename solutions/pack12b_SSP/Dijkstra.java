
package solutions.pack12b_SSP;

import java.util.Arrays;

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

        for (int i = 0; i < adjMatrix.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = 0;
        }

        distance[source] = 0;

        for (int count = 0; count < adjMatrix.length - 1; count++) {
            
            int u = minDistance(distance, visited);

            if (u == -1) {
                break; 
            }

            visited[u] = true;

            for (int v = 0; v < adjMatrix.length; v++)
                if (!visited[v] && adjMatrix[u][v] != -1
                        && distance[u] != Integer.MAX_VALUE
                        && distance[u] + adjMatrix[u][v] < distance[v]){
                            distance[v] = distance[u] + adjMatrix[u][v];
                            previous[v] = u;
                        }
                    
        }


        System.out.println(Arrays.toString(distance));
        System.out.println(Arrays.toString(previous));
    }

    int minDistance(int dist[], boolean visited[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < adjMatrix.length; v++)
            if (visited[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    public void printAllSSP() {
        for (int i = 0; i < distance.length; i++) {
            System.out.print("Shortest path from " + source + " to " + i + " is: ");

            printPath(i);
            System.out.println("\nwith a distance of " + distance[i] + "\n");
        }
    }
    
    private void printPath(int vertex) {
        if (previous[vertex] == 0 && vertex == 0) {
            System.out.print(vertex);
            return;
        }
        printPath(previous[vertex]);
        System.out.print(" -> " + vertex);
    }

}