package solutions.pack7_Recursion;

public class GridPaths {
    public static int numberOfPaths(int [][] grid, int... numbers) {
        if(numbers.length == 0){
            return numberOfPaths(grid, grid.length - 1, grid[0].length - 1);
        }

        if (numbers[0] == 0 && numbers[1] == 0){
            return 1;
        } 
        
        if (grid[numbers[0]][numbers[1]] == 1){
            return 0;
        }

        if(numbers[0] == 0) {
            return numberOfPaths(grid, numbers[0], numbers[1] - 1);
        }
         
        if(numbers[1] == 0) {
            return numberOfPaths(grid, numbers[0] - 1, numbers[1]);
        }

        return numberOfPaths(grid, numbers[0] - 1, numbers[1]) + numberOfPaths(grid, numbers[0], numbers[1] - 1);
    }
}
