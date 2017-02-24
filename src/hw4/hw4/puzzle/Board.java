
package hw4.puzzle;

import java.util.Arrays;

public class Board {

    /* The board size, N, for a given N-by-N board. */
    private int size;
    /* N-by-N matrix of integers from 1 to N^2 - 1 */
    private int[][] tiles;


    /** Constrcuts board from an N-by-N array of tiles where tiles[i][j] = tile at row i, col j. */
    public Board(int[][] tiles) {
        // Ensure that tiles is square.
        assert(tiles.length == tiles[0].length);

        this.size   = tiles.length;
        this.tiles  = new int[size][size];

        // Need to manually copy to respect immutability.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns the value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (notOnBoard(i, j)) {
            String msg = "Error: called tileAt(" + i + ", " + j + ") on board of size " + size;
            throw new IndexOutOfBoundsException(msg);
        }

        return tiles[i][j];
    }

    private boolean notOnBoard(int i, int j) {
        return (i < 0) || (i >= size) || (j < 0) || (j >= size);
    }

    public int size() {
        return size;
    }

    /** Number of tiles in the wrong position + num moves. */
    public int hamming() {
        // TODO: access to num moves?
        int numWrong = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) continue;
                if (tileAt(i, j) != goalAt(i, j)) numWrong++;
            }
        }
        return numWrong;
    }

    /** Sum of the manhattan distances from the tiles to their goal positions, + nummoves. */
    public int manhattan() {
        // TODO: access to num moves?
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) continue;
                sum += manhattan(i, j);
            }
        }
        return sum;
    }

    /**
     * @param i row of tile of interest.
     * @param j column of tile of interest.
     * @return manhattan distance of tileAt(i, j) to its goal position.
     */
    private int manhattan(int i, int j) {
        return (tileAt(i, j) / size) + (tileAt(i, j) % size - 1);
    }

    /** @return true if this.tiles is identical to goal board arrangement. */
    public boolean isGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != goalAt(i, j)) return false;
            }
        }
        return true;
    }

    /** @return the goal value for row i, column j. */
    private int goalAt(int i, int j) {
        if (i == size - 1 && j == size - 1) return 0;
        return (i * size + 1) + j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (size != board.size) return false;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.deepHashCode(tiles);
        return result;
    }

    /** Returns the string representation of the board.  */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


}
