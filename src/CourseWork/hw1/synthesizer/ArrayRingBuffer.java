package CourseWork.hw1.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = last = fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) { throw new RuntimeException("Ring buffer overflow."); }
        rb[last] = x;
        fillCount++;
        last = (last != capacity - 1) ? last + 1 : 0;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow."); }
        T deqItem = rb[first];
        fillCount--;
        first = (first != 0) ?  first - 1 : capacity - 1;
        return deqItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    /** Boilerplace needed for iteration support. */
     private class ARBIterator implements Iterator<T> {
        private int index = first;

        public boolean hasNext() {
            return !isEmpty();
        }

        public T next() {
            return rb[index++];
        }
    }

    public Iterator<T> iterator() {
        return new ARBIterator();
    }

}
