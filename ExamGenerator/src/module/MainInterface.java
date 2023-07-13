package module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface  MainInterface {
	
	public abstract ArrayList<Questions> getArrayOfquestions();
	
	public abstract OpenQuestions constructOpenQuestion(String text, Answers answer);
	
	public abstract AmericanQuestions contructAmericanQuestion(String text);
	
	public abstract AmericanQuestions contructAmericanQuestion(AmericanQuestions question, int numOfAnswers);
	
	public abstract Answers constructAnswer(String bodyOfAnswer, boolean correctness);
	
	public abstract void addQuestionToPool(Questions question);
	
	public abstract boolean contructAnswerForAmerican(AmericanQuestions question, String text, boolean correct);
	
	
	
	public abstract boolean checkIfChosenQuestionExistByIndex(int questionIndex);
	
	public abstract boolean checkIfQuestionExistViaQuestion(Questions question);
	
	public abstract boolean checkIfQuestionExistViaText(Questions question);
	
	public abstract boolean checkIfAnswerTextExist(AmericanQuestions question, String answerText);
	
	public abstract boolean checkIfAnswerExistByIndex(AmericanQuestions question, int answerIndex);
	
	public abstract boolean checkIfAnswerExistByAnswer(AmericanQuestions american, Answers answer);
	
	public abstract Questions getQuestion(int index);
	
	public abstract Answers getAnswer(AmericanQuestions american, int index);
	
	public abstract int getNumberOfAnswers(AmericanQuestions question);
	
	public abstract int getNumOfQuestionsInArray();
	
	public abstract void changeBodyOfQuestion(int indexOfQuestion, String newText);
	
	public abstract void changeAnswer(Questions chosenQuestion, int index, String text);
	
	public abstract void deleteQuestion(int questionIndex);
	
	public abstract boolean deleteAnswer(AmericanQuestions question, int chosenAnswer);
	
	public abstract String showAmericanQuestionAnswers(Questions chosenQuestion);
	
	public abstract Test createTest(int numOfQuestions);
	
	public abstract Test createTestViaCopy(Test test) throws CloneNotSupportedException;
	
	public abstract ArrayList<Test> getTests() ;
	
	public abstract void addTestToPool(Test test);
	
	public abstract void addQuestionToTest(Questions question, Test test);
	
	public abstract boolean questionExistInTest(Test test, Questions question);
	
	public abstract void verifyAnswers(AmericanQuestions question);
	
	public abstract void testSortViaLengthOfAnswers(Test test);
	
	public abstract String printTest(Test test);
	
	public abstract String showAllTests();
	
	public abstract void saveTestToFile(Test test) throws FileNotFoundException;
	
	
	public abstract ArrayList<OperatingSystemModule> loadPoolOfQuestionsAndTestsFromFile() throws FileNotFoundException, IOException, ClassNotFoundException;
	
	public abstract String toString(ArrayList<Questions> questions);

	void savePoolOfQuestionsAndTestsToFile(ArrayList<OperatingSystemModule> mList) throws IOException;

	
	
	
}
