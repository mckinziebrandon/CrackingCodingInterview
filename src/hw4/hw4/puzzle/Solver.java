package hw4.puzzle;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;


public class Solver {

    // Enum-like identifiers for desired priority function.
    private static final boolean HAMMING = true;
    private static final boolean MANHATTAN = !HAMMING;

    private class SearchNode implements Comparable<SearchNode> {

        private Board board;
        /* The number of moves made to reach board. */
        private int numMoves;
        /* The SearchNode correspdonding to the previous move. */
        private SearchNode prevNode;
        /* Memoized value of h + dist = priority. */
        private int priority = -1;

        SearchNode(Board b, int n, SearchNode node) {
            this.board      = b;
            this.numMoves   = n;
            this.prevNode   = node;
        }

        public Iterable<SearchNode> neighbors() {
            ArrayList<SearchNode> res = new ArrayList<>();
            for (Board b : BoardUtils.neighbors(this.board)) {
                if (prevNode != null && b.equals(prevNode.board)) continue;
                res.add(new SearchNode(b, numMoves + 1, this));
            }
            return res;
        }

        @Override
        public int compareTo(SearchNode other) {
            return priority(this) - priority(other);
        }

        private int priority(SearchNode node) {
            int res = 0;
            if (node.priority > 0) {
                res = node.priority;
            } else {
                res = (HAMMING) ? hamming(node) : manhattan(node);
            }
            return res;
        }

        /** Number of tiles in the wrong position + num moves. */
        private int hamming(SearchNode node) {
            return node.board.hamming() + node.numMoves;
        }

        private int manhattan(SearchNode node) {
            return node.board.manhattan() + node.numMoves;
        }

    }// End SearchNode class.

    private Board initial;
    private Iterable<Board> solution;
    private int nMoves;

    public Solver(Board initial) {
        this.initial = initial;
        solve();
    }

    private void solve() {

        PriorityQueue<SearchNode> fringe = new PriorityQueue<>();
        HashSet<Board> boardsExplored = new HashSet<>();
        fringe.add(new SearchNode(initial, 0, null));

        while (!fringe.isEmpty()) {
            // Remove 'best' node from the fringe.
            SearchNode searchNode = fringe.poll();
            boardsExplored.add(searchNode.board);
            // Goal-test.
            if (searchNode.board.isGoal()) {
                solution = boardHistory(new ArrayList<>(), searchNode);
                nMoves   = searchNode.numMoves;
                return;
            }
            // Loop over all next moves in search tree, excluding previous board configurations.
            for (SearchNode neighbor : searchNode.neighbors()) {
                if (!boardsExplored.contains(neighbor.board)) fringe.add(neighbor);
            }
        }
    }

    private Iterable<Board> boardHistory(ArrayList<Board> boards, SearchNode node) {
        if (node == null) return boards;
        // postorder whaddup
        boardHistory(boards, node.prevNode);
        boards.add(node.board);
        return boards;
    }

    public Iterable<Board> solution() {
        return this.solution;
    }

    public int moves() {
        return nMoves;
    }

    public static void main(String[] args) {
        String puzzleNum = "02";
        String PUZZLE = "/home/brandon/Documents/CrackingCodingInterview/src/hw4/input/puzzle" + puzzleNum + ".txt";
        In in = new In(PUZZLE);
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
        /*
        for (Board board : solver.solution()) {
            StdOut.println(board);
       }
       */
    }

}
