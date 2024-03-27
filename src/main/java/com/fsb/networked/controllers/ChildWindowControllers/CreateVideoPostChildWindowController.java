package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.dto.VideoPostDTO;
import com.fsb.networked.service.IndividualService;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateVideoPostChildWindowController implements Initializable {

    @FXML
    TextArea postContentTextArea;
    @FXML
    Button postBtn;
    @FXML
    Button playBtn;
    @FXML
    Button pauseBtn;
    @FXML
    Button resetBtn;
    @FXML
    Label statusLabel;
    @FXML
    Button uploadVideoBtn;
    @FXML
    MediaPlayer mediaPlayer;
    @FXML
    MediaView mediaView;
    @FXML
    Media media;
    @FXML
    public void postVideoPost() throws SQLException, IOException {
        if(isValidVideo(videoFile,mediaView)){
            PostService postService = new PostService();
            Connection connection = ConxDB.getInstance();
            IndividualService individualService = new IndividualService();
            VideoPostDTO videoPostDTO = new VideoPostDTO();
            videoPostDTO.setPostText(postContentTextArea.getText());
            videoPostDTO.setNumberOfComments(0);
            videoPostDTO.setNumberOfLikes(0);
            videoPostDTO.setOpImgSrc(individualService.getIndividualImageBlobFromDB(SessionManager.ID));
            videoPostDTO.setPublicationDateTime(LocalDateTime.now());
            videoPostDTO.setOriginalPosterName(individualService.getIndividualNameFromDB(SessionManager.ID));

            //TODO DEBUG STRING SOURCES
            //TODO INMPLEMENT VALIDATOR AND VALIDATEINFO FOR THE VIDEO UPLOADING THE POST CONTENT
            videoPostDTO.setAttachmentFile(Conversions.convertFileToBlob(videoFile,connection));
            int result = postService.addVideoPost(videoPostDTO);
            if(result > 0)
            {
                statusLabel.setText("Post added successfully !");
            }
            else{
                statusLabel.setText("There was an error posting!");
            }
        }
        else statusLabel.setText("Pls correctly fill all the fields to post !");
    }
    private File videoFile;
    private boolean isValidVideo(File file,MediaView mediaView) {
        boolean isValid = true;
        if(file == null || postContentTextArea.getText().isEmpty() || postContentTextArea.getText().isBlank())
        {
            if(file == null)
            {
                Validator.flashRedBorder(mediaView.getParent());
            }
            if(postContentTextArea.getText().isEmpty() || postContentTextArea.getText().isBlank())
            {
                Validator.flashRedBorder(postContentTextArea);
            }
            Alerts.AlertEmptyField().showAndWait();
            return false;
        }
        isValid &= Validator.validateVideoFileSize(file,mediaView ,mediaView.getParent(),Alerts.AlertVideoResumeSizeTooBig());
        isValid &= Validator.validateField(postContentTextArea,Regexes.POST_CONTENT_REGEX,Alerts.AlertPostContentField());
        return  isValid;
    }
    @FXML
    public void uploadVideo()
    {
        // Clear mediaview
        media = null;
        mediaView.setMediaPlayer(null);
        videoFile = FileLoader.uploadVideo("Choose a video to upload");
        if (videoFile != null) {
            try {
                System.out.println("Path to video : " + videoFile.toURI().getPath());
                // Set the source as the file path
                statusLabel.setText("Video uploaded successfully");
                // Initialize media and mediaPlayer
                media = new Media(videoFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setOnError(() -> {
                    statusLabel.setText("Video not supported or corrupted!");
                    System.out.println("Media player error: " + mediaPlayer.getError());
                });
                mediaView.setMediaPlayer(mediaPlayer);
            } catch (Exception e) {
                // Handle any exceptions
                e.printStackTrace();
                statusLabel.setText("Error occurred while loading video");
            }
        } else {
            statusLabel.setText("Invalid video uploaded !!");
        }
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Check if mediaPlayer is initialized
        if (mediaPlayer != null) {
            // Set mediaPlayer to mediaView
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }

    public void onCloseChildWindow() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }
    // set onCloseWindow event handler
    public void setOnCloseChildWindow(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onCloseChildWindow();
            }
        });
    }
}
