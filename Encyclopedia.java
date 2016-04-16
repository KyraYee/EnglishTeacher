package EnglishTeacher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Encyclopedia {
	private Lexicon singPersonLexicon;
	private Lexicon plurPersonLexicon;
	private Lexicon singObjectLexicon;
	private Lexicon plurObjectLexicon;
	private Lexicon presentVerbLexicon;
	private Lexicon pastVerbLexicon;
	private Lexicon conjunctionLexicon;

	/**
	 * @pre The files stored in theLexis must correspond to the order in which
	 *      the Lexicons are listed above. Also, each file must contain one word
	 *      per line, with no spaces before or after it.
	 * @param theLexis
	 */
	public Encyclopedia(ArrayList<File> theLexis) {
		
		singPersonLexicon = new Lexicon(' ');
		fillLexicon(singPersonLexicon, theLexis.get(0));
		
		plurPersonLexicon = new Lexicon(' ');
		fillLexicon(plurPersonLexicon, theLexis.get(1));
		
		singObjectLexicon = new Lexicon(' ');
		fillLexicon(singObjectLexicon, theLexis.get(2));
		
		plurObjectLexicon = new Lexicon(' ');
		fillLexicon(plurObjectLexicon, theLexis.get(3));
		
		presentVerbLexicon = new Lexicon(' ');
		fillLexicon(presentVerbLexicon, theLexis.get(4));
		
		pastVerbLexicon = new Lexicon(' ');
		fillLexicon(pastVerbLexicon, theLexis.get(5));
		
		conjunctionLexicon = new Lexicon(' ');
		fillLexicon(conjunctionLexicon, theLexis.get(6));
	}
	
	private void fillLexicon(Lexicon aLex, File aFil){
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(aFil));
			String curLine = buffer.readLine();
			while(curLine!=null){
				aLex.addWord(curLine);
				curLine = buffer.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean isNoun(String s) {
		return (singPersonLexicon.wordInLexicon(s) || plurPersonLexicon.wordInLexicon(s)
				|| singObjectLexicon.wordInLexicon(s) || plurObjectLexicon.wordInLexicon(s));
	}

	public Boolean isVerb(String s) {
		return (presentVerbLexicon.wordInLexicon(s) || pastVerbLexicon.wordInLexicon(s));
	}

	public Boolean isSingPerson(String s) {
		return (singPersonLexicon.wordInLexicon(s));
	}

	public Boolean isPlurPerson(String s) {
		return (plurPersonLexicon.wordInLexicon(s));
	}

	public Boolean isSingObject(String s) {
		return (singObjectLexicon.wordInLexicon(s));
	}

	public Boolean isPlurObject(String s) {
		return (plurObjectLexicon.wordInLexicon(s));
	}

	public Boolean isConjunction(String s) {
		return (conjunctionLexicon.wordInLexicon(s));
	}

	public Boolean isPastVerb(String s) {
		return (pastVerbLexicon.wordInLexicon(s));
	}

	public Boolean isPresentVerb(String s) {
		return (presentVerbLexicon.wordInLexicon(s));
	}
}
