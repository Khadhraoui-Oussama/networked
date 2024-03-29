package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.ImagePostDTO;
import com.fsb.networked.utils.Conversions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImagePostItemController implements Initializable {
    @FXML
    ImageView opImgView;
    @FXML
    ImageView postImageView;
    @FXML
    Label opNameLabel;
    @FXML
    Label dateOfPublicationLabel;
    @FXML
    Label postContentLabel;
    @FXML
    Label numberOfLikesLabel;
    @FXML
    Label numberOfCommentsLabel;
    @FXML
    Button likeBtn;
    @FXML
    Button commentBtn;
    @FXML
    Button shareBtn;
    ImagePostDTO imagePostDTO = new ImagePostDTO();
    public ImagePostItemController() {
    }

    public void likeUnlikePost()
    {

    }
    public void commentOnPost()
    {

    }
    public void sharePost()
    {

    }

    public <T> void setData(ImagePostDTO imagePostDTO) throws SQLException, IOException {
        //get  image from database
        //TODO THIS COULD BE A PROBLEM
        //TODO MAYBE GET THE URL AND DISPLAY IT LIKE THAT IF ANY ERROR OCCURS
        File file1 = Conversions.convertBlobToFile(imagePostDTO.getOpImgSrc());
        Image opImage = new Image(file1.toURI().toString());
        opImgView.setImage(opImage);
        postImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/female_avatar.png"))));
        opNameLabel.setText(imagePostDTO.getOriginalPosterName());
        dateOfPublicationLabel.setText(imagePostDTO.getPublicationDateTime().toString());
        postContentLabel.setText(imagePostDTO.getPostText());
        numberOfCommentsLabel.setText(imagePostDTO.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(imagePostDTO.getNumberOfLikes() + " : Likes");
        File file2 = Conversions.convertBlobToFile(imagePostDTO.getAttachmentFile());
        Image postImage = new Image(file2.toURI().toString());
        postImageView.setImage(postImage);
        this.imagePostDTO = imagePostDTO;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
