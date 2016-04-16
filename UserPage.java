package EnglishTeacher;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import objectdraw.*;
import structure5.Association;


public class UserPage extends WindowController implements ActionListener {

	Association<String, String> questionAnswer;
	private JLabel textLabel;
	private JTextField input;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JButton newQuestion;
	private String a;
	
	private String answer = "";
	private String question = "";
	private String newQ = "Generate a new question";
	private Simulate sim;
	
	
	public void begin (){
	//public static void main( String[] args){
		
	sim = new Simulate();
		String b = "Question: " + question;
		a = "Answer: " + answer;
		
		
		//JPanel with 3 rows and 1 column
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout (3,1));
	

		//Create label and text input
		questionLabel = new JLabel (b);
		input = new JTextField ("Enter your answer here");
		input.addActionListener(this);
		newQuestion = new JButton (newQ);
		newQuestion.addActionListener(this);
		answerLabel = new JLabel (a);
		
		//Set up label and text field
		panel.add(questionLabel);
		panel.add(answerLabel);
		panel.add(input);
		panel.add(newQuestion);
		
		//Add panel to content pane of window
		Container contentPane = getContentPane();
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.validate();
	}

	public void actionPerformed (ActionEvent event) {
        if (event.getSource() == input){
        	String userAnswer= input.getText();
        	if( userAnswer.contains(questionAnswer.getValue())){
        		answer="correct";
        		answerLabel.setText(a);
        	}
        } else if (event.getSource() == newQuestion){
        	questionAnswer = sim.generate();
        	questionLabel.setText(questionAnswer.getKey());
        }
    }

	
}
