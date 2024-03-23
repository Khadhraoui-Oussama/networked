package com.fsb.networked;

import com.fsb.networked.dto.ImagePostDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
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

    public void likePost()
    {

    }
    public void commentOnPost()
    {

    }
    public void sharePost()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public <T> void setData(ImagePostDTO imagePost)
    {
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/male_avatar.png")));
        opImgView.setImage(img);
        postImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/female_avatar.png"))));
        opNameLabel.setText(imagePost.getOriginalPosterName());
        dateOfPublicationLabel.setText(imagePost.getPublicationDate().toString());
        postContentLabel.setText(imagePost.getPostText());
        numberOfCommentsLabel.setText(imagePost.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(imagePost.getNumberOfLikes() + " : Likes");
        File file = new File(imagePost.getAttachmentFileSrc());
        Image postImage = new Image(file.toURI().toString());
        postImageView.setImage(postImage);
    }
}
