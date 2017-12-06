package huffman;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by knutandersstokke on 21 21.10.2017.
 */
public class NewlineTester {
    public static void main(String[] args) {
        String myString = "\nTest  Test  \n\nTest\t\n  Test";
        System.out.println(Arrays.toString(myString.split("\\s+", -1)));



        HashMap<String, Integer> hashMap = new HashMap<>();
        String s = "Dette er en liste av en liste av ord";
        for (String w : s.split("\\s+"))
            hashMap.put(w, hashMap.getOrDefault(w, 0) + 1);
        System.out.println(hashMap);
    }
}
