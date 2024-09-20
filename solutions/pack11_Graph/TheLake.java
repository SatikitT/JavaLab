package solutions.pack11_Graph;

public class TheLake {

    public int findTotalMaxDepth(int[][] map) {
        int maxDepth = 0;
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (map[i][j] != 0 && !visited[i][j]) {
                    int currentLakeDepth = dfs(map, visited, i, j, rows, cols);
                    maxDepth = Math.max(maxDepth, currentLakeDepth);
                }
            }
        }
        return maxDepth;
    }

    private int dfs(int[][] map, boolean[][] visited, int x, int y, int rows, int cols) {

        if (x < 0 || x >= rows || y < 0 || y >= cols || map[x][y] == 0 || visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;

        int totalDepth = map[x][y];

        totalDepth += dfs(map, visited, x + 1, y, rows, cols);
        totalDepth += dfs(map, visited, x - 1, y, rows, cols);
        totalDepth += dfs(map, visited, x, y + 1, rows, cols);
        totalDepth += dfs(map, visited, x, y - 1, rows, cols);

        return totalDepth;
    }
}