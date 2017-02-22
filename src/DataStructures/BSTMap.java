package DataStructures;

import DataStructures.Interfaces.Map61B;
import edu.princeton.cs.algs4.Queue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author 61B staff
 * @author Brandon McKinzie
 * With excessive reference to the Algorithms textbook by Sedgewick and Wayne.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** A BSTMap consists of BSTNodes, and our point of reference is the 'root' node. */
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V val;
        private BSTNode lchild, rchild;
        private int N;  /* Number of nodes in the subtree rooted here. */

        public BSTNode(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }


    /* ==================================================================================
     * Constructors.
     * ================================================================================== */

    /** Initializes an empty symbol table. */
    public BSTMap() {}


    /* ==================================================================================
     * The usual quick n' simple methods.
     * ================================================================================== */

    public boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    /** @return the number of <K, V> Pairs rooted at 'node'. */
    private int size(BSTNode node) {
        if (node == null) return 0;
        return node.N;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    /* ==================================================================================
     * Insertion, search, and removal methods.
     * ================================================================================== */

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)        return get(node.lchild, key);
        else if (cmp > 0)   return get(node.rchild, key);
        else                return node.val;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is null.
     *
     * @param  key the key
     * @param  val the value
     */
    @Override
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    /**
     * @param node  root of the subtree.
     * @param key   to find or create.
     * @param val   to associate with key; replace if key already exists.
     * @return      the subtree containing the new (key, val) pair.
     */
    private BSTNode put(BSTNode node, K key, V val) {
        // If null (reached 'bottom'), then
        // create & return a new node,
        // of size 1 since no children, with (key, val) mapping.
        if (node == null) return new BSTNode(key, val, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)        node.lchild =  put(node.lchild, key, val);
        else if (cmp > 0)   node.rchild = put(node.rchild, key, val);
        else                node.val = val;
        // Update the value of node.N in case of newly created LLRBNode.
        node.N = 1 + size(node.rchild) + size(node.lchild);
        return node;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        /* MFW I need to do this to comply with the Map61B interface  :| */
        HashSet<K> keys = new HashSet<K>();
        for (K key: keySet(min(), max())) {
            keys.add(key);
        }
        return keys;
    }

    public Iterable<K> keySet(K lo, K hi) {
        Queue<K> queue = new Queue<K>();
        keys(root, queue, lo, hi);
        return queue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet(min(), max()).iterator();
    }

    private void keys(BSTNode x, Queue<K> queue, K lo, K hi) {
        if (x == null) return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) keys(x.lchild, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.rchild, queue, lo, hi);
    }

    /** Remove the key-value pair with the smallest key. */
    public void deleteMin() {
        deleteMin(root);
    }

    /** @return the new subtree rooted at 'node' with any deletion taken into account. */
    private BSTNode deleteMin(BSTNode node) {
        if (node == null) return null;

        // Deletion can be accomplished by simply replacing the subtree
        // rooted at 'node' with its right child. 'node' is then garbage-collected.
        if (node.lchild == null) return node.rchild;

        // Otherwise, update the left child subtree to reflect deletion down-tree.
        node.lchild = deleteMin(node.lchild);
        node.N = 1 + size(node.lchild) + size(node.rchild);
        return node;
    }

    /** Removes the mapping for the specified key from this map if present. */
    @Override
    public V remove(K key) {
        V res = get(key);
        root = remove(root, key);
        return res;
    }

    /** Beautiful. Just beautiful. (I take no credit) */
    private BSTNode remove(BSTNode node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)        node.lchild = remove(node.lchild, key);
        else if (cmp > 0)   node.rchild = remove(node.rchild, key);
        else {
            // These two lines...so simple...yet so magnificent...I am not worthy...
            if (node.rchild == null) return node.lchild;
            if (node.lchild == null) return node.rchild;

            // 1. Save a link to the node to be deleted in nodeCopy.
            BSTNode nodeCopy = node;
            // 2. Begin by resetting node to point to its successor (which has lchild == null by definition).
            node = successorOf(nodeCopy);
            // 3. Update its rchild to be what it was before, but with [successor] deleted.
            node.rchild = deleteMin(nodeCopy.rchild);
            // 4. Set the left child of 'node' (which was null) to what it originally was when entering this function.
            node.lchild = nodeCopy.lchild;
        }
        node.N = 1 + size(node.lchild) + size(node.rchild);
        return node;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        V res = get(key);
        if (res != value) return null;
        root = remove(root, key);
        return res;
    }

    /** Print out the BSTMap in order of increasing Key. */
    public void printInOrder() {
        System.out.print("(");
        printInOrder(root);
        System.out.print(")");
    }

    private void printInOrder(BSTNode node) {
        if (node == null) return;

        if (node.lchild != null) printInOrder(node.lchild);
        System.out.print(" " + node.key);
        if (node.rchild != null) printInOrder(node.rchild);
    }

    /* ==================================================================================
     * Miscellaneous private helper methods.
     * ================================================================================== */

    private K min() {
        return min(root).key;
    }

    private BSTNode min(BSTNode subtree) {
        if (subtree.lchild == null) return subtree;
        return min(subtree.lchild);
    }

    private K max() {
        return max(root).key;
    }

    private BSTNode max(BSTNode subtree) {
        if (subtree.rchild == null) return subtree;
        return max(subtree.rchild);
    }

    private BSTNode successorOf(BSTNode node) {
        if (node.rchild == null) return null;
        return min(node.rchild);
    }


}
