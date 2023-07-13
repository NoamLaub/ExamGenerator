package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignIn {
	private Stage signIn;
	private Scene mainScene;
	private Scene signUpScene;
	private Scene signInScene;
	private StackPane mainPane;
	private StackPane signInPane;
	private StackPane signUpPane;
	private Button ok;
	private Button ok2;
	private Button newUser;
	private Button existingUser;
	private Text chooseText;
	private Text warning;
	private Text signInText;
	private Text passwordAlreadyExist;
	private Text pleaseEnterPass;
	private Text onlyNumError;
	private Text tooManyNumError;
	private Text wrongPassError;
	private TextField password;
	private TextField passwordSignIn;
	
	public SignIn(Stage stage) {
		this.signIn = stage;
		this.mainPane = new StackPane();
		this.signInPane = new StackPane();
		this.signUpPane = new StackPane();
		this.ok = new Button("Ok");
		this.ok2 = new Button("Ok");
		this.newUser = new Button("Sign Up");
		this.existingUser = new Button("Sign In");
		this.chooseText = new Text("Please choose one of the following options");
		this.warning = new Text("Please Enter password with 8 digits max,containing only numbers\n and make sure you rememeber it! NO WAY TO RETRIEVE IT!");
		this.signInText = new Text("Please Enter password to sign in your account");
		this.wrongPassError = new Text("couldn't find any account matching this password\n(password contain only 8 digits and only numbers)please try again");
		this.passwordAlreadyExist = new Text("Plz choose another password...");
		this.onlyNumError = new Text("Password must only contain numbers");
		this.tooManyNumError = new Text("Password contain too many numbers");
		this.pleaseEnterPass = new Text("Plz enter password...");
		this.password = new TextField();
		this.passwordSignIn = new TextField();
		
		chooseText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		warning.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		signInText.setFont(Font.font("Ariel", FontWeight.BOLD, FontPosture.ITALIC, 15));
		passwordAlreadyExist.setFill(Color.RED);
		pleaseEnterPass.setFill(Color.RED);
		onlyNumError.setFill(Color.RED);
		tooManyNumError.setFill(Color.RED);
		wrongPassError.setFill(Color.RED);
		passwordAlreadyExist.setVisible(false);
		pleaseEnterPass.setVisible(false);
		onlyNumError.setVisible(false);
		tooManyNumError.setVisible(false);
		wrongPassError.setVisible(false);
		
		mainPane.getChildren().add(chooseText);
		mainPane.getChildren().add(newUser);
		mainPane.getChildren().add(existingUser);
		mainPane.setPadding(new Insets(10));
		
		signInPane.getChildren().add(signInText);
		signInPane.getChildren().add(passwordSignIn);
		signInPane.getChildren().add(wrongPassError);
		signInPane.getChildren().add(ok);
		signInPane.setPadding(new Insets(10));
		
		
		signUpPane.getChildren().add(warning);
		signUpPane.getChildren().add(password);
		signUpPane.getChildren().add(passwordAlreadyExist);
		signUpPane.getChildren().add(pleaseEnterPass);
		signUpPane.getChildren().add(onlyNumError);
		signUpPane.getChildren().add(tooManyNumError);
		signUpPane.getChildren().add(ok2);
		signUpPane.setPadding(new Insets(10));
		
		
		StackPane.setAlignment(ok, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(ok2, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(warning, Pos.TOP_CENTER);
		StackPane.setAlignment(signInText, Pos.TOP_CENTER);
		
		
		
		
	
		chooseText.setTranslateY(-50);
		passwordAlreadyExist.setTranslateY(25);
		pleaseEnterPass.setTranslateY(25);
		onlyNumError.setTranslateY(25);
		tooManyNumError.setTranslateY(25);
		wrongPassError.setTranslateY(25);
		newUser.setTranslateX(-50);
		existingUser.setTranslateX(50);
		
		
		this.mainScene = new Scene(mainPane,350,150);
		this.signUpScene = new Scene(signUpPane,700,250);
		this.signInScene = new Scene(signInPane,700,250);
		
		signIn.setAlwaysOnTop(true);
		signIn.setScene(mainScene);
		signIn.setTitle("Sign In");
		
		signIn.show();
		
		newUser.setOnMouseClicked(e->{
		
			signIn.setScene(signUpScene);
		});
		
		existingUser.setOnMouseClicked(e->{
			
			signIn.setScene(signInScene);
		});
		signUpScene.setOnMouseClicked(e->{
			passwordAlreadyExist.setVisible(false);
			pleaseEnterPass.setVisible(false);
			onlyNumError.setVisible(false);
			tooManyNumError.setVisible(false);
		});
		password.setOnMouseClicked(e->{
			passwordAlreadyExist.setVisible(false);
			pleaseEnterPass.setVisible(false);
			onlyNumError.setVisible(false);
			tooManyNumError.setVisible(false);
			
		});
		passwordSignIn.setOnMouseClicked(e->{
			wrongPassError.setVisible(false);
		});
		signInScene.setOnMouseClicked(e->{
			wrongPassError.setVisible(false);
		});
		
				
	}
	public void setMainScene() {
		signIn.setScene(mainScene);
	}
	
	public void addmyEventHandlerForOkSignUp(EventHandler<ActionEvent> event) {
		ok2.setOnAction(event);
	}
	public void addmyEventHandlerForOkSignIn(EventHandler<ActionEvent> event) {
		ok.setOnAction(event);
	}
	public TextField getPassword() {
		return password;
	}
	public void setBlankPassErrorVisible() {
		pleaseEnterPass.setVisible(true);
	}
	public void setPassTakenErrorVisible() {
		passwordAlreadyExist.setVisible(true);
	}
	public void setOnlyNumErrorVisible() {
		onlyNumError.setVisible(true);
	}
	public void setTooManyNumErrorErrorVisible() {
		tooManyNumError.setVisible(true);
	}
	public void setWrongPassErrorVisible() {
		wrongPassError.setVisible(true);
	}
	public void showWindow() {
		signIn.show();
	}
	public void closeWindow() {
		passwordAlreadyExist.setVisible(false);
		pleaseEnterPass.setVisible(false);
		onlyNumError.setVisible(false);
		tooManyNumError.setVisible(false);
		wrongPassError.setVisible(false);
		password.clear();
		passwordSignIn.clear();
		signIn.setScene(mainScene);
		signIn.close();
	}
	public TextField getPasswordSignIn() {
		return passwordSignIn;
	}
}
