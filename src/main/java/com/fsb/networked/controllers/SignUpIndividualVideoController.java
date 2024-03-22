package com.fsb.networked.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import com.fsb.networked.App;
import com.fsb.networked.utils.FilePaths;
import com.fsb.networked.utils.JSONParser;
import com.fsb.networked.utils.PDFCreator;
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
    private Button btnCancel;

    @FXML
    private Media media;

    @FXML
    private Label statusLabel;

    @FXML
    private MediaPlayer mediaPlayer;
    private File videoFile;
   @FXML
    private void uploadVideoResume() {
       // Clear mediaview
       media = null;
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
       //.mkv format causes issues ( maybe not supported)
       // Set the selected file or use null if no file has been selected
       videoFile = fc.showOpenDialog(null);
       if (videoFile != null) {
           try {
               System.out.println("Path to video :" + videoFile.toURI().getPath());
               // Set the source as the file path
               statusLabel.setText("Video resume uploaded successfully");

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
           statusLabel.setText("Invalid video resume used !!");
       }
   }


    public static boolean isValidVideo(File file) {
        if (file == null) {
            return false;
        }
        return true;
    }
    @FXML
    private void goBack() throws IOException
    {
        mediaPlayer.dispose();
        App.setRoot("SignUpScenes/SignUpPageIndividualProject");
    }

    @FXML
    private void finishSignUpIndividual() throws IOException
    {
        if(isValidVideo(videoFile))
        {
            //add the video path to the json file
            JSONParser.writeToJSONFile("src/main/resources/com/fsb/networked/JSON_files/Individiual.JSON", "signUpVideo", "videoPath", videoFile.toURI().getPath());
            System.out.println("All Info Gathered");

            //create the pdf resume
            String path = FilePaths.chooseDirectoryToSaveTo();
            System.out.println(path);
            System.out.println("C:\\Users\\khadh\\IdeaProjects\\networked\\src\\main\\resources\\com\\fsb\\networked\\PDFFiles\\");
            PDFCreator.createPDF(path,"\\pdfGenerated.pdf");
        }
        else {
            statusLabel.setText("Pls select a video resume first\nto be able to generate your PDF.");
        }
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

    @FXML
    private void cancelSignUp() throws IOException
    {
        //if the user decides he no longer want to sign up the json files must be cleared of all inputs
        // and returned to the original state
        JSONParser.resetIndividualJSONFile();
        JSONParser.resetEntrepriseJSONFile();
        mediaView.setMediaPlayer(null);
        mediaPlayer.dispose();
        App.setRoot("LogInPage");
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
