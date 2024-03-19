package com.fsb.networked;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        App.setRoot("SignUpScenes/SignUpPage");
    }
	
	@FXML
	private void resetPassword() throws IOException
	{
		App.setRoot("ForgotPasswordPage");
	}
	
	@FXML
	private void doSignIn() 
	{
		System.out.println("Signed in");
	}

	@FXML
	private void togglePasswordVisible()
	{
		
	}
	
	
}
