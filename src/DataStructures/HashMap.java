package DataStructures;
import CourseWork.Lab_Hashing.lab9.Map61B;
import CourseWork.Lab_Hashing.lab9.ULLMap;

import java.util.*;

/**
 * You should increase the size of your HashMap when the loadFactor exceeds some number of your choice, unless the HashMap was instantiated with the loadFactor parameter, in which case you should use that number.
 * Your Hashmap should initially have a number of buckets equal to initialSize.
 * You are not required to resize down.
 * When resizing, make sure to multiplicatively increase the size,
 * not additively (e.g. multiply by 2, don't add 100 or something).
 * Your HashMap operation should all be constant amortized time, assuming that the hashCode of any objects inserted spread things out nicely.
 You should handle collisions by chaining. You may not import any libraries other than ArrayList, LinkedList, HashSet, iterator and Set.
 */
public class HashMap<K, V> implements Map61B<K, V> {

    /* Maximum allowed value for L = N / M. */
    private static double MAX_LOAD = 5.0;
    /* Grow factor when resizing. */
    private static int R = 2;
    /* Underlying array of ULLMap. Individual entries are <K, V> pairs (Entry objects). */
    private ArrayList<ULLMap<K, V>> arr;


    /* ==================================================================================
     * Constructors.
     * ================================================================================== */

    public HashMap() { arr = initArr(100); }
    public HashMap(int initialSize) { arr = initArr(initialSize); }
    public HashMap(int initialSize, double loadFactor) {
        arr = initArr(initialSize);
        MAX_LOAD = loadFactor;
    }


    /* ==================================================================================
     * Getters.
     * ================================================================================== */

    public int N() { return size(); }
    public int M() { return arr.toArray().length; }
    public double L() { return (double) N() / (double) M(); }


    /* ==================================================================================
     * Map61B method implementations.
     * ================================================================================== */

     /** Removes all of the mappings from this map. */
     @Override
    public void clear() {
        for (ULLMap<K, V> bucket : arr) {
            bucket.clear();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) { return get(key) != null; }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) { return arr.get(keyToIndex(key)).get(key); }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        int sum = 0;
        for (ULLMap<K, V> bucket : arr) {
            sum += bucket.size();
        }
        return sum;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (L() > MAX_LOAD) resize(R * N());
        arr.get(keyToIndex(key)).put(key, value);
    }


    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<K>();
        for (ULLMap<K, V> bucket : arr) {
            keys.addAll(bucket.keySet());
        }
        return keys;
    }

    @Override
    public Iterator<K> iterator() { return keySet().iterator(); }

    /** Removes the mapping for the specified key from this map if present. */
    @Override
    public V remove(K key) {
        return arr.get(keyToIndex(key)).remove(key);
    }

    /** Removes the entry for the specified key only if it is currently mapped to the specified value.  */
    @Override
    public V remove(K key, V value) {
        return arr.get(keyToIndex(key)).remove(key, value);
    }


    /* ==================================================================================
     * Private methods.
     * ================================================================================== */

    private ArrayList<ULLMap<K, V>> initArr(int capacity) {
        ArrayList<ULLMap<K, V>> res = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            res.add(i, new ULLMap<>());
        }
        return res;
    }

    /** ... and fill. */
    private void resize(int newSize) {
        ArrayList<ULLMap<K, V>> newArr = initArr(newSize);
        ArrayList<ULLMap<K, V>> oldArr = arr;

        arr = newArr;
        // Sanity check.
        assert(M() > oldArr.size());
        // Ensure we aren't about to execute an infinite loop...
        assert(L() <= MAX_LOAD);

        copy(oldArr, arr);
    }

    private void copy(ArrayList<ULLMap<K, V>> oldArr, ArrayList<ULLMap<K, V>> newArr) {
        for (ULLMap<K, V> bucket : oldArr) {
            for (K oldKey : bucket.keySet()) {
                newArr.get(keyToIndex(oldKey)).put(oldKey, bucket.get(oldKey));
            }
        }
    }

    private int keyToIndex(K key) {
        if (M() == 0) return 0;
        int index = (key.hashCode() & Integer.MAX_VALUE) % M();
        if (index > arr.size() || index < 0) {
            throw new RuntimeException("Computed index of " + index + " for array of size " + arr.size());
        }
        return index;
    }

}
