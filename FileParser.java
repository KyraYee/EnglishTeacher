package EnglishTeacher;

import java.io.File;
import java.util.ArrayList;

public class FileParser {

public FileParser(){
}

public ArrayList<ArrayList<String>>parseFile(File theFile){
	ArrayList<ArrayList<String>> returnMe = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<Character>> sentences = (new SentenceParser(theFile)).parser(theFile);
	for(int i=0; i<sentences.size();i++){
		returnMe.add((new WordParser(sentences.get(i))).sentenceParser(sentences.get(i)));
	}
	return returnMe;
}
/*
static public void main(String[] s){
	FileParser theParse = new FileParser();
	ArrayList<ArrayList<String>> yo = theParse.parseFile(new File("test.txt"));
	for(int i=0; i< yo.size(); i++){
		System.out.println(yo.get(i));
	}*/
//}
}
