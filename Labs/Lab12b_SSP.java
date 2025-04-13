package Labs;

import solutions.pack12b_SSP.*;


public class Lab12b_SSP {

    public static void main(String[] args) {
        System.out.println("Task 1:");
        task1();

        // System.out.println("\nTask 2:");
        // task2();

        // System.out.println("\nTask 3:");
        // task3();
        // System.out.println("bye");

    }

    public static void task1() {

        int[][] adjMatrix = {
                {  0,  2, -1,  8, -1, -1 },
                {  2,  0, -1,  5,  6, -1 },
                { -1, -1,  0, -1,  9,  3 },
                {  8,  5, -1,  0,  3,  2 },
                { -1,  6,  9,  3,  0,  1 },
                { -1, -1,  3,  2,  1,  0 }
        };

        Dijkstra dijkstra = new Dijkstra(adjMatrix, 0);
        dijkstra.findSSP();
        dijkstra.findMST();
        dijkstra.bfs();
        dijkstra.dfs();

        // Expected output:
        /*
         * [0, 2, 12, 7, 8, 9]
         * [0, 0, 5, 1, 1, 3]
         */
    }

    public static void task2() {

        int[][] adjMatrix = {
            {  0,  2, -1,  8, -1, -1 },
            {  2,  0, -1,  5,  6, -1 },
            { -1, -1,  0, -1,  9,  3 },
            {  8,  5, -1,  0,  3,  2 },
            { -1,  6,  9,  3,  0,  1 },
            { -1, -1,  3,  2,  1,  0 }
    };


        Dijkstra dijkstra = new Dijkstra(adjMatrix, 0);
        dijkstra.findSSP();
        System.out.println();
        dijkstra.printAllSSP();
    

        // Expected output:
        /*
         * 
         [0, 2, 12, 7, 8, 9]
         [0, 0, 5, 1, 1, 3]
         Shortest path from 0 to 0 is 0
         with a distance of 0
         
         Shortest path from 0 to 1 is 0 -> 1
         with a distance of 2
         
         Shortest path from 0 to 2 is 0 -> 1 -> 3 -> 5 -> 2
         with a distance of 12
         
         Shortest path from 0 to 3 is 0 -> 1 -> 3
         with a distance of 7
         
         Shortest path from 0 to 4 is 0 -> 1 -> 4
         with a distance of 8
         
         Shortest path from 0 to 5 is 0 -> 1 -> 3 -> 5
         with a distance of 9
         
         */
    }

    public static void task3() {
        Task3 task3 = new Task3();

        int[] initialState1 = { 6, 3, 2, 5, 4, 1 };
        int[] goalState1 = { 1, 2, 3, 4, 5, 6 };
        int expectedOutput1 = 3;

        int[] initialState2 = {3, 4, 2, 1};
        int[] goalState2 = {1, 2, 3, 4};
        int expectedOutput2 = -1;

        int[] initialState3 = {1, 2, 3, 4};
        int[] goalState3 = {1, 2, 3, 4};
        int expectedOutput3 = 0;

        int actualOutput1 = task3.stateSpace(initialState1, goalState1);
        int actualOutput2 = task3.stateSpace(initialState2, goalState2);
        int actualOutput3 = task3.stateSpace(initialState3, goalState3);

        if (actualOutput1 == expectedOutput1) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedOutput1 + " got: " + actualOutput1);
        } 
        else {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedOutput1 + " but got: " + actualOutput1);
        }

        if (actualOutput2 == expectedOutput2) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedOutput2 + " got: " + actualOutput2);
        } 
        else {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedOutput2 + " but got: " + actualOutput2);
        }

        if (actualOutput3 == expectedOutput3) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedOutput3 + " got: " + actualOutput3);
        } 
        else {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedOutput3 + " but got: " + actualOutput3);
        }
    }

}