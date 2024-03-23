package com.fsb.networked;

import com.fsb.networked.dto.ImagePost;
import com.fsb.networked.dto.VideoPost;
import com.fsb.networked.utils.FilePaths;
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
    ImageView postImageView;
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
        mediaPlayer.play();
    }

    @FXML
    private void pauseVideo()
    {
        mediaPlayer.pause();
    }

    @FXML
    private void resetVideo()
    {
        mediaPlayer.pause();
        mediaPlayer.seek(Duration.ZERO);

        mediaPlayer.play();
    }

    ////clear media : media = null;
    //// Initialize media and mediaPlayer
    //               media = new Media(videoFile.toURI().toString());
    //               mediaPlayer = new MediaPlayer(media);
    //// Set the media player for the mediaView:
    //               mediaView.setMediaPlayer(mediaPlayer);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public <T> void setData(VideoPost videoPost)
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
        while(mediaView.getMediaPlayer() == null)
        {
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }
}
