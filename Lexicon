package EnglishTeacher;

import java.util.ArrayList;
import java.util.Random;


//NOTE: must add a space at the end of a word when entering
// a new word to signify the end of a word!!!!
/**
 * the Lexicon is a collection of words.
 * All words in a Lexicon should be of the same syntactic type
 * When entering words into the Lexicon, must add a space after the
 * word to signify the end of the word
 * @author Kyra Yee
 *
 */
public class Lexicon {

	private String word;
	private char currentChar;
	private Lexicon parent;
	private ArrayList<Lexicon> children;
	

	/**
	 * creates a root Lexicon
	 * (its parent is null)
	 * @param s
	 */
	public Lexicon(char s) {
		this(s, null);
	}

	/**
	 * creates a Lexicon 
	 * @param c
	 * 		the character this node represents
	 * @param parent
	 */
	public Lexicon(char c, Lexicon parent) {
		children = new ArrayList<Lexicon>();
		currentChar = c;
		this.parent = parent;
		// System.out.println("parent="+parent);
		// this.word = word;

		// parent=this?
		// Lexicon childi = new Lexicon(word, parent);

		// children.add(childi);

	}
	
	/**
	 * gives the character of the given lexicon node
	 * @return
	 */
	private char giveC() {
		return currentChar;
	}
	
	/**
	 * @return
	 * tests if the children of a lexicon node 
	 * contains a certain character
	 * @param c
	 * 			the character in question
	 * 
	 */
	private boolean childrenContains(char c) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).giveC() == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * adds a character to the children of  a given lexicon
	 * the character is in index i of word
	 * @param word
	 * @param i
	 * @param lex
	 */
	private void append(String word, int i, Lexicon lex) {

		if (!lex.childrenContains(word.charAt(i))) {
			lex.getChildren().add(new Lexicon(word.charAt(i), lex));

		}
	}
	
	/**
	 * tests if a character is in the children of 
	 * a given lexicon
	 * the character is in index i of word
	 * @param word
	 * @param i
	 * @param lex
	 * @return
	 */
	private boolean inCur(String word, int i, Lexicon lex){
		if (lex.childrenContains(word.charAt(i))){
			return true;
		}
		return false;
	}

	/**
	 * @return returns the child containing char c
	 * @param c
	 * 
	 */
	private Lexicon getChildWithC(char c) {
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i).giveC() == c) {
				return children.get(i);
			}

		}
		return null;
	}

	/**
	 * adds a word to the lexicon
	 * @param word
	 */
	public void addWord(String word) {
		// Lexicon par=parent;
		Lexicon cur = this;
		for (int i = 0; i < word.length(); i++) {
			if (childrenContains(word.charAt(i))) {
				// int nextChildIndex= children.indexOf(word.charAt(i));
				// append(word,i,this.getChild(nextChildIndex));
			}
			append(word, i, cur);

			// int nextIndex= cur.getChildren().indexOf(word.charAt(i));
			Lexicon nextLex = cur.getChildWithC(word.charAt(i));
			cur = nextLex;
		}
	}

	
	/**
	 * 
	 * @param word
	 * @return
	 * tests if a word is in the lexicon
	 */
	public boolean wordInLexicon(String word){
		Lexicon cur=this;
		for(int i=0;i<word.length();i++){
			if (!inCur(word,i,cur)){
				return false;
			}
			cur=cur.getChildWithC(word.charAt(i));
		}
		
		
		return true;
	}
	
	
	int totalSize = 1;
	/**
	 * 
	 * @return 
	 * gives the tree size
	 * NOTE this method can only be used once
	 */
	public int treeSize() {

		for (int i = 0; i < children.size(); i++) {
			totalSize = totalSize + children.get(i).treeSize();

		}
		return totalSize;

	}

	/**
	 * @return gives teh child at index i
	 * @param i
	 * 
	 */
	public Lexicon getChild(int i) {
		// Random gen = new Random();

		return children.get(i);

	}
	
	/**
	 * @return
	 * returns the array of children
	 */
	public ArrayList<Lexicon> getChildren() {
		return children;
	}

	/**
	 * @return
	 * gives the parent
	 */
	public Lexicon getParent() {
		return parent;
	}

	/**
	 * @param badNode
	 * this is untested, but it supposedly removes a node
	 */
	public void removeNode(Lexicon badNode) {
		children.remove(badNode);
	}
	
	/**
	 * also untested, but supposedly removes parent
	 */
	public void removeParent() {
		// System.out.println("parent="+parent);
		if (getParent() != null) {
			Lexicon grandParent = getParent().getParent();
			if (grandParent != null) {
				grandParent.removeNode(parent);

			}
		}
		// System.out.println("test");
		// children.remove(parent);
		// parent=null;
		// System.out.println(children.size());
	}

	/**
	 * tests the funcitonality of lexicon
	 * @param args
	 */
	public static void main(String[] args) {
		Lexicon test = new Lexicon(' ');

		// System.out.println(test.treeSize());
		test.addWord("ate ");
		System.out.println(test.getChildren().size());

		test.addWord("ape ");
		// Sytem.out.println(test.getChildren().size());
		// System.out.println(test.treeSize());

		System.out.println(test.getChildWithC('a').getChildren().size());

		test.addWord("apple ");
		// System.out.println(test.treeSize());
		System.out.println(test.getChildWithC('a').getChildWithC('p').getChildren().size());

		test.addWord("bee ");
		System.out.println(test.getChildren().size());
		System.out.println(test.treeSize());
		System.out.println(test.wordInLexicon("bee "));
		System.out.println(test.wordInLexicon("ape "));
		System.out.println(test.wordInLexicon("apple "));
		System.out.println(test.wordInLexicon("ate "));
		System.out.println(test.wordInLexicon("hair "));
		System.out.println(test.wordInLexicon("beed "));
		System.out.println(test.wordInLexicon("ark "));
		System.out.println(test.wordInLexicon("a "));
		System.out.println(test.wordInLexicon("app "));

		/*
		 * if (test.getChild(0).getParent() == test) {
		 * 
		 * System.out.println("true"); } else { System.out.println("false");
		 * Lexicon test1 = test.getChild(0).getChild(0);
		 * System.out.println(test1); System.out.println(test1.getParent());
		 * 
		 * 
		 * 
		 * }
		 */
	}

}
