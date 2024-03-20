package com.fsb.networked;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ComboBoxes;

import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.JSONParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SignUpIndividualController implements Initializable {

	
	//TODO : research how to fill out the DTO with the info given from the form
	
	
	//DONE TODO : implement constraints in the basic info fields : 
	//like a minimum length for the names , or an age minimum of 16 years
	//( do we add an "Im a student in the work tab ??")	
	//DONE TODO : add a use default profile picture that changes whether the user is male or female
	//DONE TODO : implement a method to validate that the inputs are filled in,
	//if one of the fields is left out flash it with a bright red color 
	//DONE TODO : fill the Country combo box with all the countries & add autofill
	//DONE TODO : do the same for the gender combo box
	//DONE TODO : add an alert when one or more fields are left out by the user
	
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
	private Button btnUseDefaultProfilePicture;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;


	@FXML
	private ImageView profilePictureImg;
	
	@FXML
	private Label statusLabel;

	@FXML 
	private void selectDefaultProfilePicture() 
	{
		System.out.println("default \n");
		if (genderComboBox.getValue().equals("Female"))
		{
			File file;
			try
			{
				file = new File(getClass().getResource("/images/female_avatar.png").toURI());
				Image im = new Image(file.toURI().toString());
				System.out.println(im.getUrl());
				profilePictureImg.setImage(im);
				
			} catch (URISyntaxException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			File file;
			try
			{
				file = new File(getClass().getResource("/images/male_avatar.png").toURI());
				Image im = new Image(file.toURI().toString());
				System.out.println(im.getUrl());
				profilePictureImg.setImage(im);
				
			} catch (URISyntaxException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@FXML
    private void goNext() throws IOException
	{
		//DONE JSON TODO: implement a way to save the basic data so it be ready and waiting for the work data
        //one idea : push the basic data we have to the database 
		//and then push the work data with WHERE clause instead of waiting
		//DONE TODO : do the same for the email and password fields in the SignUpPage scene
		
		if(validateBasicInfo())
		{
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "firstName", firstNameField.getText());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "lastName", lastNameField.getText());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "country", countryComboBox.getValue());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "address", physicalAdressField.getText());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "gender", genderComboBox.getValue());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "dob", dateOfBirthPicker.getValue());
			JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "picture", profilePictureImg.getImage().getUrl());

			App.setRoot("SignUpScenes/SignUpPageIndividualSkills");
			System.out.println("Basic Individual Information gathered");
		}

		
    }
	@FXML
	private void goBack() throws IOException {
		App.setRoot("SignUpScenes/SignUpPage");
	}
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}

	private boolean validateBasicInfo() {

		//border flashes if no value is entered & a popup to alert the user
		//hacky solution BUT if the alert is called for each field ,
		//it will get instanciated times the number of incomplete fields
		boolean isValid = true;

		if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || dateOfBirthPicker.getValue() == null || physicalAdressField.getText().isEmpty()) {
			Alerts.AlertEmptyField();
			isValid = false;
		}

		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{1,39}$");
		Matcher fnameMatcher = namePattern.matcher(firstNameField.getText());
		Matcher lnameMatcher = namePattern.matcher(lastNameField.getText());

		Pattern addressPattern = Pattern.compile("^[\\w\\s.,'-]+(?:\\s[\\w\\s.,'-]+)*$");
		Matcher addressMatcher = addressPattern.matcher(physicalAdressField.getText());

		if (firstNameField.getText().isEmpty() || !fnameMatcher.matches()) {
			flashRedBorder(firstNameField);
			isValid = false;
		} else {
			firstNameField.setStyle("");
		}

		if (lastNameField.getText().isEmpty() || !lnameMatcher.matches()) {
			flashRedBorder(lastNameField);
			if (fnameMatcher.matches()) { // this is to only alert once if both first and last names don't match regex
				Alerts.AlertNameField();
			}
			isValid = false;
		} else {
			lastNameField.setStyle("");
		}
		if (dateOfBirthPicker.getValue() == null || (Year.now().getValue() - dateOfBirthPicker.getValue().getYear()) < 16) {
			flashRedBorder(dateOfBirthPicker);
			isValid = false;
		} else {
			dateOfBirthPicker.setStyle("");
		}
		if (physicalAdressField.getText().isEmpty() || !addressMatcher.matches()) {
			flashRedBorder(physicalAdressField);
			isValid = false;
		} else {
			physicalAdressField.setStyle("");
		}
		return isValid;
	}

	@FXML
	private void cancelSignUp() throws IOException
	{
		//if the user decides he no longer want to sign up the json files must be cleared of all inputs
		// and returned to the original state
		JSONParser.resetIndividualJSONFile();
		JSONParser.resetEntrepriseJSONFile();
		App.setRoot("LogInPage");
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
		fc.getExtensionFilters().addAll(
		    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		
		//set the selected file or use null if no file has been selected
		File file = fc.showOpenDialog(null);
		if (file != null) {
		    System.out.println("path :" + file.toURI().getPath());
		    // set the image view source as the file path
		    profilePictureImg.setImage(new Image(file.toURI().toString()));
		    statusLabel.setText("Profile Image uploaded successfully");
		} else {
		    statusLabel.setText("Invalid profile image used !!");
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//fill in the ComboBox with the country list from the ComboBoxes util class
		countryComboBox.getItems().addAll(ComboBoxes.COUNTRIES);
		//little easter egg: set the default selected country to Tunisia which is at index 177

		//we do the same for the Gender ComboBox
		genderComboBox.getItems().addAll(ComboBoxes.GENDERS);

		//load the previously entered data ,so we don't lose it if we decide to go back to check for error in input
		firstNameField.setText(JSONParser.getValueFromJSONFile(
				"src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",
				"signUpBasic",
				"firstName"));
		lastNameField.setText(JSONParser.getValueFromJSONFile(
				"src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",
				"signUpBasic",
				"lastName"));
		countryComboBox.getSelectionModel().select(JSONParser.getValueFromJSONFile(
				"src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",
				"signUpBasic",
				"country"));
		physicalAdressField.setText(JSONParser.getValueFromJSONFile(
				"src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",
				"signUpBasic",
				"address"));
		genderComboBox.getSelectionModel().select(JSONParser.getValueFromJSONFile(
				"src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON",
				"signUpBasic",
				"gender"));
		//image
		String imageURL = JSONParser.getValueFromJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "picture");
		profilePictureImg.setImage(new Image(imageURL));
		//and DOB
		String dobString = JSONParser.getValueFromJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpBasic", "dob");
		if(!dobString.isBlank())
		{
			//format the string dob so that it becomes a LocalDate object
			LocalDate dob = Conversions.stringtoLocalDate(dobString);
			dateOfBirthPicker.setValue(dob);
		}
		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		dateOfBirthPicker.setValue(LocalDate.of(2003, 10, 4));
		firstNameField.setText("John");
		lastNameField.setText("Doe");
		physicalAdressField.setText("10 Jarzouna");
		countryComboBox.getSelectionModel().select(177);
		genderComboBox.getSelectionModel().select(0);
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD
	}
}
