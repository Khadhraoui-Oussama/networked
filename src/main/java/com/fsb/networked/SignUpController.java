package com.fsb.networked;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

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
    private void gotoSignUpIndivdual() throws IOException
	{
        App.setRoot("SignUpPageIndividualBasic");
    }
	
	@FXML
    private void gotoSignUpEntreprise() throws IOException
	{
        App.setRoot("SignUpPageEntrepriseBasic");
    }
	

	@FXML
    private void cancelSignUp() throws IOException
	{
        App.setRoot("LogInPage");
    }
	
}
