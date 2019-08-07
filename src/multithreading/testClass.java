package multithreading;
import java.lang.Math;
import java.util.Random;

public class testClass {
    static final int size = 10000000;
    static final int h = size/2;
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
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    public static  void main(String ... args) {
//        simpleMethod();

        System.out.println("\n" + "Вычисление массива через потоки.");
        long a = System.currentTimeMillis();

        float[] res1, res2, res;
        res1 = res2 = new float[size/2];
        res = new float[size];

        int lbegin, lend = 0;
        int threadQty = 6;

        for (int l =0; l < threadQty; l++) {
            long b = System.currentTimeMillis();
            lbegin = lend;
            lend = lbegin + size/threadQty - 1;
            if (lend > arr.length) lend = arr.length - 1;

            float[] tempArray = new float[lend - lbegin];
            System.arraycopy(arr,lbegin,tempArray, 0, lend - lbegin);
            ThreadClass tempThread = new ThreadClass(tempArray);
            System.arraycopy(tempArray, lbegin);

            System.out.printf("Время цикла - %f милисекунд\n", (float) (System.currentTimeMillis() - b));
        }

        float[] a1 = new float[size/2];
        float[] a2 = new float[size/2];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        ThreadClass tc1 = new ThreadClass(a1);
        ThreadClass tc2 = new ThreadClass(a2);

        res1 = tc1.getArr();
        res2 = tc2.getArr();

        System.arraycopy(res1, 0, res, 0, h);
        System.arraycopy(res1, 0, res, h, h);

        System.out.printf("Время работы - %f милисекунд\n", (float) (System.currentTimeMillis() - a));

        int[] n = {3819651, 1556668, 3217037, 841715, 1159890, 9781837, 9154101, 7549502, 8755695, 8677778};
        for (int i : n) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
