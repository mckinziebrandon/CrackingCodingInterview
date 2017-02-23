import DataStructures.MinHeap;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class TestArrayHeap {

    @Test
    public void testCreateHeap() {
        MinHeap<String> heap = new MinHeap<>();
        System.out.println("yay");
        assertNull(heap.peek());
    }


    @Test
    public void testWithOneItem() {
        MinHeap<String> heap = new MinHeap<>();

        // Insert the item.
        heap.insert("c", 3);
        assertEquals("c", heap.peek().item());
        assertEquals(1, heap.size());
        System.out.println("After insertion:\n" + heap.toString());

        // Remove the item.
        String c = heap.removeMin().item();
        assertEquals("c", c);
        System.out.println("After deletion:\n" + heap.toString());

        assertEquals(0, heap.size());
    }

    @Test
    public void testWords() {
        MinHeap<String> heap = new MinHeap<>();

        // Insert the items.
        heap.insert("w", 5);
        heap.insert("o", 8);
        heap.insert("r", 10);
        heap.insert("d", 21);
        heap.insert("s", 28);
        System.out.println(heap.toString());
        assertEquals("words".length(), heap.size());


        // Remove the items.
        MinHeap.Node wNode = heap.removeMin();
        System.out.println(heap.toString());
        assertEquals("ords".length(), heap.size());

    }

	@Test
	public void testTenThings() {
		MinHeap<String> heap = new MinHeap<>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);

		List<Double> sortedItems = new ArrayList<>();

		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());

		assertIsSorted("Failed heap test given to you in the main method.", sortedItems);
	}

	public static void assertIsSorted(String msg, List<? extends Comparable> l) {
		Comparable prev = null;
		for (Comparable item : l) {
			if (prev != null && item.compareTo(prev) < 0) {
				fail(msg);
			}
			prev = item;
		}
	}

	public static void main(String[] args) {
		jh61b.junit.TestRunner.runTests(TestArrayHeap.class);	
	}
} 
