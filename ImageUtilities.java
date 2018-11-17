package hw4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImageUtilities {

	public static BufferedImage swapCorners(BufferedImage image) {
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		int halfWidth = image.getWidth() / 2;
		int halfHeight = image.getHeight() / 2;
		for (int r = 0; r < image.getHeight(); r++) {
			for (int c = 0; c < image.getWidth(); c++) {
				int newC = (c + halfWidth) % (newImg.getWidth());
				int newR = (r + (halfHeight)) % (newImg.getHeight());
				newImg.setRGB(c, r, image.getRGB(newC, newR));

			}
		}
		return newImg;

	}

	public static BufferedImage getCircleMask(int width, int height, double power) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		for (int r = 0; r < height; ++r) {
			for (int c = 0; c < width; ++c) {
				Double midC = width / (double) 2;
				Double midR = height / (double) 2;
				Double cSub = c - midC;
				Double rSub = r - midR;
				Double cSquared = Math.pow(cSub, 2);
				Double rSquared = Math.pow(rSub, 2);

				Double dFromCenter = Math.sqrt(cSquared + rSquared);
				Double radius = Math.min(midC, midR);
				Double dNormalized = dFromCenter / radius;
				
				if (dFromCenter <= radius) {
				Double tweakedDistance = Math.pow(dNormalized, power);
				float gray = (float) (1 - tweakedDistance);
				Color color = new Color(gray, gray, gray);
				int intGray = color.getRGB();
				img.setRGB(c, r, intGray);
				}
			}
		}
		swapCorners(img);
		return img;
	}

	public static int mix(int a, int b, double weight) {
		double weightedNumber = weight * a + (1 - weight) * b;
		int intWeight = (int) weightedNumber;
		return intWeight;
	}

	public static Color mix(Color a, Color b, double weight) {
		int aBlueBefore = a.getBlue();
		int bBlueBefore = b.getBlue();
		int mixOfBlue = mix(aBlueBefore, bBlueBefore, weight);

		int aGreenBefore = a.getGreen();
		int bGreenBefore = b.getGreen();
		int mixOfGreen = mix(aGreenBefore, bGreenBefore, weight);

		int aRedBefore = a.getRed();
		int bRedBefore = b.getRed();
		int mixOfRed = mix(aRedBefore, bRedBefore, weight);

		Color mixedColor = new Color(mixOfRed, mixOfGreen, mixOfBlue);
		return mixedColor;
	}

	public static BufferedImage addMasked(BufferedImage a, BufferedImage maskA, BufferedImage b, BufferedImage maskB){

		BufferedImage image = new BufferedImage(a.getWidth(), a.getHeight(), a.getType());

		for (int c = 0; c < image.getHeight(); ++c) {
			for (int r = 0; r < image.getWidth(); ++r) {

				Color A = new Color(maskA.getRGB(r, c));
				Color B = new Color(maskB.getRGB(r, c));

				int weightA = A.getRed();
				int weightB = B.getRed();

				int total = weightA + weightB;

				double weight;
				if (total == 0) {
					weight = .5;
				} else {
					weight = (double) weightA / total;
				}

				Color aOne = new Color(a.getRGB(r, c));
				Color bOne = new Color(b.getRGB(r, c));

				Color blended = mix(aOne, bOne, weight);
				image.setRGB(r, c, blended.getRGB());
			}
		}
		return image;
	}

	public static BufferedImage makeSeamless(BufferedImage imageA, double power) {
		BufferedImage swappedB = swapCorners(imageA);
		BufferedImage maskA = getCircleMask(imageA.getWidth(), imageA.getHeight(), power);
		BufferedImage maskB = swapCorners(maskA);
		BufferedImage masked = addMasked(imageA, maskA, swappedB, maskB);
		return masked;
	}

	public static BufferedImage tile(BufferedImage image, int horizontal, int vertical) throws IOException {
		BufferedImage tiles = new BufferedImage(image.getWidth() * horizontal, image.getHeight() * vertical, image.getType());
		for (int c = 0; c < tiles.getWidth(); ++c) {
			for (int r = 0; r < tiles.getHeight(); ++r) {
				tiles.setRGB(c, r, image.getRGB(c % image.getWidth(), r % image.getHeight()));
				
			}
		}
		return tiles;
		
	}
}
