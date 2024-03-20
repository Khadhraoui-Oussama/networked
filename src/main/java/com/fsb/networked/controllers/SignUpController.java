package com.fsb.networked.controllers;

import com.fsb.networked.App;
import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ImportantFileReferences;
import com.fsb.networked.utils.JSONParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable{

	@FXML
	private TextField emailAddressField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button btnIndividual;
	
	@FXML
	private Button btnEntreprise;
	
	@FXML
	private Button btnCancel;
	
	
	@FXML
    private void gotoSignUpIndivdual() throws IOException {
		if (validateBasicInfo()) {
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUp", "emailAddress", emailAddressField.getText());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUp", "password", passwordField.getText());
			//bad hacky solution to load an image path to the Individual JSON file in order to avoid Invalid url or resource not found error when  getValueFromJSONFIle is called on the picture field
			String imagePath = "/images/male_avatar.png";
			URL imageUrl = getClass().getResource(imagePath);
			Image image = null;
			if (imageUrl != null) {
				// Create an Image object using the URL
				image = new Image(imageUrl.toExternalForm());
			} else {
				System.err.println("Image resource not found: " + imagePath);
			}
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON","signUpBasic","picture",image.getUrl());
			//go to next scene
			App.setRoot("SignUpScenes/SignUpPageIndividualBasic");
		}
	}

	@FXML
    private void gotoSignUpEntreprise() throws IOException
	{
		if(validateBasicInfo())
		{
			App.setRoot("SignUpPageEntreprise");
		}
    }
	
	@FXML
    private void cancelSignUp() throws IOException
	{
		//if the user decides he no longer want to sign up the json files must be cleared of all inputs
		// and returned to the original state
		JSONParser.resetIndividualJSONFile();
		JSONParser.resetEntrepriseJSONFile();
		App.setRoot("LogInPage");
    }
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}
	
	private boolean validateBasicInfo() 
	{    
		boolean isValid = true;
	    
	    if (emailAddressField.getText().isEmpty() || passwordField.getText().isEmpty()) {
	        Alerts.AlertEmptyField();
	        isValid = false;
	    }
	    Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");
	    Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,30}$");
	    
	    Matcher emailMatcher = emailPattern.matcher(emailAddressField.getText());
	    Matcher passwordMatcher = passwordPattern.matcher(passwordField.getText());
	    
	    if (!emailAddressField.getText().isEmpty() && !emailMatcher.matches()) {
	        flashRedBorder(emailAddressField);
	        Alerts.AlertEmailField();
	        isValid = false;
	    } else {
	        emailAddressField.setStyle("");
	    }
	    if (!passwordField.getText().isEmpty() && !passwordMatcher.matches()) {
	        flashRedBorder(passwordField);
	        Alerts.AlertPasswordField();
	        isValid = false;
	    } else {
	        passwordField.setStyle("");
	    }
	    return isValid;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		emailAddressField.setText(JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","emailAddress"));
		passwordField.setText(JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","password"));

		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		passwordField.setText("Azerty123!");
		emailAddressField.setText("example@xyz.com");
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD
	}
}
