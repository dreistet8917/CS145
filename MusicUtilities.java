package hw5;

import hw5.speccheck.*;

public class MusicUtilities {
	public static Note[] getScale(Note root, int[] offSets) {
		Note[] notes = new Note[offSets.length + 1];
		notes[0] = root;
		for (int i = 1; i < notes.length; ++i) {
			Note previous = notes[i - 1];
			notes[i] = new Note(previous.getHalfstepID() + offSets[i - 1], previous.getDuration(), previous.getAmplitude(), previous.isDotted());
		}
		return notes;
	}

	public static Note[] getMajorScale(Note root) {
		int[] offsets = { 2, 2, 1, 2, 2, 2, 1 };
		Note[] notes = new Note[offsets.length + 1];
		notes[0] = root;
		for (int i = 1; i < notes.length; ++i) {
			Note previous = notes[i - 1];
			notes[i] = new Note(previous.getHalfstepID() + offsets[i - 1], previous.getDuration(), previous.getAmplitude(), previous.isDotted());
		}
		return notes;
	}

	public static Note[] getMinorPentatonicBluesScale(Note root) {
		int[] offsets = { 3, 2, 2, 3, 2 };
		Note[] notes = new Note[offsets.length + 1];
		notes[0] = root;
		for (int i = 1; i < notes.length; ++i) {
			Note previous = notes[i - 1];
			notes[i] = new Note(previous.getHalfstepID() + offsets[i - 1], previous.getDuration(), previous.getAmplitude(), previous.isDotted());
		}
		return notes;
	}

	public static Note[] getBluesScale(Note root) {
		int[] offsets = { 3, 2, 1, 1, 3, 2 };
		Note[] notes = new Note[offsets.length + 1];
		notes[0] = root;
		for (int i = 1; i < notes.length; ++i) {
			Note previous = notes[i - 1];
			notes[i] = new Note(previous.getHalfstepID() + offsets[i - 1], previous.getDuration(), previous.getAmplitude(), previous.isDotted());
		}
		return notes;
	}
	
	public static Note[] getNaturalMinorScale(Note root) {
		int[] offsets = {2, 1, 2, 2, 1, 2, 2};
		Note[] notes = new Note[offsets.length + 1];
		notes[0] = root;
		for (int i = 1; i < notes.length; ++i) {
			Note previous = notes[i - 1];
			notes[i] = new Note(previous.getHalfstepID() + offsets[i - 1], previous.getDuration(), previous.getAmplitude(), previous.isDotted());
		}
		return notes;
	}
	
	public static Note[] join(Note[] firstScale, Note[] secondScale) {
		Note[] joined = new Note[firstScale.length + secondScale.length];
		for (int i = 0; i < firstScale.length; i++) {
			joined[i] = firstScale[i];
		}
		for (int i = 0; i < secondScale.length; i++) {
			joined[firstScale.length + i] = secondScale[i];
		}
		return joined;
	}
	
	public static Note[] repeat(Note[] scale, int timesRepeated) {
		Note[] repeatedArray = new Note[0];
		for (int i = 0; i < timesRepeated; ++i) {
			repeatedArray = join(scale, repeatedArray);
		}
		return repeatedArray;
	}
	
	public static Note[] ramplify(Note[] notes, double startingAmp, double endingAmp) {
		Note[] rampedNotes = new Note[notes.length];
		rampedNotes[0] = new Note(notes[0].getLetter(), notes[0].getAccidental(), notes[0].getOctave(), notes[0].getDuration(), startingAmp, notes[0].isDotted());
		double ampStep = (endingAmp - startingAmp) / (notes.length - 1);
		for (int i = 1; i < rampedNotes.length - 1; i++) {
			rampedNotes[i] = new Note(notes[i].getLetter(), notes[i].getAccidental(), notes[i].getOctave(), notes[i].getDuration(), startingAmp + i * ampStep, notes[i].isDotted());
		}
		rampedNotes[notes.length - 1] = new Note(notes[notes.length - 1].getLetter(), notes[notes.length - 1].getAccidental(), notes[notes.length - 1].getOctave(), notes[notes.length - 1].getDuration(), endingAmp, notes[notes.length - 1].isDotted());
		return rampedNotes;
	}
	
	public static Note[] reverse(Note[] sortedNotes) {
		Note[] reverseSortedNotes = new Note[sortedNotes.length];
		int n = 0;
		for (int i = sortedNotes.length - 1; i >= 0; --i) {
				reverseSortedNotes[n] = sortedNotes[i];
				n++;
			}
		return reverseSortedNotes;
	}
	
	public static Note[] transpose(Note[] notes, Note root) {
		Note[] transposed = new Note[notes.length];
		int shift = notes[0].getHalfstepID() - root.getHalfstepID();
		transposed[0] = root;
		for (int i = 0; i < notes.length; ++i) {
			transposed[i] = new Note(notes[i].getHalfstepID() - shift, notes[i].getDuration(), notes[i].getAmplitude(), notes[i].isDotted());
		}
		return transposed;
	}
	
	public static Note[] invert(Note[] notes, Note pivotNote) {
		Note[] inverted = new Note[notes.length];
		for (int i = 0; i < inverted.length; ++i) {
			int shift = (notes[i].getHalfstepID() - pivotNote.getHalfstepID()) * 2;
			inverted[i] = new Note(notes[i].getHalfstepID() - shift, notes[i].getDuration(), notes[i].getAmplitude(), notes[i].isDotted());
		}
		return inverted; 
	}
}
