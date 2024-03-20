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
	 public static void AlertEmailField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid Email Address");
	        alert.setContentText("Please enter a valid email address.\nAn email address should be:\n in the format:\n example@example.com");
	        alert.showAndWait();
	 }
	 public static void AlertPasswordField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid Password");
	        alert.setContentText("Please enter a valid password.\nA valid password must contain:\nat least 8 characters,\nincluding at least one uppercase letter,\none lowercase letter,\none special character,\nand should not exceed 30 characters.");
	        alert.showAndWait();
	 }
	public static void AlertEmailInUse() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Email already in use");
		alert.setContentText("Please double check the address or try to log in");
		alert.showAndWait();
	}
}
