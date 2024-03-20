package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.dto.Job;
import com.fsb.networked.dto.Project;
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

public class SignUpIndividualProjectController implements Initializable {

	@FXML
	private TextField titleField;
	
	@FXML
	private TextField technologyField;
	
	@FXML
	private TextField linkField;
	
	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnAddProject;
	
	@FXML
	private Button btnDeleteProject;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private ListView<Project> projectListView;

	JSONArray projectsArray = new JSONArray();
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignUpScenes/SignUpPageIndividualSkills");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		JSONParser.updateProjectsJSONArray(projectsArray);
		App.setRoot("SignUpScenes/SignUpPageIndividualVideo");
		System.out.println("Project INFO gathered");
    }

	
	@FXML
	private void addProject()
	{
		//DONE TODO : when add job btn is pressed the job must be added to the listview of jobs
		
		if(validateProject())
		{
			Project project = new Project(titleField.getText(),technologyField.getText(),linkField.getText(),descriptionTextArea.getText());
			//now clear all the fields
			titleField.clear();
			technologyField.clear();
			linkField.clear();
			descriptionTextArea.clear();
			projectListView.getItems().add(project);
			projectsArray.put(project.toJSON());
		}
	}
	@FXML
	private void deleteProject()
	{
		//DONE TODO : when delete skill btn is presses delete the job from the listview of jobs
		Project selectedproject = projectListView.getSelectionModel().getSelectedItem();
		projectListView.getItems().remove(selectedproject);

		projectsArray.remove(projectListView.getItems().indexOf(projectListView.getSelectionModel().getSelectedItem()));
	}
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}

	private boolean validateProject() {
		if (titleField.getText().isEmpty() || technologyField.getText().isEmpty() || linkField.getText().isEmpty() || descriptionTextArea.getText().isEmpty()) {
			Alerts.AlertEmptyField();
			return false;
		}

		Pattern titlePattern = Pattern.compile("^[a-zA-Z][a-zA-Z_-]{1,38}[a-zA-Z]$");
		Pattern technologyPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{1,38}[a-zA-Z0-9]$");
		Pattern linkPattern = Pattern.compile("^(http|https)://.*$"); // Assuming the link should start with http:// or https://
		Pattern descriptionPattern = Pattern.compile("^(?=.{1,150}$)[a-zA-Z0-9]+(?:[ _-][a-zA-Z0-9]+)*$");

		Matcher titleMatcher = titlePattern.matcher(titleField.getText());
		Matcher technologyMatcher = technologyPattern.matcher(technologyField.getText());
		Matcher linkMatcher = linkPattern.matcher(linkField.getText());
		Matcher descriptionMatcher = descriptionPattern.matcher(descriptionTextArea.getText());

		if (!technologyMatcher.matches()) {
			flashRedBorder(technologyField);
			return false;
		} else {
			technologyField.setStyle("");
			if (!titleMatcher.matches()) {
				flashRedBorder(titleField);
				return false;
			} else {
				titleField.setStyle("");
				if (!linkMatcher.matches()) {
					flashRedBorder(linkField);
					return false;
				} else {
					linkField.setStyle("");
					if (!descriptionMatcher.matches()) {
						flashRedBorder(descriptionTextArea);
						return false;
					} else {
						descriptionTextArea.setStyle("");
						return true;
					}
				}
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
		projectListView.setCellFactory(new Callback<ListView<Project>, ListCell<Project>>() {
            @Override
            public ListCell<Project> call(ListView<Project> param) {
                return new ListCell<Project>() {
                    @Override
                    protected void updateItem(Project project, boolean empty) {
                        super.updateItem(project, empty);
                        if (project == null || empty) {
                            setText(null);
                        } else {
							setText("Project title: " + project.getTitle() + "\nProject technology: " + project.getTechnology() + "\nProject link: " + project.getLink() + "\nProject description : " + project.getDescription());
						}
					}
                };
            }
        });
		JSONArray projectsArray = JSONParser.getProjectsJSONArray("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON");
		// Iterate over each skill object in the JSON array and extract each field
		for (int i = 0; i < projectsArray.length(); i++) {
			JSONObject projectObject = projectsArray.getJSONObject(i);
			String titleValue = projectObject.getString("title");
			String technologyValue = projectObject.getString("technology");
			String linkValue = projectObject.getString("link");
			String descriptionValue = projectObject.getString("description");
			//create a skill object with those extracted values
			Project project = new Project(titleValue,technologyValue,linkValue,descriptionValue);
			//add that skill object to the skillsListView list of items(skills)
			//check if all fields are empty then don't add it
			//without this check at the first signUp the user will find an empty ghost task
			if(!(project.getTitle().isBlank() && project.getDescription().isBlank() && project.getLink().isBlank()))
			{
				projectListView.getItems().add(project);
				//put the skillObject in the skillsArrays so that already filled in skills won't get lost after a scene go and back idk how to explain just trust
				this.projectsArray.put(projectObject);
			}
		}
	
	}
}

///Validation
///Reset Functionality
///Initialization
