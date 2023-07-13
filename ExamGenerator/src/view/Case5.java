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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Case5 {
	private int numberOfQuestionsInArray;
	private Stage case5;
	private BorderPane mainPane;
	private Scene messageScene;
	private Scene mainScene;

	private ScrollPane centerPane;
	private VBox bigBottomPane;
	private HBox bottomPane;
	private StackPane okExitPane;

	private StackPane bottomLeftPane;
	private StackPane bottomRightPane;
	private StackPane messagePane;

	private ComboBox<Integer> questionNum;
	private ComboBox<Integer> answerNum;
	private Text questionNumber = new Text("Choose question ID:");
	private Text questionNumberError = new Text("You must choose a question ID from the list");
	private Text answerNumberError = new Text("You must choose an answer ID from the list");
	private Text atLeastFourError = new Text("An american question must have at least 4 answers");
	private Text openQuestionError = new Text("Cannot delete answer of an open question");
	private Text answerNumber = new Text("Choose answer ID:");
	private Text changedSuccessfullyPaneText = new Text("Answer deleted successully");
	private Text arrayOfQuestions = new Text();
	private Button ok1;
	private Button ok2;
	private Button exit;

	public Case5(Stage case5) {
		this.case5 = case5;
		this.mainPane = new BorderPane();
		this.centerPane = new ScrollPane();
		this.okExitPane = new StackPane();
		this.bottomLeftPane = new StackPane();
		this.bottomRightPane = new StackPane();
		this.bottomPane = new HBox(bottomLeftPane, bottomRightPane);
		this.bigBottomPane = new VBox(bottomPane,okExitPane);
		this.questionNum = new ComboBox<Integer>();
		this.answerNum = new ComboBox<Integer>();
		this.messagePane = new StackPane();
		this.ok1 = new Button("Ok");
		this.ok2 = new Button("Ok");
		this.exit = new Button("Exit");
		centerPane.setPadding(new Insets(10));		
		bigBottomPane.setPadding(new Insets(10));		

		case5.setAlwaysOnTop(true);

		arrayOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		questionNumber.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		answerNumber.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		changedSuccessfullyPaneText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
		questionNumberError.setFill(Color.RED);
		answerNumberError.setFill(Color.RED);
		openQuestionError.setFill(Color.RED);
		atLeastFourError.setFill(Color.RED);

		questionNum.setVisibleRowCount(4);
		answerNum.setVisibleRowCount(4);

		questionNumberError.setVisible(false);
		answerNumberError.setVisible(false);
		openQuestionError.setVisible(false);
		atLeastFourError.setVisible(false);
	    
		
        
		StackPane.setAlignment(changedSuccessfullyPaneText, Pos.CENTER);
		StackPane.setAlignment(questionNumber, Pos.TOP_CENTER);
		StackPane.setAlignment(questionNum, Pos.TOP_CENTER);
		StackPane.setAlignment(questionNumberError, Pos.TOP_CENTER);
		StackPane.setAlignment(openQuestionError, Pos.TOP_CENTER);
		StackPane.setAlignment(answerNumber, Pos.CENTER);
		StackPane.setAlignment(answerNum, Pos.CENTER);
		StackPane.setAlignment(answerNumberError, Pos.CENTER);
		StackPane.setAlignment(atLeastFourError, Pos.CENTER);
		StackPane.setAlignment(ok2, Pos.BOTTOM_CENTER);
		

		questionNumberError.setTranslateY(25);
		openQuestionError.setTranslateY(40);
		answerNumberError.setTranslateY(20);
		atLeastFourError.setTranslateY(30);
		answerNumber.setTranslateY(-10);
		ok1.setTranslateX(-20);
		exit.setTranslateX(20);

		bottomLeftPane.getChildren().add(questionNumber);
		bottomLeftPane.getChildren().add(answerNumber);
		

		bottomRightPane.getChildren().add(questionNum);
		bottomRightPane.getChildren().add(questionNumberError);
		bottomRightPane.getChildren().add(openQuestionError);
		bottomRightPane.getChildren().add(answerNum);
		bottomRightPane.getChildren().add(answerNumberError);
		bottomRightPane.getChildren().add(atLeastFourError);
		

		centerPane.setContent(arrayOfQuestions);

		mainPane.prefWidthProperty().bind(case5.widthProperty());
		centerPane.prefWidthProperty().bind(mainPane.widthProperty());
		bigBottomPane.prefWidthProperty().bind(mainPane.widthProperty());
		bottomPane.prefWidthProperty().bind(mainPane.widthProperty());
		

		bottomPane.setPrefHeight(150);
		bottomLeftPane.setPrefWidth(400);
		bottomRightPane.setPrefWidth(400);

		centerPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		centerPane.setPannable(true);
		
		okExitPane.getChildren().add(ok1);
		okExitPane.getChildren().add(exit);
		messagePane.getChildren().add(ok2);
		messagePane.getChildren().add(changedSuccessfullyPaneText);

		mainPane.setCenter(centerPane);
		mainPane.setBottom(bigBottomPane);

		mainScene = new Scene(mainPane,800, 800);
		messageScene = new Scene(messagePane, 500, 500);
		case5.setScene(mainScene);
		case5.setTitle("Delete answer");

		mainScene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				bottomLeftPane.setPrefWidth(((double) newSceneWidth) / 2);
				bottomRightPane.setPrefWidth(((double) newSceneWidth) / 2);
				bottomLeftPane.setMaxWidth(((double) newSceneWidth) / 2);
				bottomRightPane.setMaxWidth(((double) newSceneWidth) / 2);

			}

		});
		mainScene.setOnMouseClicked(e -> {
			setQuestionNumberErrorVisible(false);
			setAnswerNumberErrorVisible(false);
			setOpenQuestionErrorVisible(false);
			setAtLeastFourErrorVisible(false);

		});
		
		
		exit.setOnAction(e -> {
			resetStage();
			case5.close();
		});

	}
	public void addMyEventHandlerForQuestionComboBox(EventHandler<ActionEvent> event) {
		questionNum.setOnAction(event);
	}
	public void addMyEventHandlerForOk1(EventHandler<ActionEvent> event) {
		ok1.setOnAction(event);
	}

	public void showWindow() {
		case5.show();
	}
	public void close() {
		case5.close();
	}

	public void addTextToWindow(String text) {
		arrayOfQuestions.setText(text);
	}

	public ComboBox<Integer> getQuestionNum() {
		return questionNum;
	}

	public void setComboBoxQuestion(ArrayList<Integer> list) {
		questionNum.getItems().clear();
		for (int i=0; i<list.size();i++) {
			questionNum.getItems().add(list.get(i));
		}
	}
	public void setComboBoxAnswer(ArrayList<Integer> arr) {
		for (int i = 0; i < arr.size(); i++) {
			answerNum.getItems().add(arr.get(i));
		}
	}

	public void addToComboBoxQuestion(int questionSerial) {
		questionNum.getItems().add(questionSerial);
	}
	
	public ComboBox<Integer> getAnswerNum() {
		return answerNum;
	}

	

	public void setQuestionNumberErrorVisible(boolean visibility) {
		questionNumberError.setVisible(visibility);
	}

	public void setAnswerNumberErrorVisible(boolean visibility) {
		answerNumberError.setVisible(visibility);
	}

	public void setAtLeastFourErrorVisible(boolean visibility) {
		atLeastFourError.setVisible(visibility);
	}
	public void setOpenQuestionErrorVisible(boolean visibility) {
		openQuestionError.setVisible(visibility);
	}

	


	public void setArrayOfQuestions(Text arrayOfQuestions) {
		this.arrayOfQuestions = arrayOfQuestions;
	}

	public void resetStage() {
		setQuestionNumberErrorVisible(false);
		setAtLeastFourErrorVisible(false);
		setAnswerNumberErrorVisible(false);
		setOpenQuestionErrorVisible(false);
		answerNum.setValue(null);
		questionNum.setValue(null);
		case5.setScene(mainScene);
	}
	public void changeborderPaneScene() {
		case5.setScene(messageScene);
	}
	public void addMyEventHandlerOk2(EventHandler<ActionEvent> event) {
		ok2.setOnAction(event);
	}
	public void addMyEventHandlerForClose(EventHandler<WindowEvent> event) {
		case5.setOnCloseRequest(event);
	}
}


