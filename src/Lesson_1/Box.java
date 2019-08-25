package Lesson_1;

import java.util.Arrays;

public class Box {
    String name;
    String color;
    int age;

    public Box(String name) {
        this.name = name;
    }

    public Box(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Box(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void info() {
        System.out.println(name + " " + color + " " + age);
    }

    public int summ(int... a) {
        int res = 0;

        for (int i = 0; i < a.length; i++) {
            res += a[i];
        }

        return res;
    }

}

class Tools {

    protected String[] mass = {"Bob1", "Bob2", "Bob3"};

    public String[] getMass() {
        String[] currentMass = new String[mass.length];

        for (int i = 0; i < mass.length; i++) {
            currentMass[i] = mass[i];
        }

        return currentMass;
    }

}

class MainBox {
    public static void main(String[] args) {

        Tools tools = new Tools();

        String[] mass1 = tools.getMass();

        mass1[1] = "Bob555";
        System.out.println(Arrays.toString(tools.getMass()));

//        Box box1 = new Box("box1", "red", 5);
//        Tools tools = new Tools("hummer");
//
//        box1.startThis(tools);


//        Box box1 = new Box("box1", "red", 5);
//        Box box2 = new Box("box2", "black");
//
////        box1.info();
////        box2.info();
//
//        System.out.println(box1.summ(1,2,5,5,10));
//        MainBox mainBox = new MainBox();
//        mainBox.abc();


//        Box[] boxes = {new Box("box1"), new Box("box2")};
//
//        boxes[0].name = "box1";
//        boxes[1].name = "box2";
//
//        boxes[0].info();
//        boxes[1].info();

    }

//    void abc() {
//        System.out.println(1);
//    }
}

interface Run {
    void run();

    default void print() {
        System.out.println("test");
    }
}


abstract class Animal implements Run {
    //    protected String name;
//    protected int z;
//
//    public Animal(String name) {
//        this.name = name;
//    }
//
//    public void info() {
//        System.out.println("Animal");
//    }
    abstract void test1();

    public void info() {
        System.out.println("Animal");
    }

}



//class Cat extends Animal {
//
//    protected int y;
//
//    public Cat(String name, int y) {
//        super(name);
//        this.y = y;
//    }
//
//    public void info() {
//        System.out.println("Cat");
//    }
//}
//
//class SuperCat extends Cat {
//
//    protected int i;
//
//    public SuperCat(String name, int y, int i) {
//        super(name, y);
//        this.i = i;
//    }
//
//    void test() {
//        super.z = 10;
//    }
//
//    public void info() {
//        System.out.println("SuperCat");
//    }
//}

class MainZoo implements Run {
    public static void main(String[] args) {
//
//        Object o;
//        Animal animal = new Animal("asd");
//       // Cat cat = new Animal("asd");
//
//        o = animal;

//        Animal[] animals = {new Animal("asd"), new Cat("Murzik", 1), new SuperCat("Barsik",2,2)};
//
//        for (Animal animal: animals) {
//            animal.info();
//        }

    }

    @Override
    public void run() {

    }
}























