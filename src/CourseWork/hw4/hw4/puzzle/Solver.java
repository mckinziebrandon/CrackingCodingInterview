package CourseWork.hw4.hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.PriorityQueue;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        /* The number of moves made to reach board. */
        private int numMoves;
        /* The SearchNode correspdonding to the previous move. */
        private SearchNode prevNode;

        SearchNode(Board b, int n, SearchNode node) {
            this.board      = b;
            this.numMoves   = n;
            this.prevNode   = node;
        }

        @Override
        public int compareTo(SearchNode other) {
            return Hamming(this) - Hamming(other);
        }

        /** Number of tiles in the wrong position + num moves. */
        private int Hamming(SearchNode node) {
            throw new UnsupportedOperationException();
        }
    }

    private Board board;

    public Solver(Board initial) {
        this.board = initial;
    }

    public Board solution() {

        // 1. Insert initial search node into a PQ.
        PriorityQueue<SearchNode> fringe = new PriorityQueue<>();
        fringe.add(new SearchNode(board, 0, null));



    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
    }

}
