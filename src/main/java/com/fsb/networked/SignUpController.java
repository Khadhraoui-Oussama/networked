package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.utils.Alerts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private void gotoSignUpIndivdual() throws IOException
	{
		if(validateBasicInfo())
		{
        App.setRoot("SignUpControllers/SignUpPageIndividualBasic");
		}
	}
	
	@FXML
    private void gotoSignUpEntreprise() throws IOException
	{
		if(validateBasicInfo())
		{
			App.setRoot("SignUpControllers/SignUpPageEntrepriseBasic");
		}
    }
	
	@FXML
    private void cancelSignUp() throws IOException
	{
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
		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		passwordField.setText("Azerty123!");
		emailAddressField.setText("example@xyz.com");
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD
	}

	
	
}
