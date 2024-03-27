package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.dto.TextPostDTO;
import com.fsb.networked.service.IndividualService;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.Alerts;
import com.fsb.networked.utils.Regexes;
import com.fsb.networked.utils.SessionManager;
import com.fsb.networked.utils.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateTextPostChildWindowController {
    @FXML
    TextArea postContentTextArea;
    @FXML
    Button postBtn;
    @FXML
    Label statusLabel;
    @FXML
    public void postTextPost() throws SQLException, IOException {
        if(validateTextPost()){

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
        }
    }

    private boolean validateTextPost() {
        boolean isValid = true;
        isValid &= Validator.validateField(postContentTextArea,Regexes.POST_CONTENT_REGEX,Alerts.AlertPostContentField());
        return isValid;
    }
}
