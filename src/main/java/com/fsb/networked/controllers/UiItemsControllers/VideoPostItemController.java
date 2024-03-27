package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.VideoPostDTO;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.Conversions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    boolean userLikedPost = false ;
    private VideoPostDTO videoPostDTO;
    PostService postService = new PostService();
    public void likeUnlikePost() throws SQLException {
        if (!userLikedPost) {
            postService.changeVideoPostReaction(videoPostDTO, 1);
            String result = (videoPostDTO.getNumberOfLikes() + 1) != 1 ? " likes" : " like";
            result = (videoPostDTO.getNumberOfLikes()) + result;
            numberOfLikesLabel.setText(result);
            userLikedPost = true;
            likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(30, 144, 255, 1.0), null, null)));
        } else {
            postService.changeVideoPostReaction(videoPostDTO, -1);
            String result = (videoPostDTO.getNumberOfLikes() - 1) != 1 ? " likes" : " like";
            result = (videoPostDTO.getNumberOfLikes()) + result;
            numberOfLikesLabel.setText(result);
            likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(186, 186, 186, 1.0), null, null)));
            userLikedPost = false;
        }
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
        if(userLikedPost)
        {
            likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(30, 144, 255, 1.0), null, null)));
        }
        else{
            //grey color
            likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(186, 186, 186, 1.0), null, null)));
        }
    }

    public void setData(VideoPostDTO videoPostDTO) throws SQLException, IOException {
        //get  image from database
        File file1 = Conversions.convertBlobToFile(videoPostDTO.getOpImgSrc());
        Image opImage = new Image(file1.toURI().toString());
        opImgView.setImage(opImage);
        opNameLabel.setText(videoPostDTO.getOriginalPosterName());
        dateOfPublicationLabel.setText(videoPostDTO.getPublicationDateTime().toString());
        postContentLabel.setText(videoPostDTO.getPostText());
        numberOfCommentsLabel.setText(videoPostDTO.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(videoPostDTO.getNumberOfLikes() + " : Likes");
        //TODO HERE MAKE VIDEP PLAYER LIKE VIDEO SIGN UP
        //TODO COULD BE TROUBLE MAYBE GET WITH URL FROM DATABASE
        File videoFile = Conversions.convertBlobToFile(videoPostDTO.getAttachmentFile());
        media = new Media(videoFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(.1);
        mediaView.setMediaPlayer(mediaPlayer);
        this.videoPostDTO = videoPostDTO;
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