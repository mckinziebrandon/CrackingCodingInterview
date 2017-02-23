package CourseWork.Lab_GraphSearch;

/**
 *  @author Josh Hug
 */

public class DepthFirstDemo {
    /* Runs a depth first search from (1, 1) to (N, N) on the graph in the config file. */

    public static final String CONFIG_PATH = "/home/brandon/Documents/CrackingCodingInterview/src/CourseWork/Lab_GraphSearch/maze.config";
    public static void main(String[] args) {
        Maze maze = new Maze(CONFIG_PATH);

        int startX = 1; 
        int startY = 1; 
        int targetX = maze.N();
        int targetY = maze.N();

        MazeExplorer mdfp = new MazeDepthFirstPaths(maze, startX, startY, targetX, targetY);
        mdfp.solve();
    }

} 