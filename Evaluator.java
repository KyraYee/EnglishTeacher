package EnglishTeacher;

import java.io.File;
import java.util.ArrayList;

import structure5.Association;

public class Evaluator {
	Encyclopedia encyclo;

	public Evaluator(Encyclopedia encyclo) {
		this.encyclo = encyclo;
	}

	public ArrayList<ArrayList<Association<String, String>>> evaluate(ArrayList<String> input) {
		ArrayList<ArrayList<Association<String, String>>> returnMe = new ArrayList<ArrayList<Association<String, String>>>();
		ArrayList<String> sentence = input;
		int numWords = sentence.size();
		int wordIndex = 0;
		String curWord = sentence.get(0);
		String curPhrase = "";
		ArrayList<Association<String, String>> phrases = new ArrayList<Association<String, String>>();
		boolean phraseEnd = false;
		boolean phraseStart = true;
		String type = "";
		Boolean noConj = true;

		// find subject
		while (!phraseEnd) {
			curPhrase = updatePhrase(curPhrase, curWord, phraseStart);
			phraseStart = false;
			if (encyclo.isSingPerson(curWord)) {
				if (noConj) {
					type = "singPerson";
				}
				phraseEnd = true;
			} else if (encyclo.isSingObject(curWord)) {
				if (noConj) {
					type = "singObject";
				}
				phraseEnd = true;
			} else if (encyclo.isPlurObject(curWord)) {
				type = "plurObject";
				phraseEnd = true;
			} else if (encyclo.isPlurPerson(curWord)) {
				type = "plurPerson";
				phraseEnd = true;
			}
			wordIndex++;
			if (wordIndex < numWords) {
				curWord = sentence.get(wordIndex);
			}
			if (encyclo.isConjunction(curWord)) {
				phraseEnd = false;
				noConj = false;
				if (type.equals("singPerson")) {
					type = "plurPerson";
				} else if (type.equals("singObject")) {
					type = "plurObject";
				}
			}
		}
		phrases.add(new Association<String, String>(type, curPhrase));
		phraseEnd = false;
		phraseStart = true;
		curPhrase = "";

		// find verb
		while (!phraseEnd && wordIndex < numWords) {
			curPhrase = updatePhrase(curPhrase, curWord, phraseStart);
			phraseStart = false;
			if (encyclo.isPastVerb(curWord)) {
				phrases.add(new Association<String, String>("pastVerb", curPhrase));
				phraseEnd = true;
			} else if (encyclo.isPresentVerb(curWord)) {
				phrases.add(new Association<String, String>("presentVerb", curPhrase));
				phraseEnd = true;
			}
			wordIndex++;
			if (wordIndex < numWords) {
				curWord = sentence.get(wordIndex);
			}
			if (curWord != null && encyclo.isConjunction(curWord)) {

				// check if the conjoined item is a separate clause
				Boolean nounFirst = false;
				Boolean verbFirst = false;
				for (int i = wordIndex + 1; !nounFirst && !verbFirst; i++) {
					if (encyclo.isVerb(sentence.get(i))) {
						verbFirst = true;
					} else if (encyclo.isNoun(sentence.get(i))) {
						nounFirst = true;
					}
				}

				if (verbFirst) {
					phrases.remove(1);
					phraseEnd = false;
				} else if (nounFirst) {
					ArrayList<ArrayList<Association<String, String>>> additionalElements = new ArrayList<ArrayList<Association<String, String>>>();
					ArrayList<String> secondClause = new ArrayList<String>();
					for (int i = wordIndex + 1; i < numWords; i++) {
						secondClause.add(sentence.get(i));
					}
					additionalElements = evaluate(secondClause);
					returnMe.add(phrases);
					returnMe.addAll(additionalElements);
					return returnMe;
				}
			}
		}
		phraseEnd = false;
		phraseStart = true;
		curPhrase = "";

		// find optional object
		while (wordIndex < numWords && !phraseEnd) {
			curPhrase = updatePhrase(curPhrase, curWord, phraseStart);
			noConj = true;
			phraseStart = false;
			if (encyclo.isSingPerson(curWord)) {
				if (noConj) {
					type = "singPerson";
				}
				phraseEnd = true;
			} else if (encyclo.isSingObject(curWord)) {
				if (noConj) {
					type = "singObject";
				}
				phraseEnd = true;
			} else if (encyclo.isPlurObject(curWord)) {
				type = "plurObject";
				phraseEnd = true;
			} else if (encyclo.isPlurPerson(curWord)) {
				type = "plurPerson";
				phraseEnd = true;
			}
			wordIndex++;
			if (wordIndex < numWords) {
				curWord = sentence.get(wordIndex);
			}
			if (encyclo.isConjunction(curWord)) {

				// check if the conjoined item is a separate clause
				Boolean verbPresent = false;
				for (int i = wordIndex + 1; i < numWords && !verbPresent; i++) {
					if (encyclo.isVerb(sentence.get(i))) {
						verbPresent = true;
					}
				}
				if (verbPresent) {
					ArrayList<ArrayList<Association<String, String>>> moreElements = new ArrayList<ArrayList<Association<String, String>>>();
					ArrayList<String> twoClause = new ArrayList<String>();
					for (int i = wordIndex + 1; i < numWords; i++) {
						twoClause.add(sentence.get(i));
					}
					System.out.println(twoClause);
					moreElements = evaluate(twoClause);
					phrases.add(new Association<String, String>(type, curPhrase));
					returnMe.add(phrases);
					returnMe.addAll(moreElements);
					return returnMe;

				}

				else {
					phraseEnd = false;
					noConj = false;
					if (type.equals("singPerson")) {
						type = "plurPerson";
					} else if (type.equals("singObject")) {
						type = "plurObject";
					}
				}
			}
		}
		if (!curPhrase.equals("")) {
			phrases.add(new Association<String, String>(type, curPhrase));
			phraseEnd = false;
			phraseEnd = true;
		}
		phraseEnd = false;
		phraseStart = true;
		curPhrase = "";
		returnMe.add(phrases);

		return returnMe;
	}

	private String updatePhrase(String curPhrase, String curWord, Boolean start) {
		if (start) {
			curPhrase = curWord;
		} else {
			curPhrase = curPhrase + (" ") + curWord;
		}
		return curPhrase;
	}

	static public void main(String[] s) {
		ArrayList<File> theFiles = new ArrayList<File>();
		theFiles.add(new File("singPerson.txt"));
		theFiles.add(new File("plurPerson.txt"));
		theFiles.add(new File("singObject"));
		theFiles.add(new File("plurObject.txt"));
		theFiles.add(new File("presentVerb.txt"));
		theFiles.add(new File("pastVerb.txt"));
		theFiles.add(new File("conjunction.txt"));
		Encyclopedia poop = new Encyclopedia(theFiles);
		Evaluator eval = new Evaluator(poop);
		ArrayList<String> sent = new ArrayList<String>();
		sent.add("they");
		sent.add("cheer");
		ArrayList<ArrayList<Association<String, String>>> me = eval.evaluate(sent);
		for (int i = 0; i < me.size(); i++) {
			System.out.println(me.get(i));
		}
	}
}
