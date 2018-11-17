package hw6;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		boolean[][] bitmap = BitmapUtilities.create(5, 2);
		File file = new File("/Users/emadreist/Desktop/bitmap");
		BitmapUtilities.write(bitmap, file);
		
	}
}
