package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Case11 {
	private Stage case11;
	private Scene scene11;
	private MyButton b;
	private MyLabel label;
	private StackPane mainPane;
	private HBox secondPane;
	
	public Case11(Stage case11) {
		this.case11 = case11;
		this.b = new MyButton();
		this.label = new MyLabel();
		this.secondPane = new HBox(b,label);
		this.mainPane = new StackPane(secondPane);
		this.scene11 = new Scene(mainPane,600,700);
		
		case11.setAlwaysOnTop(true);
		
		b.setVisible(false);
		b.setDisable(true);
		label.setVisible(false);
		label.setText("The iterator was created");
		label.setPadding(new Insets(5));
		b.setPadding(new Insets(5));
		b.setText("Initiate Iterator");
		secondPane.setAlignment(Pos.CENTER);
		case11.setScene(scene11);
		
	}
	
	
	public void showWindow() {
		case11.show();
	}
	public void addMyEventHandlerToMyButton(EventHandler<ActionEvent> event) {
		b.setOnAction(event);
	}


	public MyButton getMyButton() {
		return b;
	}


	public MyLabel getLabel() {
		return label;
	}
	
	
	
	

}
