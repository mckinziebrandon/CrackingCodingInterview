package DataStructures;

import java.util.ArrayList;

/**
 * A Generic heap class. Unlike Java's priority queue, this heap doesn't just
 * store Comparable objects. Instead, it can store any type of object
 * (represented by type T), along with a priority value.
 *
 * CLARIFICATION: the 'min' is for 'min priority'. This is not clear in the lab spec.
 */
public class MinHeap<T> {
    private int size = 0;
    private static final int START_INDEX = 1;
	private ArrayList<Node> contents = new ArrayList<Node>();

	/**
	 * Inserts an item with the given priority value. This is enqueue, or offer.
	 */
	public void insert(T item, double priority) {

	    // 1. Put the item you're adding in the left-most spot in the bottom level of the tree.
        int newNodeIndex = getLastIndex() + 1;
	    setNode(newNodeIndex, new Node(item, priority));
	    size += 1;

	    // 2. Swap the added item with its parent until it's larger than its parent or the new root.
	    while (newNodeIndex == min(newNodeIndex, getParentOf(newNodeIndex)) && !isRoot(newNodeIndex)) {
	        newNodeIndex = bubbleUp(newNodeIndex);
        }
	}

	/**
	 * Returns the Node with the smallest priority value, but does not remove it
	 * from the heap.
	 */
	public Node peek() {
	    return getFirst();
	}

	/**
	 * Returns the Node with the smallest priority value, and removes it from
	 * the heap. This is dequeue, or poll.
	 */
	public Node removeMin() {
	    if (size == 0) return null;
	    Node minNode = getFirst();

        int bubbleIndex = 1;
        // Copy bubbleNode to front.
	    setNode(bubbleIndex, getLast());
	    // Delete bubbleNode that's still at back.
	    setNode(getLastIndex(), null);
        size -= 1;

        while (largerThanChildren(bubbleIndex)) {
            bubbleIndex = bubbleDown(bubbleIndex);
        }

        return minNode;
	}


	/**
     * @param i index of the 'parent'.
     * @return true if parent priority > both children priority. */
	private boolean largerThanChildren(int i) {
	    if (size <= 1) return false;
	    return min(i, min(getLeftOf(i), getRightOf(i))) != i;
    }

	/**
	 * Change the node in this heap with the given item to have the given
	 * priority. For this method only, you can assume the heap will not have two
	 * nodes with the same item. Check for item equality with .equals(), not ==
	 */
	public void changePriority(T item, double priority) {
	    // TODO: this does not rearrange the heap after changing the priority (but it should).
	    Node changedNode;
	    for (Node node : contents) {
	        if (node.item().equals(item)) {
	            node.myPriority = priority;
                changedNode = node;
	            break;
            }
        }
	}


	/**
	 * Prints out the heap sideways.
	 */
	@Override
	public String toString() {
        System.out.print("(raw array): ");
	    for (Node n : contents) {
	        if (n == null) {
	            System.out.print(" [null] ");
	            continue;
            }
            System.out.print(" " + n.item().toString());
        }
        System.out.println();

		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}
	public int size() {
	    return size;
    }

	private Node getLast() {
	    Node last = getNode(getLastIndex());
	    return last;
    }

    private int getLastIndex() {
	    // because offset by 1 (zeroth entry always null);
	    return size;
    }

    private Node getFirst() {
	    return getNode(1);
    }

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}

		contents.set(index, n);
	}

	/**
	 * Swap the nodes at the two indices.
	 */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/**
	 * Returns the index of the node to the left of the node at i.
	 */
	private int getLeftOf(int i) {
	    return 2 * i;
	}

	/**
	 * Returns the index of the node to the right of the node at i.
	 */
	private int getRightOf(int i) {
	    return 2 * i + 1;
	}

	/**
	 * Returns the index of the node that is the parent of the node at i.
	 */
	private int getParentOf(int i) {
	    return i / 2;
	}

	private int getSmallestChildOf(int i) {
	    return min(getLeftOf(i), getRightOf(i));
    }

	/**
     * Adds node 'n' as the left child of the node at 'index'.
	 */
	private void setLeft(int index, Node n) {
	    setNode(getLeftOf(index), n);
	}

	/**
	 * Adds the given node as the right child of the node at the given index.
	 */
	private void setRight(int index, Node n) {
        setNode(getRightOf(index), n);
	}

	/**
	 * Bubbles up the node currently at the given index.
     * NOTE: this doesn't care about min-heap properties, it just does the operation.
	 */
	private int bubbleUp(int index) {
	    int newIndex = getParentOf(index);
	    swap(index, newIndex);
	    return newIndex;
	}

	/**
	 * Bubbles down the node currently at the given index.
     * NOTE: this doesn't care about min-heap properties, it just does the operation.
	 */
	private int bubbleDown(int index) {
	    int newIndex = min(getLeftOf(index), getRightOf(index));
	    swap(index, newIndex);
	    return newIndex;
	}

    private boolean isRoot(int i) {
        return getNode(i) == getFirst();
    }

	/**
	 * Returns the index of the node with smaller priority. Precondition: Not
	 * both of the nodes are null.
	 */
	private int min(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		if (node1 == null) {
			return index2;
		} else if (node2 == null) {
			return index1;
		} else if (node1.myPriority < node2.myPriority) {
			return index1;
		} else {
			return index2;
		}
	}

	public class Node {
		private T myItem;
		private double myPriority;

		private Node(T item, double priority) {
			myItem = item;
			myPriority = priority;
		}

		public T item() {
			return myItem;
		}

		public double priority() {
			return myPriority;
		}

		@Override
		public String toString() {
			return item().toString() + ", " + priority();
		}
	}

	public static void main(String[] args) {
		MinHeap<String> heap = new MinHeap<String>();
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
		System.out.println(heap);
	}

}
