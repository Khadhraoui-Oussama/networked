package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.VideoPostDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoPostItemController implements Initializable {
    @FXML
    ImageView opImgView;
    @FXML
    Label opNameLabel;
    @FXML
    Label dateOfPublicationLabel;
    @FXML
    Label postContentLabel;
    @FXML
    MediaView mediaView;
    @FXML
    Media media;
    @FXML
    MediaPlayer mediaPlayer;
    @FXML
    Label numberOfLikesLabel;
    @FXML
    Label numberOfCommentsLabel;
    @FXML
    Button playBtn;
    @FXML
    Button pauseBtn;
    @FXML
    Button resetBtn;
    @FXML
    Button reloadBtn;
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
    @FXML
    private void playVideo()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.play();
        }
    }

    @FXML
    private void pauseVideo()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void resetVideo()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.pause();
            mediaPlayer.seek(Duration.ZERO);
        }
    }
    //TODO SOMETIMES THE VIDEO WONT LOAD IN THE POST SO REALOADVIDEO HOPEFULLY FIXES THAT
    public void reloadVideo() {
        disposeMediaPlayer();
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(.1);
        mediaView.setMediaPlayer(mediaPlayer);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (mediaPlayer != null) {
            // Set mediaPlayer to mediaView
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    public <T> void setData(VideoPostDTO videoPost)
    {
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/male_avatar.png")));
        opImgView.setImage(img);
        opNameLabel.setText(videoPost.getOriginalPosterName());
        dateOfPublicationLabel.setText(videoPost.getPublicationDate().toString());
        postContentLabel.setText(videoPost.getPostText());
        numberOfCommentsLabel.setText(videoPost.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(videoPost.getNumberOfLikes() + " : Likes");
        //TODO HERE MAKE VIDEP PLAYER LIKE VIDEO SIGN UP
        File videoFile = new File(videoPost.getAttachmentFileSrc());
        media = new Media(videoFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(.1);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    public MediaPlayer getMediaPLayer() {
        return this.mediaPlayer;
    }
    public void disposeMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null; // Resetting the mediaPlayer reference
        }
    }


}
