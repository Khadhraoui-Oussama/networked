package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.dao.Job;
import com.fsb.networked.dao.Skill;
import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ComboBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class SignUpIndividualVideoController implements Initializable {

	@FXML
	private TextField jobPositionField;
	
	@FXML
	private TextField technologyField;
	
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
	private ListView<Job> jobListView;
	
	
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignUpControllers/SignUpPageIndividualSkills");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		App.setRoot("SignUpControllers/SignUpPageIndividualWork");
		System.out.println("Work INFO gathered");
    }

	
	@FXML
	private void addJob()
	{
		//DONE TODO : when add job btn is pressed the job must be added to the listview of jobs
		
		if(validateJob()) 
		{
			jobListView.getItems().add(new Job(jobPositionField.getText(),technologyField.getText(),jobTypeField.getText(),descriptionTextArea.getText(),startDate.getValue(),endDate.getValue()));
			//now clear all the fields
			jobPositionField.clear();
			technologyField.clear();
			jobTypeField.clear();
			startDate.setValue(null);
			endDate.setValue(null);
			descriptionTextArea.clear();
		}
	}
	
	@FXML
	private void deleteJob()
	{
		//DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
		Job selectedJob = jobListView.getSelectionModel().getSelectedItem();
		jobListView.getItems().remove(selectedJob);
	}
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}
	
	private boolean validateJob()
	{
		if(jobPositionField.getText().isEmpty() || technologyField.getText().isEmpty() || jobTypeField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || startDate.getValue() == null || endDate.getValue() == null)
		{
			Alerts.AlertEmptyField();
			return false;
		}		
		Pattern positionPattern = Pattern.compile("^[a-zA-Z][a-zA-Z_-]{1,38}[a-zA-Z]$");
		Pattern technologyPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{1,38}[a-zA-Z0-9]$");
		
		Matcher positionMatcher = positionPattern.matcher(jobPositionField.getText());
		Matcher jobTypeMatcher = positionPattern.matcher(jobTypeField.getText());
		
		Matcher technologyMatcher = technologyPattern.matcher(technologyField.getText());
		
		Pattern descriptionPattern  = Pattern.compile("^(?=.{1,150}$)[a-zA-Z0-9]+(?:[ _-][a-zA-Z0-9]+)*$");
		Matcher descriptionMatcher = descriptionPattern.matcher(descriptionTextArea.getText());
				
		if (technologyField.getText().isEmpty() || !technologyMatcher.matches())
		{
			flashRedBorder(technologyField);
			return false;
		}
		else
		{
			technologyField.setStyle("");
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
                            setText("Job position: " + job.getPosition() + "\nJob technology: " + job.getTechnology() + "\nJob type: " + job.getType() + "\nJob start date : " + job.getStartDate()  + "\nJob end date : " + job.getEndDate() + "\nJob description: " + job.getDescription()); 
                        }
                    }
                };
            }
        });
	
	}
	
	
}
