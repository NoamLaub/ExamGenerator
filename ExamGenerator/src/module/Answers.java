package module;

import java.io.Serializable;

public class Answers implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int counter=1;
	private int serialNum;
	private boolean correct;
	private String bodyOfAnswer;

	public Answers(String bodyOfAnswer, boolean correct) {
		super();
		this.correct = correct;
		this.bodyOfAnswer = bodyOfAnswer;
		this.serialNum = counter++;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Answers.counter = counter;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public boolean isCorrect() {
		return correct;
	}
	public int getBooleanInInt (){
		if(correct == true) 
			return 1;
		return 0;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getBodyOfAnswer() {
		return bodyOfAnswer;
	}

	public void setBodyOfAnswer(String bodyOfAnswer) {
		this.bodyOfAnswer = bodyOfAnswer;
	}

	public String toStringForTest() {
		return bodyOfAnswer;
	}

	@Override
	public boolean equals(Object answer) {
		if (((Answers) answer).bodyOfAnswer.equalsIgnoreCase(this.bodyOfAnswer)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		if (correct) {
			return bodyOfAnswer +"(ID: "+serialNum+ ") \u2713";
		} else {
			return bodyOfAnswer + "(ID: "+serialNum+ ") x";
		}

	}

}
