package generalization;
import java.util.ArrayList;

import static java.lang.System.inheritedChannel;
import static java.lang.System.out;

class Stats <T> {
    private T[] obj;

    Stats (T ... obj) {
        this.obj = obj;
    }

    T[] getObj () {
        return obj;
    }

    @Override
    public String toString() {
        String result = new String();
        for (T t1: obj) {
            result += t1.toString();
            result += "\t";
        }
        return result;
    }
}

class AllwaysOutnumbered {

    static void changeThePlaceOfElements (Stats someArray, int pos1, int pos2) {
        Object temporaryObj = someArray.getObj()[pos1];
        someArray.getObj()[pos1] = someArray.getObj()[pos2];
        someArray.getObj()[pos2] = temporaryObj;
    }

    ArrayList<Object> getArrayList (Stats stats) {
        ArrayList<Object> al = new ArrayList<>();
        for ()
    }

    public static void main (String ... args) {
        //Задание 1
        Stats<String> stats1 = new Stats<>("Spitfire", "Memphis bells", "Girls", "Wake Up Call", "Action Radar");
        changeThePlaceOfElements(stats1, 1, 2);
        out.print( stats1.toString());

        //Задание 2

    }
}