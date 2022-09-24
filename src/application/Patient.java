package application;

public class Patient {
	

	private String PatientID;
	private String PatientName;
	private String PatientAddress;
	private String PatientSex;
	private short PatientAge;
	private String DateAdmitted;
	private String PatientBill;
	private String PatientCondition;
	private String isAdmitted;
	private String hasPaid;
	
	public Patient() {
		
	}
	
	public Patient(String patientID, String patientName, String patientAddress, String patientSex, short patientAge,
			String dateAdmitted, String patientBill, String patientCondition, String isAdmitted, String hasPaid) {
		PatientID = patientID;
		PatientName = patientName;
		PatientAddress = patientAddress;
		PatientSex = patientSex;
		PatientAge = patientAge;
		DateAdmitted = dateAdmitted;
		PatientBill = patientBill;
		PatientCondition = patientCondition;
		this.isAdmitted = isAdmitted;
		this.hasPaid = hasPaid;
	}	
	
	public String getPatientID() {
		return PatientID;
	}
	public void setPatientID(String patientID) {
		PatientID = patientID;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getPatientAddress() {
		return PatientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		PatientAddress = patientAddress;
	}
	public String getPatientSex() {
		return PatientSex;
	}
	public void setPatientSex(String patientSex) {
		PatientSex = patientSex;
	}
	public short getPatientAge() {
		return PatientAge;
	}
	public void setPatientAge(short patientAge) {
		PatientAge = patientAge;
	}
	public String getDateAdmitted() {
		return DateAdmitted;
	}
	public void setDateAdmitted(String dateAdmitted) {
		DateAdmitted = dateAdmitted;
	}
	public String getPatientBill() {
		return PatientBill;
	}
	public void setPatientBill(String patientBill) {
		PatientBill = patientBill;
	}
	public String getPatientCondition() {
		return PatientCondition;
	}
	public void setPatientCondition(String patientCondition) {
		PatientCondition = patientCondition;
	}
	public String getIsAdmitted() {
		return isAdmitted;
	}
	public void setIsAdmitted(String isAdmitted) {
		this.isAdmitted = isAdmitted;
	}
	public String getHasPaid() {
		return hasPaid;
	}
	public void setHasPaid(String hasPaid) {
		this.hasPaid = hasPaid;
	}
	
}
