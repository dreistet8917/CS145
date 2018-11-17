package hw5;
import hw5.speccheck.Accidental;
import hw5.speccheck.Duration;
import hw5.speccheck.Letter;
import hw5.speccheck.Note;
import hw5.speccheck.WavIO;

public class happyBirthday {
  public static void main(String[] args) {
    Note aNatural = new Note(Letter.A, Accidental.NATURAL, 4, Duration.QUARTER, 1, false); 
    Note aNatural1 = new Note(Letter.A, Accidental.NATURAL, 5, Duration.QUARTER, 1, false);
    Note bNatural = new Note(Letter.B, Accidental.NATURAL, 4, Duration.QUARTER, 1, false);
    Note cSharp = new Note(Letter.C, Accidental.SHARP, 5, Duration.QUARTER, 1 , false);
    Note dNatural = new Note(Letter.D, Accidental.NATURAL, 5 , Duration.QUARTER, 1, false);
    Note eNatural = new Note(Letter.E, Accidental.NATURAL, 5, Duration.QUARTER, 1, false);
    Note gNatural = new Note(Letter.G, Accidental.NATURAL, 5, Duration.QUARTER, 1, false); 
    Note fSharp = new Note(Letter.F, Accidental.SHARP, 5, Duration.QUARTER, 1, false); 
 
    Note[] notes = new Note[25];
    notes[0] = aNatural;
    notes[1] = aNatural;
    notes[2] = bNatural;
    notes[3] = aNatural;
    notes[4] = dNatural;
    notes[5] = cSharp;
    notes[6] = aNatural;
    notes[7] = aNatural;
    notes[8] = bNatural;
    notes[9] = aNatural;
    notes[10] = eNatural;
    notes[11] = dNatural;
    notes[12] = aNatural;
    notes[13] = aNatural;
    notes[14] = aNatural1;
    notes[15] = fSharp;
    notes[16] = dNatural;
    notes[17] = cSharp;
    notes[18] = bNatural;
    notes[19] = gNatural;
    notes[20] = gNatural;
    notes[21] = fSharp;
    notes[22] = dNatural;
    notes[23] = eNatural;
    notes[24] = dNatural;
    
    Note[] happyBirthday = {aNatural, aNatural, bNatural, aNatural, dNatural, cSharp, aNatural, aNatural, bNatural, aNatural, eNatural, dNatural, aNatural, aNatural, aNatural1, fSharp, dNatural, cSharp, bNatural, gNatural, gNatural, fSharp, dNatural, eNatural, dNatural};
    WavIO.write(happyBirthday, 100, new String(System.getProperty("user.home") + "/Desktop/happyBirthday.wav"));

  }
}