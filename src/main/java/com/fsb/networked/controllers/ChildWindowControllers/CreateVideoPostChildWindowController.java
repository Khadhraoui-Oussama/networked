package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.utils.FileLoader;
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
import java.net.URL;
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
    public void post()
    {

    }
    private File videoFile;
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
