package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.dto.Job;
import com.fsb.networked.dto.Skill;
import com.fsb.networked.utils.Alerts;

import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.JSONParser;
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
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}
	
	private boolean validateJob()
	{
		if(jobPositionField.getText().isEmpty() || companyField.getText().isEmpty() || jobTypeField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || startDate.getValue() == null || endDate.getValue() == null)
		{
			Alerts.AlertEmptyField();
			return false;
		}		
		Pattern positionPattern = Pattern.compile("^[a-zA-Z][a-zA-Z_-]{1,38}[a-zA-Z]$");
		Pattern technologyPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{1,38}[a-zA-Z0-9]$");
		
		Matcher positionMatcher = positionPattern.matcher(jobPositionField.getText());
		Matcher jobTypeMatcher = positionPattern.matcher(jobTypeField.getText());
		
		Matcher technologyMatcher = technologyPattern.matcher(companyField.getText());
		
		Pattern descriptionPattern  = Pattern.compile("^(?=.{1,150}$)[a-zA-Z0-9]+(?:[ _-][a-zA-Z0-9]+)*$");
		Matcher descriptionMatcher = descriptionPattern.matcher(descriptionTextArea.getText());
				
		if (companyField.getText().isEmpty() || !technologyMatcher.matches())
		{
			flashRedBorder(companyField);
			return false;
		}
		else
		{
			companyField.setStyle("");
			if (jobPositionField.getText().isEmpty() || !positionMatcher.matches())
			{
				flashRedBorder(jobPositionField);
				return false;
			}
			else
			{
				jobPositionField.setStyle("");
				if (jobTypeField.getText().isEmpty() || !jobTypeMatcher.matches())
				{
					flashRedBorder(jobTypeField);
					return false;
				}
				else
				{
					jobTypeField.setStyle("");
					if (descriptionTextArea.getText().isEmpty() || !descriptionMatcher.matches())
					{
						flashRedBorder(descriptionTextArea);
						return false;
					}
					else
					{
						descriptionTextArea.setStyle("");
						//end date should be after the start date
						if(endDate.getValue().isBefore(startDate.getValue()))
						{
							flashRedBorder(endDate);
							return false;
						}
					}	
				}	
				return true;
			}
		}
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
