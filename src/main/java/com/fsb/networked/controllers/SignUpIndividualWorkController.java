package com.fsb.networked.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.App;
import com.fsb.networked.dto.Job;
import com.fsb.networked.dto.Skill;
import com.fsb.networked.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

public class SignUpIndividualWorkController implements Initializable {

	@FXML
	private TextField jobPositionField;
	
	@FXML
	private TextField companyField;
	
	@FXML
	private TextField jobTypeField;
	
	@FXML
	private TextArea descriptionTextArea;
		
	@FXML
	private DatePicker startDate;
	
	@FXML
	private DatePicker endDate;
	
	@FXML
	private Button btnAddJob;
	
	@FXML
	private Button btnDeleteJob;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnCancel;

	@FXML
	private ListView<Job> jobListView;
	private JSONArray jobsArray = new JSONArray();
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignUpScenes/SignUpPageIndividualSkills");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		JSONParser.updateWorkJSONArray(jobsArray);
		App.setRoot("SignUpScenes/SignUpPageIndividualProject");
		System.out.println("Work INFO gathered");
    }

	
	@FXML
	private void addJob()
	{
		//DONE TODO : when add job btn is pressed the job must be added to the listview of jobs

		if(validateJob()) 
		{
			Job job = new Job(jobPositionField.getText(), companyField.getText(),jobTypeField.getText(),descriptionTextArea.getText(),startDate.getValue(),endDate.getValue());
			//now clear all the fields
			jobPositionField.clear();
			companyField.clear();
			jobTypeField.clear();
			startDate.setValue(null);
			endDate.setValue(null);
			descriptionTextArea.clear();
			jobListView.getItems().add(job);
			jobsArray.put(job.toJSON());
		}
	}
	
	@FXML
	private void deleteJob()
	{
		//DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
		Job selectedJob = jobListView.getSelectionModel().getSelectedItem();
		jobListView.getItems().remove(selectedJob);
		jobsArray.remove(jobListView.getItems().indexOf(jobListView.getSelectionModel().getSelectedItem()));
	}

	private boolean validateJob() {
		boolean isValid = true;
		isValid &= Validator.validateField(jobPositionField, Regexes.POSITION_REGEX);
		isValid &= Validator.validateField(companyField, Regexes.TECHNOLOGY_REGEX);
		isValid &= Validator.validateField(jobTypeField, Regexes.POSITION_REGEX);
		isValid &= Validator.validateField(descriptionTextArea, Regexes.DESCRIPTION_REGEX);

		//the end date must NOT be before the start date
		if (endDate.getValue().isBefore(startDate.getValue())) {
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
		jobListView.setCellFactory(new Callback<ListView<Job>, ListCell<Job>>() {
            @Override
            public ListCell<Job> call(ListView<Job> param) {
                return new ListCell<Job>() {
                    @Override
                    protected void updateItem(Job job, boolean empty) {
                        super.updateItem(job, empty);
                        if (job == null || empty) {
                            setText(null);
                        } else {
                            setText("Job position: " + job.getPosition() + "\nJob technology: " + job.getCompany() + "\nJob type: " + job.getType() + "\nJob start date : " + job.getStartDate()  + "\nJob end date : " + job.getEndDate() + "\nJob description: " + job.getDescription());
                        }
                    }
                };
            }
        });
		JSONArray jobsArray = JSONParser.getJobsJSONArray("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON");
		// Iterate over each skill object in the JSON array and extract each field
		for (int i = 0; i < jobsArray.length(); i++) {
			JSONObject jobObject = jobsArray.getJSONObject(i);
			String positionValue = jobObject.getString("position");
			String companyValue = jobObject.getString("company");
			String descriptionValue = jobObject.getString("description");
			String typeValue = jobObject.getString("type");
			String startDateValue = jobObject.getString("startDate");
			String endDateValue = jobObject.getString("endDate");

			//create a skill object with those extracted values
			Job job = new Job(positionValue,companyValue,descriptionValue,typeValue, Conversions.stringtoLocalDate(startDateValue),Conversions.stringtoLocalDate(endDateValue));
			//add that skill object to the skillsListView list of items(skills)
			//check if all fields are empty then don't add it
			//without this check at the first signUp the user will find an empty ghost task
			if(!(job.getPosition().isBlank() && job.getType().isBlank() && job.getDescription().isBlank() && job.getCompany().isBlank()))
			{
				jobListView.getItems().add(job);
				//put the skillObject in the skillsArrays so that already filled in skills won't get lost after a scene go and back idk how to explain just trust
				this.jobsArray.put(jobObject);
			}
		}

	}
}
