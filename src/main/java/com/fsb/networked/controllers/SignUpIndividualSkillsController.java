package com.fsb.networked.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fsb.networked.App;
import com.fsb.networked.dto.Skill;
import com.fsb.networked.utils.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

public class SignUpIndividualSkillsController implements Initializable {

	@FXML
	private TextField skillTitleField;
	
	@FXML
	private TextField technologyField;
	
	@FXML
	private TextArea descriptionTextArea;
	
	@FXML
	private ComboBox<String> levelComboBox;
		
	@FXML
	private Button btnAddSkill;

	@FXML
	private Button btnCancel;

	@FXML
	private Button btnDeleteSkill;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private ListView<Skill> skillsListView;

	private JSONArray skillsArray = new JSONArray();
	@FXML
    private void goBack() throws IOException
	{
		App.setRoot("SignUpScenes/SignUpPageIndividualBasic");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		JSONParser.updateSkillsJSONArray(skillsArray);
		App.setRoot("SignUpScenes/SignUpPageIndividualWork");
		System.out.println("Individual Skills Information gathered");
    }

	
	@FXML
	private void addSkill()
	{
		//DONE TODO : when add skill btn is pressed the skill must be added to the listview of skill
		//also figure out how it should be added to Skills class or person DAO iDK yet
		if(validateSkill()) 
		{
			//make a new skill object
			Skill skill = new Skill(skillTitleField.getText(),technologyField.getText(),descriptionTextArea.getText(),levelComboBox.getValue());
			//add to the skillListView
			skillsListView.getItems().add(skill);
			//add to the skillsArray (JSON)
			skillsArray.put(skill.toJSON());
			//now clear all the fields
			skillTitleField.clear();
			technologyField.clear();
			descriptionTextArea.clear();
			levelComboBox.getSelectionModel().select(0);
		}
	}
	@FXML
	private void deleteSkill()
	{
		//DONE TODO : when delete skill btn is presses delete the skill from the listview of skills
		Skill selectedSkill = skillsListView.getSelectionModel().getSelectedItem();
		skillsListView.getItems().remove(selectedSkill);
		skillsArray.remove(skillsListView.getItems().indexOf(skillsListView.getSelectionModel().getSelectedItem()));
	}

	private boolean validateSkill() {
		boolean isValid = true;

		isValid &= Validator.validateField(skillTitleField, Regexes.TITLE_REGEX);
		isValid &= Validator.validateField(technologyField, Regexes.TECHNOLOGY_REGEX);
		isValid &= Validator.validateField(descriptionTextArea, Regexes.DESCRIPTION_REGEX);

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
		levelComboBox.getItems().addAll(ComboBoxes.SKILL_LEVELS);
		levelComboBox.getSelectionModel().selectFirst();	
	
		/*------------*/
		
		skillsListView.setCellFactory(new Callback<ListView<Skill>, ListCell<Skill>>() {
            @Override
            public ListCell<Skill> call(ListView<Skill> param) {
                return new ListCell<Skill>() {
                    @Override
                    protected void updateItem(Skill skill, boolean empty) {
                        super.updateItem(skill, empty);
                        if (skill == null || empty) {
                            setText(null);
                        } else {
                            setText("Skill title: " + skill.getTitle() + "\nSkill technology: " + skill.getTechnology() + "\nSkill description: " + skill.getDescription() + "\nSkill level: " + skill.getLevel()); 
                        }
                    }
                };
            }
        });

		//this bit of code makes sure the skillsListView gets populated with whatever skills the user has added
		// so that in case of going to next scene and going back, the inputed data doesn't get lost
		JSONArray skillsArray = JSONParser.getSkillsJSONArray("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON");
		// Iterate over each skill object in the JSON array and extract each field
		for (int i = 0; i < skillsArray.length(); i++) {
			JSONObject skillObject = skillsArray.getJSONObject(i);
			String titleValue = skillObject.getString("title");
			String technologyValue = skillObject.getString("technology");
			String descriptionValue = skillObject.getString("description");
			String levelValue = skillObject.getString("level");
			//create a skill object with those extracted values
			Skill skill = new Skill(titleValue,technologyValue,descriptionValue,levelValue);
			//add that skill object to the skillsListView list of items(skills)
			//check if all fields are empty then don't add it
			//without this check at the first signUp the user will find an empty ghost task
			if(!(skill.getTitle().isBlank() && skill.getDescription().isBlank() && skill.getDescription().isBlank()))
			{
				skillsListView.getItems().add(skill);
				//put the skillObject in the skillsArrays so that already filled in skills won't get lost after a scene go and back idk how to explain just trust
				this.skillsArray.put(skillObject);
			}
		}
	}
}
