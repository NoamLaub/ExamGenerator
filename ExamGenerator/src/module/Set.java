package module;

import java.io.Serializable;
import java.util.ArrayList;

public class Set <T extends Answers> implements Serializable,Cloneable{
	
	private int numOfAnswers;
	private ArrayList<Answers> answers;
	
	public Set() {
		this.numOfAnswers = 0;
		this.answers = new ArrayList<Answers>();
	}

	public int getNumOfAnswers() {
		return numOfAnswers;
	}

	public ArrayList<Answers> getAnswers() {
		return answers;
	}
	
	public Answers get(int num) {
		return answers.get(num);
	}
	
	public boolean addAnswer(int place, Answers newanswer) {
		for (int i = 0 ; i < answers.size() ; i++) {
			if(newanswer != null) {
			if(answers.get(i).getBodyOfAnswer() == newanswer.getBodyOfAnswer()) {
				return false;
			}}
		}
		answers.add(place, newanswer);
		numOfAnswers++;
		return true;
	}
	
	public void set(int place, Answers newAnswer) {
		answers.set(place, newAnswer);
	}
	

}
