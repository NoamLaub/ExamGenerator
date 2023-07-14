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

public class Case3 {
	private Scene messageScene;
	private Scene mainScene;
	private Stage case3;
	private BorderPane mainPane;
	private ScrollPane centerPane;
	private StackPane firstPane;
	private StackPane secondPane;
	private StackPane thirdPane;
	private StackPane fourthPane;
	private StackPane messagePane;
	private VBox bottomPane;
	private Text arrayOfQuestions;
	
	private TextField newWording;
	private Text textForSearchQuestionField = new Text("Choose the number of question");
	private ComboBox<Integer> questionNum;
	private int numberOfQuestionsInArray;

	private Text textForNewQuestionField = new Text("Enter new wording");
	private Text noQuestionFoundError = new Text("Option chose isn't available in the list");
	private Text newWordingAlreadyExistsError = new Text("A question with this wording already Exists,or you left it blank");
	private Text addedSuccessfullyPaneText = new Text("Question changed successully");
	private Button ok1;
	private Button ok2;
	private Button exit;
	

	public Case3(Stage stage) {
		this.case3 = stage;
		this.mainPane = new BorderPane();
		this.centerPane = new ScrollPane();
		this.firstPane = new StackPane();
		this.secondPane = new StackPane();
		this.thirdPane = new StackPane();
		this.fourthPane = new StackPane();
		this.messagePane = new StackPane();
		this.bottomPane = new VBox();
	
		this.newWording = new TextField();
		this.ok1 = new Button("Ok");
		this.ok2 = new Button("Ok");
		this.exit = new Button("Exit");
		this.arrayOfQuestions = new Text();
		this.questionNum = new ComboBox<Integer>();
		case3.setTitle("Update a wording of a question");
		
		case3.setAlwaysOnTop(true);
		centerPane.setPadding(new Insets(10));
		bottomPane.setPadding(new Insets(10));
		
		
		
		
		
		
		bottomPane.setPrefHeight(100);
		centerPane.setPannable(true);
		centerPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		noQuestionFoundError.setFill(Color.RED);
		newWordingAlreadyExistsError.setFill(Color.RED);
		noQuestionFoundError.setVisible(false);
		newWordingAlreadyExistsError.setVisible(false);
		arrayOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		textForSearchQuestionField.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		textForNewQuestionField.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		addedSuccessfullyPaneText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));

		exit.setTranslateX(50);
		ok1.setTranslateX(-50);
		newWordingAlreadyExistsError.setTranslateX(-10);
		StackPane.setAlignment(textForSearchQuestionField, Pos.CENTER_LEFT);
		StackPane.setAlignment(questionNum, Pos.CENTER_LEFT);
		StackPane.setAlignment(noQuestionFoundError, Pos.CENTER_LEFT);
		StackPane.setAlignment(textForNewQuestionField, Pos.CENTER_RIGHT);
		StackPane.setAlignment(newWording, Pos.CENTER_RIGHT);
		StackPane.setAlignment(newWordingAlreadyExistsError, Pos.CENTER_RIGHT);

		StackPane.setAlignment(addedSuccessfullyPaneText, Pos.CENTER);
		StackPane.setAlignment(ok2, Pos.BOTTOM_CENTER);

		questionNum.setMaxWidth(400);
		newWording.setMaxWidth(400);
		questionNum.setVisibleRowCount(4);
	
		
		
		questionNum.setPrefWidth(150);
		newWording.setPrefWidth(150);
		
		

		firstPane.getChildren().add(textForSearchQuestionField);
		firstPane.getChildren().add(textForNewQuestionField);

		secondPane.getChildren().add(questionNum);
		secondPane.getChildren().add(newWording);

		thirdPane.getChildren().add(noQuestionFoundError);
		thirdPane.getChildren().add(newWordingAlreadyExistsError);

		fourthPane.getChildren().add(ok1);
		fourthPane.getChildren().add(exit);

		messagePane.getChildren().add(addedSuccessfullyPaneText);
		messagePane.getChildren().add(ok2);

		bottomPane.getChildren().add(firstPane);
		bottomPane.getChildren().add(secondPane);
		bottomPane.getChildren().add(thirdPane);
		bottomPane.getChildren().add(fourthPane);
		

		centerPane.setContent(arrayOfQuestions);

		mainPane.setCenter(centerPane);
		mainPane.setBottom(bottomPane);

		mainPane.prefWidthProperty().bind(case3.widthProperty());
		centerPane.prefWidthProperty().bind(mainPane.widthProperty());
		firstPane.prefWidthProperty().bind(mainPane.widthProperty());
		
        
		arrayOfQuestions.prefWidth(900);
		arrayOfQuestions.prefHeight(900);
		arrayOfQuestions.minHeight(100);
		arrayOfQuestions.minWidth(100);

	    mainScene = new Scene(mainPane, 800, 800);
		messageScene = new Scene(messagePane, 500, 500);
		case3.setScene(mainScene);

		exit.setOnAction(e -> {
			resetStage();
			case3.close();
		});
		mainPane.setOnMouseClicked(e -> {
			setNoQuestionFoundErrorVisible(false);
			setNewWordingAlreadyExistsErrorVisible(false);

		});
		
		bottomPane.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		       newWording.setPrefWidth(((double)newSceneWidth)/2-20);
		       questionNum.setPrefWidth(((double)newSceneWidth)/2-20);
		       questionNum.setMaxWidth(((double)newSceneWidth)/2-20);
		       newWording.setMaxWidth(((double)newSceneWidth)/2-20);
		      
		    }

			
		});
		

	}
	public ComboBox<Integer> getQuestionNum() {
		return questionNum;
	}

	public void setComboBox(int numOfquestions) {
		for (int i=1; i<=numOfquestions;i++) {
			questionNum.getItems().add(i);
		}
		numberOfQuestionsInArray = (numOfquestions+1);
	}
	public void addToComboBox() {
		questionNum.getItems().add(numberOfQuestionsInArray++);
	}

	public void close() {
		case3.close();
	}

	public void setNoQuestionFoundErrorVisible(boolean visible) {
		noQuestionFoundError.setVisible(visible);
	}

	public void setNewWordingAlreadyExistsErrorVisible(boolean visible) {
		newWordingAlreadyExistsError.setVisible(visible);
	}

	public void addMyEventHandlerOk(EventHandler<ActionEvent> event) {
		ok1.setOnAction(event);
	}

	public void addMyEventHandlerOk2(EventHandler<ActionEvent> event) {
		ok2.setOnAction(event);
	}

	public void addTextToWindow(String text) {
		arrayOfQuestions.setText(text);
	}

	public void showWindow() {
		case3.show();

	}
	

	public TextField getNewWording() {
		return newWording;
	}
	

	public void resetStage() {
		noQuestionFoundError.setVisible(false);
		newWordingAlreadyExistsError.setVisible(false);
		questionNum.setValue(null);
		newWording.clear();
		case3.setScene(mainScene);
	}

	public void changeborderPaneScene() {
		case3.setScene(messageScene);
	}

}
