package Labs;

import solutions.pack11_Graph.*;

import java.util.PriorityQueue;

public class Lab11_Main {

    public static void main(String[] args) {
        System.out.println("Task 1: Implement the compare method for EdgeComparator");
        task1();
        System.out.println();

        System.out.println(
                "Task 2: Implement PRIM's algorithm to find the minimum spanning tree by using latency as the weight");
        task2();
        System.out.println();

        System.out.println(
                "Task 3: Implement PRIM's algorithm to find the minimum spanning tree by using latency as the weight and bandwidth as the constraint");
        task3();
        System.out.println();

        System.out.println("Task 4: Implement the findTotalMaxDepth method in TheLake class");
        task4();

    }

    public static void task1() {
        try {
            PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());

            // we will focus on the latency of the edges only so we will pass null for the
            // nodes
            Edge e1 = new Edge(5, null, null);
            Edge e2 = new Edge(3, null, null);
            Edge e3 = new Edge(7, null, null);
            Edge e4 = new Edge(1, null, null);
            Edge e5 = new Edge(9, null, null);

            pq.add(e1);
            pq.add(e2);
            pq.add(e3);
            pq.add(e4);
            pq.add(e5);

            StringBuffer sb = new StringBuffer();
            while (!pq.isEmpty()) {
                sb.append(pq.poll().getLatency() + " ");
            }

            String actulString = sb.toString();
            String expectedString = "1 3 5 7 9 ";

            if (actulString.equals(expectedString)) {
                System.out.println(
                        "\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedString + " got: " + actulString);
            } else {
                System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedString + " but got: "
                        + actulString);
            }
        } catch (Exception e) {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Exception: " + e);
        }
    }

    public static void task2() {
        NetworkOptimizer n = new NetworkOptimizer();
        int N = 6;
        int m = 9;
        int input[][] = {
                { 1, 2, 2 },
                { 1, 3, 1 },
                { 1, 4, 4 },
                { 1, 5, 2 },
                { 2, 3, 3 },
                { 3, 4, 3 },
                { 3, 6, 1 },
                { 4, 5, 1 },
                { 5, 6, 5 }
        };

        int actual = n.optimizeNetwork(N, m, input);
        int expected = 7;

        if (actual == expected) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expected + " got: " + actual);
        } else {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expected + " but got: " + actual);
        }
    }

    public static void task3() {
        NetworkOptimizer n = new NetworkOptimizer();
        int N = 6;
        int M = 9;
        int T = 10;
        int input[][] = {
                { 1, 2, 2, 12 },
                { 1, 3, 1, 9 },
                { 1, 4, 4, 11 },
                { 1, 5, 2, 5 },
                { 2, 3, 3, 10 },
                { 3, 4, 3, 20 },
                { 3, 6, 1, 13 },
                { 4, 5, 1, 9 },
                { 5, 6, 5, 10 }
        };

        int actual = n.optimizeNetwork(N, M, T, input);
        int expected = 14;

        int N2 = 6;
        int M2 = 9;
        int T2 = 10;
        int input2[][] = {
                { 1, 2, 2, 12 },
                { 1, 3, 1, 9 },
                { 1, 4, 4, 11 },
                { 1, 5, 2, 5 },
                { 2, 3, 3, 10 },
                { 3, 4, 3, 20 },
                { 3, 6, 1, 2 },
                { 4, 5, 1, 9 },
                { 5, 6, 5, 1 }
        };

        int actual2 = n.optimizeNetwork(N2, M2, T2, input2);
        int expected2 = -1;

        if (actual == expected) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expected + " got: " + actual);
        } else {
            System.out.println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expected + " but got: " + actual);
        }

        if (actual2 == expected2) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expected2 + " got: " + actual2);
        } else {
            System.out
                    .println("\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expected2 + " but got: " + actual2);
        }
    }

    public static void task4() {
        TheLake t = new TheLake();
        /*
         * 1 1 1 1 1
         * 1 0 0 0 1
         * 1 1 4 0 1
         * 1 0 0 0 1
         * 1 1 1 1 1
         * 21
         * 
         * 1 2 0
         * 3 4 0
         * 0 0 5
         * 10
         */
        int map[][] = { { 1, 2, 0 }, { 3, 4, 0 }, { 0, 0, 5 } };
        int expectedMap = 10;
        int map2[][] = { { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 1, 4, 0, 1 }, { 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 1 } };
        int expectedMap2 = 21;

        int actual1 = t.findTotalMaxDepth(map);
        int actual2 = t.findTotalMaxDepth(map2);

        if (actual1 == expectedMap) {
            System.out.println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedMap + " got: " + actual1);
        } else {
            System.out.println(
                    "\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedMap + " but got: " + actual1);
        }

        if (actual2 == expectedMap2) {
            System.out
                    .println("\033[0;32m" + "Passed" + "\033[0m" + "  Expected: " + expectedMap2 + " got: " + actual2);
        } else {
            System.out.println(
                    "\033[0;31m" + "Failed" + "\033[0m" + "  Expected: " + expectedMap2 + " but got: " + actual2);
        }

    }
}