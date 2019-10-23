package generalization;
import java.util.ArrayList;

import static java.lang.System.out;

abstract class Fruit<W> {
    W weight;
    String name;

    Fruit() {}

    Fruit (W weight) {
        this.weight = weight;
    }

    W getWeight() {
        return this.weight;
    }
}

class Orange<W extends  Number> extends Fruit {
    Orange () {
        this.weight = 1.5f;
        this.name = "Orange";
    }
}

class Apple<W extends  Number> extends Fruit {
    Apple() {
        this.weight = 1f;
        this.name = "Apple";
    }

    Apple (W weight) {
        super();
    }
}

class Box<T extends Number> {
    ArrayList<Fruit<T>> boxStorage;

    Box(ArrayList<Fruit<T>> boxStorage) {
        this.boxStorage = boxStorage;
    }

    ArrayList<Fruit<T>> getObject() {
        return boxStorage;
    }

    Box() {
        this.boxStorage = new ArrayList<Fruit<T>>();
    }

    void putToTheBox(Fruit f) {
        boxStorage.add(f);
    }

    double sum() {
        double weight = 0;
        for (Fruit<T> f : boxStorage) {
            weight += f.getWeight().doubleValue();
        }
        return weight;
    }

    boolean compare (Box<?> anotherBox) {
        double difference = this.sum() - anotherBox.sum();
        return  difference > - 0.0001 && difference < 0.0001 ? true : false;
    }
}

public class FruitOperation {
//  перекладывание фруктов из ящика №1 в ящик №2
    static void fruitsRechange(Box b1, Box<?> b2) {
        ArrayList temporaryArray = new ArrayList<>();
//      перекладываем фрукты из ящика 1 во временный ящик
        for(int i = 0; i < b1.getObject().size() - 1; i++) {
            temporaryArray.add(b1.getObject().get(i));
        }
//        освобождаем ящик №1
        b1.getObject().clear();
        b1.getObject().trimToSize();

//      перекладываем фрукты из ящика 2 в ящик №1
        for(int i = 0; i < b2.getObject().size() - 1; i++) {
            b1.putToTheBox(b2.getObject().get(i));
        }
//        освобождаем ящик №2
        b2.getObject().clear();
        b2.getObject().trimToSize();

//      перекладываем фрукты из временного ящика в ящик №2
        for(int i = 0; i < temporaryArray.size() - 1; i++) {
            b1.putToTheBox(temporaryArray.get(i));
        }

    }

    public static void main(String ... args) {
        Orange<Integer> o1 = new Orange<Integer>();
        Apple<Double> a1 = new Apple<Double>();

//        Заполнение коробки с апельсинами
        Box b1 = new Box();
        for(int i = 0; i < 10; i++) {
            b1.putToTheBox(new Orange<Integer>());
        }
//        Заполнение коробки с яблоками
        Box b2 = new Box();
        for(int i = 0; i < 15; i++) {
            b2.putToTheBox(new Apple<Double>());
        }
        out.printf("Результат сравнения коробок: %b %n", b1.compare(b2));

//        Дисбалланс в сторону апельсинов
        b1.putToTheBox(new Orange<Integer>());
        b1.putToTheBox(new Orange<Integer>());
        out.printf("Результат сравнения коробок: %b %n", b1.compare(b2));

//        Уравновешиваем яблоками
        b2.putToTheBox(new Apple<Double>());
        b2.putToTheBox(new Apple<Double>());
        b2.putToTheBox(new Apple<Double>());
        out.printf("Результат сравнения коробок: %b %n", b1.compare(b2));

//        Дисбалланс в сторону яблок
        b2.putToTheBox(new Apple<Double>());
        b2.putToTheBox(new Apple<Double>());
        b2.putToTheBox(new Apple<Double>());
        out.printf("Результат сравнения коробок: %b %n", b1.compare(b2));

//        Уравновешиваем апельсинами
        b1.putToTheBox(new Orange<Integer>());
        b1.putToTheBox(new Orange<Integer>());
        out.printf("Результат сравнения коробок: %b %n", b1.compare(b2));
    }
}


