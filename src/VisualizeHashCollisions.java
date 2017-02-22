import java.util.*;

/**
 * Created by bmcki on 2/20/2017.
 */
public class VisualizeHashCollisions {

    public static void printBuckets(Map<Integer, Integer> buckets, int M) {

        System.out.println("\n\n=======================================================");
        System.out.println("Bucket contents where M = " + M + " and size = " + buckets.size());
        for (int i = 0; i < M; i++) {
            System.out.print("\n" + i + ": ");
            if (!buckets.containsKey(i)) continue;
            int val = buckets.get(i);
            int dot_ctr = 0;
            while(dot_ctr++ < val) System.out.print(".");
        }
    }

    public static int getIndex(int val, int M) {
        return (val & 0x7fffffff) % M;
    }


    /** Illustrate how non-uniform distributions are best handled with prime number of buckets M. */
    public static void main(String[] args) {

        final int N_VALS = 1000;
        final int STEP_SIZE = 3;
        final int INIT = 0;
        int[] M_vals = {12, 13};

        for (int M : M_vals) {
            HashMap<Integer, Integer> buckets = new HashMap<>();
            // Fill underlying array of size M in buckets with counts.
            for (int i = INIT; i < N_VALS; i += STEP_SIZE) {
                // Compute index that the number 'i' will be mapped to in size M array.
                int index = getIndex(i, M);
                if (!buckets.containsKey(index)) buckets.put(index, 1);
                else buckets.put(index, buckets.get(index) + 1);
            }
            printBuckets(buckets, M);
        }



    }
}
