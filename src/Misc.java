import java.util.*;

/**
 * Created by brandon on 3/24/17.
 */
public class Misc {

    /** Two-pointer technique/approach.
     * Main hurdle for me: realizing I should be incrementing end at every step.
     * */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        // Yay: just peeked and I'm doing it right :)
        HashMap<Character, Integer> charToPos = new HashMap<>();
        // Running value of max so far.
        int maxLen = 0;
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (charToPos.containsKey(c)) {
                // Update the maxLen before resetting start ptr.
                maxLen  = Math.max(maxLen, end - start);
                // Advance start ptr 1 spot past repeated char.
                start   = Math.max(start, charToPos.get(c) + 1);
            }
            charToPos.put(c, end++);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abca";
        System.out.println("LOLS = " + lengthOfLongestSubstring(s));
    }
}
