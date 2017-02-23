import DataStructures.LinkedList;

import java.util.HashSet;

/**
 * Questions from Chapter 2 of CTCI.
 */
public class LinkedListQuestions {

    public static LinkedList<Integer> dupIntList = new LinkedList<Integer>(new Integer[]{1, 2, 3, 1, 8, 2, 212, 6});

    /** 2.1 -- Remove Dups.
     * Write code to remove duplicates from an unsorted linked list.
     *
     * My attempt: one pass through list, checking for duplicate with a hashmap.
     * --> Ayy, congrats. This was actually the solution lmao.
     *
     * If no temp buffer allowed, then my first instinct is to do an in-place sort.
     * @param list  unsorted linked list.
     */
    public static LinkedList<Integer> removeDups(LinkedList<Integer> list) {
        printBegin("removeDups");
        System.out.println("Original: " + list.toString());

        HashSet<Integer> uniqueItems = new HashSet<>();
        LinkedList<Integer> newList = new LinkedList<>();

        for (int i : list) {
            if (!uniqueItems.contains(i)) newList.add(i);
            uniqueItems.add(i);
        }

        System.out.println("New: " + newList.toString());
        return newList;
    }

    public static void main(String[] args) {
        removeDups(dupIntList);

    }

    private static void printBegin(String funcName) {
        System.out.println("================== Beginning: " + funcName + " ================== ");
    }

}
