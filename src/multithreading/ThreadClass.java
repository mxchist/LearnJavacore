package multithreading;

import java.lang.Thread;

public class ThreadClass implements Runnable {
    private float[] arr;
    Thread thrd;
    private int lbegin;

    ThreadClass(float[] arr, int lbegin) {
        this.arr = arr;
        this.lbegin = lbegin;
        thrd = new Thread(this);
        thrd.start();
    }

    @Override
    public void run() {
//        System.out.println("Начинаем поток");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1f;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (lbegin + i)/5f) * Math.cos(0.2f + (lbegin + i)/5f) * Math.cos(0.4f + (lbegin + i)/2f));
        }
    }

    public void setLBegin(int lbegin) {
        this.lbegin = lbegin;
    }

    public float[] getArr() {
        return this.arr;
    }

}
