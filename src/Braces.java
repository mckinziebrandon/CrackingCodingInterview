import java.util.*;

public class Braces {

    public static HashMap<Character, Character> PAIRS;
    public static boolean match(char o, char c) { return PAIRS.get(o) == c; }
    public static boolean isOpener(char c)      { return PAIRS.containsKey(c); }
    public static boolean isCloser(char c)      { return PAIRS.containsValue(c); }

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (isOpener(c)) {
                stack.push(c);
            } else if (isCloser(c)) {
                char opener = stack.pop();
                if (!match(opener, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void printBalanced(Iterable<String> inputs) {
        for (String s : inputs) {
            if (isBalanced(s)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        PAIRS = new HashMap<>();
        PAIRS.put('(', ')');
        PAIRS.put('[', ']');
        PAIRS.put('{', '}');
        ArrayList<Integer> hi = new ArrayList<>();
        hi.toArray();

        ArrayList<String> sampleInputs = new ArrayList<>();
        int N = 3;
        sampleInputs.add("{[()]}");
        sampleInputs.add("{[(])}");
        sampleInputs.add("{{[[(())]]}}");
        printBalanced(sampleInputs);

    }
}
