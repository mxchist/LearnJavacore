package exercise4;

public class Main {
	public static void main (String ... args) {
		//Создать три потока, каждый из которых выводит определенную букву
		// (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
		PrintChar pc = new PrintChar();
		pc.v();


	}
}
