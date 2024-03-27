package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.TextPostDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TextPostItemController implements Initializable {
    @FXML
    ImageView opImgView;
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

    public void setData(TextPostDTO textPost)
    {
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/female_avatar.png")));
        opImgView.setImage(img);
        opNameLabel.setText(textPost.getOriginalPosterName());
        dateOfPublicationLabel.setText(textPost.getPublicationDateTime().toString());
        postContentLabel.setText(textPost.getPostText());
        numberOfCommentsLabel.setText(textPost.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(textPost.getNumberOfLikes() + " : Likes");
    }

    @Override
    public String toString() {
        return "textPostItemController{" +
                "opImgView=" + opImgView +
                ", opNameLabel=" + opNameLabel +
                ", dateOfPublicationLabel=" + dateOfPublicationLabel +
                ", postContentLabel=" + postContentLabel +
                ", numberOfLikesLabel=" + numberOfLikesLabel +
                ", numberOfCommentsLabel=" + numberOfCommentsLabel +
                ", likeBtn=" + likeBtn +
                ", commentBtn=" + commentBtn +
                ", shareBtn=" + shareBtn +
                '}';
    }
}
