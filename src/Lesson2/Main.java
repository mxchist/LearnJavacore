package Lesson2;
import java.lang.Integer;

public class Main {

    public static void main (String ... args) {
        int sum = 0;
        //String[][]matrix = {{"1", "3", "5", "d"}, {"32", "b", "12", "z"}, {"a", "364", "632", "ua"}, {"89", "5675", "i", "48"}};
        String[][]matrix = {{"1", "3", "5", "587"}, {"32", "135", "12", "54"}, {"935", "364", "632", "143"}, {"89", "5675", "211", "48"}};

        if (matrix.length != 4 && matrix[0].length != 4) {
            throw new MyArraySizeException("Массмв должен быть двумерным");
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {sum +=  Integer.parseInt( matrix[i][j]); }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException("В ячейке [" + j + ", " + i + "] лежит не число", j, i);
                }
            }
        }

        System.out.println(sum);
    }
}
