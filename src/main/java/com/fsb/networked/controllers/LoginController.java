package com.fsb.networked.controllers;

import com.fsb.networked.App;
import com.fsb.networked.utils.FilePaths;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
public class LoginController {

	@FXML
	private Button btnSignIn;
	
	@FXML
	private Button btnSignUp;
	
	@FXML
    private void gotoSignIn() throws IOException {
        // original stmt App.setRoot("SignInPage");
        App.setRoot("HomePageScenes/HomePageIndividual");
       // FilePaths.chooseDirectoryToSaveTo();
    }
	@FXML
    private void gotoSignUp() throws IOException {
        App.setRoot("SignUpScenes/SignUpPageCommon");
        System.out.println(FilePaths.getImagePath("/images/default_user.png"));
    }	
	
}
