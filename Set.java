package hw7;

import java.util.ArrayList;

public class Set {
	private ArrayList<String> arraylist = new ArrayList<String>();

	public Set() {

	}

	public boolean has(String element) {
		return arraylist.contains(element);
	}

	public void add(String newElement) {
		if (!has(newElement)) {
			arraylist.add(newElement);
		}
	}

	public String toString() {
		String strSet = new String();

		for (int i = 0; i < arraylist.size() - 1; i++) {
			strSet += arraylist.get(i) + String.format("%n");
		}

		strSet += arraylist.get(arraylist.size() - 1);
		return strSet;
	}

}
