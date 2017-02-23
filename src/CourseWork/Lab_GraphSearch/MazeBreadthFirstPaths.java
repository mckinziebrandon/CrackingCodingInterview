package CourseWork.Lab_GraphSearch;
import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 *  @author Brandon McKinzie
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits the following public fields:
     *      public int[] distTo;    // distTo[i] == distance from start vertex to vertex 'i'.
     *      public int[] edgeTo;    // edgeTo[i] == previous vertex, adjacent to 'i', that we took along our path.
     *      public boolean[] marked;
     *      public Maze maze;
     */

    private int start;      /* Label of starting vertex. */
    private int target;     /* Label of target vertex. */
    private Queue<Integer> fringe;

    /**
     * @param m         The maze object to search.
     * @param sourceX   X-coord of start vertex.
     * @param sourceY   Y-coord of start vertex.
     * @param targetX   X-coord of target vertex.
     * @param targetY   Y-coord of target vertex.
     */
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        // Creates & initializes the inherited integer arrays.
        super(m);
        // Store the additional info given by the parameters.
        this.start  = maze.xyTo1D(sourceX, sourceY);
        this.target = maze.xyTo1D(targetX, targetY);
        System.out.println("start vertex: " + start + " at (" + sourceX + ", " + sourceY + ")");
        System.out.println("target vertex: " + target + " at (" + targetX + ", " + targetY + ")");
        this.distTo[start] = 0;
        this.edgeTo[start] = start;
        fringe = new Queue<>();
    }

    /**
     * Conventions followed:
     *      - A vertex is marked (explored) ONLY upon removal from fringe.
     *      - announce() called ONLY upon removal from fringe.
     * @param v
     */
    private void bfs(int v) {

        // 1. Initialize fringe with starting vertex v;
        assert(fringe.isEmpty());
        fringe.enqueue(v);
        marked[v] = true;

        while (!fringe.isEmpty()) {

            // 2a. Remove some vertex 'w' from the fringe, declare it as visited.
            int w       = fringe.dequeue();
            marked[w] = true;
            announce();
            if (w == target) return;

            // 2b. Add to the fringe any unmarked vertices adjacent to 'w' and mark them.
            for (int adjToW : maze.adj(w)) {
                if (!marked[adjToW]) {
                    fringe.enqueue(adjToW);
                    edgeTo[adjToW] = w;
                    announce();
                    distTo[adjToW] = distTo[w] + 1;
                }
            }
        }
    }

    @Override
    public void solve() {
        bfs(start);
    }
}

