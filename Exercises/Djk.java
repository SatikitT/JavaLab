package Exercises;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Djk {
    int[][] adjMatrix;
    int[] previous, distance;
    boolean[] visited;
    int source;

    public Djk(int[][] matrix, int source) {
        this.adjMatrix = matrix;
        this.source = source;
        previous = new int[adjMatrix.length];
        distance = new int[adjMatrix.length];
        visited = new boolean[adjMatrix.length];
    }

    void findSSP(){
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(previous, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        distance[source] = 0;

        for (int count = 0; count < adjMatrix.length; count++){
            int u = minDistance(distance, visited);
            if (u == -1) return;
            visited[u] = true;
            
            for (int v = 0; v < adjMatrix.length; v++) {
                if (!visited[v] && adjMatrix[u][v] != -1 &&
                    distance[u] != Integer.MAX_VALUE &&
                    distance[u] + adjMatrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + adjMatrix[u][v];
                        previous[v] = u;
                    }
            }
        }
        
    }

    int minDistance(int[] distance, boolean[] visited){
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visited[i] && min < distance[i]) {
                minIndex = i;
                min = distance[i];
            }
        }
        return minIndex;
    }

    void bfs() {
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (int i = 0; i < adjMatrix.length; i++) {
                if (!visited[i] && adjMatrix[u][i] != -1){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
