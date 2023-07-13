package module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Test implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Questions> allQuestions;

	private int numOfQuestions = 0;
	private int testSize;
	private int serialNum;
	protected static int counter = 1;

	public Test(int testSize) {
		this.testSize = testSize;
		this.allQuestions = new ArrayList<Questions>(testSize);
		this.serialNum = counter++;
		//OperatingSystem.addTestToPool(this);
	}

	public Test clone() throws CloneNotSupportedException {
		Test o = null;
		o = (Test) super.clone();
		o.serialNum = counter++;
		//OperatingSystem.addTestToPool(o);
		return o;
	}

	public Test(Test test) {
		this.testSize = test.testSize;
		this.allQuestions = new ArrayList<Questions>(testSize);
		this.serialNum = counter++;
		//OperatingSystem.addTestToPool(this);
	}

	public int getSerialNum() {
		return serialNum;
	}

	public int getTestSize() {
		return testSize;
	}

	public void addQuestionToTest(Questions question) {
		allQuestions.add(numOfQuestions++, question);

	}

	public ArrayList<Questions> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(ArrayList<Questions> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public static int getCounter() {
		return counter;
	}

	public boolean questionExistInTest(Questions question) {
		for (int i = 0; i < numOfQuestions; i++) {
			if (allQuestions.get(i).equals(question)) {
				return true;
			}
		}
		return false;
	}

	public void testSortViaLengthOfAnswers() {
		for (int IndMin = 0; IndMin < allQuestions.size(); IndMin++) {
			for (int i = (IndMin + 1); i < allQuestions.size(); i++) {
				if (allQuestions.get(IndMin).getLengthOfAnswer() > allQuestions.get(i).getLengthOfAnswer()) {
					swap(allQuestions, IndMin, i);

				}
			}
		}

	}

	public static void swap(ArrayList<Questions> allQuestions, int i, int j) {
		Questions tmp = allQuestions.get(i);
		allQuestions.set(i, allQuestions.get(j));
		allQuestions.set(j, tmp);
	}


	public String toStringForFile() {
		StringBuffer sb = new StringBuffer("There are " + numOfQuestions + " questions in the test\n\n");
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append((i + 1) + ". " + allQuestions.get(i).toStringForTestFile() + "\n");
		}
		return sb.toString();

	}

	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("There are " + numOfQuestions + " questions in the test\n\n");
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append((i + 1) + ". " + allQuestions.get(i).toStringForTest() + "\n");
		}
		return sb.toString();

	}

	@Override
	public int hashCode() {
		return Objects.hash(allQuestions.get(0).getText().length(), numOfQuestions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return (numOfQuestions == other.numOfQuestions && 
				allQuestions.get(0).getText().length() == other.allQuestions.get(0).getText().length());
	}
   
}
