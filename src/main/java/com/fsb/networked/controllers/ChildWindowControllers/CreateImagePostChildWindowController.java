package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.dto.ImagePostDTO;
import com.fsb.networked.service.IndividualService;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateImagePostChildWindowController {

    @FXML
    TextArea postContentTextArea;
    @FXML
    Button postBtn;
    @FXML
    Label statusLabel;
    @FXML
    Button uploadImageBtn;
    @FXML
    ImageView imgView;
    @FXML
    public void postImagePost() throws SQLException, IOException {
        if(validateImagePost())
        {
            PostService postService = new PostService();
            Connection connection = ConxDB.getInstance();
            IndividualService individualService = new IndividualService();
            ImagePostDTO imagePostDTO = new ImagePostDTO();
            imagePostDTO.setPostText(postContentTextArea.getText());
            imagePostDTO.setNumberOfComments(0);
            imagePostDTO.setNumberOfLikes(0);
            imagePostDTO.setOpImgSrc(individualService.getIndividualImageBlobFromDB(SessionManager.ID));
            imagePostDTO.setPublicationDateTime(LocalDateTime.now());
            imagePostDTO.setOriginalPosterName(individualService.getIndividualNameFromDB(SessionManager.ID));
            File imageFile = new File(imgView.getImage().getUrl().substring(6));
            System.out.println("check image path : " + imgView.getImage().getUrl().substring(6));
            imagePostDTO.setAttachmentFile(Conversions.convertFileToBlob(imageFile,connection));
            int result = postService.addImagePost(imagePostDTO);
            if(result > 0)
            {
                statusLabel.setText("Post added successfully !");
            }
            else{
                statusLabel.setText("There was an error posting!");
            }
        }
        else{
            statusLabel.setText("Pls correctly fill all the fields to post !");
        }
    }
    private boolean validateImagePost() {
        boolean isValid = false;
        //check for image exists first , image path too long for SQL then size too big for SQL
        if(Validator.validateImageSelected(imgView,imgView.getParent(),Alerts.AlertEmptyField()))
        {
            if(Validator.validateImagePath(imgView,imgView.getParent(),Alerts.AlertImagePathTooLong()))
            {
                if(Validator.validateImageSize(imgView,imgView.getParent(),Alerts.AlertImageSizeTooBig()))
                {
                    isValid = true;
                }
            }
        }
        //check if text empty
        isValid &= Validator.validateField(postContentTextArea,Regexes.POST_CONTENT_REGEX,Alerts.AlertPostContentField());
        return isValid;
    }

    @FXML
    public void uploadImage()
    {
        File file = FileLoader.uploadPicture("Choose an image to use");
        if (file != null) {
            System.out.println("path :" + file.toURI().getPath());
            // set the image view source as the file path
            imgView.setImage(new Image(file.toURI().toString()));
            statusLabel.setText("Image uploaded successfully");
        } else {
            statusLabel.setText("Invalid image used !!");
        }
    }
}
