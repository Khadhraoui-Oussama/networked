package com.fsb.networked;

import com.fsb.networked.App;
import com.fsb.networked.dto.Education;
import com.fsb.networked.dto.Job;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.JSONParser;
import com.fsb.networked.utils.Regexes;
import com.fsb.networked.utils.Validator;
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
	private ListView<Education> educationListView;
	private JSONArray educationArray = new JSONArray();
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
			Education education = new Education(diplomaField.getText(), instituteField.getText(),diplomaTypeField.getText(),descriptionTextArea.getText(),startDate.getValue(),endDate.getValue());
			//now clear all the fields
			instituteField.clear();
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
		Education selectedEducation = educationListView.getSelectionModel().getSelectedItem();
		educationListView.getItems().remove(selectedEducation);
		educationArray.remove(educationListView.getItems().indexOf(educationListView.getSelectionModel().getSelectedItem()));
	}

	//TODO FIX ALL VALIDATE FUNCTIONS ,ARE BROKE NOW AFTER CODEBASE REFACTORING I SHOULDVE NEVER TOUCHED IT
	private boolean validateEducation() {
		boolean isValid = true;
		isValid = (Validator.validateField(diplomaField, Regexes.FOUNDERS_REGEX)
				&& Validator.validateField(instituteField, Regexes.FOUNDERS_REGEX)
				&& Validator.validateField(diplomaTypeField, Regexes.FOUNDERS_REGEX)
				&& Validator.validateField(descriptionTextArea, Regexes.FOUNDERS_REGEX));
		//the end date must NOT be before the start date
		if (endDate.getValue()!=null && startDate.getValue()!=null) {
			if (endDate.getValue().isBefore(startDate.getValue())) {
				Validator.flashRedBorder(endDate);
				isValid = false;
			}
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
		educationListView.setCellFactory(new Callback<ListView<Education>, ListCell<Education>>() {
            @Override
            public ListCell<Education> call(ListView<Education> param) {
                return new ListCell<Education>() {
                    @Override
                    protected void updateItem(Education education, boolean empty) {
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
		JSONArray educationArray = JSONParser.getEducationsJSONArray("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON");
		// Iterate over each skill object in the JSON array and extract each field
		for (int i = 0; i < educationArray.length(); i++) {
			JSONObject educationObject = educationArray.getJSONObject(i);
			String positionValue = educationObject.getString("diploma");
			String companyValue = educationObject.getString("institute");
			String descriptionValue = educationObject.getString("description");
			String typeValue = educationObject.getString("type");
			String startDateValue = educationObject.getString("startDate");
			String endDateValue = educationObject.getString("endDate");

			Education education = new Education(positionValue,companyValue,descriptionValue,typeValue, Conversions.stringtoLocalDate(startDateValue),Conversions.stringtoLocalDate(endDateValue));
			if(!(education.getDiploma().isBlank() && education.getType().isBlank() && education.getDescription().isBlank() && education.getInstitute().isBlank()))
			{
				educationListView.getItems().add(education);
				this.educationArray.put(educationObject);
			}
		}

	}
}
