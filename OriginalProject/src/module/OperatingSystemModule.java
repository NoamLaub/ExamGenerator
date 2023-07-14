package module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class OperatingSystemModule implements MainInterface {
	OperatingSystem os;

	public OperatingSystem getOs() {
		return os;
	}

	public OperatingSystemModule() {
		this.os = new OperatingSystem();
	}

	public int getNumOfTests() {
		return os.getNumOfTests();

	}

	public void subtractFromNumOfTests() {
		os.subtractFromNumOfTests();

	}

	public Class getAmericanQuestionClass() {
		return os.getAmericanQuestionClass();
	}

	public ArrayList<Questions> getArrayOfquestions() {
		return os.getArrayOfquestions();
	}

	@Override
	public OpenQuestions constructOpenQuestion(String text, Answers answer) {
		return os.constructOpenQuestion(text, answer);
	}

	@Override
	public AmericanQuestions contructAmericanQuestion(String text) {
		return os.contructAmericanQuestion(text);
	}

	@Override
	public AmericanQuestions contructAmericanQuestion(AmericanQuestions question, int numOfAnswers) {
		return os.contructAmericanQuestion(question, numOfAnswers);
	}

	@Override
	public Answers constructAnswer(String bodyOfAnswer, boolean correctness) {
		return os.constructAnswer(bodyOfAnswer, correctness);
	}

	@Override
	public void addQuestionToPool(Questions question) {
		os.addQuestionToPool(question);

	}

	@Override
	public boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct) {
		return os.contructAnswerForAmerican(question, text, correct);
	}

	@Override
	public boolean checkIfChosenQuestionExistByIndex(int questionIndex) {
		return os.checkIfChosenQuestionExistByIndex(questionIndex);
	}

	@Override
	public boolean checkIfQuestionExistViaQuestion(Questions question) {
		return os.checkIfQuestionExistViaQuestion(question);
	}

	@Override
	public boolean checkIfQuestionExistViaText(Questions question) {
		return os.checkIfQuestionExistViaText(question);
	}

	@Override
	public boolean checkIfAnswerTextExist(AmericanQuestions question, String answerText) {
		return os.checkIfAnswerTextExist(question, answerText);
	}

	@Override
	public boolean checkIfAnswerExistByIndex(AmericanQuestions question, int answerIndex) {
		return os.checkIfAnswerExistByIndex(question, answerIndex);
	}

	@Override
	public boolean checkIfAnswerExistByAnswer(AmericanQuestions american, Answers answer) {
		return os.checkIfAnswerExistByAnswer(american, answer);
	}

	@Override
	public Questions getQuestion(int index) {
		return os.getQuestion(index);
	}

	@Override
	public Answers getAnswer(AmericanQuestions american, int index) {
		return os.getAnswer(american, index);
	}

	@Override
	public int getNumberOfAnswers(AmericanQuestions question) {
		return os.getNumberOfAnswers(question);
	}

	@Override
	public int getNumOfQuestionsInArray() {
		return os.getNumOfQuestionsInArray();
	}

	@Override
	public void changeBodyOfQuestion(int indexOfQuestion, String newText) {
		os.changeBodyOfQuestion(indexOfQuestion, newText);

	}

	@Override
	public void changeAnswer(Questions chosenQuestion, int index, String text) {
		os.changeAnswer(chosenQuestion, index, text);

	}

	@Override
	public void deleteQuestion(int questionIndex) {
		os.deleteQuestion(questionIndex);

	}

	@Override
	public boolean deleteAnswer(AmericanQuestions question, int chosenAnswer) {
		return os.deleteAnswer(question, chosenAnswer);
	}

	@Override
	public String showAmericanQuestionAnswers(Questions chosenQuestion) {
		return os.showAmericanQuestionAnswers(chosenQuestion);
	}

	@Override
	public Test createTest(int numOfQuestions) {
		return os.createTest(numOfQuestions);
	}

	@Override
	public Test createTestViaCopy(Test test) throws CloneNotSupportedException {
		return os.createTestViaCopy(test);
	}

	@Override
	public ArrayList<Test> getTests() {
		return os.getTests();
	}

	@Override
	public void addTestToPool(Test test) {
		os.addTestToPool(test);

	}

	@Override
	public void addQuestionToTest(Questions question, Test test) {
		os.addQuestionToTest(question, test);

	}

	@Override
	public boolean questionExistInTest(Test test, Questions question) {
		return os.questionExistInTest(test, question);
	}

	@Override
	public void verifyAnswers(AmericanQuestions question) {
		os.verifyAnswers(question);

	}

	@Override
	public void testSortViaLengthOfAnswers(Test test) {
		os.testSortViaLengthOfAnswers(test);

	}

	@Override
	public String printTest(Test test) {
		return os.printTest(test);
	}

	@Override
	public String showAllTests() {
		return os.showAllTests();
	}

	@Override
	public void saveTestToFile(Test test) throws FileNotFoundException {
		os.saveTestToFile(test);

	}

	@Override
	public void savePoolOfQuestionsAndTestsToFile() throws IOException {
		os.savePoolOfQuestionsAndTestsToFile();

	}

	@Override
	public void loadPoolOfQuestionsAndTestsFromFile()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		os.loadPoolOfQuestionsAndTestsFromFile();

	}

	@Override
	public String toString(ArrayList<Questions> questions) {
		return os.toString(questions);
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
		os.transferTestsToTreeSet();
	}

	public void transferTestsToLinkedHashSet() {
		os.transferTestsToLinkedHashSet();
	}

	public void transferTestsFromHashLinkedSetToMyArrayList() {
		os.transferTestsFromHashLinkedSetToMyArrayList();
	}

	public MyArrayList getMyArray() {
		return os.getMyArray();
	}

	public Iterator<Test> activateIterator() {
		return os.activateIterator();
	}

	public void printMyArrayList(Iterator<Test> it) {
		System.out.println(
				"Printing after pressing the button\n------------------------------------------------------\n");
		os.printMyArrayList(it);
	}

	public boolean creatMementoToQuestions() {
		try {
			if (!os.createMementoForAllQuestions()) {
				System.out.println("********SAVING FAILED PLEASE ADD QUESTIONS TO THE DATA BASE FIRST********");
				return false;
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void retriveMementoToQuestions() {

		try {
			os.setMementoToAllQuestions();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
