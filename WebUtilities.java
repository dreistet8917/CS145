package hw7;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WebUtilities {

	public static String slurpURL(URL website) throws IOException {
		URLConnection conn = website.openConnection();
		InputStream is = conn.getInputStream();
		Scanner in = new Scanner(is);
		String newString = "";
		
		while (in.hasNextLine()) {
			newString += in.nextLine();
		}
		is.close();
		in.close();
		return newString;
	}

}
