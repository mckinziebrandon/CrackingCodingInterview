package CourseWork.Lab_GraphSearch;

/**
 *  @author Josh Hug
 */

public class TrivialMazeExplorerDemo {
    public static final String CONFIG_PATH = "/home/brandon/Documents/CrackingCodingInterview/src/CourseWork/Lab_GraphSearch/maze.config";
    public static void main(String[] args) {
        Maze maze = new Maze(CONFIG_PATH);
        TrivialMazeExplorer tme = new TrivialMazeExplorer(maze);
        tme.solve();
    }
} 