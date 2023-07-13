package module;

import java.io.Serializable;
import java.util.ArrayList;

public class AmericanQuestions extends Questions implements Serializable,Cloneable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int counter= 000002;
	private Set<Answers> answers;
	//ArrayList<Answers> answers;
	//private Answers[] answers;
	private int numberOfAnswers;
   
	public AmericanQuestions(String text) {
		super(text);
		this.setType(AMERICAN);
		this.answers = new Set<Answers>();
		for (int i = 0 ; i<=12 ; i++){
			answers.addAnswer(i,null);
		}
		this.answers.set(10, new Answers("None of the answers are correct", false));
		this.answers.set(11, new Answers("More than one answer is correct", false));
		numberOfAnswers = 0;
		this.serialNum = counter;
		counter +=2;
	}

	public AmericanQuestions(AmericanQuestions question, int arraySize) {
		super(question, arraySize);
		this.answers = new Set<Answers>();
		for (int i = 0 ; i<arraySize ; i++){
			answers.addAnswer(i,null);
		}
		numberOfAnswers = 0;
		this.answers.set(arraySize - 2, question.answers.get(10));
		this.answers.set(arraySize - 1, question.answers.get(11));
	}
	

	public Set getAnswers() {
		return answers;
	}
	public int getLengthOfAnswer() {
		int lengthOfAnswersCombined=0;
		for (int i=0; i<answers.getNumOfAnswers(); i++) {
			lengthOfAnswersCombined +=answers.get(i).toString().length();
		}
		return lengthOfAnswersCombined;
	}

	public boolean addAnswer(String text, boolean correct) {
		if (numberOfAnswers > this.answers.getNumOfAnswers() - 3) {
			return false;
		}
		answers.set(numberOfAnswers++, new Answers(text, correct));
		return true;
	}

	@Override
	public void setAnswer(int index, String newText) {
		answers.get(index).setBodyOfAnswer(newText);
	}

	public void verifyAnswers() {
		int numOfCorrectAnswers = 0;
		for (int i = 0; i < numberOfAnswers ; i++) {
			if (AmericanQuestions.this.answers.get(i).isCorrect()) {
				numOfCorrectAnswers++;
			}
		}
		if (numOfCorrectAnswers == 0) {
			AmericanQuestions.this.answers.get(AmericanQuestions.this.answers.getNumOfAnswers() - 2).setCorrect(true);
		}
		if (numOfCorrectAnswers > 1) {
			AmericanQuestions.this.answers.get(AmericanQuestions.this.answers.getNumOfAnswers() - 1).setCorrect(true);
			for (int j = 0; j < AmericanQuestions.this.answers.getNumOfAnswers() - 2; j++) {
				AmericanQuestions.this.answers.get(j).setCorrect(false);
			}
		}

		numberOfAnswers += 2;
	}

	public int getNumberOfAnswers() {
		return numberOfAnswers;
	}

	public boolean checkIfAnswerExistByAnswer(Answers answer) {

		for (int i = 0; i < numberOfAnswers; i++) {
			if (this.answers.get(i).equals(answer)) {
				return true;
			}

		}
		return false;
	}

	public boolean checkIfAnswerTextExist(String answerText) {
		boolean answerExist = false;
		for (int i = 0; i < numberOfAnswers; i++) {
			if (this.answers.get(i).getBodyOfAnswer().equalsIgnoreCase(answerText)) {
				answerExist = true;
				return answerExist;
			}
		}
		return answerExist;
	}

	public boolean checkIfAnswerExist(int answerIndex) {
		if (answerIndex > this.answers.getNumOfAnswers() - 3 || answerIndex < 0 || this.answers.get(answerIndex) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean deleteAnswer(int chosenAnswer) {
		if (chosenAnswer > this.getNumberOfAnswers() || chosenAnswer < 1 || this.numberOfAnswers == 4) {
			return false;
		} else {
			this.answers.set(chosenAnswer - 1, this.answers.get(--numberOfAnswers));
			return true;
		}
	}

	public String toStringForTest() {
		Answers correctAnswer = null;
		int answerIndex = 0;
		StringBuffer sb = new StringBuffer(text + "\n");
		for (int i = 0; i < numberOfAnswers; i++) {
			if (answers.get(i).isCorrect()) {
				correctAnswer = answers.get(i);
				answerIndex = (i + 1);
			}
			sb.append("(" + (i + 1) + ") " + answers.get(i).toStringForTest() + "\n");

		}
		sb.append("The correct answer is:" + "(" + answerIndex + ") " + correctAnswer.toStringForTest() + "\n");
		return sb.toString();

	}
	public  String toStringForTestFile() {
		StringBuffer sb = new StringBuffer(text + "\n");
		for (int i = 0; i < numberOfAnswers; i++) {
			sb.append("(" + (i + 1) + ") " + answers.get(i).toStringForTest() + "\n");

		}
		return sb.toString();

	}
	
	

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		AmericanQuestions.counter = counter;
	}

	@Override
	public boolean equals(Object question) {
		if (question instanceof AmericanQuestions && ((Questions) question).text.equalsIgnoreCase(this.text)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(text + "(ID: "+serialNum+ ")\n");
		for (int i = 0; i < numberOfAnswers; i++) {
			sb.append("(" + (i + 1) + ") " + answers.get(i).toString() + "\n");
		}
		return sb.toString();

	}

}
