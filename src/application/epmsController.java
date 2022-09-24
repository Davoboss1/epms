package application;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class epmsController {

	@FXML
	private Button admissionButton;
	@FXML
	private Button billButton;
	@FXML
	private Button paymentButton;
	@FXML
	private ImageView imageLogo;
	@FXML
	private Button patientRecordButton;
	@FXML
	private Button currentlyAdmittedButton;
	@FXML
	private Button patientDischargeButton;
	@FXML
	private Button aboutButton;
	
	@FXML
	private void initialize(){
		admissionButton.setOnAction((event) -> {switchPage((Parent) Main.formRoot);});
		imageLogo.setOnMouseClicked((event) -> {switchPage((Parent) Main.dashboardRoot);});
		paymentButton.setOnAction((event) -> {
			
			Main.patientviewcontroller.showMakePayment();
			switchPage((Parent) Main.patientViewRoot);
		});	
		billButton.setOnAction((event) -> {
			Main.patientviewcontroller.showBill();
			switchPage((Parent) Main.patientViewRoot);
		});	
		patientRecordButton.setOnAction((event) -> {
			Main.patientviewcontroller.showOnlySearch();
			switchPage((Parent) Main.patientViewRoot);
			});		
		currentlyAdmittedButton.setOnAction((event) -> {
			Main.patientviewcontroller.displayOnlyAdmitted();
			switchPage((Parent) Main.patientViewRoot);
			});	
		patientDischargeButton.setOnAction((event) -> {
			Main.patientviewcontroller.showDischarge();
			switchPage((Parent) Main.patientViewRoot);
			});
		aboutButton.setOnAction((event) -> {
			switchPage((Parent) Main.aboutViewRoot);
		});
	}
	
	private void switchPage(Parent root) {
		Main.mainRoot.setCenter(root);
	}
	
	@FXML
	private void logout() {
		Scene scene = Main.root.getScene();
		Stage stage = (Stage) imageLogo.getScene().getWindow();
		stage.setScene(scene);
	}
}
