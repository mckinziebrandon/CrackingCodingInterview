package DataStructures.Interfaces;

/**
 * Created by brandon on 2/23/17.
 */
public interface Paths {

    /** Is there a path from s to v? */
    boolean hasPathTo(int v);

    /** Path from s to v (if any). */
    Iterable<Integer> pathTo(int v);

}
