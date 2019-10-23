package generalization;
import java.util.ArrayList;

import static java.lang.System.out;

abstract class Fruit<W> {
    W weight;
    String type;

    Fruit() {}

    String getType () {return this.type;}

    W getWeight() {
        return this.weight;
    }
}

class Orange<W extends  Number> extends Fruit {
    Orange () {
        this.weight = 1.5f;
        this.type = "Orange";
    }

    String getType() {
        return super.getType();
    }
}

class Apple<W extends  Number> extends Fruit {
    Apple() {
        this.weight = 1f;
        this.type = "Apple";
    }

    String getType() {
        return super.getType();
    }
}

class Box<T extends Number> {
    ArrayList<Fruit<T>> boxStorage;

    Box(ArrayList<Fruit<T>> boxStorage) {
        this.boxStorage = boxStorage;
    }

    ArrayList<Fruit<T>> getBox() {
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
//  перекладывание фруктов из коробки №1 в коробку №2
    static void fruitsRechange(Box<?> b1, Box<?> b2) {
        ArrayList<Fruit> temporaryArray = new ArrayList<>();
//      перекладываем фрукты из ящика 1 во временный ящик
        for(int i = 0; i < b1.getBox().size() - 1; i++) {
            temporaryArray.add(b1.getBox().get(i));
        }
//        освобождаем коробку №1
        b1.getBox().clear();
        b1.getBox().trimToSize();

//      перекладываем фрукты из коробки 2 в коробку №1
        for(int i = 0; i < b2.getBox().size() - 1; i++) {
            b1.putToTheBox(b2.getBox().get(i));
        }
//        освобождаем ящик №2
        b2.getBox().clear();
        b2.getBox().trimToSize();

//      перекладываем фрукты из временного коробки в коробку №2
        for(int i = 0; i < temporaryArray.size() - 1; i++) {
            b2.putToTheBox(temporaryArray.get(i));
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

//        Задание № 4
//        out.print(b1.getBox().get(0).getType());  почему-то не доступен метод
        String type;
        type = b1.getBox().get(0).getClass().toString();
        out.printf("Фрукт в коробке 1: %s, ", type);
        type = b2.getBox().get(0).getClass().toString();
        out.printf("Фрукт в коробке 2: %s %n", type);
        fruitsRechange(b1, b2);

        type = b1.getBox().get(0).getClass().toString();
        out.printf("Фрукт в коробке 1: %s, ", type);
        type = b2.getBox().get(0).getClass().toString();
        out.printf("Фрукт в коробке 2: %s %n", type);
    }
}


