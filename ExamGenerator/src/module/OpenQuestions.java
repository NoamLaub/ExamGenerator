package module;

import java.io.Serializable;

public class OpenQuestions extends Questions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Answers answer;
	private static int counter = 000001;

	public OpenQuestions(String text, Answers answer) {
		super(text);
		this.answer = answer;
		this.serialNum = counter;
		counter +=2;
		this.setType(OPEN);
	}
	
	

	public static void setCounter(int counter) {
		OpenQuestions.counter = counter;
	}



	public static int getCounter() {
		return counter;
	}



	public Answers getAnswer() {
		return answer;
	}

	public void setAnswer(Answers answer) {
		this.answer = answer;
	}

	@Override
	public void setAnswer(int index, String newText) {
		answer.setBodyOfAnswer(newText);
	}

	public int getLengthOfAnswer() {
		return answer.toString().length();
	}

	@Override
	public boolean equals(Object question) {
		if (question instanceof OpenQuestions && ((Questions) question).text.equalsIgnoreCase(this.text)) {
			return true;
		} else {
			return false;
		}
	}

	public String toStringForTest() {
		StringBuffer sb = new StringBuffer(text + "\nThe correct answer is: " + answer.toString() + "\n");
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(text + "(ID: "+serialNum+")\nThe correct answer is: " + answer.toString() + "\n");
		return sb.toString();
	}

	
	public String toStringForTestFile() {
		StringBuffer sb = new StringBuffer(text + "\n");
		return sb.toString();
	}

	

}
