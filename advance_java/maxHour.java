public class MazeSolver {
    private int[][] maze;
    private int[][] solution;
    private int rows;
    private int cols;

    public MazeSolver(int[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.solution = new int[rows][cols];
    }

    public void solveMaze() {
        if (solve(0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution exists.");
        }
    }

    private boolean solve(int x, int y) {
        // Check if (x, y) is a valid cell
        if (x < 0 || x >= rows || y < 0 || y >= cols || maze[x][y] == 0 || solution[x][y] == 1) {
            return false;
        }

        // Mark the current cell as part of the solution path
        solution[x][y] = 1;

        // Check if we have reached the destination
        if (x == rows - 1 && y == cols - 1) {
            return true;
        }

        // Move right
        if (solve(x, y + 1)) {
            return true;
        }

        // Move down
        if (solve(x + 1, y)) {
            return true;
        }

        // Move left
        if (solve(x, y - 1)) {
            return true;
        }

        // Move up
        if (solve(x - 1, y)) {
            return true;
        }

        // If none of the above movements work, backtrack
        solution[x][y] = 0;
        return false;
    }

    private void printSolution() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };

        MazeSolver solver = new MazeSolver(maze);
        solver.solveMaze();
    }
}
