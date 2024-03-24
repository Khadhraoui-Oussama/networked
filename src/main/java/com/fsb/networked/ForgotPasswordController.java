package com.fsb.networked;

import com.fsb.networked.service.ForgotPasswordService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
public class ForgotPasswordController {

	private final ForgotPasswordService forgotPasswordService;
	
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
        App.setRoot("SignUpControllers/SignUpPageCommon");
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
