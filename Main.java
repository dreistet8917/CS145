package hw5;

import java.util.Arrays;

import hw5.speccheck.Note;

public class Main {
	public static void main(String[] args) {
		int[] offSets = {2, 2, 1, 2, 2, 2, 1};
		Note[] output = MusicUtilities.getScale(new Note("4C5"), offSets);
		System.out.println(Arrays.toString(output));
		
		Note[] majorScale = MusicUtilities.getMajorScale(new Note("4C5"));
		System.out.println(Arrays.toString(majorScale));
		
		Note[] minorBluesScale = MusicUtilities.getMinorPentatonicBluesScale(new Note("4C5"));
		System.out.println(Arrays.toString(minorBluesScale));
		
		Note[] bluesScale = MusicUtilities.getBluesScale(new Note("4C5")); 
		System.out.println(Arrays.toString(bluesScale));
		
		Note[] naturalMinorScale = MusicUtilities.getMajorScale(new Note("4C5"));
		System.out.println(Arrays.toString(naturalMinorScale));
	}
}
