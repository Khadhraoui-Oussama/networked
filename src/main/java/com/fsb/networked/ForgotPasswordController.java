package com.fsb.networked;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.fsb.networked.service.ForgotPasswordService;
public class ForgotPasswordController {

	private ForgotPasswordService forgotPasswordService;
	
	public ForgotPasswordController()
	{
		this.forgotPasswordService = new ForgotPasswordService();
	}
	
	@FXML
	private Button btnSignIn;
	
	@FXML
	private Button btnSignUp;

	@FXML 
	private TextField emailAddressField;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnBack;
	
	@FXML
    private void gotoSignIn() throws IOException {
        App.setRoot("SignInPage");
    }
	
	@FXML
    private void gotoSignUp() throws IOException {
        App.setRoot("SignUpPage");
    }
	
	@FXML
	private void goBack() throws IOException {
        App.setRoot("SignInPage");
    }
	
	@FXML
	private void sendRecoveryEmail() throws IOException {
		forgotPasswordService.sendMail();
    }
	
}
