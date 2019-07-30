package collections;
import static java.lang.System.out;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.StringBuilder;

public class PhoneBook {
    static HashMap<String, ArrayList<Double> > phoneBook = new HashMap<>();

    static void addPhone(String name, double number) {
        if (phoneBook.containsKey(name)) {
            ArrayList<Double> phoneList = phoneBook.get(name);
            phoneList.add(number);
            phoneBook.put(name, phoneList);
        }
        else {
            ArrayList<Double> phoneList = new ArrayList<>();
            phoneList.add(number);
            phoneBook.put(name, phoneList);
        }
    }

    static void getPhone(String name) {
        ArrayList<Double> phoneList = phoneBook.get(name);
        if ( phoneList.isEmpty()) {
            return;
        }
        out.printf("[");
        for (int i = 0; i < phoneList.size(); i++) {
            if (i > 1 ) out.print(", ");
            out.printf("%12.0f", phoneList.get(i));
        }
        out.printf("]");
        out.println();
    }


    public static void main (String ... args) throws IOException {
        //Scanner нужен, чтобы не вводить номера вручную
        Scanner freader = new Scanner(new File("src/collections/phones.txt"));
        while (freader.hasNext()) {
            String name = freader.next();
            double number = freader.nextDouble();
            addPhone(name, number);
        }

        for(Map.Entry<String, ArrayList<Double> > c : phoneBook.entrySet() ) {
//            out.printf("%-12s %12s %-10s\n", c.getKey(), "", c.getValue().toString());
            out.printf("%-12s %12s", c.getKey(), "");
            getPhone(c.getKey());
        }

//        out.println(a + "\t" + number);
//        String current = new java.io.File( "." ).getCanonicalPath();
//        System.out.println("Current dir:"+current);
//        String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
    }
}
