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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Menu {
	private Button case1 = new Button("Present all questions and answers related to questions");
	private Button case2 = new Button("Add a new question");
	private Button case3 = new Button("Update wording of a question");
	private Button case4 = new Button("Update wording of an answer");
	private Button case5 = new Button("Delete an answer of a question");
	private Button case6 = new Button("Create a new test manually");
	private Button case7 = new Button("Create a new test automatically");
	private Button case8 = new Button("Create a text file of a test");
	private Button case12 = new Button("Exit program");
	private Button Ok = new Button("Ok");
	private Text exitText1 = new Text("Goodbye!");
	private Text exitText2 = new Text("Couldn't save the pool of questions to a file, Goodbye!");
	private Text exitText3 = new Text(165, 200, "Hello, File loaded successfully");
	private Text exitText4 = new Text("Hello, No file was found to load a pool of questions from");
	private ArrayList<Button> buttonList = new ArrayList<Button>();

	private StackPane stackPane;
	private Scene stackScene;

	private Stage primaryStage;
	private Scene scene;
	private VBox pane;

	public Menu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.stackPane = new StackPane();
		this.pane = new VBox(10);
		this.scene = new Scene(pane, 500, 500);
		
        pane.setFillWidth(true);
		exitText1.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		exitText2.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		exitText3.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		exitText4.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		exitText1.setVisible(false);
		exitText2.setVisible(false);
		exitText3.setVisible(false);
		exitText4.setVisible(false);
		buttonList.add(case1);
		buttonList.add(case2);
		buttonList.add(case3);
		buttonList.add(case4);
		buttonList.add(case5);
		buttonList.add(case6);
		buttonList.add(case7);
		buttonList.add(case8);
		buttonList.add(case12);
		
		
		
		
		pane.setPadding(new Insets(10));
		stackPane.setPadding(new Insets(10));
		//pane.setSpacing(10);
		
		for (Button i : buttonList) {
			pane.getChildren().add(i);
		}
	
		stackPane.getChildren().add(exitText1);
		stackPane.getChildren().add(exitText2);
		stackPane.getChildren().add(exitText3);
		stackPane.getChildren().add(exitText4);
		Ok.setTranslateY(50);
		stackPane.getChildren().add(Ok);
		this.stackScene = new Scene(stackPane, 500, 500);
		
	
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setTitle("Menu");

		Ok.setOnAction(e -> {
			primaryStage.setScene(scene);
			if (exitText1.isVisible() || exitText2.isVisible() ) {
			 primaryStage.close();
			}
		});
		
		
		scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	for (Button i : buttonList) {
		    		i.setPrefWidth((double)newSceneWidth);
		    	    i.setMaxWidth((double)newSceneWidth);
		    	}
		}});
		scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		    	for (Button i : buttonList) {
		    		i.setPrefHeight(((double)newSceneHeight)/10);
		    	    i.setMaxHeight(((double)newSceneHeight)/10);
		    	}
		        
		       
		      
		    }

			
		});
	
	
	}

	public void showStage() {
		primaryStage.show();
	}
	public void closeStage() {
		primaryStage.close();
	}
	public void showMenu() {
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		showStage();
	}
	

	public void setVBoxPaneVisible() {
		primaryStage.setScene(scene);
	}

	public void setStackPaneVisible() {
		
		primaryStage.setScene(stackScene);
	}

	public void setExitText1Visibility(boolean isVisible) {
		exitText1.setVisible(isVisible);
	}

	public void setExitText2Visibility(boolean isVisible) {
		exitText2.setVisible(isVisible);
	}

	public void setExitText3Visibility(boolean isVisible) {
		exitText3.setVisible(isVisible);
	}

	public void setExitText4Visibility(boolean isVisible) {
		exitText4.setVisible(isVisible);
	}
	

	public void addMyEventHandlerForCase1(EventHandler<ActionEvent> event) {
		case1.setOnAction(event);
	}

	public void addMyEventHandlerForCase2(EventHandler<ActionEvent> event) {
		case2.setOnAction(event);
	}
	public void addMyEventHandlerForCase3(EventHandler<ActionEvent> event) {
		case3.setOnAction(event);
	}
	public void addMyEventHandlerForCase4(EventHandler<ActionEvent> event) {
		case4.setOnAction(event);
	}
	public void addMyEventHandlerForCase5(EventHandler<ActionEvent> event) {
		case5.setOnAction(event);
	}
	public void addMyEventHandlerForCase6(EventHandler<ActionEvent> event) {
		case6.setOnAction(event);
	}
	public void addMyEventHandlerForCase7(EventHandler<ActionEvent> event) {
		case7.setOnAction(event);
	}
	public void addMyEventHandlerForCase8(EventHandler<ActionEvent> event) {
		case8.setOnAction(event);
	}
	
	public void addMyEventHandlerForCase12(EventHandler<ActionEvent> event) {
		case12.setOnAction(event);
	}
	public void addMyEventHandlerForClose(EventHandler<WindowEvent> event) {
		primaryStage.setOnCloseRequest(event);
	}
	
	
	

}
