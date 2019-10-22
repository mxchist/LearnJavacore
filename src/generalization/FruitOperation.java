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

    double getOVerallWieght() {
        return this.sum();
    }

    boolean compare (Box<?> anotherBox) {
        double difference = this.sum() - anotherBox.sum();
        return  difference > - 0.0001 && difference < 0.0001 ? true : false;
    }
}

public class FruitOperation {
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
