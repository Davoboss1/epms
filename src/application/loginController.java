package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private PasswordField passwordField;
	
	
	@FXML
	private void initialize() {
		System.out.println("Fxml works fine");
	}
	
	@FXML
	private void login() throws IOException {
		if(usernameField.getText().equals("Admin") && passwordField.getText().equals("Admin2022")) {
		Main.mainRoot.setCenter(Main.dashboardRoot);
		Stage stage = (Stage) usernameField.getScene().getWindow();
		try {
			stage.setScene(new Scene(Main.mainRoot));	
		}catch(IllegalArgumentException e) {
			stage.setScene(Main.mainRoot.getScene());
		}
		
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong Username or Password, Enter a correct username and password and try again..");
			alert.setTitle("Login Error");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		
	}
}
