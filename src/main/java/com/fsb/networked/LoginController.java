package com.fsb.networked;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class LoginController {

	@FXML
	private Button btnSignIn;
	
	@FXML
	private Button btnSignUp;
	
	@FXML
    private void gotoSignIn() throws IOException {
        App.setRoot("SignInPage");
    }
	@FXML
    private void gotoSignUp() throws IOException {
        App.setRoot("SignUpPage");
    }
	
}
