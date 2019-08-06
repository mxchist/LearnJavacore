package multithreading;

import java.lang.Thread;

public class ThreadClass implements Runnable {
    float[] arr;
    int partialSize = arr.length;
    Thread thrd;

    ThreadClass(float[] arr) {
        this.arr = arr;
        thrd.start();
    }

    @Override
    public void run() {
        System.out.println("Начинаем поток");
        for (int i = 0; i < partialSize; i++) {
            arr[i] = 1;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
        }
    }

    public float[] getArr() {
        return this.arr;
    }

//    public float[] operateArray() {
//        Thread tr1 = new Thread(new Runnable() {
//            public void run() {
//                System.out.println();
//            }
//        })
//    }
}
