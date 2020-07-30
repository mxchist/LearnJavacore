package fileAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

	public static void main (String[] args) {
		// Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
		File file50b = new File("file50b");
		byte[] buf = new byte[40];
		try (FileInputStream fis = new FileInputStream(file50b)) {
			int count;
			while ((count = fis.read(buf)) > 0) {
				for (int i = 0; i < count; i++) {
					System.out.print((char) buf[i]);
				}
			}
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}
