/**
 * Functions useful for questions involvie bitwise operations.
 */
public class BitOps {

    /** Return the total number of different bits bw x,y.
     * XOR: x ^ y
     */
    public static int hammingDistance(int x, int y) {
        // Return number of 1-bits in (x ^ y).
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        System.out.println("Hamming distance between 1 and 4 is " + hammingDistance(1, 4));
    }
}
