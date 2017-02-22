package DataStructures.Simple;
/** Array based list. [DIY FILLED-IN]
 *  @author Josh Hug
 *  @author Brandon McKinzie
 */
public class AList<Item> {

    private static final int RFACTOR = 2; // grow/shrink factor when resizing.

    private int size;
    private Item[] arr;

    /** Creates an empty list. */
    public AList() {
        arr = (Item[]) new Object[10];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void insertBack(Item x) {
        if (size == arr.length) { resize(RFACTOR * size); }
        arr[size++] = x;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item deleteBack() {
        return arr[--size];
    }

    /** Resize our backing array so that it is
    * of the given newSize. */
    private void resize(int newSize) {
        Item[] a = (Item[]) new Object[newSize];
        System.arraycopy(arr, 0, a, 0, size);
        arr = a;
    }

    /** Returns the item from the back of the list. */
    public Item getBack() {
        return arr[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return arr[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }
}