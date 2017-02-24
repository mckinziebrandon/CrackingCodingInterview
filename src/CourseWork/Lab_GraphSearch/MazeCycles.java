package CourseWork.Lab_GraphSearch;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Observable;
/** 
 *  @author Josh Hug
 */

public class MazeCycles extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int start;      /* Label of starting vertex. */
    private Queue<Integer> fringe;
    private ArrayList<Integer>[] pathOf;

    public MazeCycles(Maze m) {
         // Creates & initializes the inherited integer arrays.
        super(m);
        this.start  = maze.xyTo1D(1, 1); // assuming that maze is connected.
        this.distTo[start] = 0;
        this.edgeTo[start] = start;
        this.pathOf = (ArrayList<Integer>[]) new ArrayList[maze.V()];
        fringe = new Queue<>();
    }

    @Override
    public void solve() {


        // 1. Initialize fringe with starting vertex v;
        assert(fringe.isEmpty());
        fringe.enqueue(start);
        marked[start] = true;

        int prevNode = start;
        while (!fringe.isEmpty()) {

            int w = fringe.dequeue();
            //ihavenoideawhatimdoing
            //this.pathOf[prevNode].add(w);
            marked[w] = true;
            announce();

            for (int adjToW : maze.adj(w)) {
                if (adjToW == prevNode) continue;

                if (!marked[adjToW]) fringe.enqueue(adjToW);
                else identifyCycle(adjToW);
                announce();
                distTo[adjToW] = distTo[w] + 1;
            }

            prevNode = w;
        }

        System.out.println("chill");
    }

    private void identifyCycle(int v) {
        //announce();
        //ihavenoideawhatimdoing
    }
} 

