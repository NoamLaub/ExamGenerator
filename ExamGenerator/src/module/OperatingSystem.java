package module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class OperatingSystem implements Serializable {
	protected static int arraySize = 20;
	protected static int numOfQuestions = 0;
	protected static int numOfTests = 0;
	protected static ArrayList<Questions> questions = new ArrayList<Questions>();
	protected static ArrayList<Test> tests = new ArrayList<Test>();
	protected static TreeSet<Test> treeTests;
	protected static LinkedHashSet<Test> hash;
	protected static MyArrayList arr;

	public static Test createTest(int numOfQuestions) {
		Test newTest = new Test(numOfQuestions);
		return newTest;
	}

	public static OpenQuestions constructOpenQuestion(String text, Answers answer) {
		OpenQuestions question = new OpenQuestions(text, answer);
		questions.add(question);
		return question;
	}

	public static AmericanQuestions contructAmericanQuestion(String text) {
		AmericanQuestions question = new AmericanQuestions(text);
		questions.add(question);
		return question;
	}

	public static AmericanQuestions contructAmericanQuestion(AmericanQuestions question, int numOfAnswers) {
		AmericanQuestions questionForExam = new AmericanQuestions(question, numOfAnswers);
		return questionForExam;
	}

	public static Answers constructAnswer(String bodyOfAnswer, boolean correctness) {
		Answers answer = new Answers(bodyOfAnswer, correctness);
		return answer;
	}

	public static boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct) {
		return question.addAnswer(text, correct);
	}

	public static Questions getQuestion(int index) {
		return questions.get(index);
	}

	public static ArrayList<Questions> getQuestions() {
		return questions;
	}

	public static void saveTestToFile(String testText, String TestSolution)
			throws FileNotFoundException {
		LocalDate testDate = LocalDate.now();
		String testFileName = "exam_" + testDate;
		String answersFileName = "solution_" + testDate;
		PrintWriter testFile = new PrintWriter(testFileName);
		PrintWriter solutionOfTestFile = new PrintWriter(answersFileName);
		testFile.println("Date test created:" + testDate+"\n");
		solutionOfTestFile.println(TestSolution);
		testFile.println(testText);
		testFile.println("Good Luck!");
		solutionOfTestFile.println("I really hope you're reading this after doing the test...");
		testFile.close();
		solutionOfTestFile.close();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * public static int getNumOfTests() { return numOfTests; }
	 * 
	 * public static ArrayList<Questions> getQuestions() { return questions; }
	 * 
	 * public static void subtractFromNumOfTests() { numOfTests--; }
	 * 
	 * public static ArrayList<Questions> getArrayOfquestions() { return questions;
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * public static void addQuestionToPool(Questions question) {
	 * questions.add(numOfQuestions++, question); }
	 * 
	 * 
	 * public static void
	 * savePoolOfQuestionsAndTestsToFile(ArrayList<OperatingSystemModule>
	 * moduleList) throws IOException { ObjectOutputStream poolOfQuestionsAndTests =
	 * new ObjectOutputStream(new FileOutputStream("Pool_Of_questions"));
	 * poolOfQuestionsAndTests.writeInt(numOfQuestions);
	 * poolOfQuestionsAndTests.writeInt(Questions.getNumOfQuestions());
	 * poolOfQuestionsAndTests.writeInt(OpenQuestions.getCounter());
	 * poolOfQuestionsAndTests.writeInt(AmericanQuestions.getCounter());
	 * poolOfQuestionsAndTests.writeInt(Answers.getCounter());
	 * poolOfQuestionsAndTests.writeInt(OperatingSystemModule.getCounter());
	 * poolOfQuestionsAndTests.writeObject(questions);
	 * poolOfQuestionsAndTests.writeInt(numOfTests);
	 * poolOfQuestionsAndTests.writeInt(Test.getCounter());
	 * poolOfQuestionsAndTests.writeObject(tests);
	 * poolOfQuestionsAndTests.writeObject(moduleList);
	 * poolOfQuestionsAndTests.close(); }
	 * 
	 * @SuppressWarnings("unchecked") public static ArrayList<OperatingSystemModule>
	 * loadPoolOfQuestionsAndTestsFromFile() throws FileNotFoundException,
	 * IOException, ClassNotFoundException { ObjectInputStream
	 * poolOfQuestionsAndTests = new ObjectInputStream(new
	 * FileInputStream("Pool_Of_questions")); numOfQuestions =
	 * poolOfQuestionsAndTests.readInt(); Questions.numOfQuestions =
	 * poolOfQuestionsAndTests.readInt(); int openCounter =
	 * poolOfQuestionsAndTests.readInt(); OpenQuestions.setCounter(openCounter); int
	 * closeCounter = poolOfQuestionsAndTests.readInt();
	 * AmericanQuestions.setCounter(closeCounter); int answerCounter =
	 * poolOfQuestionsAndTests.readInt(); Answers.setCounter(answerCounter); int
	 * moduleCounter = poolOfQuestionsAndTests.readInt();
	 * OperatingSystemModule.setCounter(moduleCounter); questions =
	 * (ArrayList<Questions>) poolOfQuestionsAndTests.readObject(); numOfTests =
	 * poolOfQuestionsAndTests.readInt(); Test.counter =
	 * poolOfQuestionsAndTests.readInt(); tests = (ArrayList<Test>)
	 * poolOfQuestionsAndTests.readObject(); ArrayList<OperatingSystemModule>
	 * moduleList =(ArrayList<OperatingSystemModule> )
	 * poolOfQuestionsAndTests.readObject(); poolOfQuestionsAndTests.close(); return
	 * moduleList; }
	 * 
	 * 
	 * 
	 * public static Class<AmericanQuestions> getAmericanQuestionClass() { return
	 * AmericanQuestions.class; }
	 * 
	 * public static boolean checkIfChosenQuestionExistByIndex(int questionIndex) {
	 * if (questionIndex < 0 || questionIndex > questions.size() - 1 ||
	 * questions.get(questionIndex) == null) { return false; } else { return true; }
	 * }
	 * 
	 * public static boolean checkIfQuestionExistViaQuestion(Questions question) {
	 * for (int i = 0; i < numOfQuestions; i++) { if
	 * (questions.get(i).equals(question) && questions.get(i).getSerialNum() !=
	 * question.getSerialNum()) { numOfQuestions--; return true; } } return false; }
	 * 
	 * public static boolean checkIfQuestionExistViaText(Questions question) {
	 * boolean questionExist = checkIfQuestionExistViaQuestion(question); if
	 * (questionExist) { numOfQuestions++; } return questionExist; }
	 * 
	 * 
	 * public static boolean checkIfAnswerTextExist(AmericanQuestions question,
	 * String answerText) { return question.checkIfAnswerTextExist(answerText); }
	 * 
	 * public static boolean checkIfAnswerExistByIndex(AmericanQuestions question,
	 * int answerIndex) { return question.checkIfAnswerExist(answerIndex); }
	 * 
	 * public static boolean checkIfAnswerExistByAnswer(AmericanQuestions american,
	 * Answers answer) { return american.checkIfAnswerExistByAnswer(answer); }
	 * 
	 * public static Questions getQuestion(int index) { return questions.get(index);
	 * }
	 * 
	 * public static Answers getAnswer(AmericanQuestions american, int index) {
	 * return american.getAnswers().get(index); }
	 * 
	 * public static int getNumberOfAnswers(AmericanQuestions question) { return
	 * question.getNumberOfAnswers(); }
	 * 
	 * public static int getNumOfQuestionsInArray() { return numOfQuestions; }
	 * 
	 * public static void changeBodyOfQuestion(int indexOfQuestion, String newText)
	 * { questions.get(indexOfQuestion).setText(newText); }
	 * 
	 * public static void changeAnswer(Questions chosenQuestion, int index, String
	 * text) { chosenQuestion.setAnswer(index, text); }
	 * 
	 * public static void deleteQuestion(int questionIndex) {
	 * questions.set(questionIndex, questions.get(--numOfQuestions)); }
	 * 
	 * public static boolean deleteAnswer(AmericanQuestions question, int
	 * chosenAnswer) { return question.deleteAnswer(chosenAnswer); }
	 * 
	 * public static String showAmericanQuestionAnswers(Questions chosenQuestion) {
	 * return chosenQuestion.toString(); }
	 * 
	 * 
	 * 
	 * public static Test createTestViaCopy(Test test) throws
	 * CloneNotSupportedException { Test newTest = test.clone(); return newTest; }
	 * 
	 * public static ArrayList<Test> getTests() { return tests; }
	 * 
	 * public static void addTestToPool(Test test) { tests.add(numOfTests++, test);
	 * 
	 * }
	 * 
	 * public static void addQuestionToTest(Questions question, Test test) {
	 * test.addQuestionToTest(question); }
	 * 
	 * public static boolean questionExistInTest(Test test, Questions question) {
	 * return (test.questionExistInTest(question)); }
	 * 
	 * public static void verifyAnswers(AmericanQuestions question) {
	 * question.verifyAnswers(); }
	 * 
	 * public static void testSortViaLengthOfAnswers(Test test) {
	 * test.testSortViaLengthOfAnswers(); }
	 * 
	 * public static String printTest(Test test) { return test.toString(); }
	 * 
	 * 
	 * public static void savePoolOfQuestionsToFile() throws IOException {
	 * ObjectOutputStream poolOfQuestions = new ObjectOutputStream(new
	 * FileOutputStream("Pool_Of_questions"));
	 * poolOfQuestions.writeInt(numOfQuestions);
	 * poolOfQuestions.writeInt(Questions.getNumOfQuestions());
	 * poolOfQuestions.writeObject(questions); poolOfQuestions.close(); }
	 * 
	 * public static String showAllTests() { StringBuffer sb = new
	 * StringBuffer("There are " + numOfTests + " tests \n\n"); for (int i = 0; i <
	 * numOfTests; i++) { sb.append("Test number " + tests.get(i).getSerialNum() +
	 * "\n"); } return sb.toString(); }
	 * 
	 * public static String toString(ArrayList<Questions> questions) { StringBuffer
	 * sb = new StringBuffer("There are " + numOfQuestions + " questions \n\n"); for
	 * (int i = 0; i < numOfQuestions; i++) { sb.append((i + 1) + ". " +
	 * questions.get(i).toString() + "\n"); } return sb.toString();
	 * 
	 * }
	 * 
	 * public static void transferTestsToTreeSet() { int testIndex = 1; treeTests =
	 * new TreeSet<Test>(new Comparator<Test>() {
	 * 
	 * @Override public int compare(Test t1, Test t2) { int compare =
	 * t2.getNumOfQuestions() - t1.getNumOfQuestions(); if (compare == 0) return 1;
	 * return compare;
	 * 
	 * }
	 * 
	 * }); treeTests.addAll(tests); Iterator<Test> it = treeTests.iterator();
	 * System.out.
	 * println("Tree set of tests sorted in descending order by the number of questions in them:\n\n"
	 * ); while (it.hasNext()) {
	 * System.out.printf("----------------Test %d---------------:\n\n",
	 * testIndex++); System.out.println(it.next()); } }
	 * 
	 * public static void transferTestsToLinkedHashSet() { int testIndex = 1; hash =
	 * new LinkedHashSet<Test>(treeTests); Iterator<Test> iter = hash.iterator();
	 * System.out.println(
	 * "Linked hash set of tests (values defining equality are: numOfQuestions and the first question's text length):\n\n"
	 * );
	 * 
	 * while (iter.hasNext()) {
	 * System.out.printf("----------------Test %d---------------:\n\n",
	 * testIndex++); System.out.println(iter.next()); } }
	 * 
	 * public static void transferTestsFromHashLinkedSetToMyArrayList() { arr = new
	 * MyArrayList(); ArrayList<Test> list = new ArrayList<Test>(); Iterator<Test>
	 * iter = hash.iterator();
	 * 
	 * while (iter.hasNext()) { Test temp = iter.next(); arr.add(temp);
	 * list.add(temp); } Iterator<Test> it = arr.iterator(); Iterator<Test> ite =
	 * list.iterator();
	 * 
	 * System.out.println("These are the tests from MyArray\n");
	 * System.out.println("-----------------------------------------------\n");
	 * while (it.hasNext()) { System.out.println(it.next()); }
	 * 
	 * System.out.println("These are the tests from the regular ArrayList \n");
	 * System.out.println("-----------------------------------------------\n");
	 * while (ite.hasNext()) { System.out.println(ite.next()); }
	 * 
	 * System.out.println(
	 * "\n\nHere is an example of the iterator remove function from MyArray(removing every test with an even number of questions):\n\n"
	 * ); Iterator<Test> itera = arr.iterator(); while (itera.hasNext()) { Test temp
	 * = itera.next(); if (temp.getNumOfQuestions() % 2 == 0) itera.remove(); }
	 * Iterator<Test> iterat = arr.iterator();
	 * System.out.println("These are the tests from MyArray after removing Test\n");
	 * while (iterat.hasNext()) { System.out.println(iterat.next()); }
	 * System.out.println(
	 * "\n\nHere is an example of the iterator remove function from the regular ArrayList(removing every test with an even number of questions):\n\n"
	 * ); Iterator<Test> iterato = list.iterator(); while (iterato.hasNext()) { Test
	 * temp = iterato.next(); if (temp.getNumOfQuestions() % 2 == 0)
	 * iterato.remove(); } Iterator<Test> iterator = list.iterator(); System.out.
	 * println("These are the tests from the regular ArrayList after removing Test\n"
	 * ); while (iterator.hasNext()) { System.out.println(iterator.next()); } arr =
	 * new MyArrayList(); Iterator<Test> iT = hash.iterator();
	 * 
	 * while (iT.hasNext()) { Test temp = iT.next(); arr.add(temp); list.add(temp);
	 * }
	 * 
	 * 
	 * }
	 * 
	 * public static Iterator<Test> activateIterator() {
	 * 
	 * return arr.iterator(); }
	 * 
	 * public static MyArrayList getMyArray() { return arr; }
	 * 
	 * public static void printMyArrayList(Iterator<Test> it) { while (it.hasNext())
	 * { System.out.println(it.next()); } }
	 */

}
