package fileAccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Collections;


import java.io.FileNotFoundException;
import java.io.IOException;


import static java.lang.System.out;

public class main {

	public static void main (String ... args) {

		exercise1();
		exercise2();
	}

	private static void exercise1() {
		// exercise 1
		// Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль
		File file = new File("file100b1");
		out.print(file.exists());
		FileInputStream fis;
		try {
			fis = new FileInputStream("file100b1");
			int x;
			while ((x = fis.read()) != -1) {
				out.print((char) x);
			}
		} catch (FileNotFoundException  exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		System.out.println();
	}

	private static void exercise2() {
		//exercise 2
		// Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
		ArrayList <InputStream> ali = new ArrayList<InputStream>();
		try {
			ali.add(new FileInputStream("file100b1"));
			ali.add(new FileInputStream("file100b2"));
			ali.add(new FileInputStream("file100b3"));
			ali.add(new FileInputStream("file100b4"));
			ali.add(new FileInputStream("file100b5"));
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}

		SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(ali));
		int x;

		try {
			while ((x = sis.read()) != -1) {
				out.print((char)x);
			}
		}
		catch (IOException exc ) {
			exc.printStackTrace();
		}

	}
}
