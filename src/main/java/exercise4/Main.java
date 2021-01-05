package exercise4;

public class Main {
	public static void main (String ... args) {
		//Создать три потока, каждый из которых выводит определенную букву
		// (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
		PrintChar pc = new PrintChar();
//		Первый способ реализации
//		pc.v();

//		Второй способ реализации
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				pc.printA();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				pc.printB();
			}
		});

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				pc.printC();
			}
		});

		t1.start();
		t2.start();
		t3.start();

	}
}
