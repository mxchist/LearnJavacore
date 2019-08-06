package multithreading;
import java.lang.Math;

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
        System.out.printf("Время работы - %f милисекунд", (float) (System.currentTimeMillis() - a));
    }

    public static  void main(String ... args) {
//        simpleMethod();

        float[] a1 = new float[size/2];
        float[] a2 = new float[size/2];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        ThreadClass tc1 = new ThreadClass(a1);
        ThreadClass tc2 = new ThreadClass(a2);
        float [] res1, res2, res;

        
    }
}
