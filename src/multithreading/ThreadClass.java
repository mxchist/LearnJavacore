package multithreading;

import java.lang.Thread;

public class ThreadClass implements Runnable {
    private float[] arr;
    private int partialSize;
    private Thread thrd;
    private int lbegin;

    ThreadClass(float[] arr) {
        this.arr = arr;
        this.partialSize = arr.length;
        thrd = new Thread(this);
        thrd.start();
    }

    @Override
    public void run() {
        System.out.println("Начинаем поток");
        for (int i = 0; i < partialSize; i++) {
            arr[i] = 1;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + lbegin + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + lbegin + i/2));
        }
    }

    public void setLBegin(int lbegin) {
        this.lbegin = lbegin;
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
