import java.io.*;
import java.util.*;

public class WordParser {
	// passed in from sentenceParser.
	ArrayList<Character> sentenceList;
	ArrayList<ArrayList<ArrayList<Character>>> finalList;
	ArrayList<ArrayList<Character>> wordList = new ArrayList<ArrayList<Character>>();

	public WordParser(ArrayList<Character> list) {
		this.sentenceList = list;
	}

	String getString(ArrayList<Character> list) {
		StringBuilder builder = new StringBuilder(list.size());
		for (Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}

	public ArrayList<String> sentenceParser(ArrayList<Character> sentence) {
		ArrayList<String> returnMe = new ArrayList<String>();
		int last = 0;
		char currChar;
		String currentWord = "";
		for (int i = 0; i < sentence.size(); i++) {
			currChar = sentence.get(i);
			if (currChar == ' ' || i + 1 == sentence.size()) {
				ArrayList<Character> collectList = new ArrayList<Character>();
				for (int k = last; k <= i; k++) {
					collectList.add(sentence.get(k));
				}
				currentWord = getString(collectList);
				returnMe.add(currentWord);
				currentWord = "";
				last = i + 1;
			}
		}
		
		for(int r = 0; r < returnMe.size(); r++) {
			  returnMe.set(r, returnMe.get(r).toLowerCase());
			}
		
		return returnMe;
	}

	
	public static void main(String[] args) {

		/*File tester = new File("test.txt");
		SentenceParser testParser = new SentenceParser(tester);
		ArrayList<ArrayList<Character>> output = testParser.parser(tester);
		WordParser wordTest = new WordParser(output);

		System.out.println(wordTest.sentenceParser(output.get(0)));*/
	}
}
