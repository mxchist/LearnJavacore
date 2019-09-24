import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Password {

    public static boolean find(String password, String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find() == true) {
            return true;
        }
        return false;
    }

    public static boolean isMatch(String password) {
        Pattern pattern1 = Pattern.compile("\\S{8,}");
//        Pattern pattern2 = Pattern.compile("[A-Z]");
//        Pattern pattern3 = Pattern.compile("[a-z]");
//        Pattern pattern4 = Pattern.compile("\\d");
        ArrayList<String> patterns = new ArrayList<String>();
        patterns.add("[A-Z]");
        patterns.add("[a-z]");
        patterns.add("\\d");

        if (pattern1.matcher(password).matches() == true) {
            for (String p : patterns) {
                if (find(password, p) == false ) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your passphrase");
        String password = scanner.nextLine();
        if (isMatch(password) == true) {
            System.out.println("Password is strong!");
        }
        else {
            System.out.println("Password is not enough secure");
        }
    }
}
