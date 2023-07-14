package view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Case6 {
	private Stage case6;
	private Scene mainScene;
	private Scene testScene;
	private StackPane messagePane;
	private StackPane testCreatedPane;
	private BorderPane mainPane;
	private ScrollPane centerPane, mainbottomPane,testCreatedScrollPane;
	private StackPane bottomUpperPane;
	private StackPane bottomLowerPane;
	private HBox bottomCenterPane;
	private VBox leftHboxPane, rightHboxPane, mainVboxBottomPane;
	private ArrayList<Text> answerTextArray;
	private ArrayList<Text> answerTextErrorArray;
	private ArrayList<ComboBox<Integer>> answerComboArray;
	private int numberOfQuestionsInTest;
	private int currentNumOfQuestions = 1;
	private int numberOfOptionsInComboBox;

	private Text arrayOfQuestions;
	private Text createdTest;
	private Text testCreated = new Text("Test created successully");
	private Text questionAdded = new Text("Question Added successully");
	private Text chooseNumOfQuestions = new Text("Choose the desired amount of questions in test");
	private Text chooseNumOfAnswers = new Text("Choose the desired amount of answers for this question:");
	private Text chooseQuestion = new Text("Choose the question number from the list, press ok to confirm ("
			+ currentNumOfQuestions + " of " + numberOfQuestionsInTest + ")");
	private Text chooseAnswer1 = new Text("Choose an answer");
	private Text chooseAnswer2 = new Text("Choose an answer");
	private Text chooseAnswer3 = new Text("Choose an answer");
	private Text chooseAnswer4 = new Text("Choose an answer");
	private Text chooseAnswer5 = new Text("Choose an answer");
	private Text chooseAnswer6 = new Text("Choose an answer");
	private Text chooseAnswer7 = new Text("Choose an answer");
	private Text chooseAnswer8 = new Text("Choose an answer");
	private Text chooseAnswer9 = new Text("Choose an answer");
	private Text chooseAnswer10 = new Text("Choose an answer");

	private Text questionError = new Text("You must choose a question");

	private Text errorAnswer1 = new Text("You must choose an option");
	private Text errorAnswer2 = new Text("You must choose an option");
	private Text errorAnswer3 = new Text("You must choose an option");
	private Text errorAnswer4 = new Text("You must choose an option");
	private Text errorAnswer5 = new Text("You must choose an option");
	private Text errorAnswer6 = new Text("You must choose an option");
	private Text errorAnswer7 = new Text("You must choose an option");
	private Text errorAnswer8 = new Text("You must choose an option");
	private Text errorAnswer9 = new Text("You must choose an option");
	private Text errorAnswer10 = new Text("You must choose an option");
	private Text numberOfQuestionsError = new Text("You must choose how many questions you'd like in the test");
	private Text numberOfAnswersError = new Text("You must choose how many answers you'd like for this question");
	private Text saveTestError = new Text("Couldn't save test to a file");

	private ComboBox<Integer> numOfQuestionsInTest;
	private ComboBox<Integer> numOfQuestion;
	private ComboBox<Integer> numOfAnswers;
	private ComboBox<Integer> answer1;
	private ComboBox<Integer> answer2;
	private ComboBox<Integer> answer3;
	private ComboBox<Integer> answer4;
	private ComboBox<Integer> answer5;
	private ComboBox<Integer> answer6;
	private ComboBox<Integer> answer7;
	private ComboBox<Integer> answer8;
	private ComboBox<Integer> answer9;
	private ComboBox<Integer> answer10;

	private Button ok1, ok2, ok3, exit,exit2;
	private boolean testCreatedAlready;

	public Case6(Stage case6) {
		this.case6 = case6;
		case6.setTitle("Create a test manually");
		this.mainPane = new BorderPane();
		this.centerPane = new ScrollPane();
		this.messagePane = new StackPane();
		this.bottomUpperPane = new StackPane();
		this.bottomLowerPane = new StackPane();
		this.leftHboxPane = new VBox();
		this.rightHboxPane = new VBox();
		this.bottomCenterPane = new HBox(leftHboxPane, rightHboxPane);
		this.mainbottomPane = new ScrollPane(bottomCenterPane);
		this.mainVboxBottomPane = new VBox(bottomUpperPane, mainbottomPane, bottomLowerPane);
		this.testCreatedScrollPane = new ScrollPane();
		this.answer1 = new ComboBox<Integer>();
		this.answer2 = new ComboBox<Integer>();
		this.answer3 = new ComboBox<Integer>();
		this.answer4 = new ComboBox<Integer>();
		this.answer5 = new ComboBox<Integer>();
		this.answer6 = new ComboBox<Integer>();
		this.answer7 = new ComboBox<Integer>();
		this.answer8 = new ComboBox<Integer>();
		this.answer9 = new ComboBox<Integer>();
		this.answer10 = new ComboBox<Integer>();
		this.numOfAnswers = new ComboBox<Integer>();
		this.numOfQuestionsInTest = new ComboBox<Integer>();
		this.numOfQuestion = new ComboBox<Integer>();
		this.ok1 = new Button("Ok");
		this.ok2 = new Button("Ok");
		this.ok3 = new Button("Ok");
		this.exit = new Button("Exit");
		this.exit2 = new Button("Exit");
		this.answerTextArray = new ArrayList<Text>();
		this.answerComboArray = new ArrayList<ComboBox<Integer>>();
		this.answerTextErrorArray = new ArrayList<Text>();
		this.testCreatedAlready = false;
		arrayOfQuestions = new Text();
		createdTest = new Text();
		testCreatedScrollPane.setContent(createdTest);
		this.testCreatedPane = new StackPane(testCreatedScrollPane,testCreated,ok3,exit2,saveTestError);
		testCreatedPane.setPadding(new Insets(10));
		StackPane.setAlignment(createdTest, Pos.TOP_CENTER);
		StackPane.setAlignment(testCreated, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(saveTestError, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(ok3, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(exit2, Pos.BOTTOM_CENTER);
		exit2.setTranslateX(20);
		ok3.setTranslateX(-20);
		testCreated.setTranslateY(-50);
		saveTestError.setTranslateY(-40);
		case6.setAlwaysOnTop(true);
		

		
		

		mainVboxBottomPane.setPadding(new Insets(10));
		leftHboxPane.setPadding(new Insets(10));
		rightHboxPane.setPadding(new Insets(10));
		bottomUpperPane.setPadding(new Insets(10));
		mainbottomPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		mainbottomPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		testCreatedScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		centerPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		centerPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(mainVboxBottomPane);
		bottomLowerPane.getChildren().add(ok1);
		bottomLowerPane.getChildren().add(ok2);
		bottomLowerPane.getChildren().add(exit);
		centerPane.setContent(arrayOfQuestions);
		mainScene = new Scene(mainPane, 700, 800);
		testScene = new Scene(testCreatedPane, 700, 800);
		

		answerTextArray.add(chooseAnswer1);
		answerTextArray.add(chooseAnswer2);
		answerTextArray.add(chooseAnswer3);
		answerTextArray.add(chooseAnswer4);
		answerTextArray.add(chooseAnswer5);
		answerTextArray.add(chooseAnswer6);
		answerTextArray.add(chooseAnswer7);
		answerTextArray.add(chooseAnswer8);
		answerTextArray.add(chooseAnswer9);
		answerTextArray.add(chooseAnswer10);

		answerComboArray.add(answer1);
		answerComboArray.add(answer2);
		answerComboArray.add(answer3);
		answerComboArray.add(answer4);
		answerComboArray.add(answer5);
		answerComboArray.add(answer6);
		answerComboArray.add(answer7);
		answerComboArray.add(answer8);
		answerComboArray.add(answer9);
		answerComboArray.add(answer10);

		answerTextErrorArray.add(errorAnswer1);
		answerTextErrorArray.add(errorAnswer2);
		answerTextErrorArray.add(errorAnswer3);
		answerTextErrorArray.add(errorAnswer4);
		answerTextErrorArray.add(errorAnswer5);
		answerTextErrorArray.add(errorAnswer6);
		answerTextErrorArray.add(errorAnswer7);
		answerTextErrorArray.add(errorAnswer8);
		answerTextErrorArray.add(errorAnswer9);
		answerTextErrorArray.add(errorAnswer10);

		arrayOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		createdTest.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		saveTestError.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		testCreated.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
		questionAdded.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
		chooseNumOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		chooseNumOfAnswers.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		chooseQuestion.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		questionError.setFill(Color.RED);
		saveTestError.setFill(Color.RED);
		numberOfAnswersError.setFill(Color.RED);
		numberOfQuestionsError.setFill(Color.RED);
		questionError.setVisible(false);
		numberOfAnswersError.setVisible(false);
		numberOfQuestionsError.setVisible(false);
		numOfQuestion.setVisible(false);
		chooseQuestion.setVisible(false);
		chooseNumOfAnswers.setVisible(false);
		numOfAnswers.setVisible(false);
		ok2.setVisible(false);
		saveTestError.setVisible(false);
		bottomUpperPane.getChildren().add(chooseNumOfQuestions);
		bottomUpperPane.getChildren().add(numOfQuestionsInTest);
		bottomUpperPane.getChildren().add(chooseQuestion);
		bottomUpperPane.getChildren().add(numOfQuestion);
		bottomUpperPane.getChildren().add(questionError);
		bottomUpperPane.getChildren().add(chooseNumOfAnswers);
		bottomUpperPane.getChildren().add(numOfAnswers);
		bottomUpperPane.getChildren().add(numberOfAnswersError);
		bottomUpperPane.getChildren().add(numberOfQuestionsError);
		messagePane.getChildren().add(questionAdded);
		
		chooseNumOfQuestions.setTranslateY(-20);
		chooseQuestion.setTranslateY(-30);
		numOfQuestionsInTest.setTranslateY(5);
		numberOfQuestionsError.setTranslateY(25);
		questionError.setTranslateY(25);
		chooseNumOfAnswers.setTranslateY(35);
		chooseNumOfAnswers.setTranslateX(-150);
		numOfAnswers.setTranslateY(35);
		numberOfAnswersError.setTranslateY(55);
		numOfAnswers.setTranslateX(100);
		exit.setTranslateX(20);
		exit.setTranslateY(5);
		ok1.setTranslateY(5);
		ok1.setTranslateX(-20);
		ok2.setTranslateY(5);
		ok2.setTranslateX(-20);
	
		
		for (int i = 0; i < answerTextArray.size(); i++) {
			int currentComboBox = i;
			answerTextArray.get(i).setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
			answerTextArray.get(i).setVisible(false);
			answerComboArray.get(i).setVisible(false);
			answerTextErrorArray.get(i).setVisible(false);
			answerComboArray.get(i).setPrefWidth(50);
			answerComboArray.get(i).setVisibleRowCount(4);
			answerComboArray.get(i).setOnAction(e ->{
			if (answerComboArray.get(currentComboBox).getValue() != null) {
            int removeNum =answerComboArray.get(currentComboBox).getValue();
			for (int j =0; j<answerComboArray.size() ;j++) {
				if (j != currentComboBox) {
					removeItem(answerComboArray.get(j), removeNum);
				}
			}
			}});
			answerTextErrorArray.get(i).setFill(Color.RED);
			answerTextArray.get(i).setTextAlignment(TextAlignment.CENTER);
			answerTextErrorArray.get(i).setTextAlignment(TextAlignment.CENTER);
			if ((i % 2) == 0) {
				leftHboxPane.getChildren().add(answerTextArray.get(i));
				leftHboxPane.getChildren().add(answerComboArray.get(i));
				leftHboxPane.getChildren().add(answerTextErrorArray.get(i));
			} else {
				rightHboxPane.getChildren().add(answerTextArray.get(i));
				rightHboxPane.getChildren().add(answerComboArray.get(i));
				rightHboxPane.getChildren().add(answerTextErrorArray.get(i));
			}
		}

		// rightHboxPane.setTranslateX(25);
		mainVboxBottomPane.setPrefHeight(400);
		bottomUpperPane.setPrefHeight(150);
		bottomCenterPane.setPrefHeight(250);

		mainPane.prefWidthProperty().bind(case6.widthProperty());
		centerPane.prefWidthProperty().bind(mainPane.widthProperty());
		mainVboxBottomPane.prefWidthProperty().bind(mainPane.widthProperty());
		bottomCenterPane.prefWidthProperty().bind(mainPane.widthProperty());

		mainVboxBottomPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				leftHboxPane.setPrefWidth(((double) newSceneWidth) / 2 - 10);
				rightHboxPane.setPrefWidth(((double) newSceneWidth) / 2 - 10);
				// rightHboxPane.setTranslateX((double) newSceneWidth-);
				leftHboxPane.setMaxWidth(((double) newSceneWidth) / 2 - 10);
				rightHboxPane.setMaxWidth(((double) newSceneWidth) / 2 - 10);
				for (int i = 0; i < answerTextArray.size(); i++) {
					answerComboArray.get(i).setPrefWidth((double) newSceneWidth / 2 - 10);
				}

			}

		});

		case6.setScene(mainScene);
		

		numOfQuestionsInTest.setOnAction(e -> {
			numberOfQuestionsError.setVisible(false);
			changeToChooseQuestionNumBottomUpperPaneVisibility(true);
			numberOfQuestionsInTest =getNumOfQuestionsInTest() ;
			chooseQuestion.setText("Choose the question number from the list, press ok to confirm ("
					+ currentNumOfQuestions + " of " + numberOfQuestionsInTest + ")");
		});
		numOfAnswers.setOnAction(e -> {
			if (numOfAnswers.getValue() != null) {
			int selectedNumOfAnswers =numOfAnswers.getValue();
			for (int i = 0; i < selectedNumOfAnswers; i++) {
				answerTextArray.get(i).setVisible(true);
				answerComboArray.get(i).setVisible(true);
				
			}
			for (int j = selectedNumOfAnswers; j < answerTextArray.size(); j++) {
				answerTextArray.get(j).setVisible(false);
				answerComboArray.get(j).setVisible(false);
			}
			
		}});
		ok2.setOnAction(e -> {
			changeToChooseQuestionNumBottomUpperPaneVisibility(true);
		});
		mainScene.setOnMouseClicked(e -> {
			numOfQuestionsInTest.setStyle(null);
			numOfQuestion.setStyle(null);
			questionError.setVisible(false);
			numberOfAnswersError.setVisible(false);
			numberOfQuestionsError.setVisible(false);
			saveTestError.setVisible(false);
			for (Text i : answerTextErrorArray) {
			    i.setVisible(false);
			    i.setStyle(null);
			}

		});
		ok3.setOnAction(e -> {
			clearStage();
			case6.close();
		});
		
		

	}
	public boolean isTestCreatedAlready() {
		return testCreatedAlready;
	}
	public void setTestCreatedAlready(boolean testCreatedAlready) {
		this.testCreatedAlready = testCreatedAlready;
	}
	public Text getQuestionError() {
		return questionError;
	}
	public void addMyEventHandlerToExit(EventHandler<ActionEvent> event) {
		exit.setOnAction(event);
	}
	public void showWindow() {
		chooseNumOfQuestions.setVisible(true);
		case6.show();
	}
	public void addToCurrentNumOfQuestions() {
		currentNumOfQuestions++;
		chooseQuestion.setText("Choose the question number from the list, press ok to confirm ("
				+ currentNumOfQuestions + " of " + numberOfQuestionsInTest + ")");
		
	}
	public void setSaveTestErrorVisible() {
		saveTestError.setVisible(true);
	}
	public void setQuestionErrorVisible() {
		questionError.setVisible(true);
		numOfQuestion.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
	}
	public int getCurrentNumOfQuestions() {
		return currentNumOfQuestions;
	}
	public void setNumberOfAnswersErrorVisible(boolean visible) {
		numberOfAnswersError.setVisible(visible);
		
	}
	public void setNumberOfQuestionsErrorVisible( ) {
		numberOfQuestionsError.setVisible(true);
		numOfQuestionsInTest.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
	}
	public void setChooseNumOfAnswers(boolean visible) {
		chooseNumOfAnswers.setVisible(visible);
		
	}
	public int getValueOfAnswerBox(int index) {
		return (answerComboArray.get(index).getValue()-1);
	}

	public void setNumOfAnswers(boolean visible) {
		numOfAnswers.setVisible(visible);
	}
	public void setValuesfornumOfAnswersForBoxArray(int numberOfAnswers) {
		for (int j=1;j <= numberOfAnswers; j++) {
			for (ComboBox<Integer> c : answerComboArray) {
				if (j==1) {
					c.getItems().clear();
				}
				c.getItems().add(j);
			}
		}
	}
	public void setValuesfornumOfAnswersForMainBox(int numberOfAnswers) {
		setValuesfornumOfAnswersForBoxArray(numberOfAnswers);
		numOfAnswers.getItems().clear();
		for (int i = 4; i <= numberOfAnswers; i++) {
			numOfAnswers.getItems().add(i);
		}
		
	}
	public boolean checkThatAllOptionsAreFilled() {
		boolean allFilled = true;
		for (int  i =0 ;i< answerComboArray.size();i++) {
			if (answerComboArray.get(i).isVisible() && answerComboArray.get(i).getValue() == null) {
				answerTextErrorArray.get(i).setVisible(true);
				allFilled = false;
			}
		}
		return allFilled;
	}
	public void clearNumOfAnswers() {
		numOfAnswers.getItems().clear();
	}

	public int  getNumOfAnswers() {
		if (numOfAnswers.getValue() == null) {
			return 0;
		}
		return numOfAnswers.getValue();
	}
	public void setNumberOfQuestionsInDataBase(int numberOfQuestions) {
		numOfQuestionsInTest.getItems().clear();
		numOfQuestion.getItems().clear();
		for (int i = 1; i <= numberOfQuestions; i++) {
			numOfQuestionsInTest.getItems().add(i);
			numOfQuestion.getItems().add(i);
			numberOfOptionsInComboBox = (numberOfQuestions + 1);
		}
	}
	

	public void addToComboBox() {
		numOfQuestionsInTest.getItems().add(numberOfOptionsInComboBox++);
		numOfQuestion.getItems().add(numberOfOptionsInComboBox++);
	}

	public void addTextToWindow(String text) {
		arrayOfQuestions.setText(text);
	}

	public void changeToChooseQuestionNumBottomUpperPaneVisibility(boolean secondVisible) {
		clearBottomCenterPane();
		numOfQuestion.setValue(null);
		ok2.setVisible(false);
		ok1.setVisible(true);
		numOfQuestion.setVisible(secondVisible);
		chooseQuestion.setVisible(secondVisible);
		numOfQuestionsInTest.setVisible(!secondVisible);
		chooseNumOfQuestions.setVisible(!secondVisible);
		questionAdded.setVisible(false);
		bottomCenterPane.setVisible(true);
		bottomUpperPane.setVisible(true);
		mainPane.setCenter(centerPane);

	}
	public void addMyEvenHandlerForQuestionNumComboBox(EventHandler<ActionEvent> event) {
		numOfQuestion.setOnAction(event);
		
	}
	public void addMyEvenHandlerForOk1(EventHandler<ActionEvent> event) {
		ok1.setOnAction(event);
	}
	public int  getNumOfQuestionsInTest() {
		if (numOfQuestionsInTest.getValue() == null) {
			return 0;
		}
		return numOfQuestionsInTest.getValue();
	}
	public boolean isNumOfQuestionsInTestVisible() {
		return numOfQuestionsInTest.isVisible();
	}
	public boolean isNumOfQuestionVisible() {
		return numOfQuestion.isVisible();
	}
	public int getNumOfQuestion() {
		if (numOfQuestion.getValue() == null) {
			return 0;
		}
		return numOfQuestion.getValue();
	}
	public void clearBottomCenterPane() {
		numOfAnswers.setVisible(false);
		chooseNumOfAnswers.setVisible(false);
		for (int i =0;i< answerTextArray.size();i++) {
			answerTextArray.get(i).setVisible(false);
			answerComboArray.get(i).setVisible(false);
			answerComboArray.get(i).getItems().clear();
			answerTextErrorArray.get(i).setVisible(false);
			
		}
	}
	public void removeItem(ComboBox<Integer> i, int removeNum) {
		for (int j=0; j<i.getItems().size();j++) {
			if (i.getItems().get(j) == removeNum) {
				i.getItems().remove(j);
			}
		}
	}
	public void removeItemFromQuestionNum(int removeNum) {
		for (int j=0; j<numOfQuestion.getItems().size();j++) {
			if (numOfQuestion.getItems().get(j) == removeNum) {
				numOfQuestion.getItems().remove(j);
			}
		}
	}

	
	public void setMessagePane(boolean forQuestionAdded) {
		ok1.setVisible(false);
		ok2.setVisible(true);
		questionAdded.setVisible(true);
		mainPane.setCenter(messagePane);
		clearBottomCenterPane();
		bottomCenterPane.setVisible(false);
		bottomUpperPane.setVisible(false);
	}
	public void setCreatedTestPane(String Test) {
		createdTest.setText(Test);
		case6.setScene(testScene);
	
	}
	public void clearStage() {
		setTestCreatedAlready(false);
		ok2.setVisible(false);
		ok1.setVisible(true);
		numOfQuestionsInTest.setValue(null);
		numOfQuestionsInTest.setVisible(true);
		numOfQuestion.setVisible(false);
		chooseQuestion.setVisible(false);
		currentNumOfQuestions =1;
		bottomCenterPane.setVisible(true);
		bottomUpperPane.setVisible(true);
		setNumberOfQuestionsInDataBase(numberOfOptionsInComboBox-1);
		case6.setScene(mainScene);
		}
	public void close() {
		case6.close();
	}

}
