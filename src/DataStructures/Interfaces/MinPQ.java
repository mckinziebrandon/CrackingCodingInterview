package DataStructures.Interfaces;

/**
 * (Min) Priority Queue: Allowing tracking and removal of the
 * smallest item in a priority queue.
 */
public interface MinPQ<Item> {
    /** Adds the item to the priority queue. */
    void add(Item x);

    /** Return the smallest item in the PQ. */
    Item getSmallest();

    /** Remove smallest item from PQ. */
    Item removeSmallest();

    /** Number of items in the PQ. */
    int size();
}
