module EPMS {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires commons.csv;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
