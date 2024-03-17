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

import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ComboBoxes;

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
	private ImageView profilePictureImg;
	
	@FXML
	private Label statusLabel;
	
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignUpPage");
    }
	
	
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
		//TODO: implement a way to save the basic data so it be ready and waiting for the work data
        //one idea : push the basic data we have to the database 
		//and then push the work data with WHERE clause instead of waiting
		//DONE TODO : do the same for the email and password fields in the SignUpPage scene
		
		validateBasicInfo();
		App.setRoot("SignUpPageIndividualSkills");
		System.out.println("Basic Individual Information gathered");
		
    }
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}
	
	private void validateBasicInfo()
	{
		
		//border flashes if no value is entered & a popup to alert the user
		//hacky solution BUT if the alert is called for each field ,
		//it will get instanciated times the number of incomplete fields
		if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || dateOfBirthPicker.getValue() == null || physicalAdressField.getText().isEmpty())
		{
			Alerts.AlertEmptyField();
		}
		
		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{1,39}$");
		Matcher fnameMatcher = namePattern.matcher(firstNameField.getText());
		Matcher lnameMatcher = namePattern.matcher(lastNameField.getText());
		
		Pattern addressPattern  = Pattern.compile("^[\\w\\s.,'-]+(?:\\s[\\w\\s.,'-]+)*$");
		Matcher addressMatcher = addressPattern.matcher(physicalAdressField.getCharacters());
		
		if (firstNameField.getText().isEmpty())
		{
			flashRedBorder(firstNameField);
		}
		else
		{
			firstNameField.setStyle("");
			if(!fnameMatcher.matches())
			{
				flashRedBorder(firstNameField);
				Alerts.AlertNameField();
			}
			
		}
		if (lastNameField.getText().isEmpty())
		{
			
			flashRedBorder(lastNameField);
		}
		else
		{
			lastNameField.setStyle("");	
			if(!lnameMatcher.matches() ) 
			{
				flashRedBorder(lastNameField);
				if(fnameMatcher.matches()) // this is to only alert once if both first and last names dont match regex
				{
					Alerts.AlertNameField();
		
				}
			}
		}
		
		if (dateOfBirthPicker.getValue() == null)
		{
			flashRedBorder(dateOfBirthPicker);
		}
		else
		{
			dateOfBirthPicker.setStyle("");
			if((Year.now().getValue() - dateOfBirthPicker.getValue().getYear()) < 16)
			{
				flashRedBorder(dateOfBirthPicker);
				Alerts.AlertDOBField();
			}
		}
		if (physicalAdressField.getText().isEmpty())
		{
			flashRedBorder(physicalAdressField);
		}
		else
		{
			physicalAdressField.setStyle("");
			if(!addressMatcher.matches())
			{
				flashRedBorder(physicalAdressField);
				Alerts.AlertAddressField();
			}
		}
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
		countryComboBox.getSelectionModel().select(177);
		//we do the same for the Gender ComboBox
		genderComboBox.getItems().addAll(ComboBoxes.GENDERS);
		genderComboBox.getSelectionModel().select(0);
		
		//THIS IS ONLY TO SPEED UP THE DEVELEOPMENT REMOVE WHEN READY TO PUSH TO PROD
		dateOfBirthPicker.setValue(LocalDate.of(2003, 10, 4));
		firstNameField.setText("John");
		lastNameField.setText("Doe");
		physicalAdressField.setText("10 Jarzouna");
		//REMOVE ALL THE ABOVE WHEN READY TO PUSH TO PROD
	}



}
