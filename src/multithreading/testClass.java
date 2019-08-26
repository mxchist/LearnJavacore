package multithreading;
import java.lang.Math;
import java.util.Random;

public class testClass {
    static final int size = 10000000;
    static float[] arr = new float [size];

    static void simpleMethod() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
        }
        System.out.printf("Время работы - %f милисекунд\n", (float) (System.currentTimeMillis() - a));

        int[] n = {3819651, 1556668, 3217037, 841715, 1159890, 9781837, 9154101, 7549502, 8755695, 8677778};
        for (int i : n) {
            System.out.printf("%12.8f", arr[i]);
        }
        System.out.println();
    }

    public static  void main(String ... args) {
//        simpleMethod();

        System.out.println("\n" + "Вычисление массива через потоки.");
        long a = System.currentTimeMillis();

        float[] res1, res;
        res = new float[size];

        int lbegin;
        int lend = -1;
        int threadQty = 6;          //Количество потоков

        for (int l =1; lend + 1 < size - 1 ; l++) {
            long b = System.currentTimeMillis();
            lbegin = lend + 1;
            lend = lbegin + size/threadQty;
            if (lend >= res.length) lend = size - 1;

            float[] tempArray = new float[lend - lbegin];
            System.arraycopy(res,lbegin,tempArray, 0, lend - lbegin);
            ThreadClass tempThread = new ThreadClass(tempArray);
            try {
                tempThread.thrd.join();
            }
            catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            res1 = tempThread.getArr();
            System.arraycopy(res1, 0, res, lbegin, lend - lbegin);

//            System.out.printf("Время цикла - %f милисекунд\n", (float) (System.currentTimeMillis() - b));
        }

        System.out.printf("Время работы - %f милисекунд\n", (float) (System.currentTimeMillis() - a));

        int[] n = {3819651, 1556668, 3217037, 841715, 1159890, 9781837, 9154101, 7549502, 8755695, 8677778};
        for (int i : n) {
            System.out.printf("%12.8f", res[i]);
        }
        System.out.println();
    }
}
