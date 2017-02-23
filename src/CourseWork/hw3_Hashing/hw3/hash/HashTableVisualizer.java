package CourseWork.hw3_Hashing.hw3.hash;

import java.util.HashSet;
import java.util.Set;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        double scale = 0.5; //1.0;
        int N = 100; //50;
        int M = 5; //10;

        HashTableDrawingUtility.setScale(scale);
        Set<Oomage> oomies = new HashSet<Oomage>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(SimpleOomage.randomSimpleOomage());
        }
        visualize(oomies, M, scale);
    }

    /**
     *
     * @param set
     * @param M        number of buckets
     * @param scale
     */
    public static void visualize(Set<Oomage> set, int M, double scale) {

        HashTableDrawingUtility.drawLabels(M);

        int[] bucketPositions = new int[M];
        for (Oomage o : set) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            o.draw(HashTableDrawingUtility.xCoord(bucketPositions[bucketNum]++),
                    HashTableDrawingUtility.yCoord(bucketNum, M));
        }
        /* When done with visualizer, be sure to try
           scale = 0.5, N = 2000, M = 100. */           
    }
} 
