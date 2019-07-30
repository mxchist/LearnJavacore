package collections;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class collections {
    public static void main (String ... args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("Имбирь");
        al.add("Кардамон");
        al.add("Паприка");
        al.add("Корица");
        al.add("Имбирь");
        al.add("Перец");
        al.add("Корица");
        al.add("Базилик");
        al.add("Кардамон");
        al.add("Имбирь");
        al.add("Корица");
        al.add("Ванилин");
        al.add("Гвоздика");
        al.add("Имбирь");
        al.add("Сода");
        al.add("Лавр");
        al.add("Перец");
        al.add("Куркума");
        al.add("Ванилин");
        al.add("Корица");
        al.add("Базилик");
        al.add("Гвоздика");
        al.add("Имбирь");
        al.add("Мускат");

        out.println(al.size());

        HashMap<String, Integer> res = new HashMap<>();
        for (int i = 0; i < al.size(); i++) {
            if (res.containsKey(al.get(i)) ) {
                int qty = 0;
                qty = res.get(al.get(i));
                res.put(al.get(i), qty + 1);
            }
            else {
                res.put(al.get(i), 1);
            }
        }

        for (Map.Entry<String, Integer> c : res.entrySet()) {
            out.println(c.getKey() + ":" + c.getValue());
        }
    }
}
