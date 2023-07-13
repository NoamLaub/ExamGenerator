package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Case7 {
	private Stage case7;
	private Scene mainScene;
	private BorderPane mainPane;
	private ScrollPane testPane;
	private StackPane bottomPane;

	private Text numOfQuestions;
	private Text testCreated;
	private Text theTest;
	private Text testFileError;

	private ComboBox<Integer> chosenNumOfQuestions;
	private Button ok, exit;

	public Case7(Stage case7) {
		this.case7 = case7;
		this.mainPane = new BorderPane();
		this.testPane = new ScrollPane();
		this.bottomPane = new StackPane();
		this.numOfQuestions = new Text(
				"Please choose how many questions you'd like in the test\nPress Ok to confirm.");
		this.testCreated = new Text("Test created successfully!");
		this.theTest = new Text();
		this.testFileError = new Text("Couldn't save test created to a file");
		this.chosenNumOfQuestions = new ComboBox<Integer>();
		this.ok = new Button("Ok");
		this.exit = new Button("Exit");
		this.mainScene = new Scene(mainPane, 800,800);
		mainPane.setPadding(new Insets(10));
		bottomPane.setPadding(new Insets(10));
		testPane.setPadding(new Insets(10));

		testPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		testPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		numOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		testFileError.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		testFileError.setFill(Color.RED);
		theTest.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 12));
		testCreated.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));

		bottomPane.getChildren().add(numOfQuestions);
		bottomPane.getChildren().add(chosenNumOfQuestions);
		bottomPane.getChildren().add(testCreated);
		bottomPane.getChildren().add(testFileError);
		bottomPane.getChildren().add(ok);
		bottomPane.getChildren().add(exit);
		mainPane.setCenter(testPane);
		mainPane.setBottom(bottomPane);
		testCreated.setVisible(false);
		testFileError.setVisible(false);
		theTest.setVisible(false);
		exit.setVisible(false);

		StackPane.setAlignment(ok, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(exit, Pos.BOTTOM_CENTER);

		numOfQuestions.setTranslateY(-50);
		chosenNumOfQuestions.setTranslateY(30);
		testFileError.setTranslateY(50);
		chosenNumOfQuestions.setVisibleRowCount(5);

		bottomPane.setPrefHeight(200);

		case7.setScene(mainScene);
		case7.setAlwaysOnTop(true);
		case7.setTitle("Create Test automatically");

		exit.setOnAction(e -> {
			resetStage();
			case7.close();
		});

	}
   
	public void addmyEventHandlerForOk(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}

	public int getValueChosenNumOfQuestions() {
		if (chosenNumOfQuestions.getValue() == null) {
			return 0;
		}
		return chosenNumOfQuestions.getValue();
	}

	public void showWindow() {
		theTest.setVisible(false);
		testPane.setContent(theTest);
		chosenNumOfQuestions.setValue(null);
		chosenNumOfQuestions.setVisible(true);
		numOfQuestions.setVisible(true);
		testCreated.setVisible(false);
		testFileError.setVisible(false);
		case7.show();
	}

	public void setComboBox(int numberOfQuestions) {
		chosenNumOfQuestions.getItems().clear();
		for (int i = 1; i <= numberOfQuestions; i++) {
			chosenNumOfQuestions.getItems().add(i);
		}

	}

	public void setTestFileErrorVisible() {
		testFileError.setVisible(true);
	}

	public void setTestCreatedSuccessfully() {
		theTest.setVisible(true);
		chosenNumOfQuestions.setVisible(false);
		numOfQuestions.setVisible(false);
		testCreated.setVisible(true);
		ok.setVisible(false);
		exit.setVisible(true);
	}

	public void setTextToWindow(String finishedTest) {
		this.theTest.setText(finishedTest);
		testPane.setContent(theTest);

	}

	public void resetStage() {
		testCreated.setVisible(false);
		testFileError.setVisible(false);
		theTest.setVisible(false);
		theTest.setText(null);
		chosenNumOfQuestions.getItems().clear();
		ok.setVisible(true);
		exit.setVisible(false);
	}

}
