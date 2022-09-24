package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class dashboardController {
	@FXML
	PieChart piechart;
	@FXML
	Label patientAdmitted;
	
	@FXML
	Label totalPatients;
	
	@FXML
	Label dischargedPatients;
	@FXML
	Label outstandingPatients;
	
	@FXML
	Button refreshList;
	
	@FXML
	private void initialize() {
		setData();
	}
	
	private void setData() {
		int admitted_patients = getAdmittedPatients();
		int discharged_patients = getDischargedPatients();
		int outstanding_patients = getOustandingPatients();
		int total_patients = Main.all_patients.size();
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList( 
				   new PieChart.Data("Total Patients", total_patients), 
				   new PieChart.Data("Admitted Patients", admitted_patients), 
				   new PieChart.Data("Discharged Patients", discharged_patients), 
				   new PieChart.Data("Outstanding Patients", outstanding_patients));
		piechart.setData(pieChartData);
		piechart.setTitle("Patients statistics");
		piechart.setLabelsVisible(true);
		totalPatients.setText("Total Patients: " + total_patients);
		patientAdmitted.setText("Admitted Patients: " + admitted_patients);
		dischargedPatients.setText("Discharged Patients: " + discharged_patients);
		outstandingPatients.setText("Outstanding Patients: " + outstanding_patients);
		
		refreshList.setOnAction((event) -> {setData();});
	}
	
	private int getAdmittedPatients() {
		int count = 0;
		for(Patient p : Main.all_patients) {
			if(p.getIsAdmitted().equals("Yes"))
			count++;
		}
		return count;
	}

	private int getDischargedPatients() {
		int count = 0;
		for(Patient p : Main.all_patients) {
			if(p.getIsAdmitted().equals("No"))
			count++;
		}
		return count;
	}
	
	private int getOustandingPatients() {
		int count = 0;
		for(Patient p : Main.all_patients) {
			if(p.getHasPaid().equals("No"))
			count++;
		}
		return count;
	}
}
