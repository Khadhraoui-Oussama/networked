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
	 public static void AlertNameField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Are u sure you correctly entered your first/last name");
	        alert.setContentText("Your first/last name should start with an Uppercase letter\n and should be at least be 2 letters long");
	        alert.showAndWait();
	 }
	 public static void AlertDOBField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Age Restriction Alert");
	        alert.setContentText("Sorry ,but You must be at least 16 years old to proceed.");
	        alert.showAndWait();
	 }
	 public static void AlertAddressField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Incomplete Address");
	        alert.setContentText("Please provide a complete physical address, \n including street name, city, state/province,\n postal code, and country.\n Also no special characters except - , ' .");
	        alert.showAndWait();
	 }
	 
}
