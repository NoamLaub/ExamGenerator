package module;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Query {
	private Connection connector = null;
	private Statement stmt;
	private final String NO_ANS_CORRECT = "None of the answers are correct";
	private final String MORE_THAN_ONE_CORRECT = "More than one answer is correct";

	public Query() throws ClassNotFoundException, SQLException {
		super();
		this.connector = createConnector();
		this.stmt = connector.createStatement();
	}

	public Connection createConnector() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbUrl = "jdbc:mysql://localhost:3306/examgeneratordb";
		Connection connector = DriverManager.getConnection(dbUrl, "root", "mostherock22!");
		return connector;
	}

	public void addModuleToDB(OperatingSystemModule module) throws SQLException {
		String insertModule = "INSERT INTO operatingsystemmodule(moduleSerialNum,closeQuestionCounter,openQuestionCounter,testCounter,answerCounter)"
				+ "VALUES('" + module.getSerialNum() + "','" + module.getCloseCounter() + "','"
				+ module.getOpenCounter() + "','" + module.getTestCounter() + "','" + module.getAnswerCounter() + "')";
		stmt.executeUpdate(insertModule);
	}

	public void addQuestionToDB(Questions tmp, int moduleSerialNum) throws SQLException {
		String insertQuestion = "INSERT INTO question (SerialNum,moduleSerialNum,QuestionText,QuestionType)"
				+ " VALUES ('" + tmp.getSerialNum() + "','" + moduleSerialNum + "','" + tmp.getText() + "','"
				+ tmp.getType() + "')";
		stmt.executeUpdate(insertQuestion);
	}

	public void addOpenQuestionToDB(OpenQuestions tmp, int moduleSerialNum) throws SQLException {
		String insertQuestion = "INSERT INTO question (SerialNum,moduleSerialNum,QuestionText,QuestionType)"
				+ " VALUES ('" + tmp.getSerialNum() + "','" + moduleSerialNum + "','" + tmp.getText() + "','"
				+ tmp.getType() + "')";
		stmt.executeUpdate(insertQuestion);
		UpdateCounter("Open", moduleSerialNum);
		addAnswerToDB(tmp.getAnswer(), tmp.getSerialNum(), moduleSerialNum);
	}

	public void addAmericanQuestionToDB(AmericanQuestions tmp, int moduleSerialNum) throws SQLException {
		addQuestionToDB(tmp, moduleSerialNum);
		String insertAmericanQuestion = "INSERT INTO americanquestion(SerialNum,moduleSerialNum,NumOfAnswers)"
				+ "VALUES('" + tmp.getSerialNum() + "','" + moduleSerialNum + "','" + tmp.getNumberOfAnswers() + "')";
		stmt.executeUpdate(insertAmericanQuestion);
		UpdateCounter("Closed", moduleSerialNum);

		for (int i = 0; i < tmp.getNumberOfAnswers(); i++)
			addAnswerToDB(tmp.getAnswers().get(i), tmp.getSerialNum(), moduleSerialNum);
		addAnswerToDB(tmp.getAnswers().get(10), tmp.getSerialNum(), moduleSerialNum);
		addAnswerToDB(tmp.getAnswers().get(11), tmp.getSerialNum(), moduleSerialNum);

	}

	public void addAnswerToDB(Answers ans, int questionId, int moduleSerialNum) throws SQLException {
		String insertAnswer = "INSERT INTO answer(AnswerID,moduleSerialNum,AnswerText,AnswerCorrectness,QuestionID)"
				+ "VALUES('" + ans.getSerialNum() + "','" + moduleSerialNum + "','" + ans.getBodyOfAnswer() + "','"
				+ ans.getBooleanInInt() + "','" + Integer.toString(questionId) + "')";
		stmt.executeUpdate(insertAnswer);
		UpdateCounter("Answer", moduleSerialNum);

	}

	public void addTestToDB(Test test, int moduleSerial) throws SQLException {
		String insertTest = "INSERT INTO test(TestID,moduleSerialNum,NumOfQuestions) " + "VALUES('"
				+ test.getSerialNum() + "','" + moduleSerial + "','" + test.getTestSize() + "')";
		stmt.executeUpdate(insertTest);
		UpdateCounter("Test", moduleSerial);

	}

	public void addAmericanQuestionToModuleHasTestAndQuestions(int moduleSerial, int testID, int questionSerial,
			int questionIndex, int numOfAnswers) throws SQLException {
		String insertToModuleHasTestAndQuestion = "INSERT INTO modulehastestsandquestions(TestID,QuestionID,moduleSerialNum,QuestionIndex,NumOfAnswers) "
				+ "VALUES('" + testID + "','" + questionSerial + "','" + moduleSerial + "','" + questionIndex + "','"
				+ (numOfAnswers + 2) + "')";
		stmt.executeUpdate(insertToModuleHasTestAndQuestion);
		addFixedAnswersToTest(moduleSerial, testID, questionSerial, numOfAnswers);
	}

	public void addOpenQuestionToModuleHasTestAndQuestions(int moduleSerial, int testID, int questionSerial,
			int questionIndex) throws SQLException {
		String insertToModuleHasTestAndQuestion = "INSERT INTO modulehastestsandquestions(TestID,QuestionID,moduleSerialNum,QuestionIndex,NumOfAnswers) "
				+ "VALUES('" + testID + "','" + questionSerial + "','" + moduleSerial + "','" + questionIndex + "','"
				+ 1 + "')";
		stmt.executeUpdate(insertToModuleHasTestAndQuestion);
		String getAnswerSerial = "SELECT AnswerID FROM answer where moduleSerialNum = '" + moduleSerial
				+ "' AND QuestionID = '" + questionSerial + "';";
		ResultSet rs = stmt.executeQuery(getAnswerSerial);
		rs.next();
		int ansSerial = rs.getInt("AnswerID");
		rs.close();
		addAnswerToModuleHasTestsAndAns(moduleSerial, testID, questionSerial, ansSerial, 1);

	}

	public void addAnswerToModuleHasTestsAndAns(int moduleSerial, int testID, int questionSerial, int answerSerial,
			int answerIndex) throws SQLException {
		String getCorrectValue = "SELECT AnswerCorrectness FROM answer where AnswerID = '" + answerSerial
				+ "' and moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(getCorrectValue);
		rs.next();
		String insertToModuleHasTestAndAnswers = "INSERT INTO modulehastestsandanswers(TestID,AnswerID,moduleSerialNum,AnswerIndex,QuestionID,AnswerCorrectness) "
				+ "VALUES('" + testID + "','" + answerSerial + "','" + moduleSerial + "','" + answerIndex + "','"
				+ questionSerial + "','" + rs.getInt("AnswerCorrectness") + "')";
		stmt.executeUpdate(insertToModuleHasTestAndAnswers);
	}

	public void addFixedAnswersToTest(int moduleSerial, int testID, int questionSerial, int numOfAnswers)
			throws SQLException {
		int firstAnswerIndex = numOfAnswers + 1;
		int secondAnswerIndex = numOfAnswers + 2;

		String getFirstAndSecondAnswerSerial = "SELECT AnswerID FROM answer where moduleSerialNum = '" + moduleSerial
				+ "' and QuestionID = '" + questionSerial + "' and (AnswerText = '" + NO_ANS_CORRECT
				+ "' or AnswerText = '" + MORE_THAN_ONE_CORRECT + "');";
		ResultSet rs = stmt.executeQuery(getFirstAndSecondAnswerSerial);
		rs.next();

		String addAnswerOne = "INSERT INTO modulehastestsandanswers(TestID,AnswerID,moduleSerialNum,AnswerIndex,QuestionID,AnswerCorrectness)"
				+ "VALUES('" + testID + "','" + rs.getInt("AnswerID") + "','" + moduleSerial + "','" + firstAnswerIndex
				+ "','" + questionSerial + "','" + 0 + "')";
		rs.next();
		String addAnswerTwo = "INSERT INTO modulehastestsandanswers(TestID,AnswerID,moduleSerialNum,AnswerIndex,QuestionID,AnswerCorrectness)"
				+ "VALUES('" + testID + "','" + rs.getInt("AnswerID") + "','" + moduleSerial + "','" + secondAnswerIndex
				+ "','" + questionSerial + "','" + 0 + "')";
		rs.close();
		stmt.executeUpdate(addAnswerOne);
		stmt.executeUpdate(addAnswerTwo);

	}

	public void deleteAnswerFromDB(int moduleSerialNum, int questionSerial, int answerSerialNum) throws SQLException {
		String deleteAnswer = "DELETE FROM answer WHERE  AnswerID = '" + answerSerialNum + "' and moduleSerialNum = '"
				+ moduleSerialNum + "'";
		deleteAnsFromModuleHasTestsAndAns(moduleSerialNum, answerSerialNum);
		stmt.executeUpdate(deleteAnswer);
		updateNumOfAnswersInQDB(questionSerial, moduleSerialNum);
		updateNumOfAnswersInModuleTestQuestionDB(questionSerial, moduleSerialNum);
	}

	public void updateAnswerOrder(int questionSerialNum, int moduleSerialNum, int answerID) throws SQLException {
		String getAnswerIndex = "select AnswerIndex FROM testhasquestionsandanswers WHERE QuestionID = " + "'"
				+ questionSerialNum + "' and moduleSerialNum = '" + moduleSerialNum + "'and AnswerID = '" + answerID
				+ "'";
		String countNumOfAnswers = "select COUNT(QuestionID) FROM testhasquestionsandanswers WHERE QuestionID = " + "'"
				+ questionSerialNum + "' and moduleSerialNum = '" + moduleSerialNum + "'";
		ResultSet st = stmt.executeQuery(countNumOfAnswers);
		ResultSet rs = stmt.executeQuery(getAnswerIndex);
		st.next();
		rs.next();
		int answerIndex = rs.getInt(1);
		int questionNumOfAnswers = st.getInt(1);
		for (int i = answerIndex + 1; i <= questionNumOfAnswers; i++) {
			String updateQuestion = "UPDATE Question SET AnswerIndex = (AnswerIndex-1)  WHERE SerialNum = '"
					+ questionSerialNum + "' and moduleSerialNum = '" + moduleSerialNum + "' and AnswerIndex = '" + i
					+ "'";
			stmt.executeUpdate(updateQuestion);
		}
		st.close();
		rs.close();

	}

	public void deleteAnsFromModuleHasTestsAndAns(int moduleSerial, int ansSerialNum) throws SQLException {
		String deleteRow = "DELETE FROM modulehastestsandanswers WHERE AnswerID = '" + ansSerialNum
				+ "' and moduleSerialNum = '" + moduleSerial + "'";
		stmt.executeUpdate(deleteRow);
	}

	public void deleteTest(int moduleSerial, int testSerial) throws SQLException {
		String deleteTestFromTestHasQuestions = "DELETE FROM modulehastestsandquestions WHERE TestID = '" + testSerial
				+ "' and moduleSerialNum = '" + moduleSerial + "'";
		String deleteTestFromTestHasAnswers = "DELETE FROM modulehastestsandanswers WHERE TestID = '" + testSerial
				+ "' and moduleSerialNum = '" + moduleSerial + "'";
		String deleteTest = "DELETE FROM test WHERE TestID = '" + testSerial + "' and moduleSerialNum = '"
				+ moduleSerial + "'";
		stmt.executeUpdate(deleteTestFromTestHasAnswers);
		stmt.executeUpdate(deleteTestFromTestHasQuestions);
		stmt.executeUpdate(deleteTest);

	}

	public void updateNumOfAnswersInQDB(int questionSerialNum, int moduleSerialNum) throws SQLException {
		String updateQuestion = "UPDATE americanquestion SET NumOfAnswers = (NumOfAnswers-1)  WHERE SerialNum = '"
				+ questionSerialNum + "' and moduleSerialNum = '" + moduleSerialNum + "'";
		stmt.executeUpdate(updateQuestion);
	}

	public void updateNumOfAnswersInModuleTestQuestionDB(int questionSerialNum, int moduleSerialNum)
			throws SQLException {
		String updateQuestion = "UPDATE modulehastestsandquestions SET NumOfAnswers = (NumOfAnswers-1)  WHERE QuestionID = '"
				+ questionSerialNum + "' and moduleSerialNum = '" + moduleSerialNum + "'";
		stmt.executeUpdate(updateQuestion);
	}

	public void updateQuestionInDB(int questionSerialNum, int moduleSerial, String text) throws SQLException {
		String updateQuestion = "UPDATE Question SET QuestionText ='" + text + "' WHERE SerialNum = '"
				+ questionSerialNum + "'and moduleSerialNum = '" + moduleSerial + "'";
		stmt.executeUpdate(updateQuestion);
	}

	public void updateAnswerInDB(int moduleSerial, int answerSerial, String text) throws SQLException {
		String updateQuestion = "UPDATE answer SET AnswerText ='" + text + "' WHERE moduleSerialNum = '" + moduleSerial
				+ "'and AnswerID = '" + answerSerial + "'";
		stmt.executeUpdate(updateQuestion);
	}
	public void updateAnswerOfOpenInDB(int moduleSerial, int questionSerial, String text) throws SQLException {
		String updateQuestion = "UPDATE answer SET AnswerText ='" + text + "' WHERE moduleSerialNum = '" + moduleSerial
				+ "'and QuestionID = '" + questionSerial + "'";
		stmt.executeUpdate(updateQuestion);
	}

	public boolean checkIfOpenQuestionExistInDB(String questionText, int moduleSerial) throws SQLException {
		String open = "open";
		String searchForQ = "SELECT * FROM question where '" + questionText + "'= QuestionText and  QuestionType = '"
				+ open + "'and  moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(searchForQ);

		if (rs.isBeforeFirst()) {
			rs.close();
			return true;
		} else {
			rs.close();
			return false;
		}
	}

	public boolean checkIfQuestionExistInWithSerialNumDB(String questionText, int serialNum, int moduleSerial)
			throws SQLException {

		if (serialNum % 2 == 0)
			return checkIfAmericanQuestionExistInDB(questionText, moduleSerial);
		else
			return checkIfOpenQuestionExistInDB(questionText, moduleSerial);
	}

	public boolean checkIfAmericanQuestionExistInDB(String questionText, int moduleSerial) throws SQLException {
		String close = "closed";
		String searchForQ = "SELECT * FROM question where '" + questionText + "'= QuestionText and  QuestionType =+'"
				+ close + "'and  moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(searchForQ);

		if (rs.isBeforeFirst()) {
			rs.close();
			return true;
		} else {
			rs.close();
			return false;
		}

	}

	public ArrayList<Integer> getAnswersSerial(int moduleSerial, int questionSerial) throws SQLException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String getSerial = "SELECT AnswerID from answer WHERE moduleSerialNum = '" + moduleSerial + "'"
				+ "and QuestionID = '" + questionSerial + "' and AnswerText != '" + NO_ANS_CORRECT
				+ "' AND AnswerText != '" + MORE_THAN_ONE_CORRECT + "';";
		ResultSet rs = stmt.executeQuery(getSerial);

		while (rs.next()) {
			list.add(rs.getInt(1));
		}
		rs.close();
		return list;
	}

	public ArrayList<Integer> getQuestionsSerial(int moduleSerial) throws SQLException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String getSerial = "SELECT SerialNum from question WHERE moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(getSerial);

		while (rs.next()) {
			list.add(rs.getInt(1));
		}
		rs.close();
		return list;
	}

	public String showAllQuestions(int moduleSerial) throws SQLException {
		String showQuestions = "select * from ((select question.SerialNum, question.QuestionText from question where question.moduleSerialNum = '"
				+ moduleSerial + "')as q join"
				+ " (select answer.QuestionID, answer.AnswerID,answer.AnswerText,answer.AnswerCorrectness from answer where answer.moduleSerialNum ='"
				+ moduleSerial + "' and answer.AnswerText != '" + NO_ANS_CORRECT + "' and  answer.AnswerText != '"
				+ MORE_THAN_ONE_CORRECT + "') as a on SerialNum = QuestionID);";

		StringBuffer sb = new StringBuffer("\n\nHere are the relevant questions from the Data Base:\n\n");

		ResultSet rs = stmt.executeQuery(showQuestions);

		
		int previousQuestion = -1;
		while (rs.next()) {
			if (previousQuestion == -1 || rs.getInt(1) != previousQuestion) {
				
				sb.append("\nQuestion ID: " + rs.getInt(1) + " | Question Text: " + rs.getString(2) + "\n");

				previousQuestion = rs.getInt(1);

			}
			if (rs.getInt(6) == 1)
				sb.append("\tAnswer ID: " + rs.getInt(4) + " | Answer Text: " + rs.getString(5)
						+ " \u2713\n");
			else
				sb.append("\tAnswer ID: " + rs.getInt(4) + " | Answer Text: " + rs.getString(5)
						+ " X\n");
			
		}
		rs.close();
		return sb.toString();
	}

	public String showTest(int moduleSerial, int testSerial,boolean testForTextFile) throws SQLException {
		StringBuffer sb = new StringBuffer("Test ID:" + testSerial + "\n\n");
		String getTestQuestionsAndAnswers = "SELECT Quest.QuestionID,Quest.QuestionText, Ans.AnswerText, Ans.AnswerCorrectness,QUEST.QuestionIndex,Ans.AnswerIndex FROM (SELECT testQuestions.QuestionID,allQuestions.QuestionText,testQuestions.QuestionIndex from ((select moduleSerialNum,QuestionID,QuestionIndex FROM modulehastestsandquestions where moduleSerialNum='"
				+ moduleSerial + "' and TestID = '" + testSerial
				+ "') as testQuestions join (SELECT moduleSerialNum,SerialNum,QuestionText from question) as allQuestions on testQuestions.moduleSerialNum = allQuestions.moduleSerialNum and testQuestions.QuestionID = allQuestions.SerialNum)) as Quest join (select allAnswers.AnswerText,testAnswers.AnswerCorrectness,testAnswers.QuestionID,testAnswers.AnswerIndex FROM ((SELECT moduleSerialNum,AnswerID,AnswerText FROM answer where moduleSerialNum ='"
				+ moduleSerial
				+ "') as allAnswers join (SELECT AnswerID,QuestionID,AnswerCorrectness,TestID,AnswerIndex FROM modulehastestsandanswers WHERE moduleSerialNum='"
				+ moduleSerial + "'  and TestID ='" + testSerial
				+ "') as testAnswers on allAnswers.AnswerID = testAnswers.AnswerID)) as Ans on Quest.QuestionID= Ans.QuestionID ORDER BY QUEST.QuestionIndex,Ans.AnswerIndex ;";
		ResultSet rs = stmt.executeQuery(getTestQuestionsAndAnswers);
		int questionIndex = 1;
		int answerIndex = 1;
		int previousQuestion = -1;
		while (rs.next()) {
			if (previousQuestion == -1 || previousQuestion != rs.getInt("Quest.QuestionID")) {
				sb.append("\n" + questionIndex + ". " + rs.getString("Quest.QuestionText") + "\n");
				answerIndex = 1;
				questionIndex++;
				previousQuestion = rs.getInt("Quest.QuestionID");
			}
			sb.append("  " + answerIndex + ". " + rs.getString("AnswerText"));
			if(!testForTextFile) {
				if (rs.getInt("AnswerCorrectness") == 1)
					sb.append("\t\u2713\n");
				else
					sb.append("\tX\n");
			}else {
				sb.append("\n");
			}
			
			answerIndex++;

		}
		rs.close();
		return sb.toString();

	}

	public int getNumOfQuestionsInDB(int moduleSerial) throws SQLException {
		String countQuestions = "SELECT COUNT(SerialNum) FROM question where moduleSerialNum = '" + moduleSerial + "'";
		ResultSet rs = stmt.executeQuery(countQuestions);
		rs.next();
		int result = rs.getInt(1);
		rs.close();
		return result;
	}

	public int getNumOfAnswersForQuestionInDB(int moduleSerial, int questionSerial) throws SQLException {
		String countAnswers = "SELECT COUNT(AnswerID) from answer WHERE moduleSerialNum = '" + moduleSerial
				+ "' and QuestionID = '" + questionSerial + "';";
		ResultSet rs = stmt.executeQuery(countAnswers);
		rs.next();
		int numOfAnswers = rs.getInt(1);
		rs.close();
		return numOfAnswers;
	}

	public boolean checkIfQuestionOpen(int moduleSerial, int questionSerial) throws SQLException {
		String getType = "SELECT QuestionType FROM question where SerialNum = '" + questionSerial
				+ "' and moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(getType);
		rs.next();
		String type = rs.getString(1);
		if (type.equalsIgnoreCase("Open")) {
			rs.close();
			return true;
		}
		rs.close();
		return false;

	}

	public boolean checkIfAnswerTextExist(int moduleSerial, int questionSerial, String newAnswer) throws SQLException {
		String check = "SELECT AnswerID FROM answer where moduleSerialNum = '" + moduleSerial + "'and QuestionID = '"
				+ questionSerial + "'and AnswerText = '" + newAnswer + "'";
		ResultSet rs = stmt.executeQuery(check);

		if (rs.isBeforeFirst()) {
			rs.close();
			return true;
		} else {
			rs.close();
			return false;
		}
	}

	public void verifyCorrectnessOfAnswersInTest(int moduleSerial, int testSerial, int questionSerial)
			throws SQLException {
		String countTrueValue = "SELECT COUNT(AnswerCorrectness) FROM ( SELECT AnswerCorrectness,TestID,QuestionID FROM modulehastestsandanswers  WHERE moduleSerialNum  = '"
				+ moduleSerial + "' AND TestID = '" + testSerial + "' AND QuestionID = '" + questionSerial
				+ "' AND AnswerCorrectness = '" + 1 + "') as module;";
		ResultSet rs = stmt.executeQuery(countTrueValue);
		rs.next();
		if (rs.getInt(1) > 1) {
			changeAllAnswersToFalse(moduleSerial, testSerial, questionSerial);
			setMoreThanOneAnsCorrectTrueInTest(moduleSerial, testSerial, questionSerial);
		} else if (rs.getInt(1) == 0) {
			changeAllAnswersToFalse(moduleSerial, testSerial, questionSerial);
			setNoneOfTheAnsCorrectTrueInTest(moduleSerial, testSerial, questionSerial);
		}
		rs.close();

	}

	public void addRandomQuestionsToTest(int moduleSerial, int testSerial, int numOfQuestions) throws SQLException {
		int questionIndex = 1;
		int AnswerIndex = 1;
		int numOfAnswers, numOfAnswersForTest;
		int[][] arrayOfRandomQuestions = new int[numOfQuestions][3];
		String getRandomQuestions = "SELECT allQuestions.SerialNum,allQuestions.QuestionType,NumOfAnswers FROM ((select * FROM question WHERE moduleSerialNum ='"
				+ moduleSerial + "') as allQuestions LEFT JOIN (select * FROM americanquestion WHERE moduleSerialNum ='"
				+ moduleSerial
				+ "') AS americanQuestions on allQuestions.SerialNum = americanQuestions.SerialNum )   order by RAND() LIMIT "
				+ numOfQuestions + ";";
		ResultSet rs = stmt.executeQuery(getRandomQuestions);

		while (rs.next()) {
			arrayOfRandomQuestions[questionIndex - 1][0] = rs.getInt("allQuestions.SerialNum");
			arrayOfRandomQuestions[questionIndex - 1][1] = questionIndex;

			numOfAnswers = rs.getInt("NumOfAnswers");

			if (numOfAnswers != 0) {
				numOfAnswersForTest = (int) (Math.random() * (numOfAnswers - 3)) + 4;
				arrayOfRandomQuestions[questionIndex - 1][2] = numOfAnswersForTest;
			} else
				arrayOfRandomQuestions[questionIndex - 1][2] = 1;
			questionIndex++;
		}
		rs.close();

		for (int i = 0; i < numOfQuestions; i++) {

			if (arrayOfRandomQuestions[i][2] > 1) {
				addAmericanQuestionToModuleHasTestAndQuestions(moduleSerial, testSerial, arrayOfRandomQuestions[i][0],
						arrayOfRandomQuestions[i][1], arrayOfRandomQuestions[i][2]);

				addRandomAnswersToTest(moduleSerial, testSerial, arrayOfRandomQuestions[i][0],
						arrayOfRandomQuestions[i][2]);
			} else {
				addOpenQuestionToModuleHasTestAndQuestions(moduleSerial, testSerial, arrayOfRandomQuestions[i][0],
						arrayOfRandomQuestions[i][1]);

			}

		}

	}

	public void addRandomAnswersToTest(int moduleSerial, int testSerial, int questionSerial, int numOfAnswers)
			throws SQLException {
		int answerIndex = 1;
		int[][] arrOfAnswerToBeAddedToTest = new int[numOfAnswers][2];
		String getRandomAnswers = "SELECT AnswerID FROM answer where moduleSerialNum ='" + moduleSerial
				+ "' and QuestionID ='" + questionSerial + "' and AnswerText != '" + NO_ANS_CORRECT
				+ "' and AnswerText != '" + MORE_THAN_ONE_CORRECT + "' ORDER BY RAND() LIMIT " + numOfAnswers + ";";
		ResultSet rs = stmt.executeQuery(getRandomAnswers);
		while (rs.next()) {
			arrOfAnswerToBeAddedToTest[answerIndex - 1][0] = rs.getInt("AnswerID");
			arrOfAnswerToBeAddedToTest[answerIndex - 1][1] = answerIndex;

			answerIndex++;
		}
		rs.close();
		for (int i = 0; i < numOfAnswers; i++) {
			addAnswerToModuleHasTestsAndAns(moduleSerial, testSerial, questionSerial, arrOfAnswerToBeAddedToTest[i][0],
					arrOfAnswerToBeAddedToTest[i][1]);
		}
		verifyCorrectnessOfAnswersInTest(moduleSerial, testSerial, questionSerial);
	}

	public void setMoreThanOneAnsCorrectTrueInTest(int moduleSerial, int testSerial, int questionSerial)
			throws SQLException {
		String update = "UPDATE modulehastestsandanswers SET AnswerCorrectness = (AnswerCorrectness+1) WHERE AnswerID IN (SELECT AnswerID FROM answer WHERE moduleSerialNum = '"
				+ moduleSerial + "' and QuestionID ='" + questionSerial + "' and AnswerText = '" + MORE_THAN_ONE_CORRECT
				+ "');";
		stmt.executeUpdate(update);
	}

	public void setNoneOfTheAnsCorrectTrueInTest(int moduleSerial, int testSerial, int questionSerial)
			throws SQLException {
		String update = "UPDATE modulehastestsandanswers SET AnswerCorrectness = (AnswerCorrectness+1) WHERE AnswerID IN (SELECT AnswerID FROM answer WHERE moduleSerialNum = '"
				+ moduleSerial + "' and QuestionID ='" + questionSerial + "' and AnswerText = '" + NO_ANS_CORRECT
				+ "');";
		stmt.executeUpdate(update);
	}

	public void changeAllAnswersToFalse(int moduleSerial, int testSerial, int questionSerial) throws SQLException {
		String update = "UPDATE modulehastestsandanswers SET AnswerCorrectness = 0 WHERE moduleSerialNum = '"
				+ moduleSerial + "' AND TestID = '" + testSerial + "' AND QuestionID = '" + questionSerial + "';";

		stmt.executeUpdate(update);
	}

	public boolean checkIfQuestionHasMoreThanFourAns(int moduleSerial, int questionSerial) throws SQLException {
		String check = "SELECT NumOfAnswers from americanquestion where moduleSerialNum = '" + moduleSerial
				+ "' and SerialNum = '" + questionSerial + "';";
		ResultSet rs = stmt.executeQuery(check);
		rs.next();

		if (rs.getInt(1) > 4) {
			rs.close();
			return true;
		} else {
			rs.close();
			return false;
		}

	}

	public boolean checkIfPassIsTaken(int password) throws SQLException {
		String getModuleSerials = "SELECT moduleSerialNum FROM operatingsystemmodule;";
		ResultSet rs = stmt.executeQuery(getModuleSerials);
		while (rs.next()) {
			if (rs.getInt("moduleSerialNum") == password)
				return true;
		}
		return false;
	}

	public void UpdateCounter(String counterType, int moduleSerial) throws SQLException {
		if (counterType.equalsIgnoreCase("Open")) {
			String update = "UPDATE operatingsystemmodule SET openQuestionCounter = (openQuestionCounter+2) where moduleSerialNum = '"
					+ moduleSerial + "';";
			stmt.executeUpdate(update);
		} else if (counterType.equalsIgnoreCase("Closed")) {
			String update = "UPDATE operatingsystemmodule SET closeQuestionCounter = (closeQuestionCounter+2) where moduleSerialNum = '"
					+ moduleSerial + "';";
			stmt.executeUpdate(update);
		} else if (counterType.equalsIgnoreCase("Answer")) {
			String update = "UPDATE operatingsystemmodule SET answerCounter = (answerCounter+1) where moduleSerialNum = '"
					+ moduleSerial + "';";
			stmt.executeUpdate(update);
		} else if (counterType.equalsIgnoreCase("Test")) {
			String update = "UPDATE operatingsystemmodule SET testCounter = (testCounter+1) where moduleSerialNum = '"
					+ moduleSerial + "';";
			stmt.executeUpdate(update);
		}
	}

	public OperatingSystemModule retrieveModule(int password) throws SQLException {
		OperatingSystemModule module = null;
		String getModuleSerials = "SELECT * FROM operatingsystemmodule;";
		ResultSet rs = stmt.executeQuery(getModuleSerials);
		while (rs.next()) {
			if (rs.getInt("moduleSerialNum") == password)
				module = new OperatingSystemModule(password, rs.getInt("closeQuestionCounter"),
						rs.getInt("openQuestionCounter"), rs.getInt("testCounter"), rs.getInt("answerCounter"));
		}
		rs.close();
		return module;
	}

	public String showTestSerials(int moduleSerial) throws SQLException {
		StringBuffer sb = new StringBuffer("These are your account tests:\n\n");
		String getSerials = "SELECT TestID,NumOfQuestions FROM test where  moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(getSerials);
		while (rs.next()) {
			sb.append("Test ID: " + rs.getInt("TestID") + " |  Number Of Questions In Test: "
					+ rs.getInt("NumOfQuestions")+"\n");
		}
		rs.close();
		return sb.toString();

	}
	public ArrayList<Integer> getTestSerials(int moduleSerial) throws SQLException{
		ArrayList<Integer> list = new ArrayList<Integer>();
		String getSerials = "SELECT TestID FROM test where moduleSerialNum = '" + moduleSerial + "';";
		ResultSet rs = stmt.executeQuery(getSerials);
		while(rs.next()) {
			list.add(rs.getInt("TestID"));
		}
		rs.close();
		return list;
		
	}
	

	public void closeConnection() {
		try {
			stmt.close();
			connector.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
