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
        
        for (int c = 0; c < adjMatrix.length; c++){
            int u = minDistance(distance, visited);
            if (u == -1) break;
            visited[u] = true;
            for (int v = 0; v < adjMatrix.length; v++) {
                if (!visited[v] && distance[v] != Integer.MAX_VALUE
                    && adjMatrix[u][v] != -1
                    && distance[u] + adjMatrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + adjMatrix[u][v];
                        previous[v] = u;
                    }
            }
        }
        
    }

    int minDistance(int[] distance, boolean[] visited){
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < adjMatrix.length; i++){
            if (!visited[i] && distance[i] < min) {
                min =  distance[i]; 
                minIndex = i;
            }
        }
        return minIndex;
    }


    public void bfs(){
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int p = queue.poll();
            System.out.println(p + ", ");
            for (int i = 0; i < adjMatrix.length; i++) {
                if (!visited[i] && adjMatrix[p][i] != -1) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }


    void dfs() {
        Arrays.fill(visited, false);
        dfs(source);
    }

    void dfs (int u) {
        visited[u] = true;
        System.out.println(u + " ");
        for (int i = 0; i < adjMatrix.length; i++) {
            if(!visited[i] && adjMatrix[u][i] != -1){
                dfs(i);
            }
        }
    }
}
