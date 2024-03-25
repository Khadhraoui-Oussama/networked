package com.fsb.networked.controllers.ChildWindowControllers;

import com.fsb.networked.utils.FileLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class CreateImagePostChildWindowController {

    @FXML
    TextArea postContentTextArea;
    @FXML
    Button postBtn;
    @FXML
    Label statusLabel;
    @FXML
    Button uploadImageBtn;
    @FXML
    ImageView imgView;
    @FXML
    public void post()
    {

    }
    @FXML
    public void uploadImage()
    {
        File file = FileLoader.uploadPicture("Choose an image to use");
        if (file != null) {
            System.out.println("path :" + file.toURI().getPath());
            // set the image view source as the file path
            imgView.setImage(new Image(file.toURI().toString()));
            statusLabel.setText("Image uploaded successfully");
        } else {
            statusLabel.setText("Invalid image used !!");
        }
    }
}
