package com.fsb.networked;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fsb.networked.dao.Skill;
import com.fsb.networked.utils.ComboBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
		App.setRoot("SingUpPageIndividualWork");
		System.out.println("Individual Skills Information gathered");
    }

	
	@FXML
	private void addSkill()
	{
		//TODO : when add skill btn is pressed the skill must be added to the listview of skill
		//also figure out how it should be added to Skills class or person DAO iDK yet
		
	}
	
	@FXML
	private void deleteSkill()
	{
		//TODO : when deleteskill btn is presses delete the skill from the listview of skills
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		levelComboBox.getItems().addAll(ComboBoxes.SKILLLEVELS);
		levelComboBox.getSelectionModel().selectFirst();	
	}
	
	
}
