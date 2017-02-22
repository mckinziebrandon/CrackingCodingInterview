package DataStructures;

import DataStructures.Interfaces.Map61B;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** A BSTMap consists of BSTNodes, and our point of reference is the 'root' node. */
    private BSTNode root;

    private class BSTNode {
        K key;
        V val;
        BSTNode lchild;
        BSTNode rchild;

        public BSTNode(K key, V val, BSTNode lchild, BSTNode rchild) {
            this.key = key;
            this.val = val;
            this.lchild = lchild;
            this.rchild = rchild;
        }

        public BSTNode put(K key, V val) {
            this.key = key;
            this.val = val;
            return this;
        }

        K key() { return key; }
        V val() { return val; }


        public void setVal(V val)  { this.val = val; }
        public void setKey(K key)  { this.key = key; }
        public int numChildren() {
            int n = 0;
            if (lchild != null) n += 1;
            if (rchild != null) n += 1;
            return n;
        }
    }

    /** Initializes an empty symbol table. */
    public BSTMap() {}

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
        return 1 + size(node.lchild) + size(node.rchild);
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

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, BSTNode node) {
        if (node == null || node.key() == null) return null;

        int cmp = key.compareTo(node.key());
        if (cmp < 0)        return get(key, node.lchild);
        else if (cmp == 0)  return node.val();
        else if (cmp > 0)   return get(key, node.rchild);
        else throw new RuntimeException("[get] Error: key comparison failed.");
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
        root = put(key, val, root, null);
    }

    private BSTNode put(K key, V val, BSTNode node, BSTNode parent) {
        if (node == null) {
            return new BSTNode(key, val, parent, null, null);
        } else if (node.key() == null) {
            return node.put(key, val);
        }

        int cmp = key.compareTo(node.key());
        if (cmp < 0)        node.lchild =  put(key, val, node.lchild, node);
        else if (cmp > 0)   node.rchild = put(key, val, node.rchild, node);
        else                node.setVal(val);
        return node;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<K>();
        fillKeySet(keys, root);
        return keys;
    }

    public void fillKeySet(Set<K> keys, BSTNode node) {
        if (node == null || node.key() == null) return;
        keys.add(node.key());
        fillKeySet(keys, node.lchild);
        fillKeySet(keys, node.rchild);
    }

    /** Removes the mapping for the specified key from this map if present. */
    public V remove(K key) {
        if (key == null || get(key) == null) return null;
        return remove(key, root, null);
    }

    /** Responsible for finding which node to remove and then calling removeNode on it. */
    private V remove(K key, BSTNode node, BSTNode parent) {
        if (node == null || node.key() == null) return null;

        int cmp = key.compareTo(node.key());
        if (cmp < 0) {
            // Keep searching.
            return remove(key, node.lchild, node);
        } else if (cmp > 0) {
            // Keep searching.
            return remove(key, node.rchild, node);
        } else {
            // Found the node to remove.
            return removeNode(node, parent);
        }
    }

    // TODO: review how to remove from trees...recall it is like 'trickle-down' removal from heaps.
    /** Delete single specified node from tree and return its value (does not recurse). */
    private V removeNode(BSTNode node, BSTNode parent) {
        if (node == null || node.key() == null) return null;

        V val = node.val;
        switch(node.numChildren()) {
            case 0:
                node = null;
                break;
            case 1: /* Reassign parent.[r,l]child to the sole child of node. */
                BSTNode newChildOfParent = (node.rchild != null) ? node.rchild : node.lchild;
                if (parent.lchild == node)  parent.lchild = newChildOfParent;
                else                        parent.rchild = newChildOfParent;
                break;
            case 2:
                // Get successor of node.
                BSTNode succ = successorOf(node);
                // Replace node with successor.
                succ.rchild = node.rchild;
                succ.lchild = node.lchild;
                succ.parent = node.parent;
                if (parent.lchild == node)  parent.lchild = succ;
                else                        parent.rchild = succ;
                removeNode(succ, succ.parent);
        }
        return val;
    }

    private BSTNode successorOf(BSTNode node) {
        if (node.rchild == null) throw new RuntimeException("Error: successor does not exist.");
        BSTNode succ = node.rchild;
        while (succ.lchild != null) {
            succ = succ.lchild;
        }
        return succ;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) return remove(key);
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
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
        System.out.print(" " + node.key());
        if (node.rchild != null) printInOrder(node.rchild);
    }



}
