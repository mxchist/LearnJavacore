package exercise4;

import java.util.Stack;

public class MFU {
	Stack<Document> printCount = new Stack<Document>();
	Stack<Document> scanCount = new Stack<Document>();

	synchronized void print() {
		while (true) {
			if (printCount.empty()) {
//				System.out.print("printCount: 0");
				notify();
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
				continue;
			}
			Document document = printCount.pop();
			for (int i = 1; i <= document.pages; i++) {
				System.out.println("printing " + document.name + ", " + i + "th page");
				if (i == document.pages)
					System.out.println();
				notify();
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		}
	};

	synchronized void scan() {
		while (true) {
			if (scanCount.empty()) {
//				System.out.print("scanCount: 0");
				notify();
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
				continue;
			}
			Document document = scanCount.pop();
			for (int i = 1; i <= document.pages; i++) {
				System.out.println("scanning " + document.name + ", " + i + "th page");
				if (i == document.pages)
					System.out.println();
				notify();
				try {
					wait();
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
			}
		}
	};



}
