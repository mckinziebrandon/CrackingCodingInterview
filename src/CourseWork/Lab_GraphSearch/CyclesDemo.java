package CourseWork.Lab_GraphSearch;

/**
 *  @author Josh Hug
 */

public class CyclesDemo {
    /* Identifies a cycle (if any exist) in the given graph, and draws the cycle with
     * a purple line. */
    public static final String CONFIG_PATH = "/home/brandon/Documents/CrackingCodingInterview/src/CourseWork/Lab_GraphSearch/maze.config";
    public static void main(String[] args) {
        Maze maze = new Maze(CONFIG_PATH);

        MazeCycles mc = new MazeCycles(maze);
        mc.solve();
    }

} 