package DataStructures;

/** Represent a list of integers, where all the "list" work is delegated
 * to a naked IntList. */
public class SList {

    private class IntNode {
        public int item;
        public IntNode next;

        /** Used when making the sentinel node. */
        public IntNode() {}

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }
    }

    private int size;
    private IntNode sentinel;

    /** Creates an empty list. */
    public SList() {
        sentinel = new IntNode();
        size = 0;
    }

    public SList(int x) {
        sentinel = new IntNode();
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item of the front. */
    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Puts an item at the back of the list. */
    public void insertBack(int x) {
        IntNode backNode = getBackNode();
        backNode.next = new IntNode(x, null);
        size += 1;
    }

    /** Gets the front item of the list. */
    public int getFront() {
        return sentinel.next.item;
    }

    /** Returns the back node of our list. */
    private IntNode getBackNode() {
        IntNode currNode = sentinel;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        return currNode;
    }

    /** Returns last item */
    public int getBack() {
        return getBackNode().item;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        SList s1 = new SList();
        s1.insertBack(6);
        s1.insertFront(4);
        s1.insertFront(3);
        System.out.println(s1.getBack());
        System.out.println(s1.getFront());
    }
}