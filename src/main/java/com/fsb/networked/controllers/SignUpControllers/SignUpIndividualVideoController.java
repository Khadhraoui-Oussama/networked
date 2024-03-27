package com.fsb.networked.controllers.SignUpControllers;

import com.fsb.networked.App;
import com.fsb.networked.service.IndividualService;
import com.fsb.networked.utils.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
       videoFile = FileLoader.uploadVideo("Choose a video resume to use");
       if (videoFile != null) {
           try {
               System.out.println("Path to video : " + videoFile.toURI().getPath());
               // Set the source as the file path
               statusLabel.setText("Video resume uploaded successfully");

               // Initialize media and mediaPlayer
               media = new Media(videoFile.toURI().toString());
               mediaPlayer = new MediaPlayer(media);
               mediaPlayer.setOnError(() -> {
                   statusLabel.setText("Video not supported or corrupted!");
                   System.out.println("Media player error: " + mediaPlayer.getError());
                   if(mediaPlayer != null){
                       mediaPlayer.dispose();
                   }
               });
               mediaView.setMediaPlayer(mediaPlayer);
           } catch (Exception e) {
               // Handle any exceptions
               e.printStackTrace();
               statusLabel.setText("Error occurred while loading video");
               if(mediaPlayer != null){
                   mediaPlayer.dispose();
               }
           }
       } else {
           statusLabel.setText("Invalid video resume used !!");
           if(mediaPlayer != null){
               mediaPlayer.dispose();
           }
       }
   }


    public static boolean isValidVideo(File file,MediaView mediaView) {
        if(file == null)
        {
            return false;
        }
        else{
            return Validator.validateVideoFileSize(file,mediaView ,mediaView.getParent(),Alerts.AlertVideoResumeSizeTooBig());
        }
   }
    @FXML
    private void goBack() throws IOException
    {
        if(mediaPlayer != null) {
            mediaPlayer.dispose();
            App.setRoot("SignUpScenes/SignUpPageIndividualProject");
        }
    }
  @FXML
    private void finishSignUpIndividual() throws IOException
    {
        if(isValidVideo(videoFile,mediaView))
        {
            IndividualService individualService = new IndividualService();
            String path = FileLoader.chooseDirectoryToSaveTo();
            System.out.println("PDF PATH HERE" + path);
            if(!path.isEmpty() && !path.equals("-1"))
            {
                //add the video path to the json file
                JSONParser.writeToJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpVideo", "videoPath", "file:" + videoFile.toURI().getPath());
                System.out.println("All Info Gathered");
                //create the pdf resume

                System.out.println("PDF resume saved at : " + path);
                File pdfFile = PDFCreator.createPDF(path,"\\resume.pdf");
                int individualID = individualService.saveIndividualToDB(Conversions.convertFileToByteArray(pdfFile));
                SessionManager.setID(individualID);
            }
            else {
                statusLabel.setText("Pls choose a directory to save your pdf resume to.");
            }
        }
        else {
            statusLabel.setText("Pls select a valid video resume first\nto continue.");
        }
    }

    @FXML
    private void playVideo()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.setVolume(.1);
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

    @FXML
    private void cancelSignUp() throws IOException
    {
        //if the user decides he no longer want to sign up the json files must be cleared of all inputs
        // and returned to the original state
        JSONParser.resetIndividualJSONFile();
        JSONParser.resetEntrepriseJSONFile();
        if(mediaPlayer != null)
        {
            mediaView.setMediaPlayer(null);
            mediaPlayer.dispose();
        }
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
