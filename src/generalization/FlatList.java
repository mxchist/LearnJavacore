package generalization;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

import static java.lang.System.out;

public class FlatList {

    static void showCollection(LinkedList<String> l) {

    }

    public static void main(String... args) {
        LinkedList<String> l = new LinkedList<String>();
        l.add(null);

        l.add(1, "Test1");
        l.add(2, "Test1");
        l.add(3, "Test1");
        l.add(4, "Test2");
        l.add(5, "Test2");
        l.add(6, "Test3");
        l.add(7, "Test3");
        l.add(8, "Test4");

        out.println("LinkedList");
        for (int i = 1; i < l.size(); i++) {
            out.printf("%o : %s %n", i, l.get(i));
        }

        ArrayList<HashMap<Integer, String>> al = new ArrayList<HashMap<Integer, String>>();

        for (int i = 0; i < 4; i++) {
            al.add(new HashMap<Integer, String>());
        }

        for (String s : l) {
            if (s== null) continue;
            for (int i = 0; i < l.lastIndexOf("Test" + (i+1)); i++) {
                if (s.equals("Test" + (i+1))) {
                    int j = al.get(i).size();
                    al.get(i).put(j, "Test1");
                }
            }
        }

        out.println("HashMap");
        for (HashMap<Integer, String> hm : al) {
            for (int i = 0; i < hm.size(); i++) {
                out.printf("%o : %s %n", i, hm.get(i));
            }
        }
    }
}
