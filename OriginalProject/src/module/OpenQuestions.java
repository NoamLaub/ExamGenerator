package module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class OpenQuestions extends Questions implements Serializable,Memento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Answers answer;

	public OpenQuestions(String text, Answers answer) {
		super(text);
		this.answer = answer;
		this.setType(OPEN);
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
		StringBuffer sb = new StringBuffer(text + "\nThe correct answer is: " + answer.toString() + "\n");
		return sb.toString();
	}

	
	public String toStringForTestFile() {
		StringBuffer sb = new StringBuffer(text + "\n");
		return sb.toString();
	}

	public OpenMemento createOpenMemento() throws CloneNotSupportedException   {
		return new OpenMemento(text,answer);
	}

	public void setOpenMemento(OpenMemento m) throws CloneNotSupportedException   {
		this.text=m.text;
		this.answer=m.answer.clone();
	}

	public static class OpenMemento implements Memento {
		private String text;
		private Answers answer;

		private OpenMemento(String text,Answers answer) throws CloneNotSupportedException   {
			this.text=text;
			if(answer != null)
				this.answer=answer.clone();
		}
	}

}
