package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Case1 {
	private Stage case1;
	private Text arrayOfQuestions;
	private ScrollPane pane;
	private VBox vPane;

	public Case1(Stage case1) {
		this.case1 = case1;
		this.vPane = new VBox();
		this.pane = new ScrollPane();
		arrayOfQuestions = new Text();
		
        pane.setPadding(new Insets(10));
		pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		arrayOfQuestions.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 13));
		case1.setTitle("Present all questions and answers related to questions");
		Scene scene = new Scene(pane,700, 700);
		case1.setScene(scene);
		case1.setAlwaysOnTop(true);
		vPane.getChildren().add(arrayOfQuestions);
		pane.setContent(vPane);

	}

	public void showWindow() {
		case1.show();
	}

	public void addTextToWindow(String text) {
		arrayOfQuestions.setText(text);
	}

	public void updateTextToWindow() {

	}

}
