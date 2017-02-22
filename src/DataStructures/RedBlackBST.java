package DataStructures;

import DataStructures.Interfaces.Map61B;
import edu.princeton.cs.algs4.Queue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Overview:
 *      An implementation of a left-leaning red-black binary search tree (map from key->val).
 *
 * Definitions/Terminology:
 *
 * -->  2-3 tree: a tree that is either empty or a
 *          * 2-node:  2 children links, 1 key-val pair.
 *          * 3-node: 3 children links, 2 key-val pairs.
 *
 * --> perfectly balanced: a tree whose null links are all the same distance from the root.
 *
 * --> Left-leaning Red-black BST:
 *          * red links (fake/abstraction): bind together two 2-nodes to represent 3-nodes.
 *              Example: [A E]  -->         E
 *              red links  ALWAYS LEAN LEFT (for LLRBs)
 *                                      A
 *          * black links (real): the actual links.
 *          * rotations: needed to satisfy left-leaning restriction.
 *
 * --> Misc. subtleties:
 *          *   In LLRB illustrations, the parent link will always connect to the largest key node in the
 *              red-connected nodes because of left-leaning condition. Think about it.
 *
 *
 */
public class RedBlackBST<K extends Comparable<K>, V> implements Map61B<K, V> {

    /* Abstraction for colors to improve readability. */
    private static final boolean RED    = true;
    private static final boolean BLACK  = false;

    private LLRBNode root;

    private class LLRBNode {
        private K key;
        private V val;
        private LLRBNode left, right;
        private int N;          /* Number of nodes in the subtree rooted here. */
        private boolean color; /* either RED or BLACK. */

        public LLRBNode(K key, V val, int N, boolean color) {
            this.key    = key;
            this.val    = val;
            this.N      = N;
            this.color  = color;
        }

    }


    /* ==================================================================================
     * Constructors.
     * ================================================================================== */

    /** Initializes an empty symbol table. */
    public RedBlackBST() {}

    /* ==================================================================================
     * Insertion, search, and removal methods.
     * ================================================================================== */

