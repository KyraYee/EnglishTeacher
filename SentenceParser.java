import java.io.*;
import java.util.*;

//Takes in a file, returns array list of strings
public class SentenceParser {
	ArrayList <ArrayList<Character>> list;
	File file;
	
	public SentenceParser (File file){
		list = new ArrayList <ArrayList<Character>> ();
		this.file = file;
	}
	
	public ArrayList<ArrayList<Character>> parser (File file){
		 String s;
		 BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(file));
			s = buffer.readLine();
			while (s != null) {
				int last = 0;
				for (int i = 0; i < s.length(); i++){
					char currChar = s.charAt(i);
					if (currChar == '.' || currChar == '!'){
						ArrayList<Character> subList = new ArrayList <Character>();
						for (int k = last; k < i; k++){
							subList.add(s.charAt(k)); 
						}
						list.add(subList);
						last = i+2;
					} else if (currChar == '?'){
						last = i+2;
					}
				}
				System.out.println(list);
				System.out.println(s);
				s = buffer.readLine();
			} 
		}catch (IOException e) {
				e.printStackTrace();
			}
			return list;
	}
	
	public static void main (String[] args){
		File tester = new File ("test.txt");
		SentenceParser testParser = new SentenceParser (tester);
		testParser.parser(tester);
	}
	
}
	

