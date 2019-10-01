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

    public static void main(String... args) {

        float[] myArray = new float[size];
        fillArray(myArray);
        out.println(myArray[100000]);
        long startTime = System.currentTimeMillis() / 1000;
        processArray(myArray, 0);
        out.println(myArray[100000]);
        out.printf("Затраченное время на выполнение: %.1f секунд %n", (float) (System.currentTimeMillis() / 1000 - startTime));

        //Пробуем в многопоточность
        int threadCount, tempArraySize;
        int start = 0, end = start;
        int[] theNewArray = new int[size];
        Scanner sc = new Scanner(System.in);

        try {
            out.printf("Введите желаемое количество потоков %n");
            threadCount = sc.nextInt();
            fillArray(myArray);
            tempArraySize = (int)(size / threadCount);
            ArrayList<float[]> tempArraySet = new ArrayList<float[]>();
            for (int currentThread = 0; currentThread < threadCount; currentThread++) {
                end += tempArraySize;
                if (end + tempArraySize >= size)  {
                    end = size - 1;
                    tempArraySize = end - start;
                }
                float[] tempArray = new float[tempArraySize];
                System.arraycopy(myArray, start, tempArray, 0, tempArraySize);
                tempArraySet.add(tempArray);
            }

            out.println(tempArraySet.size());

            for (int currentThread = 0; currentThread < threadCount; currentThread++) {
                float[] tempArray = tempArraySet.get(threadCount);
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            
                        }
                    }).start();
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }

        } catch (InputMismatchException exc) {
                    exc.printStackTrace();
        }
    }
}
