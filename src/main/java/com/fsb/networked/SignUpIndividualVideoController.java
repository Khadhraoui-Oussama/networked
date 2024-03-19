package com.fsb.networked;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class SignUpIndividualVideoController implements Initializable {


    @FXML
    private MediaView mediaView;

    @FXML
    private Button playBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button resetBtn;

    @FXML
    private Button chooseVideoBtn;

    @FXML
    private Button generatePDFBtn;

    @FXML
    private Button finishBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Media media;

    @FXML
    private Label statusLabel;

    @FXML
    private MediaPlayer mediaPlayer;

    private File videoFile;


    @FXML
    private void uploadVideoResume() {
        //clear mediaview
        mediaView.setMediaPlayer(null);

        final FileChooser fc = new FileChooser();
        // Set the title
        fc.setTitle("Choose a video resume to use ");
        // Set the initial directory (default one)
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        // Extension filters
        // Clear all extension filters
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mov", "*.avi", "*.webm"));

        // Set the selected file or use null if no file has been selected
        videoFile = fc.showOpenDialog(null);
        if (videoFile != null) {
            System.out.println("Path to video :" + videoFile.toURI().getPath());
            // Set the source as the file path
            statusLabel.setText("Video resume uploaded successfully");

            // Initialize media and mediaPlayer
            media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
        } else {
            statusLabel.setText("Invalid video resume used !!");
        }
    }

    @FXML
    private void goBack() throws IOException
    {
        App.setRoot("SignUpScenes/SignUpPageIndividualSkills");
    }

    @FXML
    private void goNext() throws IOException
    {
        System.out.println("All Info Gathered");
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Check if mediaPlayer is initialized
        if (mediaPlayer != null) {
            // Set mediaPlayer to mediaView
            mediaView.setMediaPlayer(mediaPlayer);
        }
    }


}
