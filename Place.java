package hw7;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Place {
	private String placeName = "";
	private double nLong = 0.0;
	private double nLat = 0.0;
	private Set nameList = new Set();

	public Place(String placeName1, double latitude, double longitude) {
		placeName = placeName1;
		nLong = longitude;
		nLat = latitude;
	}

	public String getName() {
		return placeName;
	}

	public double getLongitude() {
		return nLong;
	}

	public double getLatitude() {
		return nLat;

	}

	public URL toGeocodeURL() throws MalformedURLException, URISyntaxException {
		URI uri = new URI("http", "www.twodee.org", "/teaching/cs145/2016c/homework/hw7/geocode.php",
				"place=" + placeName, null);
		URL geocodeURL = uri.toURL();
		return geocodeURL;
	}

	public Place(String placeName2) throws NoSuchPlaceException {
		try {
			placeName = placeName2;
			URL newUrl = toGeocodeURL();
			Scanner in = new Scanner(WebUtilities.slurpURL(newUrl));
			double longitude = 0.0;
			double latitude = 0.0;

			latitude = Double.parseDouble(in.next());
			longitude = Double.parseDouble(in.next());
			placeName = placeName2;
			nLat = latitude;
			nLong = longitude;
			in.close();

		} catch (Exception e) {
			throw new NoSuchPlaceException(placeName2);
		}

	}

	public void addPerson(String personName1) {
		nameList.add(personName1);
	}

	public String toKML() {
		String stringName = nameList.toString();
		return String.format(
				"<Placemark>%n<name>%s</name>%n<description>%s</description>%n<Point><coordinates>%.2f,%.2f</coordinates></Point>%n</Placemark>",
				placeName, stringName, nLong, nLat);
	}
}
