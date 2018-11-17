package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class DondeUtilities {

	public static PlacesCache readCSV(File file) throws URISyntaxException, IOException, FileNotFoundException {
		PlacesCache placeList = new PlacesCache();
		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
			String newLine = in.nextLine();
			String[] places = newLine.split("\\|", -1);
			String name = places[0];
			String placeName = places[1];
			Place newPlace;
			try {
				newPlace = placeList.getPlace(placeName);
				newPlace.addPerson(name);
			} catch (NoSuchPlaceException e) {
				e.printStackTrace();
			}
		}
		return placeList;
	}

	public static void writeKML(PlacesCache cache, File file)
			throws NoSuchPlaceException, URISyntaxException, IOException, FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		writer.println("<Document>");
		writer.println("<name>Donde</name>");
		for (int i = 0; i < cache.size(); i++) {
			Place newPlace = cache.get(i);
			writer.println(newPlace.toKML());
		}
		writer.println("</Document>");
		writer.println("</kml>");
		writer.close();
	}
}
