package DataStructures.Interfaces;

public interface Graph {

    /** Add an edge v-w. */
    void addEdge(int v, int w);

    /** Vertices adjacent to v. */
    Iterable<Integer> adj(int v);

    /** Number of vertices. */
    int V();

    /** Number of edges. */
    int E();


}
