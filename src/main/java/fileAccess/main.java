package fileAccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;

public class main {

	public static void main (String ... args) {

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

	}
}
