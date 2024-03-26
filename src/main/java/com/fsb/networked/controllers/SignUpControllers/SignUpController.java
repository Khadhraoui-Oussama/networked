package com.fsb.networked.controllers.SignUpControllers;

import com.fsb.networked.App;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private void gotoSignUpIndividual() throws IOException {
		if (Validator.validateTextFieldSQLUnique(emailAddressField,Alerts.AlertEmailInUse(),"email","individual") && validateBasicInfo()) {
			JSONParser.writeToJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUp", "emailAddress", emailAddressField.getText());
			JSONParser.writeToJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUp", "password", passwordField.getText());
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
			JSONParser.writeToJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","picture",image.getUrl());
			//go to next scene
			App.setRoot("SignUpScenes/SignUpPageIndividualBasic");
		}
	}
	@FXML
    private void gotoSignUpEntreprise() throws IOException
	{
		if(Validator.validateTextFieldSQLUnique(emailAddressField,Alerts.AlertEmailInUse(),"email","individual") && validateBasicInfo())
		{
			JSONParser.writeToJSONFile(ImportantFileReferences.ENTREPRISEJSON, "signUp", "email", emailAddressField.getText());
			JSONParser.writeToJSONFile(ImportantFileReferences.ENTREPRISEJSON, "signUp", "password", passwordField.getText());
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
	
	private boolean validateBasicInfo() {
		boolean isValid = true;
		isValid &= Validator.validateField(emailAddressField, Regexes.EMAIL_REGEX,Alerts.AlertEmailField());
		isValid &= Validator.validateField(passwordField, Regexes.PASSWORD_REGEX,Alerts.AlertPasswordField());
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
