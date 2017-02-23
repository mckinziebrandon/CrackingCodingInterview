package CourseWork.hw3_Hashing.hw3.hash;

import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.StdRandom;


public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    public boolean haveNiceHashCodeSpread(Set<ComplexOomage> oomages) {
        /* Mod each's hashCode by M = 10,
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int N = oomages.size();
        int M = 10;
        int[] bucketPositions = new int[M];
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketPositions[bucketNum]++;
        }

        for (int i = 0; i < M; i++) {
            if (bucketPositions[i] < N / 50) return false;
            if (bucketPositions[i] > N / 2.5) return false;
        }

        return true;
    }


    @Test
    public void testRandomItemsHashCodeSpread() {
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    @Test
    public void testWithDeadlyParams() {
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        int N = 1000;

        final int LO = 250;
        final int N_PARAMS = 100;
        ArrayList<Integer> params;

        for (int i_oomage = 0; i_oomage < N; i_oomage++) {
            params = new ArrayList<>();

            params.add(0, 1);
            for (int i = 1; i < N_PARAMS; i++) {
                params.add(i, 0);
                if (i_oomage == 0 && i < 20) System.out.println(params.get(i));
            }

            oomages.add(new ComplexOomage(params));
        }

        int blah = 0;
        System.out.println("\n");
        for (Oomage o : oomages) {
            System.out.println(o.hashCode());
            if (blah++ > 20) break;
        }


        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
