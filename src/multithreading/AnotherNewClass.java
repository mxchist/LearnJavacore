package multithreading;

public class AnotherNewClass {
    public static void main (String ... args) {
        System.out.printf("%12.3f", 1e1);

        int arr1[] = new int[10];
        int arr2[] = {1,2,3,4,5,6,7,8,9,10};
        System.arraycopy(arr2,0,arr1,0,8);
        arr1.toString();
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + arr1[i]);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + arr2[i]);
        }


        for (int i = 1; i< 25; i++) {
//            System.out.printf("25 / %d \t\t %d \n", i, 25/i);
        }
    }
}
