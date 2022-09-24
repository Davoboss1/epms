package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class formController {
	@FXML
	private TextField PatientID;
	@FXML
	private TextField PatientName;
	@FXML
	private TextField PatientAddress;
	@FXML
	private ComboBox<String> PatientSex;
	@FXML
	private TextField PatientAge;
	@FXML
	private DatePicker PatientDateAdmitted;
	@FXML
	private TextField PatientCondition;
	String sex_arr[] = { "Male", "Female", "Others"};
	FileWriter writer;
	@FXML
	private void initialize() {
		PatientSex.setItems(FXCollections.observableArrayList(sex_arr));
		PatientID.setEditable(false);
		PatientID.setText("P" + (Main.all_patients.size()+1));
	}
	
	@FXML
	public void submitPatientData() throws IOException{
		
		//Check if fields are empty then show dialog
		if(checkIfEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input detected, please recheck the form and try again.");
			alert.setHeaderText(null);
			alert.setTitle("Invalid Form Input");
			alert.showAndWait();
			return;
		}
		
		//Create or append to csv data file
		File file = new File("data.csv");
		CSVPrinter csvPrinter;
		if (file.exists()) {
			writer = new FileWriter(file, true);
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withSkipHeaderRecord());
	        
		}else {
			writer = new FileWriter(file, false);
			csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("PatientID", "PatientName", "PatientAddress", "PatientAge","PatientSex","PatientBill","PatientDateAdmitted","PatientCondition","isAdmitted","hasPaid"));
	        
		}
        csvPrinter.printRecord(PatientID.getText(), PatientName.getText(), PatientAddress.getText(), Short.parseShort(PatientAge.getText()), PatientSex.getValue(), null, PatientDateAdmitted.getValue(),PatientCondition.getText(),"Yes","No");
        csvPrinter.flush();
        
        //Apply some changes after data created
        Main.all_patients.add(new Patient(PatientID.getText(), PatientName.getText(), PatientAddress.getText(), PatientSex.getValue().toString(), Short.parseShort(PatientAge.getText()), PatientDateAdmitted.getValue().toString(), "",PatientCondition.getText(),"Yes","No"));
        new Alert(Alert.AlertType.CONFIRMATION, "Patient has been added successfully").showAndWait();
        resetForm();
        Main.patientviewcontroller.setListToTable();
	}
	
	private boolean checkIfEmpty() {
		if(PatientID.getText().trim().isEmpty() || PatientName.getText().trim().isEmpty() || PatientAddress.getText().trim().isEmpty() || PatientAge.getText().trim().isEmpty() || PatientSex.getValue() == null || PatientDateAdmitted.getValue() == null || PatientCondition.getText().trim().isEmpty()) {
			return true;
		}{
			return false;
		}
	}
	
	private void resetForm() {
		PatientID.setText("P" + (Main.all_patients.size()+1));
		PatientName.setText("");
		PatientAddress.setText("");
		PatientAge.setText("");
		PatientSex.setValue(null);
		PatientDateAdmitted.setValue(null);
		PatientCondition.setText("");
        
	}
}
