package collections;
import static java.lang.System.out;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class PhoneBook {
    HashMap<String, ArrayList<Integer> > phoneBook = new HashMap<>();

    private void addPhone(String name, int number) {
        if (phoneBook.containsKey(name)) {
            ArrayList<Integer> phoneList = phoneBook.get(name);
            phoneList.add(number);
            phoneBook.put(name, phoneList);
        }
        else {
            ArrayList<Integer> phoneList = new ArrayList<>();
            phoneList.add(number);
            phoneBook.put(name, phoneList);
        }
    }

    public static void main (String ... args) throws IOException {
        //Scanner нужен, чтобы не вводить номера вручную
        //Scanner freader = new Scanner(new File(""));
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
    }
}
