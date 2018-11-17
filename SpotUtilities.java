package hw6;

import java.io.File;
import java.io.FileNotFoundException;

import hw6.speccheck.GifSequenceWriter;

public class SpotUtilities {

	public static int[] countNeighbors(boolean[][] bitmap, int horizOuter, int vertOuter, int horizInner, int vertInner,
			int column, int row) {
		int countInner = 0;
		int countOuter = 0;
		int[] alive = new int[2];
		for (int nr = row - vertOuter; nr <= row + vertOuter; ++nr) {
			for (int nc = column - horizOuter; nc <= column + horizOuter; ++nc) {
				double rowDistance = row - nr;
				double columnDistance = column - nc;
				double innerDistance = (Math.pow(rowDistance, 2)) / (Math.pow(vertInner, 2))
						+ (Math.pow(columnDistance, 2)) / (Math.pow(horizInner, 2));
				double outerDistance = (Math.pow(rowDistance, 2)) / (Math.pow(vertOuter, 2))
						+ (Math.pow(columnDistance, 2)) / (Math.pow(horizOuter, 2));
				if (innerDistance <= 1 && outerDistance <= 1) {
					if (BitmapUtilities.isOn(bitmap, nc, nr)) {
						countInner++;
					}
				}
				if (outerDistance <= 1) {
					if (BitmapUtilities.isOn(bitmap, nc, nr)) {
						countOuter++;
					}
				}
			}
		}
		countOuter = countOuter - countInner;
		alive[0] = countInner;
		alive[1] = countOuter;
		return alive;
	}

	public static boolean[][] step(boolean[][] bitmap, int horizontalOuter, int verticalOuter, int horizontalInner,
			int verticalInner, double proportion) {
		boolean[][] destination = new boolean[bitmap.length][bitmap[0].length];
		for (int r = 0; r < bitmap.length; ++r) {
			for (int c = 0; c < bitmap[0].length; ++c) {
				int[] neighbors = countNeighbors(bitmap, horizontalOuter, verticalOuter, horizontalInner, verticalInner,
						c, r);
				int nactivators = neighbors[0];
				int ninhibitors = neighbors[1];
				double difference = nactivators - (proportion * ninhibitors);
				if (Math.abs(difference) < 0.001) {
					destination[r][c] = bitmap[r][c];
				} else if (difference > 0) {
					destination[r][c] = true;
				} else
					destination[r][c] = false;

			}
		}

		return destination;
	}

	public static int converge(boolean[][] bitmap, int horizOuter, int vertOuter, int horizInner, int vertInner, double proportional, File gif, int maxSteps) throws FileNotFoundException {

		GifSequenceWriter out = new GifSequenceWriter(gif, 200, true);
		boolean[][] pre = bitmap;
		boolean[][] post = step(bitmap, horizOuter, vertOuter, horizInner, vertInner, proportional);
		out.appendFrame(BitmapUtilities.toBufferedImage(pre));
		int numberOfSteps;

		for (numberOfSteps = 0; numberOfSteps < maxSteps; numberOfSteps++) {

			if (BitmapUtilities.equals(pre, post)) {

				out.appendFrame(BitmapUtilities.toBufferedImage(post));
				out.close();
				return numberOfSteps + 2;
			}
			out.appendFrame(BitmapUtilities.toBufferedImage(post));
			pre = BitmapUtilities.clone(post);
			post = step(pre, horizOuter, vertOuter, horizInner,vertInner, proportional);
		}
		out.appendFrame(BitmapUtilities.toBufferedImage(post));
		out.close();
		return numberOfSteps + 1;
	}
}
