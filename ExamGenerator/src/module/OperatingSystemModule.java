package module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class OperatingSystemModule implements /*MainInterface,*/ Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int counter = 1;
	private int serialNum;
	private int openCounter = 000001;
	private int closeCounter = 000002;
	private int answerCounter = 000001;
	private int testCounter = 000001;

	public OperatingSystemModule(int serialNum) {
		this.serialNum = serialNum;

	}

	public OperatingSystemModule(int serialNum, int closeCounter, int openCounter, int testCounter, int answerCounter) {
		this.serialNum = serialNum;
		this.closeCounter = closeCounter;
		this.openCounter = openCounter;
		this.testCounter = testCounter;
		this.answerCounter = answerCounter;
		setOpenCounter(openCounter);
		setCloseCounter(closeCounter);
		setAnswerCounter(answerCounter);
		setTestCounter(testCounter);
	}

	public int getOpenCounter() {
		return openCounter;
	}

	private void setOpenCounter(int openCounter) {
		OpenQuestions.setCounter(openCounter);
	}

	public int getCloseCounter() {
		return closeCounter;
	}

	private void setCloseCounter(int closeCounter) {
		AmericanQuestions.setCounter(closeCounter);
	}

	
	  public int getAnswerCounter() { 
		  return answerCounter; 
		  }
	 

	private void setAnswerCounter(int answerCounter) {
		Answers.setCounter(answerCounter);
	}

	public int getSerialNum() {
		return serialNum;
	}

	public int getTestCounter() {
		return Test.getCounter();
	}

	private void setTestCounter(int testCounter) {
		Test.counter = testCounter;
	}
	
	public boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct) {
		return OperatingSystem.contructAnswerForAmerican(question, text, correct);
	}
	public Questions getQuestion(int index) {
		return OperatingSystem.getQuestion(index);
	}
	public ArrayList<Questions> getQuestions() {
		return OperatingSystem.getQuestions();
	}

	public void saveTestToFile(String testText,String TestSolution) throws FileNotFoundException {
		OperatingSystem.saveTestToFile(testText,TestSolution);

	}
	public Test createTest(int numOfQuestions) {
		return OperatingSystem.createTest(numOfQuestions);
	}

	public OpenQuestions constructOpenQuestion(String text, Answers answer) {
		return OperatingSystem.constructOpenQuestion(text, answer);
	}

	public AmericanQuestions contructAmericanQuestion(String text) {
		return OperatingSystem.contructAmericanQuestion(text);
	}

	public AmericanQuestions contructAmericanQuestion(AmericanQuestions question, int numOfAnswers) {
		return OperatingSystem.contructAmericanQuestion(question, numOfAnswers);
	}

	public Answers constructAnswer(String bodyOfAnswer, boolean correctness) {
		return OperatingSystem.constructAnswer(bodyOfAnswer, correctness);
	}

	public void addModuleToDB(Query connection) throws SQLException {
		connection.addModuleToDB(this);
	}

	public void addOpenQuestionToDB(Query connection, OpenQuestions question) throws SQLException {
		connection.addOpenQuestionToDB(question, serialNum);
	}

	public void addAmericanQuestionToDB(Query connection, AmericanQuestions question) throws SQLException {
		connection.addAmericanQuestionToDB(question, serialNum);
	}

	public void addTestToDB(Query connection, Test test) throws SQLException {
		connection.addTestToDB(test, serialNum);
	}

	public void addAmericanQuestionToModuleHasTestAndQuestions(Query connection, int testID, int questionSerial,
			int questionIndex, int numOfAnswers) throws SQLException {
		connection.addAmericanQuestionToModuleHasTestAndQuestions(serialNum, testID, questionSerial, questionIndex,
				numOfAnswers);
	}

	public void addOpenQuestionToModuleHasTestAndQuestions(Query connection, int testID, int questionSerial,
			int questionIndex) throws SQLException {
		connection.addOpenQuestionToModuleHasTestAndQuestions(serialNum, testID, questionSerial, questionIndex);
	}

	public void addAnswerToModuleHasTestsAndAns(Query connection, int testID, int questionSerial, int answerSerial,
			int answerIndex) throws SQLException {
		connection.addAnswerToModuleHasTestsAndAns(serialNum, testID, questionSerial, answerSerial, answerIndex);
	}

	public void addRandomQuestionsToTest(Query connection, int testID, int numOfQuestions) throws SQLException {
		connection.addRandomQuestionsToTest(serialNum, testID, numOfQuestions);
	}

	public boolean checkIfOpenQuestionExistInDB(Query connection, String questionText) throws SQLException {
		return connection.checkIfOpenQuestionExistInDB(questionText, serialNum);
	}

	public boolean checkIfAmericanQuestionExistInDB(Query connection, String questionText) throws SQLException {
		return connection.checkIfAmericanQuestionExistInDB(questionText, serialNum);
	}

	public boolean checkIfQuestionExistInWithSerialNumDB(Query connection, String questionText, int questionSerialNum,
			int moduleSerial) throws SQLException {
		return connection.checkIfQuestionExistInWithSerialNumDB(questionText, questionSerialNum, moduleSerial);
	}

	public void updateQuestionInDB(Query connection, int questionSerial, String newText) throws SQLException {
		connection.updateQuestionInDB(questionSerial, serialNum, newText);
	}

	public void updateAnswerInDB(Query connection, int answerSerial, String newText) throws SQLException {
		connection.updateAnswerInDB(serialNum, answerSerial, newText);
	}
	public void updateAnswerOfOpenInDB(Query connection, int questionSerial, String newText) throws SQLException {
		connection.updateAnswerOfOpenInDB(serialNum, questionSerial, newText);
	}

	public ArrayList<Integer> getAnswersSerial(Query connection, int questionSerial) throws SQLException {
		return connection.getAnswersSerial(serialNum, questionSerial);
	}

	public ArrayList<Integer> getQuestionsSerial(Query connection) throws SQLException {
		return connection.getQuestionsSerial(serialNum);
	}

	public String showAllQuestions(Query connection) throws SQLException {
		return connection.showAllQuestions(serialNum);
	}

	public String showTest(Query connection, int testSerial,boolean testForTextFile) throws SQLException {
		return connection.showTest(serialNum, testSerial,testForTextFile);
	}

	public int getNumOfQuestionsInDB(Query connection) throws SQLException {
		return connection.getNumOfQuestionsInDB(serialNum);

	}

	public boolean checkIfQuestionOpen(Query connection, int questionSerial) throws SQLException {
		return connection.checkIfQuestionOpen(serialNum, questionSerial);
	}

	public boolean checkIfAnswerTextExist(Query connection, int questionSerial, String newText) throws SQLException {
		return connection.checkIfAnswerTextExist(serialNum, questionSerial, newText);
	}

	public boolean checkIfQuestionHasMoreThanFourAns(Query connection, int questionSerial) throws SQLException {
		return connection.checkIfQuestionHasMoreThanFourAns(serialNum, questionSerial);
	}

	public void deleteAnswerFromDB(Query connection, int questionSerial, int answerSerial) throws SQLException {
		connection.deleteAnswerFromDB(serialNum, questionSerial, answerSerial);
	}

	public void deleteTest(Query connection, int testSerial) throws SQLException {
		connection.deleteTest(serialNum, testSerial);
	}

	public int getNumOfAnswersForQuestionInDB(Query connection, int questionSerial) throws SQLException {
		return connection.getNumOfAnswersForQuestionInDB(serialNum, questionSerial);
	}

	public void verifyCorrectnessOfAnswersInTest(Query connection, int testSerial, int questionSerial)
			throws SQLException {
		connection.verifyCorrectnessOfAnswersInTest(serialNum, testSerial, questionSerial);
	}

	public static boolean checkIfPassIsTaken(Query connection, int password) throws SQLException {
		return connection.checkIfPassIsTaken(password);
	}

	public static OperatingSystemModule retrieveModule(Query connection, int password) throws SQLException {
		return connection.retrieveModule(password);
	}
	public ArrayList<Integer> getTestSerials(Query connection) throws SQLException{
		return connection.getTestSerials(serialNum);
	}
	public String showTestSerials(Query connection) throws SQLException {
		return connection.showTestSerials(serialNum);
	}

	public void closeConnection(Query connection) {
		connection.closeConnection();
	}



	/*public static int getCounter() {
		return counter;
	}

	public int getNumOfTests() {
		return OperatingSystem.getNumOfTests();

	}

	public void subtractFromNumOfTests() {
		OperatingSystem.subtractFromNumOfTests();

	}

	public static void setCounter(int counter) {
		OperatingSystemModule.counter = counter;
	}

	public Class getAmericanQuestionClass() {
		return OperatingSystem.getAmericanQuestionClass();
	}

	public ArrayList<Questions> getArrayOfquestions() {
		return OperatingSystem.getArrayOfquestions();
	}

	@Override
	public void addQuestionToPool(Questions question) {
		OperatingSystem.addQuestionToPool(question);

	}

	@Override
	public boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct) {
		return OperatingSystem.contructAnswerForAmerican(question, text, correct);
	}

	@Override
	public boolean checkIfChosenQuestionExistByIndex(int questionIndex) {
		return OperatingSystem.checkIfChosenQuestionExistByIndex(questionIndex);
	}

	@Override
	public boolean checkIfQuestionExistViaQuestion(Questions question) {
		return OperatingSystem.checkIfQuestionExistViaQuestion(question);
	}

	@Override
	public boolean checkIfQuestionExistViaText(Questions question) {
		return OperatingSystem.checkIfQuestionExistViaText(question);
	}

	@Override
	public boolean checkIfAnswerTextExist(AmericanQuestions question, String answerText) {
		return OperatingSystem.checkIfAnswerTextExist(question, answerText);
	}

	@Override
	public boolean checkIfAnswerExistByIndex(AmericanQuestions question, int answerIndex) {
		return OperatingSystem.checkIfAnswerExistByIndex(question, answerIndex);
	}

	@Override
	public boolean checkIfAnswerExistByAnswer(AmericanQuestions american, Answers answer) {
		return OperatingSystem.checkIfAnswerExistByAnswer(american, answer);
	}

	@Override
	public Questions getQuestion(int index) {
		return OperatingSystem.getQuestion(index);
	}

	@Override
	public Answers getAnswer(AmericanQuestions american, int index) {
		return OperatingSystem.getAnswer(american, index);
	}

	@Override
	public int getNumberOfAnswers(AmericanQuestions question) {
		return OperatingSystem.getNumberOfAnswers(question);
	}

	@Override
	public int getNumOfQuestionsInArray() {
		return OperatingSystem.getNumOfQuestionsInArray();
	}

	@Override
	public void changeBodyOfQuestion(int indexOfQuestion, String newText) {
		OperatingSystem.changeBodyOfQuestion(indexOfQuestion, newText);

	}

	@Override
	public void changeAnswer(Questions chosenQuestion, int index, String text) {
		OperatingSystem.changeAnswer(chosenQuestion, index, text);

	}

	@Override
	public void deleteQuestion(int questionIndex) {
		OperatingSystem.deleteQuestion(questionIndex);

	}

	@Override
	public boolean deleteAnswer(AmericanQuestions question, int chosenAnswer) {
		return OperatingSystem.deleteAnswer(question, chosenAnswer);
	}

	@Override
	public String showAmericanQuestionAnswers(Questions chosenQuestion) {
		return OperatingSystem.showAmericanQuestionAnswers(chosenQuestion);
	}

	@Override
	public Test createTestViaCopy(Test test) throws CloneNotSupportedException {
		return OperatingSystem.createTestViaCopy(test);
	}

	@Override
	public ArrayList<Test> getTests() {
		return OperatingSystem.getTests();
	}

	@Override
	public void addTestToPool(Test test) {
		OperatingSystem.addTestToPool(test);

	}

	@Override
	public void addQuestionToTest(Questions question, Test test) {
		OperatingSystem.addQuestionToTest(question, test);

	}

	@Override
	public boolean questionExistInTest(Test test, Questions question) {
		return OperatingSystem.questionExistInTest(test, question);
	}

	@Override
	public void verifyAnswers(AmericanQuestions question) {
		OperatingSystem.verifyAnswers(question);

	}

	@Override
	public void testSortViaLengthOfAnswers(Test test) {
		OperatingSystem.testSortViaLengthOfAnswers(test);

	}

	@Override
	public String printTest(Test test) {
		return OperatingSystem.printTest(test);
	}

	@Override
	public String showAllTests() {
		return OperatingSystem.showAllTests();
	}

	

	@Override
	public void savePoolOfQuestionsAndTestsToFile(ArrayList<OperatingSystemModule> mList) throws IOException {
		OperatingSystem.savePoolOfQuestionsAndTestsToFile(mList);

	}

	public ArrayList<OperatingSystemModule> loadPoolOfQuestionsAndTestsFromFile()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		return OperatingSystem.loadPoolOfQuestionsAndTestsFromFile();

	}

	@Override
	public String toString(ArrayList<Questions> questions) {
		return OperatingSystem.toString(questions);
	}

	public Test createAutomaticTest(int numberOfQuestions) {
		int randomQuestionNum;
		Questions ranQuestion;
		Test randomTest = createTest(numberOfQuestions);
		for (int i = 0; i < numberOfQuestions; i++) {
			do {
				randomQuestionNum = (int) (Math.random() * (getNumOfQuestionsInArray()));
				ranQuestion = getQuestion(randomQuestionNum);
			} while (questionExistInTest(randomTest, ranQuestion));
			if (ranQuestion instanceof AmericanQuestions) {
				AmericanQuestions newQuestion = contructAmericanQuestion((AmericanQuestions) ranQuestion, 6);
				int randomIndex;
				for (int j = 0; j < 4; j++) {
					// do {
					randomIndex = (int) (Math.random() * (getNumberOfAnswers((AmericanQuestions) ranQuestion)));

					if (checkIfAnswerExistByAnswer(newQuestion,
							getAnswer((AmericanQuestions) ranQuestion, randomIndex)) == false) {
						contructAnswerForAmerican(newQuestion,
								getAnswer((AmericanQuestions) ranQuestion, randomIndex).getBodyOfAnswer(),
								getAnswer((AmericanQuestions) ranQuestion, randomIndex).isCorrect());

					} else {
						j--;
					}
				}
				verifyAnswers(newQuestion);
				ranQuestion = newQuestion;
			}
			addQuestionToTest(ranQuestion, randomTest);
		}
		testSortViaLengthOfAnswers(randomTest);
		return randomTest;

	}

	public void transferTestsToTreeSet() {
		OperatingSystem.transferTestsToTreeSet();
	}

	public void transferTestsToLinkedHashSet() {
		OperatingSystem.transferTestsToLinkedHashSet();
	}

	public void transferTestsFromHashLinkedSetToMyArrayList() {
		OperatingSystem.transferTestsFromHashLinkedSetToMyArrayList();
	}

	public MyArrayList getMyArray() {
		return OperatingSystem.getMyArray();
	}

	public Iterator<Test> activateIterator() {
		return OperatingSystem.activateIterator();
	}

	public void printMyArrayList(Iterator<Test> it) {
		System.out.println(
				"Printing after pressing the button\n------------------------------------------------------\n");
		OperatingSystem.printMyArrayList(it);
	}

	
	*/

	
}
