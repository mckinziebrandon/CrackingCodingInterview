
import static org.junit.Assert.*;

import static Algorithms.QuickSort.*;
import org.junit.Test;
import edu.princeton.cs.algs4.Queue;

import java.util.StringTokenizer;

public class TestQuickSort {
    public String FRESH_PRINCE = "Now this is a story all about how\n"+
            "My life got flipped-turned upside down\n"+
            "And I'd like to take a minute\n"+
            "Just sit right there\n"+
            "I'll tell you how I became the prince of a town called Bel-Air\n"+
            "\n"+
            "In west Philadelphia born and raised\n"+
            "On the playground was where I spent most of my days\n"+
            "Chillin' out maxin' relaxin' all cool\n"+
            "And all shooting some b-ball outside of the school\n"+
            "When a couple of guys who were up to no good\n"+
            "Started making trouble in my neighborhood\n"+
            "I got in one little fight and my mom got scared\n"+
            "She said, \"You're movin' with your auntie and uncle in Bel-Air.\"\n"+
            "\n"+
            "I begged and pleaded with her day after day\n"+
            "But she packed my suitcase and sent me on my way\n"+
            "She gave me a kiss and then she gave me my ticket.\n"+
            "I put my Walkman on and said, \"I might as well kick it.\"\n"+
            "\n"+
            "First class, yo, this is bad\n"+
            "Drinking orange juice out of a champagne glass.\n"+
            "Is this what the people of Bel-Air living like?\n"+
            "Hmm, this might be alright.\n"+
            "\n"+
            "But wait I hear they're prissy, bourgeois, all that\n"+
            "Is this the type of place that they just send this cool cat?\n"+
            "I don't think so\n"+
            "I'll see when I get there\n"+
            "I hope they're prepared for the prince of Bel-Air\n"+
            "\n"+
            "Well, the plane landed and when I came out\n"+
            "There was a dude who looked like a cop standing there with my name out\n"+
            "I ain't trying to get arrested yet\n"+
            "I just got here\n"+
            "I sprang with the quickness like lightning, disappeared\n"+
            "\n"+
            "I whistled for a cab and when it came near\n"+
            "The license plate said \"Fresh\" and it had dice in the mirror\n"+
            "If anything I could say that this cab was rare\n"+
            "But I thought, \"Nah, forget it.\"\n"+
            "– \"Yo, home to Bel-Air.\"\n"+
            "\n"+
            "I pulled up to the house about 7 or 8\n"+
            "And I yelled to the cabbie, \"Yo home smell ya later.\"\n"+
            "I looked at my kingdom\n"+
            "I was finally there\n"+
            "To sit on my throne as the Prince of Bel-Air";

    /** stay fresh */
    public Queue<String> getFreshQueue() {
        StringTokenizer tokenizer = new StringTokenizer(FRESH_PRINCE, " \t\n\r\f,.:;?![]'");
        Queue<String> freshQ = new Queue<>();
        while (tokenizer.hasMoreElements()) {
            freshQ.enqueue(tokenizer.nextToken());
        }
        return freshQ;
    }

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

        Queue<String> mergeSortedHi = quickSort(hi);
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

        Queue<String> sortedStudents = quickSort(students);
        System.out.println(sortedStudents.toString());
    }

    @Test
    public void testFreshSort() {
        Queue<String> freshQ = getFreshQueue();
        Queue<String> sortedFreshQ = quickSort(freshQ);
        //System.out.println(sortedFreshQ);
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMergeSort.class);
    }
}
