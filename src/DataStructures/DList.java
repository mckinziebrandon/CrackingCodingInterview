package DataStructures;

/** Doubly-linked version of S[ingly-linked]List.java.
 * Also supports generic type elements instead of requiring Integers. */
public class DList<E> {

    private class Node {
        public E item;
        public Node next, prev;
        public Node() {}
        public Node(E item) { this.item = item; }
        public Node(Node prev, E item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    /** Creates an empty list. */
    public DList() {
        sentinel = new Node();
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
    }

    public DList(E x) {
        sentinel = new Node();
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
        this.insertFront(x);
    }

    public DList(E[] items) {
        sentinel = new Node();
        sentinel.next = sentinel.prev = sentinel;
        size = 0;
        for (E i : items) {
            insertBack(i);
        }
    }

    /** Adds an item of the front. */
    public void insertFront(E x) {
        // Get/create the relevant nodes.
        Node oldFront = sentinel.next;
        Node newFront = new Node(sentinel, x, oldFront);
        // Update connections.
        oldFront.prev = sentinel.next = newFront;
        size += 1;
    }

    /** Puts an item at the back of the list. */
    public void insertBack(E x) {
        // Get/create the relevant nodes.
        Node oldBack = sentinel.prev;
        Node newBack = new Node(oldBack, x, sentinel);
        // Update connections.
        oldBack.next = sentinel.prev = newBack;
        size += 1;
    }

    /** Typical alias for insertBack. */
    public void add(E x) { insertBack(x); }

    /** Gets the front item of the list. */
    public E getFront() {
        return sentinel.next.item;
    }

    /** Returns last item */
    public E getBack() {
        return sentinel.prev.item;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0) return "()";

        Node currNode = sentinel.next;
        String s = "(" + currNode.item;
        while (currNode.next != sentinel) {
            currNode = currNode.next;
            s += ", " + currNode.item;
        }
        s += ")";
        return s;
    }
}
