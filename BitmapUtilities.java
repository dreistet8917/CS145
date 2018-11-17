package hw6;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class BitmapUtilities {

	public static boolean[][] create(int width, int height) {
		boolean[][] bitmap = new boolean[height][width];
		return bitmap;
	}

	public static void randomize(boolean[][] bitmap, long seed) {
		Random randomSeed = new Random(seed);
		for (int c = 0; c < bitmap.length; c++) {
			for (int r = 0; r < bitmap[0].length; r++) {
				bitmap[c][r] = randomSeed.nextBoolean();
			}
		}
	}

	public static void write(boolean[][] bitmap, File file) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.write("P1\n" + bitmap[0].length + " " + bitmap.length + "\n");

		for (int c = 0; c < bitmap.length; c++) {
			for (int r = 0; r < bitmap[0].length; r++) {
				if (bitmap[c][r] == true) {
					writer.write("0\n");
				} else if (bitmap[c][r] == false) {
					writer.write("1\n");
				}
			}
		}
		writer.close();
	}

	public static BufferedImage toBufferedImage(boolean[][] bitmap) throws FileNotFoundException { 

		Color white = new Color(255, 255, 255); 
		int nWhite = white.getRGB();

		Color black = new Color(0, 0, 0);
		int nBlack = black.getRGB();

		BufferedImage image = new BufferedImage(bitmap[0].length, bitmap.length, BufferedImage.TYPE_BYTE_BINARY);

		for (int c = 0; c < image.getWidth(); c++) {
			for (int r = 0; r < image.getHeight(); r++) {

				if (bitmap[r][c]) {
					image.setRGB(c, r, nBlack);
				} else {
					image.setRGB(c, r, nWhite);
				}

			}
		}

		return image;
	}

	public static boolean equals(boolean[][] bitmap1, boolean[][] bitmap2) {
		if (bitmap1.length != bitmap2.length) {
			return false;
		} else if (bitmap1[0].length != bitmap2[0].length) {
			return false;
		}

		for (int i = 0; i < bitmap1.length; i++) {
			for (int n = 0; n < bitmap1[0].length; n++) {
				if (bitmap1[i][n] != bitmap2[i][n]) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean[][] clone(boolean[][] bitmap) {
		boolean[][] deep = new boolean[bitmap.length][bitmap[0].length];
		for (int i = 0; i < bitmap.length; i++) {
			for (int n = 0; n < bitmap[0].length; n++) {
				deep[i][n] = bitmap[i][n];
			}
		}
		return deep;
	}

	public static int wrapIndex(int upperBound, int index) {
		int wrappedIndex = 0;
		if (index > 0) {
			wrappedIndex = index % upperBound;
		} else if (index < 0) {
			wrappedIndex = index % upperBound;
			wrappedIndex = wrappedIndex + upperBound;
			wrappedIndex = wrappedIndex % upperBound;
		}

		return wrappedIndex;
	}

	public static boolean isOn(boolean[][] bitmap, int column, int row) {
		column = wrapIndex(bitmap[0].length, column);
		row = wrapIndex(bitmap.length, row);
		
		return bitmap[row][column];
		
		}
}
