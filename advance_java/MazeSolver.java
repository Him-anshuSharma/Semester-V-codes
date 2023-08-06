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

    private void solveMaze(){
        if(solve(0, 0)){
            printSolution();
        }
        else{
            System.out.println("Error");
        }
    }
    private boolean solve(int x, int y){
        if(x<0 || x>=rows || y<0 || y>=cols || maze[x][y] == 0 || solution[x][y] == 1){
            return false;
        }
        solution[x][y] = 1;

        if(x == rows-1 && y == cols-1){
            return true;
        }

        if(solve(x+1, y)){
            return true;
        }
        if(solve(x, y+1)){
            return true;
        }
        if(solve(x-1, y)){
            return true;
        }
        if(solve(x, y-1)){
            return true;
        }
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
