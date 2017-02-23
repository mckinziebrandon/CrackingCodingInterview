import static org.junit.Assert.*;

import static Algorithms.MergeSort.*;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

public class TestMergeSort {

    @Test
    public void testSemantics() {
        Queue<String> hi = new Queue<>();
        hi.enqueue("hi");
        hi.enqueue("bye");
        hi.enqueue("sigh");

        Queue<String> sortedHi = new Queue<>();
        sortedHi.enqueue("bye");
        sortedHi.enqueue("hi");
        sortedHi.enqueue("sigh");

        Queue<String> mergeSortedHi = mergeSort(hi);
        for (int _ = 0; _ < 3; _++) {
            assertEquals(sortedHi.dequeue(), mergeSortedHi.dequeue());
        }
    }

    @Test
    public void testSimple() {
        Queue<String> students = new Queue<>();
        students.enqueue("Beavis");
        students.enqueue("Tyrone");
        students.enqueue("Harold");

        Queue<String> sortedStudents = mergeSort(students);
        System.out.println(sortedStudents.toString());

    }

    @Test
    public void testSingleItemQueues() {
        Queue<String> languages = new Queue<>();
        languages.enqueue("Japanese");
        languages.enqueue("English");
        languages.enqueue("French");


        Queue<Queue<String>> singleLanguages = makeSingleItemQueues(languages);
        System.out.println(singleLanguages.toString());
    }

    @Test
    public void testMergeSortedQueues() {
        Queue<String> languages = new Queue<>();
        languages.enqueue("Japanese");
        languages.enqueue("English");
        languages.enqueue("French");
        Queue<String> students = new Queue<>();
        students.enqueue("Beavis");
        students.enqueue("Tyrone");

        System.out.println(mergeSortedQueues(languages , students).toString());
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMergeSort.class);
    }
}
