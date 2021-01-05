package exercise4;

public class PrintChar {

	char state;
	PrintChar() {
		this.state = 'A';
	}

	synchronized void printB() {
		for (int i = 0; i < 10000; i++) {
			while (state != 'B') {
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
			System.out.println('B');
			this.state = 'C';
			notifyAll();
		}
	}

	synchronized void printA() {
		for (int i = 0; i < 10000; i++) {
			while (state != 'A') {
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
			System.out.println('A');
			this.state = 'B';
			notifyAll();
		}
	}

	synchronized void printC() {
		for (int i = 0; i < 10000; i++) {
			while (state != 'C') {
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
			System.out.println('C');
			this.state = 'A';
			notifyAll();
		}
	}

	void v() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				printA();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				printB();
			}
		});

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				printC();
			}
		});

		t1.start();
		t2.start();
		t3.start();
	}

}
