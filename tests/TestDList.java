import org.junit.Test;
import static org.junit.Assert.*;
import DataStructures.LinkedList;

public class TestDList {

    @Test
    public void testDList() {

        // Create & test [1, 4, 2, 9]
        LinkedList<Integer> intDList = new LinkedList<>();
        intDList.insertFront(4);
        intDList.insertBack(2);
        intDList.insertFront(1);
        intDList.insertBack(9);
        assertEquals("(1, 4, 2, 9)", intDList.toString());

        // Create & test "Hi how are you";
        String[] testStr = "Hi how are you".split(" ");
        LinkedList<String> strDList = new LinkedList<>(testStr);
        assertEquals("(Hi, how, are, you)", strDList.toString());
    }
}
