package hw7;

public class NoSuchPlaceException extends Exception {

	public NoSuchPlaceException(String placeName) {
		super(placeName);
	}
}