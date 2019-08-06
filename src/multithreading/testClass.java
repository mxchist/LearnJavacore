package multithreading;
import java.lang.Math;

public class testClass {
    static final int size = 1000;
    static final int h = size/2;
    static float[] arr = new float [size];

    static void simpleMethod() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
        }
        System.out.printf("Время работы - %f милисекунд", a - System.currentTimeMillis());
    }

    public static  void main(String ... args) {
        simpleMethod();
    }
}