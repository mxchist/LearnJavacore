package multithreading;

import java.lang.Thread;

public class ThreadClass implements Runnable {
    float[] arr;

    ThreadClass(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        System.out.println("Начинаем поток");
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
