package com.fsb.networked.controllers.SignUpControllers;

import com.fsb.networked.App;
import com.fsb.networked.dto.EntrepriseDTO;
import com.fsb.networked.service.EntrepriseService;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpEntrepriseController implements Initializable {

	@FXML
	private TextField entrepriseNameField;

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
	private ImageView entrepriseLogoImageView;

	@FXML
	private Label statusLabel;

	@FXML
	private void finishSignUpEntreprise() throws IOException {
		boolean res =  validateInfo();
		System.out.println(res);
		if (res) {
			// TODO : FILL OUT THE DTO FOR ENTREPRISE
			EntrepriseDTO entrepriseDTO = new EntrepriseDTO();
			//FILL THE DTO CLASS WITH DATA FROM THE CONTROLLER COMPONENTS
			entrepriseDTO.setEntrepriseName( entrepriseNameField.getText());
			entrepriseDTO.setEntrepriseFounders(foundersTextField.getText());
			entrepriseDTO.setEntrepriseDateOfFoundation(dateOfFoundationDatePicker.getValue());
			entrepriseDTO.setEntrepriseLocation(locationTextField.getText());
			entrepriseDTO.setEntrepriseSector(sectorComboBox.getValue());
			entrepriseDTO.setEntrepriseLogoPath(entrepriseLogoImageView.getImage().getUrl());
			entrepriseDTO.setEntrepriseSize(sizeComboBox.getValue());
			entrepriseDTO.setEntrepriseWebsite( websiteTextField.getText());
			entrepriseDTO.setPassword(JSONParser.getValueFromJSONFile(ImportantFileReferences.ENTREPRISEJSON,"signUp","password"));
			entrepriseDTO.setEmail(JSONParser.getValueFromJSONFile(ImportantFileReferences.ENTREPRISEJSON,"signUp","email"));
			//save to DB
			EntrepriseService entrepriseService = new EntrepriseService();
			int entrepriseID = entrepriseService.saveEntrepriseToDB(entrepriseDTO);
			SessionManager.setID(entrepriseID);
		}
	}
	@FXML
	private void goBack() throws IOException {
		App.setRoot("SignUpScenes/SignUpPageCommon");
	}

	private boolean validateInfo() {
		boolean isValid = true;

		isValid &= Validator.validateField(entrepriseNameField, Regexes.TITLE_REGEX,Alerts.AlertTitleField());
		System.out.println("company name :" + isValid);
		isValid &= Validator.validateField(foundersTextField, Regexes.FOUNDERS_REGEX,Alerts.AlertFoundersField());
		System.out.println("Founders :" + isValid);
		isValid &= Validator.validateField(websiteTextField, Regexes.LINK_REGEX,Alerts.AlertLinkField());
		System.out.println("Website :" + isValid);
		isValid &= Validator.validateField(locationTextField, Regexes.LOCATION_REGEX,Alerts.AlertAddressField());
		System.out.println("Address :" + isValid);
		isValid &= Validator.validateField(dateOfFoundationDatePicker, null,null); // Date is not validated using regex
		isValid &= Validator.validateImageSize(entrepriseLogoImageView,entrepriseLogoImageView.getParent(),Alerts.AlertImageSizeTooBig());
		isValid &= Validator.validateImagePath(entrepriseLogoImageView,entrepriseLogoImageView.getParent(),Alerts.AlertImagePathTooLong());
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
		File file = FileLoader.uploadPicture("Choose a profile picture to use ");
		if (file != null) {
			System.out.println("path :" + file.toURI().getPath());
			// set the image view source as the file path
			entrepriseLogoImageView.setImage(new Image(file.toURI().toString()));
			statusLabel.setText("Profile Image uploaded successfully");
		} else {
			statusLabel.setText("Invalid profile image used !!");
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sectorComboBox.getItems().addAll(ComboBoxes.SECTORS);
		sizeComboBox.getItems().addAll(ComboBoxes.COMPANY_SIZES);
		//TODO FIX THE ENTREPRISE INITILAIZE WITH JSON FILE AND DAO /SERVICE
		//image
		String imageURL = FileLoader.getImagePath("/images/anonymous_logo.jpg");
		entrepriseLogoImageView.setImage(new Image(imageURL));

		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		dateOfFoundationDatePicker.setValue(LocalDate.of(2003, 10, 4));
		entrepriseNameField.setText("Boga Cidre");
		foundersTextField.setText("Ali Ben Bouga ,Sirine Cidraoui");
		locationTextField.setText("200 Jarzouna");
		websiteTextField.setText("https://bogaCidre.com");
		sectorComboBox.getSelectionModel().select(2);
		sizeComboBox.getSelectionModel().select(3);
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD

	}
}