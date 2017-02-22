package DataStructures;


/** Defines a recursive list of integers.
 *  @author Josh Hug
 */
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        /* your code here */
        this.item = i;
        this.next = n;
    }

    /** Retuns the size of this Node */
    public int size() {
        /* your code here */
        if (this.next == null) return 1;
        return 1 + next.size();
    }

    /** Returns the size of this Node. */
    public int iterativeSize() {
        /* your code here */
        IntNode node = this;
        int size = 0;
        while (node != null) {
            node = node.next;
            size += 1;
        }
        return size;
    }

    /** In class exercise 1:
     * Returns ith item of this Node. For
     * simplicity, assume the item exists. */
    public int get(int i) {
        /* your code here */
        if (i == 0) {
            return this.item;
        }
        return next.get(i - 1);
    }

    /** Returns the Intlist as a string. Automatically called
     * whenever you try to print an Node. */
    public String toString() {
        /* your code here */
        if (size() == 0) return "()";
        String s = "(" + Integer.toString(get(0));
        for (int i = 1; i < this.size(); i++) {
            s += ", " + Integer.toString(get(i));
        }
        s += ")";
        return s;
    }

    /** Returns an Node identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntNode incrList(IntNode L, int x) {
        /* Your code here. */
        return L;
    }

    /** Returns an Node identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntNode dincrList(IntNode L, int x) {
        /* Your code here. */
        return L;
    }

    public static void main(String[] args) {
        // Test your answers by uncommenting. Or use the Visualizer.
        IntNode L = new IntNode(5, null);
        L.next = new IntNode(7, null);
        L.next.next = new IntNode(9, null);
        System.out.println("Size is " + L.size());
        System.out.println("IterativeSize is " + L.iterativeSize());
        System.out.println("get(1) yields " + L.get(1));
        // System.out.println(incrList(L, 3));
        // System.out.println(dincrList(L, 3));

    }
}