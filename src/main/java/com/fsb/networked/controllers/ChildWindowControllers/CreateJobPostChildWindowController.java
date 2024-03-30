package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.ComboBoxes;
import com.fsb.networked.utils.Regexes;
import com.fsb.networked.utils.Validator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateJobPostChildWindowController implements Initializable {
    @FXML
    TextArea jobDescriptionTextArea;
    @FXML
    TextArea jobTitleTextArea;
    @FXML
    ComboBox<String> experienceComboBox;
    @FXML
    ComboBox<String> salaryComboBox;
    @FXML
    ComboBox<String> jobTypeComboBox;
    @FXML
    Button postBtn;
    @FXML
    Label statusLabel;
    @FXML
    public void postJobPost() throws SQLException, IOException {
       /* if(validateJobPost()){

            PostService postService = new PostService();
            IndividualService individualService = new IndividualService();
            TextPostDTO textPostDTO = new TextPostDTO();
            textPostDTO.setPostText(postContentTextArea.getText());
            textPostDTO.setNumberOfComments(0);
            textPostDTO.setNumberOfLikes(0);
            textPostDTO.setOpImgSrc(individualService.getIndividualImageBlobFromDB(SessionManager.ID));
            textPostDTO.setPublicationDateTime(LocalDateTime.now());
            textPostDTO.setOriginalPosterName(individualService.getIndividualNameFromDB(SessionManager.ID));
            int result = postService.addTextPost(textPostDTO);
            if(result > 0)
            {
                statusLabel.setText("Post added successfully !");
            }
            else{
                statusLabel.setText("There was an error posting!");
            }
        }
        else {
            statusLabel.setText("Pls correctly fill all the fields to post !");
        }*/
    }

    private boolean validateJobPost() {
        boolean isValid = true;
        isValid &= Validator.validateField(jobDescriptionTextArea,Regexes.POST_CONTENT_REGEX,Alerts.AlertJobPostContentField());
        isValid &= Validator.validateField(jobTitleTextArea,Regexes.POST_CONTENT_REGEX,Alerts.AlertJobTitleField());
        return isValid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salaryComboBox.getItems().addAll(ComboBoxes.SALARY_RANGE);
        salaryComboBox.getSelectionModel().selectFirst();

        jobTypeComboBox.getItems().addAll(ComboBoxes.JOB_TYPE);
        jobTypeComboBox.getSelectionModel().selectFirst();

        experienceComboBox.getItems().addAll(ComboBoxes.EXPERIENCE_NEEDED);
        experienceComboBox.getSelectionModel().selectFirst();

    }
}
