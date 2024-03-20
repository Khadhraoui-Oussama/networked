package com.fsb.networked.controllers;

import com.fsb.networked.App;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpEntrepriseController implements Initializable {

	@FXML
	private TextField companyNameField;

	@FXML
	private ComboBox<String> sectorComboBox;

	@FXML
	private DatePicker dateOfFoundationDatePicker;

	@FXML
	private ComboBox<String> sizeComboBox;

	@FXML
	private TextField foundersTextField;

	@FXML
	private TextField websiteTextField;

	@FXML
	private TextField locationTextField;

	@FXML
	private Button btnUploadProfilePicture;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private ImageView companyLogoImageView;

	@FXML
	private Label statusLabel;

	@FXML
	private void goNext() throws IOException {
		boolean res =  validateInfo();
		System.out.println(res);
		if (res) {
			// TODO : FILL OUT THE DTO FOR ENTREPRISE
			//debug stmts
			System.out.println("company name : " + companyNameField.getText());
			System.out.println("company sector : " + sectorComboBox.getValue());
			System.out.println("date  : " + dateOfFoundationDatePicker.getValue());
			System.out.println("size : " + sizeComboBox.getValue());
			System.out.println("founders : " + foundersTextField.getText());
			System.out.println("website : " + websiteTextField.getText());
			System.out.println("location : " + locationTextField.getText());
			App.setRoot("SignUpScenes/SignUpPageIndividualSkills");
			System.out.println("Entreprise Information gathered");
			//
		}
	}
	@FXML
	private void goBack() throws IOException {
		App.setRoot("SignUpScenes/SignUpPageCommon");
	}

	private boolean validateInfo() {
		boolean isValid = true;

		//LocalDate date = dateOfFoundationDatePicker.getValue();
		//isValid &= (date != null && date.isBefore(LocalDate.now()));
		//System.out.println("Dob:" + isValid);
		isValid &= Validator.validateField(companyNameField, Regexes.FOUNDERS_REGEX);
		System.out.println("company name :" + isValid);
		isValid &= Validator.validateField(foundersTextField, Regexes.FOUNDERS_REGEX);
		System.out.println("Founders :" + isValid);
		isValid &= Validator.validateField(websiteTextField, Regexes.LINK_REGEX);
		System.out.println("Website :" + isValid);
		isValid &= Validator.validateField(locationTextField, Regexes.ADDRESS_REGEX);
		System.out.println("Address :" + isValid);

		isValid &= Validator.validateField(dateOfFoundationDatePicker, null); // Date is not validated using regex
		return isValid;
	}


	@FXML
	private void cancelSignUp() throws IOException {
		//if the user decides he no longer want to sign up the json files must be cleared of all inputs
		// and returned to the original state
		JSONParser.resetIndividualJSONFile();
		JSONParser.resetEntrepriseJSONFile();
		App.setRoot("LogInPage");
	}

	@FXML
	private void uploadProfilePicture() {
		final FileChooser fc = new FileChooser();
		//set the title
		fc.setTitle("Choose a profile picture to use ");
		//set the initial directory (default one)
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		//extension filters
		//clear all extension filters
		fc.getExtensionFilters().clear();
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		//set the selected file or use null if no file has been selected
		File file = fc.showOpenDialog(null);
		if (file != null) {
			System.out.println("path :" + file.toURI().getPath());
			// set the image view source as the file path
			companyLogoImageView.setImage(new Image(file.toURI().toString()));
			statusLabel.setText("Profile Image uploaded successfully");
		} else {
			statusLabel.setText("Invalid profile image used !!");
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sectorComboBox.getItems().addAll(ComboBoxes.SECTORS);
		sizeComboBox.getItems().addAll(ComboBoxes.COMPANY_SIZES);

		//image
		String imageURL = FilePaths.getImagePath("/images/anonymous_logo.jpg");
		companyLogoImageView.setImage(new Image(imageURL));


		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		dateOfFoundationDatePicker.setValue(LocalDate.of(2003, 10, 4));
		companyNameField.setText("Boga Cidre");
		foundersTextField.setText("Ali Ben Bouga ,Sirine Cidraoui");
		locationTextField.setText("200 Jarzouna");
		websiteTextField.setText("https://bogaCidre.com");
		sectorComboBox.getSelectionModel().select(2);
		sizeComboBox.getSelectionModel().select(3);
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD

		}

}

