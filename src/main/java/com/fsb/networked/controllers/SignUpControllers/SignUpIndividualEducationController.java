package com.fsb.networked.controllers.SignUpControllers;

import com.fsb.networked.App;
import com.fsb.networked.dto.EducationDTO;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpIndividualEducationController implements Initializable {

	@FXML
	private TextField diplomaField;
	
	@FXML
	private TextField instituteField;
	
	@FXML
	private TextField diplomaTypeField;
	
	@FXML
	private TextArea descriptionTextArea;
		
	@FXML
	private DatePicker startDate;
	
	@FXML
	private DatePicker endDate;
	
	@FXML
	private Button btnAddEducation;
	
	@FXML
	private Button btnDeleteEducation;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private ListView<EducationDTO> educationListView;
	private final JSONArray educationArray = new JSONArray();
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignUpScenes/SignUpPageIndividualBasic");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		JSONParser.updateEducationJSONArray(educationArray);
		App.setRoot("SignUpScenes/SignUpPageIndividualWork");
		System.out.println("Education INFO gathered");
    }
	@FXML
	private void addEducation()
	{
		//DONE TODO : when add job btn is pressed the job must be added to the listview of jobs
		System.out.println("addEducation called");
		boolean res =  validateEducation();
		System.out.println(res);
		if(res)
		{
			EducationDTO education = new EducationDTO(diplomaField.getText(), instituteField.getText(),diplomaTypeField.getText(),descriptionTextArea.getText(),startDate.getValue(),endDate.getValue());
			//now clear all the fields
			instituteField.clear();
			diplomaField.clear();
			diplomaTypeField.clear();
			startDate.setValue(null);
			endDate.setValue(null);
			descriptionTextArea.clear();
			System.out.println("Number of items in educationListView: " + educationListView.getItems().size());
			educationListView.getItems().add(education);
			System.out.println("Number of items in educationListView: " + educationListView.getItems().size());

			educationArray.put(education.toJSON());
		}
	}
	@FXML
	private void deleteEducation()
	{
		//DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
		EducationDTO selectedEducation = educationListView.getSelectionModel().getSelectedItem();
		educationListView.getItems().remove(selectedEducation);
		educationArray.remove(educationListView.getItems().indexOf(educationListView.getSelectionModel().getSelectedItem()));
	}

	//TODO FIX ALL VALIDATE FUNCTIONS ,ARE BROKE NOW AFTER CODEBASE REFACTORING I SHOULDVE NEVER TOUCHED IT
	private boolean validateEducation() {
		boolean isValid = true;
		isValid &= (Validator.validateField(diplomaField, Regexes.TITLE_REGEX, Alerts.AlertTitleField()));
		isValid	&= Validator.validateField(diplomaTypeField, Regexes.TITLE_REGEX,Alerts.AlertTypeField());
		isValid	&= Validator.validateField(instituteField, Regexes.TITLE_REGEX,Alerts.AlertTitleField());
		isValid	&= Validator.validateField(descriptionTextArea, Regexes.DESCRIPTION_REGEX,Alerts.AlertDescriptionField());
		//the end date must NOT be before the start date
		if (startDate.getValue() == null ||  endDate.getValue() == null  ||  endDate.getValue().isBefore(startDate.getValue())) {
			Validator.flashRedBorder(endDate);
			isValid = false;
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

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		/*------------*/
		//held together by glue
		educationListView.setCellFactory(new Callback<ListView<EducationDTO>, ListCell<EducationDTO>>() {
            @Override
            public ListCell<EducationDTO> call(ListView<EducationDTO> param) {
                return new ListCell<EducationDTO>() {
                    @Override
                    protected void updateItem(EducationDTO education, boolean empty) {
                        super.updateItem(education, empty);
                        if (education == null || empty) {
                            setText(null);
                        } else {
                            setText("Education Diploma: " + education.getDiploma() + "\nEducation Institute: " + education.getInstitute()+ "\nEducation Type: " + education.getType() + "\nEducation start date : " + education.getStartDate()  + "\nEducation end date : " + education.getEndDate() + "\nEducation description: " + education.getDescription());
                        }
                    }
                };
            }
        });
		JSONArray educationArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpEducation");
		// Iterate over each skill object in the JSON array and extract each field
		for (int i = 0; i < educationArray.length(); i++) {
			JSONObject educationObject = educationArray.getJSONObject(i);
			String positionValue = educationObject.getString("diploma");
			String companyValue = educationObject.getString("institute");
			String descriptionValue = educationObject.getString("description");
			String typeValue = educationObject.getString("type");
			String startDateValue = educationObject.getString("startDate");
			String endDateValue = educationObject.getString("endDate");

			EducationDTO education = new EducationDTO(positionValue,companyValue,descriptionValue,typeValue, Conversions.stringtoLocalDate(startDateValue),Conversions.stringtoLocalDate(endDateValue));
			if(!(education.getDiploma().isBlank() && education.getType().isBlank() && education.getDescription().isBlank() && education.getInstitute().isBlank()))
			{
				educationListView.getItems().add(education);
				this.educationArray.put(educationObject);
			}
		}

	}
}
