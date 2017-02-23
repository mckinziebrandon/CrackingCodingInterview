package DataStructures.Simple;
import DataStructures.Interfaces.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Adjacency-list representation.
 */
public class SimpleGraph implements Graph {

    private final int V;
    private List<Integer>[] adj;

    public SimpleGraph(int V) {
        this.V = V;
        this.adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }


    /** Add an edge v-w. */
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    /** Vertices adjacent to v. */
    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /** Number of vertices. */
    @Override
    public int V() {
        return V;
    }

    /** Number of edges. */
    @Override
    public int E() {
        int E = 0;
        for (List<Integer> adjList : adj) {
            E += adjList.size();
        }
        return E / 2;
    }


}
