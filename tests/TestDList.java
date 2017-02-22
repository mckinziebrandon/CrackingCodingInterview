import org.junit.Test;
import static org.junit.Assert.*;
import DataStructures.DList;

public class TestDList {

    @Test
    public void testDList() {

        // Create & test [1, 4, 2, 9]
        DList<Integer> intDList = new DList<>();
        intDList.insertFront(4);
        intDList.insertBack(2);
        intDList.insertFront(1);
        intDList.insertBack(9);
        assertEquals("(1, 4, 2, 9)", intDList.toString());

        // Create & test "Hi how are you";
        String[] testStr = "Hi how are you".split(" ");
        DList<String> strDList = new DList<>(testStr);
        assertEquals("(Hi, how, are, you)", strDList.toString());
    }
}
