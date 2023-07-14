package view;

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
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
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

public class Case4 {
	private int numberOfQuestionsInArray;
	private Stage case4;
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
	private Text questionNumber = new Text("Choose question number:");
	private Text questionNumberError = new Text("You must choose a question number from the list");
	private Text answerNumberError = new Text("You must choose an answer number from the list");
	private Text answerWithSameTextError = new Text("An answer with this text already exists");
	private Text answerTextEmptyError = new Text("Cannot enter a blank answer");
	private Text answerNumber = new Text("Choose answer number:");
	private Text answerText = new Text("Enter new text for answer:");
	private Text changedSuccessfullyPaneText = new Text("Answer changed successully");
	private Text arrayOfQuestions = new Text();
	private TextField answerTextField;
	private Button ok1;
	private Button ok2;
	private Button exit;

	public Case4(Stage case4) {
		this.case4 = case4;
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
		this.answerTextField = new TextField();
        bigBottomPane.setPadding(new Insets(10));
        bigBottomPane.setPadding(new Insets(10));
        centerPane.setPadding(new Insets(10));
		case4.setAlwaysOnTop(true);

		arrayOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		questionNumber.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		answerNumber.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		answerText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		changedSuccessfullyPaneText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
		questionNumberError.setFill(Color.RED);
		answerNumberError.setFill(Color.RED);
		answerWithSameTextError.setFill(Color.RED);
		answerTextEmptyError.setFill(Color.RED);

		questionNum.setVisibleRowCount(4);
		answerNum.setVisibleRowCount(4);

		questionNumberError.setVisible(false);
		answerNumberError.setVisible(false);
		answerWithSameTextError.setVisible(false);
		answerTextEmptyError.setVisible(false);
		answerNumber.setVisible(false);
		answerNum.setVisible(false);
		
        
		StackPane.setAlignment(changedSuccessfullyPaneText, Pos.CENTER);
		StackPane.setAlignment(questionNumber, Pos.TOP_CENTER);
		StackPane.setAlignment(questionNum, Pos.TOP_CENTER);
		StackPane.setAlignment(questionNumberError, Pos.TOP_CENTER);
		StackPane.setAlignment(answerNumber, Pos.CENTER);
		StackPane.setAlignment(answerNum, Pos.CENTER);
		StackPane.setAlignment(answerNumberError, Pos.CENTER);
		StackPane.setAlignment(ok2, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(answerText, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(answerTextField, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(answerWithSameTextError, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(answerTextEmptyError, Pos.BOTTOM_CENTER);

		questionNumberError.setTranslateY(20);
		answerNumberError.setTranslateY(20);
		answerTextField.setTranslateY(-20);
		answerText.setTranslateY(-30);
		answerNumber.setTranslateY(-10);
		answerTextEmptyError.setTranslateY(-10);
		ok1.setTranslateX(-20);
		exit.setTranslateX(20);

		bottomLeftPane.getChildren().add(questionNumber);
		bottomLeftPane.getChildren().add(answerNumber);
		bottomLeftPane.getChildren().add(answerText);
		bottomLeftPane.getChildren().add(answerTextEmptyError);

		bottomRightPane.getChildren().add(questionNum);
		bottomRightPane.getChildren().add(questionNumberError);
		bottomRightPane.getChildren().add(answerNum);
		bottomRightPane.getChildren().add(answerNumberError);
		bottomRightPane.getChildren().add(answerTextField);
		bottomRightPane.getChildren().add(answerWithSameTextError);
		

		centerPane.setContent(arrayOfQuestions);

		mainPane.prefWidthProperty().bind(case4.widthProperty());
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

		mainScene = new Scene(mainPane, 700, 700);
		messageScene = new Scene(messagePane, 500, 500);
		case4.setScene(mainScene);

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
			setAnswerWithSameTextErrorVisible(false);
			setAnswerNumberErrorVisible(false);
			setAnswerTextEmptyErrorVisible(false);

		});
		exit.setOnAction(e -> {
			resetStage();
			case4.close();
		});

	}
	public void addMyEventHandlerForQuestionComboBox(EventHandler<ActionEvent> event) {
		questionNum.setOnAction(event);
	}
	public void addMyEventHandlerForOk1(EventHandler<ActionEvent> event) {
		ok1.setOnAction(event);
	}

	public void showWindow() {
		case4.show();
	}
	public void close() {
		case4.close();
	}

	public void addTextToWindow(String text) {
		arrayOfQuestions.setText(text);
	}

	public ComboBox<Integer> getQuestionNum() {
		return questionNum;
	}

	public void setComboBoxQuestion(int numOfquestions) {
		for (int i = 1; i <= numOfquestions; i++) {
			questionNum.getItems().add(i);
		}
		numberOfQuestionsInArray = (numOfquestions + 1);

	}
	public void setComboBoxAnswer(int numOfquestions) {
		for (int i = 1; i <= numOfquestions; i++) {
			answerNum.getItems().add(i);
		}
		

	}

	public void addToComboBoxQuestion() {
		questionNum.getItems().add(numberOfQuestionsInArray++);
	}

	public ComboBox<Integer> getAnswerNum() {
		return answerNum;
	}

	public TextField getAnswerTextField() {
		return answerTextField;
	}

	public void setQuestionNumberErrorVisible(boolean visibility) {
		questionNumberError.setVisible(visibility);
	}

	public void setAnswerNumberErrorVisible(boolean visibility) {
		answerNumberError.setVisible(visibility);
	}

	public void setAnswerWithSameTextErrorVisible(boolean visibility) {
		answerWithSameTextError.setVisible(visibility);
	}
	public void setAnswerTextEmptyErrorVisible(boolean visibility) {
		answerTextEmptyError.setVisible(visibility);
	}
	public void setAnswerNumberVisible(boolean visibility) {
		answerNumber.setVisible(visibility);
	}
	public void setAnswerNumVisible(boolean visibility) {
		answerNum.setVisible(visibility);
	}


	public void setArrayOfQuestions(Text arrayOfQuestions) {
		this.arrayOfQuestions = arrayOfQuestions;
	}

	public void resetStage() {
		setQuestionNumberErrorVisible(false);
		setAnswerWithSameTextErrorVisible(false);
		setAnswerNumberErrorVisible(false);
		setAnswerNumberVisible(false);
		setAnswerNumVisible(false);
		setAnswerTextEmptyErrorVisible(false);
		answerTextField.clear();
		answerNum.setValue(null);
		questionNum.setValue(null);
		case4.setScene(mainScene);
	}
	public void changeborderPaneScene() {
		case4.setScene(messageScene);
	}
	public void addMyEventHandlerOk2(EventHandler<ActionEvent> event) {
		ok2.setOnAction(event);
	}

}
