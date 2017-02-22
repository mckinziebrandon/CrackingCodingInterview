package CourseWork.hw1.synthesizer;

import java.util.Iterator;

/** Spec:
 * We will start by defining a BoundedQueue interface.
 * The BoundedQueue is similar to our Deque from project 1, but with a more limited API.
 * Specifically, items can only be enqueued at the back of the queue, and can only be dequeued from the front of the queue.
 * Unlike our Deque, the BoundedDeque has a fixed capacity, and nothing is allowed to enqueue if the Deque is full.
 */

public interface BoundedQueue<T> extends Iterable<T> {
    Iterator<T> iterator();
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front
    default boolean isEmpty()   { return fillCount() == 0; }
    default boolean isFull()    { return fillCount() == capacity(); }
}
