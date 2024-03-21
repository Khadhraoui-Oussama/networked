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
	 public static Alert AlertNameField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid name");
	        alert.setContentText("Your first/last name should start with an Uppercase letter\ncontain only letters\nand should be at least be 2 letters long");
		 return alert;
	 }
	 public static Alert AlertTypeField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid type");
	        alert.setContentText("The Type should start with an Uppercase letter\ncontain only letters\nand should be at least be 2 letters long");
		 return alert;
	 }
	 public static Alert AlertDOBField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Age Restriction Alert");
	        alert.setContentText("Sorry ,but You must be at least 16 years old to proceed.");
		 return alert;
	 }
	 public static Alert AlertAddressField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Incomplete Address");
	        alert.setContentText("Please provide a complete physical address, \n including street name, city, state/province,\n postal code, and country.\n Also no special characters except - , ' .");
         return alert;
     }
	 public static Alert AlertDescriptionField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid Description");
	        alert.setContentText("Please provide a valid description, \nA valid description should not excedd 150 characters.");
         return alert;
     }
	 public static Alert AlertEmailField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid Email Address");
	        alert.setContentText("Please enter a valid email address.\nAn email address should be:\n in the format:\n example@example.com");
		 	return alert;
	 }
	 public static Alert AlertPasswordField()
	 {
		 	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Invalid Password");
	        alert.setContentText("Please enter a valid password.\nA valid password must contain:\nat least 8 characters,\nincluding at least one uppercase letter,\none lowercase letter,\none special character,\nand should not exceed 30 characters.");
		 return alert;
	 }
	public static Alert AlertTitleField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Title");
		alert.setContentText("Please enter a valid title.\nA valid title must:\n- Start with a letter or digit\n- Contain 1 to 38 characters\n- Only include letters, digits, spaces,\n underscores, commas, hyphens, and no trailing spaces");
		return alert;
	}
	public static Alert AlertCompanyField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Company name");
		alert.setContentText("Please enter a valid name.\nA valid name must:\n- Start with a letter or digit\n- Contain 1 to 38 characters\n- Only include letters, digits, spaces,\n underscores, commas, hyphens, and no trailing spaces");
		return alert;
	}
	public static Alert AlertPositionField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Position");
		alert.setContentText("Please enter a valid Position.\nA valid Position must:\n- Start with a letter or digit\n- Contain 1 to 38 characters\n- Only include digits, spaces,\n underscores, commas, hyphens, and no trailing spaces");
		return alert;
	}
	public static Alert AlertTechnologyField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Title");
		alert.setContentText("Please enter a valid Technology.\nA valid Technology must:\n- Start with a letter or digit\n- Contain 1 to 38 characters\n- Only include letters, digits, spaces, underscores, commas, hyphens, and no trailing spaces");
		return alert;
	}

	public static Alert AlertEmailInUse() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Email already in use");
		alert.setContentText("Please double check the address or try to log in");
		return alert;
	}

	public static Alert AlertLinkField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid link: ");
		alert.setContentText("Please enter a valid link.\nA valid link must look like this:\nhttp(s)://example.com\nand have no special characters.");
		return alert;
	}

	public static Alert AlertFoundersField() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Founders");
		alert.setContentText("Please enter valid founder names.\nEach founder name must:\n- Start with a letter\n- Consist of letters and hyphens\n- Multiple founders should be separated\nby commas or hyphens with spaces");
		return alert;
	}

}
