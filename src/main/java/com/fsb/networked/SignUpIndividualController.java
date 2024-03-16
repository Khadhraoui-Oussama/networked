package com.fsb.networked;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SignUpIndividualController {

	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField lastNameField;
	
	@FXML
	private DatePicker dateOfBirthPicker;
	
	@FXML
	private ComboBox<String> countryComboBox;
	
	@FXML
	private ComboBox<String> genderComboBox;
	
	
	@FXML
	private TextField physicalAdressField ;

	@FXML
	private Button btnUploadProfilePicture;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private ImageView profilePictureImg;
	
	@FXML
	private Label statusLabel;
	
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignInPage");
    }
	
	@FXML
    private void goNext() throws IOException
	{
        App.setRoot("SiginUpPageIndividualWork");
    }
	
	
	@FXML
	private void uploadProfilePicture()
	{
		final FileChooser fc  = new FileChooser();
		//set the title
		fc.setTitle("Choose a profile picture to use ");
		//set the initial directory (default one)
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		//extension filters 
		//clear all extension filters
		fc.getExtensionFilters().clear();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", "."));
		
		//set the selected file or use null if no file has been selected
		File file = fc.showOpenDialog(null);
		
		if(file != null)
		{
			//set the image view source as the file path
			profilePictureImg.setImage(new Image(file.toURI().getPath()));
			statusLabel.setText("Profile Image uploaded successfully");
		}
		else
		{

			statusLabel.setText("Invalid profile image used !!");
		}
		
		
		
		
		
	}
	
	
}
