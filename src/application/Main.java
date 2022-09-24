package application;
	
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	//Initialize all pages
	static VBox root;
	static BorderPane mainRoot;
	static GridPane dashboardRoot;
	static ScrollPane formRoot;
	static VBox aboutViewRoot;
	static VBox patientViewRoot;
	static ArrayList<Patient> all_patients;
	static patientViewController patientviewcontroller;
	@Override
	public void start(Stage primaryStage) {
		try {
			all_patients = readDataFromCsv();
			root = (VBox)FXMLLoader.load(getClass().getResource("login.fxml"));
			mainRoot = (BorderPane)FXMLLoader.load(getClass().getResource("epms.fxml"));
			dashboardRoot = (GridPane)FXMLLoader.load(getClass().getResource("dashboard.fxml"));
			formRoot = (ScrollPane)FXMLLoader.load(getClass().getResource("form.fxml"));
			aboutViewRoot = (VBox)FXMLLoader.load(getClass().getResource("about.fxml"));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("patientView.fxml"));
			patientViewRoot = (VBox) loader.load();
			
			
			//Splash screen root
			VBox loaderRoot = (VBox)FXMLLoader.load(getClass().getResource("loader.fxml"));
			
			Scene scene = new Scene(loaderRoot,650,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Electronic Patient Management System");
			primaryStage.show();
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Platform.runLater(() -> {
						scene.setRoot(root);
					});
				}
			}, 5000l);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Patient> readDataFromCsv() throws IOException {
		File file = new File("data.csv");
		if(file.exists()) {
		FileReader csvInput = new FileReader(file);
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvInput);
		ArrayList<Patient> all_patients = new ArrayList<Patient>();
		Patient patient;
		for (CSVRecord record : records) {
			patient = new Patient();		
		    patient.setPatientID(record.get("PatientID"));
		    patient.setPatientName(record.get("PatientName"));
		    patient.setPatientAddress(record.get("PatientAddress"));
		    patient.setPatientAge(Short.parseShort(record.get("PatientAge")));
		    patient.setPatientSex(record.get("PatientSex"));
		    patient.setPatientBill(record.get("PatientBill"));
		    patient.setDateAdmitted(record.get("PatientDateAdmitted"));
		    patient.setPatientCondition(record.get("PatientCondition"));
		    patient.setIsAdmitted(record.get("isAdmitted"));
		    patient.setHasPaid(record.get("hasPaid"));
		    all_patients.add(patient);
		}
		
		return all_patients;
		}else {
			return new ArrayList<Patient>();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
