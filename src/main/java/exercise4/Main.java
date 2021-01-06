package exercise4;

public class Main {
	public static void main (String ... args) {
//		exercise1();
		exercise2();
	}

	static void exercise1() {
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

	static void exercise2() {
		// Создать модель MFU с возможностью печати и сканирования
		// (данные процессы могут происходить параллельно)

		MFU mfu = new MFU();

		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				mfu.print();
			}
		});

		Thread t5 = new Thread(new Runnable() {
			@Override
			public void run() {
				mfu.scan();
			}
		});

		t4.start();
		t5.start();

//		try {
//			t4.join();
//			System.out.println("t4 joined");
//			t5.join();
//			System.out.println("t5 joined");
//		} catch (InterruptedException exc) {
//			exc.printStackTrace();
//		}

		mfu.scanCount.add(new Document(4));
		mfu.scanCount.add(new Document(1));
		mfu.scanCount.add(new Document(5));
		mfu.scanCount.add(new Document(2));
		mfu.scanCount.add(new Document(7));

		mfu.printCount.add(new Document(12));
		mfu.printCount.add(new Document(4));
		mfu.printCount.add(new Document(5));
		mfu.printCount.add(new Document(7));
		mfu.printCount.add(new Document(4));

	}
}
