import static java.lang.System.out;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class theadMyThread {
    static final int size = 10000000;


    static void fillArray(float [] array) {
        for (int i = 0; i<array.length; i++) {
            array[i] = 1;
        }
    }

    static float[] processArray(float[] array, int start) {
        for (int i = 0; i < array.length; i++) {
            array[i + start] = (float)(Math.sin(0.2f + (i + start)/5) * Math.cos(0.2f + (i + start)/5) * Math.cos(0.4f + (i + start)/2));
        }
        return array;
    }

    static void multithreading () {
        float[] myArray = new float[size];
        int threadCount, tempArraySize;
        int start = 0, end = start;
        int[] theNewArray = new int[size];
        Scanner sc = new Scanner(System.in);

        try {
            out.printf("Введите желаемое количество потоков %n");
            threadCount = sc.nextInt();
            fillArray(myArray);

            tempArraySize = (int)(size / threadCount);
//            ArrayList<float[]> tempArraySet = new ArrayList<float[]>();
            ArrayList<Thread> threadSet = new ArrayList<Thread>();
            ArrayList<float[]> resultArraySet = new ArrayList<float[]>();
            long startTime = System.currentTimeMillis() / 1000;

            for (int currentThread = 0; currentThread < threadCount && start < myArray.length; currentThread++) {
                end += tempArraySize;
                final int startInRunnable = currentThread * tempArraySize;
                if (end + tempArraySize >= size)  {
                    end = size - 1;
                    tempArraySize = end - start;
                }
                float[] tempArray = new float[tempArraySize];
                System.arraycopy(myArray, start, tempArray, 0, tempArraySize);
//                tempArraySet.add(tempArray);
                threadSet.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        resultArraySet.add(processArray(tempArray, startInRunnable));
                    }
                }));
                start = end + 1;
            }

            out.printf("Количество тредов в коллекции: %d", threadSet.size());

            for (int currentThread = 0; currentThread < threadCount; currentThread++) {
                    threadSet.get(currentThread).start();
            }

            for (int currentThread = 0; currentThread < threadCount; currentThread++) {
                System.arraycopy(resultArraySet.get(currentThread), 0, myArray, currentThread * tempArraySize
                , resultArraySet.get(currentThread).length);
            }
            out.printf("Затраченное время на выполнение: %.1f секунд %n", (float) (System.currentTimeMillis() / 1000 - startTime));

        } catch (InputMismatchException exc) {
            exc.printStackTrace();
        }

    }

    public static void main(String... args) {
        //всё в один поток
        float[] myArray = new float[size];
        fillArray(myArray);
        out.println(myArray[100000]);
        long startTime = System.currentTimeMillis() / 1000;
        processArray(myArray, 0);
        out.println(myArray[100000]);
        out.printf("Затраченное время на выполнение: %.1f секунд %n", (float) (System.currentTimeMillis() / 1000 - startTime));

        //Пробуем в многопоточность
        multithreading();
    }
}