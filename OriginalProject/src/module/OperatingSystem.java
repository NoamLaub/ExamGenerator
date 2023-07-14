package module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import module.AmericanQuestions.AmericanMemento;
import module.OpenQuestions.OpenMemento;

public class OperatingSystem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int numOfQuestions = 0;
	private int numOfTests = 0;
	private ArrayList<Questions> questions = new ArrayList<Questions>();
	private ArrayList<Test> tests = new ArrayList<Test>();
	private TreeSet<Test> treeTests;
	private LinkedHashSet<Test> hash;
	private MyArrayList arr;
	private ArrayList<Memento> memArr = new ArrayList<Memento>();

	public int getNumOfTests() {
		return numOfTests;

	}

	public void subtractFromNumOfTests() {
		numOfTests--;
	}

	public ArrayList<Questions> getArrayOfquestions() {
		return questions;
	}

	public Answers constructAnswer(String bodyOfAnswer, boolean correctness) {
		Answers answer = new Answers(bodyOfAnswer, correctness);
		return answer;
	}

	public OpenQuestions constructOpenQuestion(String text, Answers answer) {
		OpenQuestions question = new OpenQuestions(text, answer);
		addQuestionToPool(question);
		return question;
	}

	public AmericanQuestions contructAmericanQuestion(String text) {
		AmericanQuestions question = new AmericanQuestions(text);
		addQuestionToPool(question);
		return question;
	}

	public AmericanQuestions contructAmericanQuestion(AmericanQuestions question, int numOfAnswers) {
		AmericanQuestions questionForExam = new AmericanQuestions(question, numOfAnswers);
		return questionForExam;
	}

	public void addQuestionToPool(Questions question) {
		questions.add(numOfQuestions++, question);
	}

	public void savePoolOfQuestionsAndTestsToFile() throws IOException {
		ObjectOutputStream poolOfQuestionsAndTests = new ObjectOutputStream(new FileOutputStream("Pool_Of_questions"));
		poolOfQuestionsAndTests.writeInt(numOfQuestions);
		poolOfQuestionsAndTests.writeInt(Questions.getCounter());
		poolOfQuestionsAndTests.writeObject(questions);
		poolOfQuestionsAndTests.writeInt(numOfTests);
		poolOfQuestionsAndTests.writeInt(Test.getCounter());
		poolOfQuestionsAndTests.writeObject(tests);
		poolOfQuestionsAndTests.close();
	}

	@SuppressWarnings("unchecked")
	public void loadPoolOfQuestionsAndTestsFromFile()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream poolOfQuestionsAndTests = new ObjectInputStream(new FileInputStream("Pool_Of_questions"));
		numOfQuestions = poolOfQuestionsAndTests.readInt();
		Questions.counter = poolOfQuestionsAndTests.readInt();
		questions = (ArrayList<Questions>) poolOfQuestionsAndTests.readObject();
		numOfTests = poolOfQuestionsAndTests.readInt();
		Test.counter = poolOfQuestionsAndTests.readInt();
		tests = (ArrayList<Test>) poolOfQuestionsAndTests.readObject();
		poolOfQuestionsAndTests.close();
	}

	public boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct) {
		return question.addAnswer(text, correct);
	}

	public Class<AmericanQuestions> getAmericanQuestionClass() {
		return AmericanQuestions.class;
	}

	public boolean checkIfChosenQuestionExistByIndex(int questionIndex) {
		if (questionIndex < 0 || questionIndex > questions.size() - 1 || questions.get(questionIndex) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkIfQuestionExistViaQuestion(Questions question) {
		for (int i = 0; i < numOfQuestions; i++) {
			if (questions.get(i).equals(question) && questions.get(i).getSerialNum() != question.getSerialNum()) {
				numOfQuestions--;
				return true;
			}
		}
		return false;
	}

	public boolean checkIfQuestionExistViaText(Questions question) {
		boolean questionExist = checkIfQuestionExistViaQuestion(question);
		if (questionExist) {
			numOfQuestions++;
		}
		return questionExist;
	}

	public boolean checkIfAnswerTextExist(AmericanQuestions question, String answerText) {
		return question.checkIfAnswerTextExist(answerText);
	}

	public boolean checkIfAnswerExistByIndex(AmericanQuestions question, int answerIndex) {
		return question.checkIfAnswerExist(answerIndex);
	}

	public boolean checkIfAnswerExistByAnswer(AmericanQuestions american, Answers answer) {
		return american.checkIfAnswerExistByAnswer(answer);
	}

	public Questions getQuestion(int index) {
		return questions.get(index);
	}

	public Answers getAnswer(AmericanQuestions american, int index) {
		return american.getAnswers().get(index);
	}

	public int getNumberOfAnswers(AmericanQuestions question) {
		return question.getNumberOfAnswers();
	}

	public int getNumOfQuestionsInArray() {
		return numOfQuestions;
	}

	public void changeBodyOfQuestion(int indexOfQuestion, String newText) {
		questions.get(indexOfQuestion).setText(newText);
	}

	public void changeAnswer(Questions chosenQuestion, int index, String text) {
		chosenQuestion.setAnswer(index, text);
	}

	public void deleteQuestion(int questionIndex) {
		questions.set(questionIndex, questions.get(--numOfQuestions));
	}

	public boolean deleteAnswer(AmericanQuestions question, int chosenAnswer) {
		return question.deleteAnswer(chosenAnswer);
	}

	public String showAmericanQuestionAnswers(Questions chosenQuestion) {
		return chosenQuestion.toString();
	}

	public Test createTest(int numOfQuestions) {
		Test newTest = new Test(numOfQuestions);
		addTestToPool(newTest);
		return newTest;
	}

	public Test createTestViaCopy(Test test) throws CloneNotSupportedException {
		Test newTest = test.clone();
		addTestToPool(newTest);
		return newTest;
	}

	public ArrayList<Test> getTests() {
		return tests;
	}

	public void addTestToPool(Test test) {
		tests.add(numOfTests++, test);

	}

	public void addQuestionToTest(Questions question, Test test) {
		test.addQuestionToTest(question);
	}

	public boolean questionExistInTest(Test test, Questions question) {
		return (test.questionExistInTest(question));
	}

	public void verifyAnswers(AmericanQuestions question) {
		question.verifyAnswers();
	}

	public void testSortViaLengthOfAnswers(Test test) {
		test.testSortViaLengthOfAnswers();
	}

	public String printTest(Test test) {
		return test.toString();
	}

	public void saveTestToFile(Test test) throws FileNotFoundException {
		LocalDate testDate = LocalDate.now();
		String testFileName = "exam_" + testDate;
		String answersFileName = "solution_" + testDate;
		PrintWriter testFile = new PrintWriter(testFileName);
		PrintWriter solutionOfTestFile = new PrintWriter(answersFileName);
		testFile.println(
				"Number of questions in test: " + test.getNumOfQuestions() + " || Date test created:" + testDate);
		solutionOfTestFile.println(test.toString());
		testFile.println(test.toStringForFile());
		testFile.println("Good Luck!");
		solutionOfTestFile.println("I really hope you're reading this after doing the test...");
		testFile.close();
		solutionOfTestFile.close();

	}

	public void savePoolOfQuestionsToFile() throws IOException {
		ObjectOutputStream poolOfQuestions = new ObjectOutputStream(new FileOutputStream("Pool_Of_questions"));
		poolOfQuestions.writeInt(numOfQuestions);
		poolOfQuestions.writeInt(Questions.getCounter());
		poolOfQuestions.writeObject(questions);
		poolOfQuestions.close();
	}

	public String showAllTests() {
		StringBuffer sb = new StringBuffer("There are " + numOfTests + " tests \n\n");
		for (int i = 0; i < numOfTests; i++) {
			sb.append("Test number " + tests.get(i).getSerialNum() + "\n");
		}
		return sb.toString();
	}

	public String toString(ArrayList<Questions> questions) {
		StringBuffer sb = new StringBuffer("There are " + numOfQuestions + " questions \n\n");
		for (int i = 0; i < numOfQuestions; i++) {
			sb.append((i + 1) + ". " + questions.get(i).toString() + "\n");
		}
		return sb.toString();

	}

	public void transferTestsToTreeSet() {
		int testIndex = 1;
		treeTests = new TreeSet<Test>(new Comparator<Test>() {

			@Override
			public int compare(Test t1, Test t2) {
				int compare = t2.getNumOfQuestions() - t1.getNumOfQuestions();
				if (compare == 0)
					return 1;
				return compare;

			}

		});
		treeTests.addAll(tests);
		Iterator<Test> it = treeTests.iterator();
		System.out.println("Tree set of tests sorted in descending order by the number of questions in them:\n\n");
		while (it.hasNext()) {
			System.out.printf("----------------Test %d---------------:\n\n", testIndex++);
			System.out.println(it.next());
		}
	}

	public void transferTestsToLinkedHashSet() {
		int testIndex = 1;
		hash = new LinkedHashSet<Test>(treeTests);
		Iterator<Test> iter = hash.iterator();
		System.out.println(
				"Linked hash set of tests (values defining equality are: numOfQuestions and the first question's text length):\n\n");

		while (iter.hasNext()) {
			System.out.printf("----------------Test %d---------------:\n\n", testIndex++);
			System.out.println(iter.next());
		}
	}

	public void transferTestsFromHashLinkedSetToMyArrayList() {
		arr = new MyArrayList();
		ArrayList<Test> list = new ArrayList<Test>();
		Iterator<Test> iter = hash.iterator();

		while (iter.hasNext()) {
			Test temp = iter.next();
			arr.add(temp);
			list.add(temp);
		}
		Iterator<Test> it = arr.iterator();
		Iterator<Test> ite = list.iterator();

		System.out.println("\n\n-----------------------Second Assignment---------------------------------------\n\n");

		System.out.println("These are the tests from MyArray\n");
		System.out.println("-----------------------------------------------\n");
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("These are the tests from the regular ArrayList \n");
		System.out.println("-----------------------------------------------\n");
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}

		System.out.println(
				"\n\nHere is an example of the iterator remove function from MyArray(removing every test with an even number of questions):\n\n");
		Iterator<Test> itera = arr.iterator();
		while (itera.hasNext()) {
			Test temp = itera.next();
			if (temp.getNumOfQuestions() % 2 == 0)
				itera.remove();
		}
		Iterator<Test> iterat = arr.iterator();
		System.out.println("These are the tests from MyArray after removing Test\n");
		while (iterat.hasNext()) {
			System.out.println(iterat.next());
		}
		System.out.println(
				"\n\nHere is an example of the iterator remove function from the regular ArrayList(removing every test with an even number of questions):\n\n");
		Iterator<Test> iterato = list.iterator();
		while (iterato.hasNext()) {
			Test temp = iterato.next();
			if (temp.getNumOfQuestions() % 2 == 0)
				iterato.remove();
		}
		Iterator<Test> iterator = list.iterator();
		System.out.println("These are the tests from the regular ArrayList after removing Test\n");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		arr = new MyArrayList();
		Iterator<Test> iT = hash.iterator();
		while (iT.hasNext()) {
			Test temp = iT.next();
			arr.add(temp);
			list.add(temp);
		}

	}

	public Iterator<Test> activateIterator() {
		return arr.iterator();
	}

	public MyArrayList getMyArray() {
		return arr;
	}

	public void printMyArrayList(Iterator<Test> it) {
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public boolean createMementoForAllQuestions() throws CloneNotSupportedException {
		if (!questions.isEmpty()) {
			System.out.println("\n *****************ALL QUESTION MEMORY SAVED*****************");
			memArr.removeAll(memArr);
			for (int i = 0; i < questions.size(); i++) {
				Questions q = questions.get(i);
				if (q instanceof AmericanQuestions) {
					memArr.add((Memento) ((AmericanQuestions) q).createAmericanMemento());
				} else {
					memArr.add((Memento) ((OpenQuestions) q).createOpenMemento());
				}

			}
			return true;
		}
		return false;
	}

	public void setMementoToAllQuestions() throws CloneNotSupportedException {
		System.out.println("\n *****************ALL QUESTION MEMORY RESTORED*****************");
		while (memArr.size() < questions.size()) {
			questions.remove(questions.size() - 1);
			numOfQuestions--;
		}
		for (int i = 0; i < memArr.size(); i++) {
			Questions q = questions.get(i);
			if (q instanceof AmericanQuestions) {
				((AmericanQuestions) q).setAmericanMemento((AmericanMemento) memArr.get(i));
			} else {
				((OpenQuestions) q).setOpenMemento((OpenMemento) memArr.get(i));
			}
		}
	}
}
