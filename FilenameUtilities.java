package hw4;

import java.io.File;

public class FilenameUtilities {
	public static void main(String[] args) {
		
	}

	public static String getExtension(File file) {
		String strFile = file.getName();

		for (int i = 0; i <= strFile.length(); ++i) {
			if (strFile.lastIndexOf('.') == i && i != 0) {
				String extension = strFile.substring(i + 1, strFile.length());
				return extension;
			}
		}
		return null;
	}

	public static File appendToName(File parentDirectory, String text) {
		String strFile = parentDirectory.getName();
		String newFile = "";
		int indexOfPeriod = strFile.indexOf('.');

		if (strFile.contains(".") && strFile.indexOf('.') != 0) {
			newFile = strFile.substring(0, indexOfPeriod) + "_" + text
					+ strFile.substring(indexOfPeriod, strFile.length());
		} else {
			newFile = strFile.substring(0, strFile.length()) + "_" + text;
		}
		File finalFile = new File(parentDirectory.getParentFile(), newFile);
		return finalFile;
	}

}
