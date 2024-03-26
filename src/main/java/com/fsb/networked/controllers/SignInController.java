package com.fsb.networked.controllers;

import com.fsb.networked.App;
import com.fsb.networked.service.EntrepriseService;
import com.fsb.networked.service.IndividualService;
import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.Regexes;
import com.fsb.networked.utils.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {

	@FXML
	private TextField emailAddressField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Button btnTogglePasswordVisibile;
	
	@FXML
	private Button btnForgotPassword;
	
	@FXML
	private Button btnSignIn;
	
	@FXML
	private Button btnSignUp;
	
	@FXML
    private void gotoSignUp() throws IOException
	{
        App.setRoot("SignUpScenes/SignUpPageCommon");
    }
	
	@FXML
	private void resetPassword() throws IOException
	{
		App.setRoot("ForgotPasswordPage");
		System.out.println(App.getFxmlPath());
	}

	@FXML
	private void doSignIn() throws IOException {
		IndividualService individualService = new IndividualService();
		EntrepriseService entrepriseService = new EntrepriseService();

		// Validate if email and password fields are filled
		if (validateBasicInfo()) {
			// Check if individual exists
			int individualResult = individualService.individualExists(emailAddressField.getText(), passwordField.getText());
			if (individualResult != -1) {
				// Individual exists, redirect to individual home page
				App.setRoot("HomePageScenes/HomePageIndividual");
				System.out.println("Signed in as individual");
				return;
			} else if (individualResult == -1) {
				// Individual does not exist, check if enterprise exists
				int enterpriseResult = entrepriseService.entrepriseExists(emailAddressField.getText(), passwordField.getText());
				if (enterpriseResult != -1) {
					// Enterprise exists, redirect to enterprise home page
					App.setRoot("HomePageScenes/HomePageEntreprise");
					System.out.println("Signed in as entreprise");
					return;
				}
			}
			else Alerts.AlertCheckInput().showAndWait();
		}
		// No user found or invalid input, show alert

	}

	private boolean validateBasicInfo() {
		boolean isValid = true;
		isValid &= Validator.validateField(emailAddressField, Regexes.EMAIL_REGEX,Alerts.AlertEmailField());
		isValid &= Validator.validateField(passwordField, Regexes.PASSWORD_REGEX,Alerts.AlertPasswordField());
		return isValid;
	}

	@FXML
	private void togglePasswordVisible()
	{
		
	}
	
	
}
