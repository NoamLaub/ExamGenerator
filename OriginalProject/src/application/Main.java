package application;
	
import java.sql.SQLException;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import module.AmericanQuestions;
import module.Answers;
import module.OpenQuestions;
import module.OperatingSystem;
import module.OperatingSystemModule;
//import module.Query;
import view.Case1;
import view.Case11;
import view.Case2;
import view.Case3;
import view.Case4;
import view.Case5;
import view.Case6;
import view.Case7;
import view.Case8;
import view.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		Stage stageCase1 = new Stage();
		Stage stageCase2 = new Stage();
		Stage stageCase3 = new Stage();
		Stage stageCase4 = new Stage();
		Stage stageCase5 = new Stage();
		Stage stageCase6 = new Stage();
		Stage stageCase7 = new Stage();
		Stage stageCase8 = new Stage();
		Stage stageCase11 = new Stage();
		
		OperatingSystemModule operatingSystem = new OperatingSystemModule();
		
		Menu menu = new Menu(primaryStage);
	
		Case1 case1 = new Case1(stageCase1);
		Case2 case2 = new Case2(stageCase2);
		Case3 case3 = new Case3(stageCase3);
		Case4 case4 = new Case4(stageCase4);
		Case5 case5 = new Case5(stageCase5);
		Case6 case6 = new Case6(stageCase6);
		Case7 case7 = new Case7(stageCase7);
		Case8 case8 = new Case8(stageCase8);
		Case11 case11 = new Case11(stageCase11);
		
		
		
	
		Controller controller = new Controller(menu,operatingSystem,case1,case2,case3,case4,case5,case6,case7,case8,case11);
		
		
		}
	}

