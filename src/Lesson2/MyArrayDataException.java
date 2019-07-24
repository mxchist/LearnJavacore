package Lesson2;

public class MyArrayDataException extends RuntimeException {
    private int y, x;

    public MyArrayDataException (String msg, int y, int x) {
        super(msg);
        this.y = y;
        this.x = x;
    }
}
