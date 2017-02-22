package DataStructures.Interfaces;

/** API for a generic basic symbol table.
 * Algorithms Chapter 3 - Searching.
 * The symbol table is the general interface. */
public interface SymbolTable<K, V> {

    /** Put key-value pair in the table (remove key from table if value is null). */
    void put(K key, V val);

    /** Retrieve value paired with key (null if key is absent). */
    V get(K key);

    /** Remove key and its value from table. */
    void delete(K key);

    /** Is there a value paired with this key? */
    boolean contains(K key);

    boolean isEmpty();

    int size();

    Iterable<K> keys();
}
