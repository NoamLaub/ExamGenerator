package module;

import java.io.Serializable;

public abstract class Questions implements Serializable,Cloneable {

	/**
	 * 
	 */
	protected final String  AMERICAN = "Closed";
	protected final String  OPEN = "Open";
	
	private static final long serialVersionUID = 1L;
	
	
	protected int serialNum;
	protected String text;
	private String type;
	protected static int numOfQuestions =0;

	

	public static int getNumOfQuestions() {
		return numOfQuestions;
	}

	public Questions(String text) {
		this.text = text;
		numOfQuestions++;
		//OperatingSystem.addQuestionToPool(this);
		
	}

	public Questions(AmericanQuestions question, int numOfQuestion) {
		this.serialNum = question.getSerialNum();
		this.text = question.getText();
		this.type = AMERICAN;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSerialNum() {
		return serialNum;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract int getLengthOfAnswer();

	public abstract void setAnswer(int index1, String newText1);

	public abstract String toStringForTest();

	public abstract String toStringForTestFile();

	

	@Override
	public abstract boolean equals(Object question);

	@Override
	public abstract String toString();

}
