package view;

import java.util.ArrayList;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Case8 {
	private Stage case8;
	private Scene mainScene;
	private BorderPane mainPane;
	private ScrollPane testListPane;
	private StackPane bottomPane;

	private Text testList;
	private Text chosenTest;
	private Text chooseTest;
	private Text confirmMessage;
	private Text copied;
	private Text copiedError;

	private ComboBox<Integer> testOptions;
	private Button ok,exit;
	

	public Case8(Stage case8) {
		this.case8 = case8;
		this.mainPane = new BorderPane();
		this.testListPane = new ScrollPane();
        this.bottomPane = new StackPane();
        this.testList = new Text();
        
        this.chooseTest = new Text("Please choose the test ID to save in text file");
        this.chosenTest = new Text();
        this.confirmMessage = new Text("Are you sure this is the test you want to save?");
        this.copied = new Text("Test saved!");
        this.copiedError = new Text("Couldn't save selected test");
        this.testOptions = new ComboBox<Integer>();
        this.ok = new Button("Ok");
        this.exit = new Button("Exit");
    	this.mainScene = new Scene(mainPane,700,700);
        
        testList.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
        chooseTest.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
        confirmMessage.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
        copied.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
        copiedError.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 30));
        copiedError.setFill(Color.RED);
        confirmMessage.setVisible(false);
        ok.setVisible(false);
        exit.setVisible(false);
        
        
        testListPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        testListPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        testOptions.setVisibleRowCount(5);
        
        testListPane.setContent(testList);
        mainPane.setPadding(new Insets(10));
        testListPane.setPadding(new Insets(10));
        bottomPane.setPadding(new Insets(10));
        
        bottomPane.getChildren().add(chooseTest);
        bottomPane.getChildren().add(testOptions);
        bottomPane.getChildren().add(ok);
        bottomPane.getChildren().add(exit);
        bottomPane.getChildren().add(confirmMessage);
        
        StackPane.setAlignment(ok, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(exit, Pos.BOTTOM_CENTER);
        
    	exit.setTranslateX(20);
		ok.setTranslateX(-20);
		chooseTest.setTranslateY(-25);
	//	testOptions.setTranslateY();
		
		mainPane.setCenter(testListPane);
		mainPane.setBottom(bottomPane);
		
		bottomPane.setPrefHeight(150);
		
		case8.setScene(mainScene);
		
		
		case8.setAlwaysOnTop(true);
		
		exit.setOnAction(e->{
			resetStage();
			closeWindow();
		});
        
        
        
        
        
	}
	public void setCopied() {
		testListPane.setContent(copied);
		confirmMessage.setVisible(false);
		ok.setVisible(false);
	}
	public void setError() {
		testListPane.setContent(copied);
		ok.setVisible(false);
	}
	public int  getValueOfComboBox() {
		if (testOptions.getValue() == null) {
			return 0;
		}
		return testOptions.getValue();
	}
	public void addToMyEventHandlerForComboBox(EventHandler<ActionEvent> event) {
		testOptions.setOnAction(event);
	}
	public void addToMyEventHandlerForOk(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}
	
		
	
	public void setTextListToWindow(String newText) {
		testList.setText(newText);
		testListPane.setContent(testList);
	}
	public void setTextChosenTestToWindow(String newText) {
		chosenTest.setText(newText);
		testListPane.setContent(chosenTest);
	}
	
	/*public void setComboBox(int numOfTests) {
		testOptions.getItems().clear();
		for (int i=1;i<=numOfTests;i++) {
			testOptions.getItems().add(i);
		}
	}*/
	public void setComboBox(ArrayList<Integer> list) {
		testOptions.getItems().clear();
		for (int i=0; i<list.size();i++) {
			testOptions.getItems().add(list.get(i));
		}
	}
	public void showWindow() {
		case8.show();
	}
	public void closeWindow() {
		case8.close();
	}
	public void changeToConfirmationWindow(boolean change) {
		chooseTest.setVisible(!change);
		testOptions.setVisible(!change);
		confirmMessage.setVisible(change);
		ok.setVisible(change);
		exit.setVisible(change);
		
		
	}
	public void resetStage() {
		testOptions.setValue(null);
		testListPane.setContent(testList);
		changeToConfirmationWindow(false);

}
}
