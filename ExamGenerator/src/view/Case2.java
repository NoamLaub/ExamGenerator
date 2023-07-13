package view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Case2 {
	private boolean okVisibility = true;
	private int selectedIndex;

	private Stage case2;
	private Button ok = new Button("Ok");
	private Button ok2 = new Button("Ok");
	private Button Exit = new Button("Exit");
	private HBox buttonHbox = new HBox(ok, Exit);

	private ScrollBar scroll = new ScrollBar();
	private ArrayList<TextField> answerTextFieldArray = new ArrayList<TextField>();
	private ArrayList<Text> answerTextErrorArray = new ArrayList<Text>();
	private ArrayList<CheckBox> cBoxArray = new ArrayList<CheckBox>();
	private TextField questionTextField = new TextField();
	private TextField answerTextField1 = new TextField();
	private TextField answerTextField2 = new TextField();
	private TextField answerTextField3 = new TextField();
	private TextField answerTextField4 = new TextField();
	private TextField answerTextField5 = new TextField();
	private TextField answerTextField6 = new TextField();
	private TextField answerTextField7 = new TextField();
	private TextField answerTextField8 = new TextField();
	private TextField answerTextField9 = new TextField();
	private TextField answerTextField10 = new TextField();
	private Text addedSuccessfullyPaneText = new Text("Question Added successully");
	private Text chooseNumOfAnswers = new Text("Please choose answers quantity for the question:");

	private Text answerError1 = new Text("Multiple answers with this wording");
	private Text answerError2 = new Text("Multiple answers with this wording");
	private Text answerError3 = new Text("Multiple answers with this wording");
	private Text answerError4 = new Text("Multiple answers with this wording");
	private Text answerError5 = new Text("Multiple answers with this wording");
	private Text answerError6 = new Text("Multiple answers with this wording");
	private Text answerError7 = new Text("Multiple answers with this wording");
	private Text answerError8 = new Text("Multiple answers with this wording");
	private Text answerError9 = new Text("Multiple answers with this wording");
	private Text answerError10 = new Text("Multiple answers with this wording");
	private Text questionBlankError = new Text("You must enter a text for your question");
	private Text answerBlankError = new Text("You must enter a text for your answer/s");
	private Text questionTextError = new Text("Question with this text already exists");

	private Text radioButtonError = new Text("Please choose the Type of question");
	private Text notEnoughAnswerError = new Text("Please choose how many answers you'd like and fill all of the chosen answer's fields");

	private CheckBox checkBox1 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox2 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox3 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox4 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox5 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox6 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox7 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox8 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox9 = new CheckBox("Answer is true if selected");
	private CheckBox checkBox10 = new CheckBox("Answer is true if selected");

	private ComboBox<Object> numberOfAnswers = new ComboBox<Object>();

	private BorderPane pane;
	private StackPane stackPane;
	private ScrollPane scrollPane;
	private StackPane messagePane;

	private ToggleGroup group = new ToggleGroup();
	private RadioButton radioButton1 = new RadioButton("Open question");
	private RadioButton radioButton2 = new RadioButton("American question");
	private VBox root;
	private VBox checkBox;

	public Case2(Stage stage) {
		this.case2 = stage;
		this.pane = new BorderPane();
		this.stackPane = new StackPane();
		this.scrollPane = new ScrollPane();
		this.root = new VBox(10);
		this.checkBox = new VBox(10);
		this.messagePane = new StackPane();
		scrollPane.setPadding(new Insets(10));
		stackPane.setPadding(new Insets(10));
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		Exit.setTranslateX(20);
		ok.setTranslateX(-20);

		root.setPadding(new Insets(10));
		checkBox.setPadding(new Insets(10));
		// root.setFillWidth(true);
		checkBox.setFillWidth(true);

		ok2.setVisible(false);
		numberOfAnswers.getItems().addAll(4, 5, 6, 7, 8, 9, 10);

		radioButtonError.setFill(Color.RED);
		questionTextError.setFill(Color.RED);
		questionTextError.setFill(Color.RED);
		notEnoughAnswerError.setFill(Color.RED);
		questionBlankError.setFill(Color.RED);
		answerBlankError.setFill(Color.RED);

		answerTextErrorArray.add(answerError1);
		answerTextErrorArray.add(answerError2);
		answerTextErrorArray.add(answerError3);
		answerTextErrorArray.add(answerError4);
		answerTextErrorArray.add(answerError5);
		answerTextErrorArray.add(answerError6);
		answerTextErrorArray.add(answerError7);
		answerTextErrorArray.add(answerError8);
		answerTextErrorArray.add(answerError9);
		answerTextErrorArray.add(answerError10);

		answerTextFieldArray.add(answerTextField1);
		answerTextFieldArray.add(answerTextField2);
		answerTextFieldArray.add(answerTextField3);
		answerTextFieldArray.add(answerTextField4);
		answerTextFieldArray.add(answerTextField5);
		answerTextFieldArray.add(answerTextField6);
		answerTextFieldArray.add(answerTextField7);
		answerTextFieldArray.add(answerTextField8);
		answerTextFieldArray.add(answerTextField9);
		answerTextFieldArray.add(answerTextField10);

		cBoxArray.add(checkBox1);
		cBoxArray.add(checkBox2);
		cBoxArray.add(checkBox3);
		cBoxArray.add(checkBox4);
		cBoxArray.add(checkBox5);
		cBoxArray.add(checkBox6);
		cBoxArray.add(checkBox7);
		cBoxArray.add(checkBox8);
		cBoxArray.add(checkBox9);
		cBoxArray.add(checkBox10);

		addedSuccessfullyPaneText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
		chooseNumOfAnswers.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 13));
		questionTextError.setVisible(false);
		chooseNumOfAnswers.setVisible(false);
		numberOfAnswers.setVisible(false);

		answerTextField1.setVisible(false);
		answerTextField1.setPromptText("Enter answer text here...");
		questionTextField.setPromptText("Enter Question text here...");
		checkBox1.setVisible(false);
		answerError1.setVisible(false);
		radioButtonError.setVisible(false);
		notEnoughAnswerError.setVisible(false);
		questionBlankError.setVisible(false);
		answerBlankError.setVisible(false);

		messagePane.getChildren().add(addedSuccessfullyPaneText);
		root.setPadding(new Insets(10));
		root.getChildren().add(questionTextField);
		root.getChildren().add(questionTextError);
		root.getChildren().add(questionBlankError);
		root.getChildren().add(answerBlankError);
		root.getChildren().add(radioButton1);
		root.getChildren().add(radioButton2);
		root.getChildren().add(radioButtonError);
		root.getChildren().add(chooseNumOfAnswers);
		root.getChildren().add(numberOfAnswers);
		root.getChildren().add(notEnoughAnswerError);
		radioButton1.setToggleGroup(group);
		radioButton2.setToggleGroup(group);
		scrollPane.prefWidthProperty().bind(pane.widthProperty());
		root.prefWidthProperty().bind(scrollPane.widthProperty());

		for (int j = 0; j < answerTextErrorArray.size(); j++) {
			answerTextErrorArray.get(j).setFill(Color.RED);
			answerTextFieldArray.get(j).setPromptText("Enter answer text here...");
			answerTextFieldArray.get(j).setVisible(false);
			cBoxArray.get(j).setVisible(false);
			answerTextErrorArray.get(j).setVisible(false);

		}

		for (int g = 0; g < answerTextFieldArray.size(); g++) {

			root.getChildren().add(answerTextFieldArray.get(g));
			root.getChildren().add(cBoxArray.get(g));
			root.getChildren().add(answerTextErrorArray.get(g));
		}
		scrollPane.setContent(root);
		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		buttonHbox.setAlignment(Pos.BASELINE_CENTER);
		stackPane.getChildren().add(buttonHbox);
		stackPane.getChildren().add(ok2);
		case2.setTitle("Add a Question");
		pane.setCenter(scrollPane);
		pane.setBottom(stackPane);
		Scene scene = new Scene(pane, 500, 500);

		case2.setScene(scene);
		case2.setAlwaysOnTop(true);

		radioButton1.setOnAction(e -> {
			chooseNumOfAnswers.setVisible(false);
			numberOfAnswers.setVisible(false);
			answerTextField1.setVisible(true);
			checkBox1.setVisible(true);
			for (int i = 1; i < answerTextFieldArray.size(); i++) {
				answerTextFieldArray.get(i).setVisible(false);
				cBoxArray.get(i).setVisible(false);

			}
		});
		radioButton2.setOnAction(e -> {
			chooseNumOfAnswers.setVisible(true);
			numberOfAnswers.setVisible(true);
			answerTextField1.setVisible(true);
			checkBox1.setVisible(true);

		});
		numberOfAnswers.setOnAction(e -> {
			if (numberOfAnswers.getSelectionModel().getSelectedItem() != null) {
				selectedIndex = (int) numberOfAnswers.getSelectionModel().getSelectedItem();
			}
			;
			for (int i = 1; i < selectedIndex; i++) {
				answerTextFieldArray.get(i).setVisible(true);
				cBoxArray.get(i).setVisible(true);
			}
			for (int j = selectedIndex; j < answerTextFieldArray.size(); j++) {
				answerTextFieldArray.get(j).setVisible(false);
				cBoxArray.get(j).setVisible(false);
			}
		});
		root.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				questionTextField.setPrefWidth(((double) newSceneWidth - 50));
				questionTextField.setMaxWidth(((double) newSceneWidth - 50));
				for (TextField i : answerTextFieldArray) {
					i.setPrefWidth(((double) newSceneWidth - 50));
					i.setMaxWidth(((double) newSceneWidth - 50));
				}

			}

		});

		Exit.setOnAction(e -> {
			resetStage();
			close();
		});
		pane.setOnMouseClicked(e -> {
			setQuestionBlankErrorVisible(false);
			setAnswerBlankErrorvisible(false);
			setRadioButtonErrorVisible(false);
			setNotEnoughAnswersErrorVisible(false);
			setAnswerErrorTofalse();
			questionTextError.setVisible(false);

		});

	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public ArrayList<CheckBox> getcBoxArray() {
		return cBoxArray;
	}

	public void showWindow() {
		case2.show();
	}

	public void close() {
		case2.close();
	}

	public void addMyEventHandlerOk(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}

	public void addMyEventHandlerOk2(EventHandler<ActionEvent> event) {
		ok2.setOnAction(event);
	}

	public Button getOk() {
		return ok;
	}

	public void changeOkVisiblity(boolean OkVisibility, boolean Ok2Visibility) {
		buttonHbox.setVisible(OkVisibility);
		ok2.setVisible(Ok2Visibility);

	}

	public ComboBox<Object> getNumberOfAnswers() {
		return numberOfAnswers;
	}

	public Text getQuestionTextError() {
		return questionTextError;
	}

	public TextField getQuestionTextField() {
		return questionTextField;
	}

	public ArrayList<TextField> getAnswerTextFieldArray() {
		return answerTextFieldArray;
	}

	public void setQuestionTextErrorVisible() {
		questionTextError.setVisible(true);

	}

	public RadioButton getRadioButton1() {
		return radioButton1;
	}

	public RadioButton getRadioButton2() {
		return radioButton2;
	}

	public void setRadioButtonErrorVisible(boolean visibile) {
		radioButtonError.setVisible(visibile);
	}

	public void setNotEnoughAnswersErrorVisible(boolean visibile) {
		notEnoughAnswerError.setVisible(visibile);
	}

	public void changeborderPaneScene() {
		pane.setCenter(messagePane);
	}

	public void setborderPaneScroll() {
		pane.setCenter(scrollPane);
	}

	public void displayAnswerError(int fAnswer, int sAnswer) {
		answerTextErrorArray.get(fAnswer).setVisible(true);
		answerTextFieldArray.get(fAnswer).setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
		answerTextErrorArray.get(sAnswer).setVisible(true);
		answerTextFieldArray.get(sAnswer).setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
	}

	public void setAnswerErrorTofalse() {
		for (Text i : answerTextErrorArray) {
			i.setVisible(false);

		}
		for (TextField j : answerTextFieldArray) {
			j.setStyle(null);
		}
	}

	public void resetStage() {
		changeOkVisiblity(true, false);
		questionTextError.setVisible(false);
		setRadioButtonErrorVisible(false);
		setAnswerErrorTofalse();
		setNotEnoughAnswersErrorVisible(false);
		setborderPaneScroll();
		questionTextField.clear();
		numberOfAnswers.setValue(null);
        numberOfAnswers.setVisible(false);
		radioButton1.setSelected(false);
		radioButton2.setSelected(false);
		setQuestionBlankErrorVisible(false);
		setAnswerBlankErrorvisible(false);
		chooseNumOfAnswers.setVisible(false);
		clearAnswerFields();
		clearCheckBoxes();

	}

	public void clearAnswerFields() {
		for (TextField i : answerTextFieldArray) {
			i.clear();
			i.setVisible(false);
		}
	}

	public void clearCheckBoxes() {
		for (CheckBox i : cBoxArray) {
			i.setSelected(false);
			i.setVisible(false);
		}
	}

	public void setQuestionBlankErrorVisible(boolean visible) {
		questionBlankError.setVisible(visible);
		if (visible) {
			questionTextField.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
		} else {
			questionTextField.setStyle(null);
		}
	}

	public void setAnswerBlankErrorvisible(boolean visible) {
		answerBlankError.setVisible(visible);
	}

	public boolean getAnswerTextFieldArrayEmpty() {
		for (TextField i : answerTextFieldArray) {
			if (!i.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	public void addMyEventHandlerForClose(EventHandler<WindowEvent> event) {
		case2.setOnCloseRequest(event);
	}
}
