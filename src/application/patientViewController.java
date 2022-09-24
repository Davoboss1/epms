package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class patientViewController {
	@FXML
	private Button searchButton;
	@FXML
	private Button makePaymentButton;
	@FXML
	private Button makeNotPaymentButton;
	@FXML
	private Button setBillButton;
	@FXML
	private TextField searchPatientField;
	@FXML
	private TextField setBillField;
	@FXML
	private TableView<Patient> tableView;
	@FXML
	private Button dischargeButton;
	@FXML
	private Button notDischargeButton;
	
	private ArrayList<Patient> currentList;
	
	@FXML
	private void initialize() {

		//Set all table columns and its data
        TableColumn<Patient, String> patientID = new TableColumn<Patient, String>("Patient ID");
        patientID.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientID"));

        TableColumn<Patient, String> patientName = new TableColumn<Patient, String>("Patient Name");
        patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientName"));
        patientName.setMinWidth(150);
        
        TableColumn<Patient, String> patientAddress = new TableColumn<Patient, String>("Address");
        patientAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientAddress"));
        patientAddress.setMinWidth(250);
        
        TableColumn<Patient, String> patientAge = new TableColumn<Patient, String>("Age");
        patientAge.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientAge"));
        patientAge.setMinWidth(50);
        
        TableColumn<Patient, String> patientSex = new TableColumn<Patient, String>("Sex");
        patientSex.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientSex"));
        patientSex.setMinWidth(50);
        
        TableColumn<Patient, String> patientBill = new TableColumn<Patient, String>("Bill");
        patientBill.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientBill"));
        patientBill.setMinWidth(100);
        
        TableColumn<Patient, String> patientDateAdmitted = new TableColumn<Patient, String>("Date Admitted");
        patientDateAdmitted.setCellValueFactory(new PropertyValueFactory<Patient, String>("DateAdmitted"));
        patientDateAdmitted.setMinWidth(130);
        
        TableColumn<Patient, String> patientCondition = new TableColumn<Patient, String>("Condition");
        patientCondition.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatientCondition"));
        patientCondition.setMinWidth(100);
        
        TableColumn<Patient, String> isAdmitted = new TableColumn<Patient, String>("is Admitted");
        isAdmitted.setCellValueFactory(new PropertyValueFactory<Patient, String>("isAdmitted"));
        isAdmitted.setMinWidth(100);
        
        TableColumn<Patient, String> hasPaid = new TableColumn<Patient, String>("has Paid");
        hasPaid.setCellValueFactory(new PropertyValueFactory<Patient, String>("hasPaid"));
        hasPaid.setMinWidth(100);
        
        tableView.getColumns().setAll(patientID, patientName, patientAddress, patientAge, patientSex, patientBill, patientDateAdmitted, patientCondition, isAdmitted, hasPaid);
        setListToTable();
        
        
        
        //Expose current controller reference so other classes can access
        Main.patientviewcontroller = this;
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        searchButton.setOnAction((event) -> {
        	searchPatients();
        });
        
        // set listener of make payment button
        makePaymentButton.setOnAction((event) -> {
        	try {
        		performOperation(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        makeNotPaymentButton.setOnAction((event) -> {
        	try {
        		performOperation(2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        setBillButton.setOnAction((event) -> {
        	try {
        		performOperation(3);
        		setBillField.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        dischargeButton.setOnAction((event) -> {
        	try {
        		performOperation(4);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        notDischargeButton.setOnAction((event) -> {
        	try {
        		performOperation(5);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
	}
	
	
	public void showOnlySearch() {
		setBillButton.setVisible(false);
		makePaymentButton.setVisible(false);
		makeNotPaymentButton.setVisible(false);
		setBillField.setVisible(false);
		makePaymentButton.setManaged(false);
		makeNotPaymentButton.setManaged(false);
		setBillField.setManaged(false);
		dischargeButton.setVisible(false);
		dischargeButton.setManaged(false);
		notDischargeButton.setVisible(false);
		notDischargeButton.setManaged(false);
		setListToTable();
	}
	public void showBill() {
		setBillButton.setVisible(true);
		setBillButton.setManaged(true);
		setBillField.setVisible(true);
		setBillField.setManaged(true);
		makePaymentButton.setVisible(false);
		makeNotPaymentButton.setVisible(false);
		makePaymentButton.setManaged(false);
		makeNotPaymentButton.setManaged(false);
		dischargeButton.setVisible(false);
		dischargeButton.setManaged(false);
		notDischargeButton.setVisible(false);
		notDischargeButton.setManaged(false);
		setListToTable();
	}
	
	public void showMakePayment() {
		makePaymentButton.setVisible(true);
		makeNotPaymentButton.setVisible(true);
		makePaymentButton.setManaged(true);
		makeNotPaymentButton.setManaged(true);
		setBillField.setVisible(false);
		setBillButton.setVisible(false);
		setBillField.setManaged(false);
		setBillButton.setManaged(false);
		dischargeButton.setVisible(false);
		dischargeButton.setManaged(false);
		notDischargeButton.setVisible(false);
		notDischargeButton.setManaged(false);
		setListToTable();
	}
	
	public void showDischarge() {
		dischargeButton.setVisible(true);
		dischargeButton.setManaged(true);
		notDischargeButton.setVisible(true);
		notDischargeButton.setManaged(true);
		makePaymentButton.setVisible(false);
		makeNotPaymentButton.setVisible(false);
		makePaymentButton.setManaged(false);
		makeNotPaymentButton.setManaged(false);
		setBillField.setVisible(false);
		setBillButton.setVisible(false);
		setBillField.setManaged(false);
		setBillButton.setManaged(false);
		setListToTable();
	}
	
	public void setListToTable() {
		tableView.setItems(FXCollections.observableArrayList(Main.all_patients));
		currentList = Main.all_patients;
	}
	
	//Function writes to csv file new fields record
	// Performs different operations based on number status
	// 1 = Mark as paid
	// 2 = set Bill
	// 3 = set is admitted
	public void performOperation(int status) throws IOException {
		System.out.println(tableView.getSelectionModel().getSelectedItems().size());
		//Create or append to csv data file
		File file = new File("data.csv");
		FileWriter writer = new FileWriter(file, false);
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("PatientID", "PatientName", "PatientAddress", "PatientAge","PatientSex","PatientBill","PatientDateAdmitted","PatientCondition","isAdmitted","hasPaid"));
		ObservableList<Patient> patientList = tableView.getSelectionModel().getSelectedItems();
		for(Patient patient: Main.all_patients) {
			for(Patient new_patient: patientList) {
				if(new_patient.getPatientID()==patient.getPatientID()) {
					if(status == 1)
						patient.setHasPaid("Yes");
					else if(status == 2)
						patient.setHasPaid("No");
					else if(status==3)
						patient.setPatientBill(setBillField.getText());
					else if(status==4)
						patient.setIsAdmitted("No");
					else if(status==5)
						patient.setIsAdmitted("Yes");
				}				
			}
			csvPrinter.printRecord(patient.getPatientID(), patient.getPatientName(), patient.getPatientAddress(), patient.getPatientAge(), patient.getPatientSex(), patient.getPatientBill(),patient.getDateAdmitted(), patient.getPatientCondition(),patient.getIsAdmitted(),patient.getHasPaid());	
		}
		csvPrinter.flush();
		tableView.refresh();
	}
	
	public void displayOnlyAdmitted() {
		showOnlySearch();
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		for(Patient patient: Main.all_patients) {
			if(patient.getIsAdmitted().equals("Yes")) {
				patientList.add(patient);
			}
		}
		tableView.setItems(FXCollections.observableArrayList(patientList));
		currentList = patientList;
	}
	
	public void searchPatients() {
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		for(Patient patient: currentList) {
			if(patient.getPatientName().toLowerCase().contains(searchPatientField.getText().toLowerCase())) {
				patientList.add(patient);
			}
		}
		tableView.setItems(FXCollections.observableArrayList(patientList));
	}
}
