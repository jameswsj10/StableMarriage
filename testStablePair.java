import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.*;

import static org.junit.Assert.*;

public class testStablePair {
    @Test
    public void test1() {
        String data = "2" +
                "\nTheresa, James, Michael" +
                "\nShanna, Michael, James" +
                "\nJames, Theresa, Shanna" +
                "\nMichael, Theresa, Shanna";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        HashMap<String, ArrayList<String>> a = new HashMap<>();
        ArrayList<String> names1 = new ArrayList<String>();
        names1.add("James");
        names1.add("Michael");

        a.put("Theresa", names1);
        a.put("Shanna", names1);

        HashMap<String, Queue<String>> b = new HashMap<>();
        Queue<String> names2 = new LinkedList<>();
        names2.add("Theresa");
        names2.add("Shanna");

        Queue<String> names3 = new LinkedList<>();
        names3.add("Theresa");
        names3.add("Shanna");

        b.put("James", names2);
        b.put("Michael", names3);

        StableMarriage.Solution(2, a, b);
    }
}