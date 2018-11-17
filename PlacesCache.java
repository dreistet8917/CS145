package hw7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class PlacesCache {

	private ArrayList<Place> cacheList = new ArrayList<Place>();

	public PlacesCache() {

	}

	public boolean isCached(String placeName) {
		for(Place place: cacheList) {
			if(place.getName().equals(placeName)) {
				return true;
			}
		}
		return false;
	}

	public Place getPlace(String placeName) throws URISyntaxException, NoSuchPlaceException, IOException {
		if (!isCached(placeName)) {
			Place newPlace = new Place(placeName);
			cacheList.add(newPlace);
			return newPlace;
		}

		for (Place place : cacheList) {
			if (place.getName().equals(placeName)) {
				return place;
			}
				
		}
		
		return null;
	}

	public int size() {
		return cacheList.size();
	}

	public Place get(int i) throws NoSuchPlaceException, URISyntaxException, IOException {
		return cacheList.get(i);
	}

}
