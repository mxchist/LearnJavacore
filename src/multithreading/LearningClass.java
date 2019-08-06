package multithreading;

class LearningClass implements Runnable {
    String thrdName;

    LearningClass(String name) {
        thrdName = name;
    }

    // Точка входа в поток.
    public void run() {
        System.out.println(thrdName + " - запуск");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(400);
                System.out.println("B " + thrdName +
                        ", счетчик: " + count);
            }
        } catch (InterruptedException exc) {
            System.out.println(thrdName + " - прерван");
        }
        System.out.println(thrdName + " - завершение");
    }
}

class UseThreads {
    public static void main(String args[]) {
        System.out.println("Зaпycк основного потока");
        // Сначала создать объект типа MyThread.
        LearningClass mt = new LearningClass("Child #1");
        // Затем сформировать поток на основе этого объекта.
        Thread newThrd = new Thread(mt);
// Наконец, начать выполнение потока
        newThrd.start();
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ехс) {
                System.out.println("Прерывание основного потока");
            }
        }
        System.out.println("Завершение основного потока");
    }
}