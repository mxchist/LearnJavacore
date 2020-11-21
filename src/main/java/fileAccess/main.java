package fileAccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Collections;
import java.io.RandomAccessFile;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;


import static java.lang.System.out;

public class main {

	public static void main (String ... args) {

		exercise1();
		exercise2();
		exercise3();
		exercise3_slow1();
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
			sis.close();
		}
		catch (IOException exc ) {
			exc.printStackTrace();
		}

		out.println();
	}

	private static void exercise3() {
		long page;
		int pageSize = 1800;
		String answer = "y";
		out.println("Enter the file path:");
		String filePath = new Scanner(System.in).next();
		//d:\Max\Documents\Driver.txt
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			while (answer.equals("y"))
			{
				out.println("Введите номер страницы для чтения");
				Scanner sc = new Scanner(System.in);
				page = sc.nextInt();

				int x;
				byte[] arr = new byte[pageSize];
				raf.seek((page -1) * pageSize);
				if ((raf.read(arr)) > 0) {
				out.print( new String(arr, 0, pageSize));

			};
			out.println("\nПрочитать ещё страницу? Введите y / n");
			answer = sc.next();
			if (!answer.equals("y")) {
				answer = "n";
			}
			}
		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}

		out.println();
	}

	private static void exercise3_slow1() {
		long page;
		int pageSize = 1800;
		String answer = "y";
		out.println("Enter the file path:");
		String filePath = new Scanner(System.in).next();
		//d:\Max\Documents\Driver.txt
		try (FileInputStream raf = new FileInputStream(filePath)) {
			while (answer.equals("y"))
			{
				out.println("Введите номер страницы для чтения");
				Scanner sc = new Scanner(System.in);
				page = sc.nextInt();

				int x, n = 1;
				while (n++ <= pageSize * (page) & (x = raf.read()) != -1) {
					if (n < pageSize * page)
						continue;

					out.print( (char)x);
				};
				out.println("\nПрочитать ещё страницу? Введите y / n");
				answer = sc.next();
				if (!answer.equals("y")) {
					answer = "n";
				}
			}
		}
		catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}

		out.println();
	}

}
