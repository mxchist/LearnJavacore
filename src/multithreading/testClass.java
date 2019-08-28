package multithreading;
import java.lang.Math;
import java.util.ArrayList;

public class testClass {
    static final int size = 10000000;
    static float[] arr = new float [size];
    static int threadQty = 2;          //Количество потоков
    static int length = size/threadQty;


    static void showArray(float[] array) {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int i =1; i < threadQty; i++) {
            for (int j = 0, k = i * length -5; j < 10; j++ ) {
                positions.add(k + j);
            }
        }
        for (int j = 0, k = threadQty * length -5; j < 10 && k + j < size; j++ ) {
            positions.add(k + j);
        }

        int j = 0;

        for (int i : positions) {
            System.out.printf("%15.8f", array[i]);
            if (++j %10 == 0) System.out.println();
        }
    }

    static void simpleMethod() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = 1f;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
        }
        System.out.printf("Время работы - %f милисекунд\n", (float) (System.currentTimeMillis() - a));

        showArray(arr);
        System.out.println();
    }

    public static  void main(String ... args) {
        simpleMethod();

        System.out.println("\n" + "Вычисление массива через потоки.");
        long a = System.currentTimeMillis();

        float[] res1, res;
        res = new float[size];

        int lbegin;
        int lend = -1;
        ArrayList<ThreadClass> storeThreadClass = new ArrayList<ThreadClass>();

        for (int l =1; lend + 1 < size - 1 ; l++) {
            long b = System.currentTimeMillis();
            lbegin = lend + 1;
            lend = lbegin + length;
            if (lend >= size) lend = size - 1;

            float[] tempArray = new float[lend - lbegin];
            ThreadClass tempThread = new ThreadClass(tempArray, lbegin);
            storeThreadClass.add(tempThread);

        }


        for (ThreadClass tempThread : storeThreadClass) {
            try {
                tempThread.thrd.join();
            }
            catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
        lbegin = 0;

        for (ThreadClass tempThread : storeThreadClass) {
            res1 = tempThread.getArr();
            System.arraycopy(res1, 0, res, lbegin, res1.length);
            lbegin += res1.length;
            lbegin += 1;
        }

        System.out.printf("Время работы - %f милисекунд\n", (float) (System.currentTimeMillis() - a));

        showArray(res);
    }
}
