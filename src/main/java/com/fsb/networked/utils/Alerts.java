package com.fsb.networked.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts
{
	 public static void AlertEmptyField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Some Input fields are empty");
	        alert.setContentText("Please fill all input fields");
	        alert.showAndWait();
	 }
}