    /**
     * (Unchanged from BSTMap since LLRB trees are encoded as typical binary search trees. Woot!)
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(LLRBNode node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)        return get(node.left, key);
        else if (cmp > 0)   return get(node.right, key);
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
        root.color = BLACK;
    }

    /**
     * @param node  root of the subtree.
     * @param key   to find or create.
     * @param val   to associate with key; replace if key already exists.
     * @return      the subtree containing the new (key, val) pair.
     */
    private LLRBNode put(LLRBNode node, K key, V val) {
        // Always first expand node (re: make its parent link RED).
        if (node == null) return new LLRBNode(key, val, 1, RED);

        // -------------- Identical to BSTMap code block: --------------------
        int cmp = key.compareTo(node.key);
        if (cmp < 0)        node.left =  put(node.left, key, val);
        else if (cmp > 0)   node.right = put(node.right, key, val);
        else                node.val = val;
        // -------------------------------------------------------------------


        // -------------- Additional logic needed to preserve LLRB isometry. --------------------
        if (isRed(node.right) && !isRed(node.left))     rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))  rotateRight(node);
        if (isRed(node.left) && isRed(node.right))      flipColors(node);
        // --------------------------------------------------------------------------------------

        // Update the value of node.N in case of newly created node.
        node.N = 1 + size(node.right) + size(node.left);
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

    private void keys(LLRBNode x, Queue<K> queue, K lo, K hi) {
        if (x == null) return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /** Remove the key-value pair with the smallest key. */
    public void deleteMin() {
        throw new UnsupportedOperationException();
    }


    /** @return the new subtree rooted at 'LLRBNode' with any deletion taken into account. */
    private LLRBNode deleteMin(LLRBNode node) {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /** Beautiful. Just beautiful. (I take no credit) */
    private LLRBNode remove(LLRBNode LLRBNode, K key) {
        throw new UnsupportedOperationException();
    }


    /** Print out the BSTMap in order of increasing Key. */
    public void printInOrder() {
        System.out.print("(");
        printInOrder(root);
        System.out.print(")");
    }

    private void printInOrder(LLRBNode LLRBNode) {
        if (LLRBNode == null) return;

        if (LLRBNode.left != null) printInOrder(LLRBNode.left);
        System.out.print(" " + LLRBNode.key);
        if (LLRBNode.right != null) printInOrder(LLRBNode.right);
    }

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

    /** @return the number of <K, V> Pairs rooted at 'LLRBNode'. */
    private int size(LLRBNode LLRBNode) {
        if (LLRBNode == null) return 0;
        return LLRBNode.N;
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
     * Miscellaneous private helper methods.
     * ================================================================================== */

    /** @return True if link from node to its __parent__ is RED, else false. */
    private boolean isRed(LLRBNode node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * Left-rotation to ensure left-leaning.
     * Overall goal: push leftNode below-and-left of rightNode, and make their connection red.
     * Expected form of usage: node = rotateLeft(node);
     * @param leftNode must have a right child, linked by a red link.
     * @return the subtree rooted at leftNode.rightChild after the rotation.
     */
    private LLRBNode rotateLeft(LLRBNode leftNode) {
        if (!isRed(leftNode.right)) {
            throw new RuntimeException("Error: rotation not permitted.");
        }

        LLRBNode rightNode  = leftNode.right;
        // Reassign the 'in between' child to leftNode.right.
        leftNode.right      = rightNode.left;
        // Move rightNode up -- assign its left child as leftNode.
        rightNode.left      = leftNode;

        // Since rightNode is moving to where leftNode was previously, make sure its parent link has correct color.
        rightNode.color = leftNode.color;
        // Set leftNode color to RED since it is now below-and-left of rightNode (who has been promoted).
        leftNode.color  = RED;

        // Since rightNode is now the new subtree-root, we can just set its N to that of leftNode.
        rightNode.N = leftNode.N;
        // Update the value of leftNode.N, which will be smaller.
        leftNode.N = 1 + size(leftNode.left) + size(leftNode.right);

        // Return the new subtree-root, after rotating leftNode down-and-left.
        return rightNode;
    }

    /** Pushed rightNode down-and-right */
    private LLRBNode rotateRight(LLRBNode rightNode) {
        if (!isRed(rightNode.left)) {
            throw new RuntimeException("Error: rotation not permitted.");
        }

        LLRBNode leftNode  = rightNode.left;
        // Reassign the 'in between' child to rightNode.right.
        rightNode.left      = leftNode.right;
        // Move leftNode up -- assign its left child as rightNode.
        leftNode.right      = rightNode;

        // Since leftNode is moving to where rightNode was previously, make sure its parent link has correct color.
        leftNode.color = rightNode.color;
        // Set rightNode color to RED since it is now below-and-left of leftNode (who has been promoted).
        rightNode.color  = RED;

        // Since leftNode is now the new subtree-root, we can just set its N to that of rightNode.
        leftNode.N = rightNode.N;
        // Update the value of rightNode.N, which will be smaller.
        rightNode.N = 1 + size(rightNode.left) + size(rightNode.right);

        // Return the new subtree-root, after rotating rightNode down-and-left.
        return leftNode;
    }

    private void flipColors(LLRBNode node) {
        assert(node.color == BLACK && node.right.color == RED && node.left.color == RED);
        node.color = RED;
        node.left.color = node.right.color = BLACK;
    }

    private K min() {
        return min(root).key;
    }

    private LLRBNode min(LLRBNode subtree) {
        if (subtree.left == null) return subtree;
        return min(subtree.left);
    }

    private K max() {
        return max(root).key;
    }

    private LLRBNode max(LLRBNode subtree) {
        if (subtree.right == null) return subtree;
        return max(subtree.right);
    }

    private LLRBNode successorOf(LLRBNode LLRBNode) {
        if (LLRBNode.right == null) return null;
        return min(LLRBNode.right);
    }

}
