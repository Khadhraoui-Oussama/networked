package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fsb.networked.dao.Skill;
import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ComboBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;

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
	private Button btnDeleteSkill;
	
	@FXML
	private Button btnNext;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private ListView<Skill> skillsListView;
	
	
	@FXML
    private void goBack() throws IOException
	{
        App.setRoot("SignInIndividualController");
    }
	
	@FXML
    private void goNext() throws IOException
	{
		App.setRoot("SignUpPageIndividualWork");
		System.out.println("Individual Skills Information gathered");
    }

	
	@FXML
	private void addSkill()
	{
		//DONE TODO : when add skill btn is pressed the skill must be added to the listview of skill
		//also figure out how it should be added to Skills class or person DAO iDK yet		
		
		if(validateSkill()) 
		{
			skillsListView.getItems().add(new Skill(skillTitleField.getText(),technologyField.getText(),descriptionTextArea.getText(),levelComboBox.getValue()));
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
	}
	
	private <T> void flashRedBorder(T field)
	{
		((Node) field).setStyle("-fx-border-color:red;");
	}
	
	private boolean validateSkill()
	{
		if(skillTitleField.getText().isEmpty() || technologyField.getText().isEmpty() || descriptionTextArea.getText().isEmpty())
		{
			Alerts.AlertEmptyField();
			return false;
		}		
		Pattern titlePattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9 _-]{1,38}[a-zA-Z0-9]$");
		Matcher titleMatcher = titlePattern.matcher(skillTitleField.getText());
		Matcher technologyMatcher = titlePattern.matcher(technologyField.getText());
		
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
			if (skillTitleField.getText().isEmpty() || !titleMatcher.matches())
			{
				flashRedBorder(skillTitleField);
				return false;
			}
			else
			{
				skillTitleField.setStyle("");
				if (descriptionTextArea.getText().isEmpty() || !descriptionMatcher.matches())
				{
					flashRedBorder(descriptionTextArea);
					return false;
				}
				else
				{
					descriptionTextArea.setStyle("");
				}	
				return true;
			}
		}
	}
	

	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		levelComboBox.getItems().addAll(ComboBoxes.SKILLLEVELS);
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
	
	}
	
	
}
