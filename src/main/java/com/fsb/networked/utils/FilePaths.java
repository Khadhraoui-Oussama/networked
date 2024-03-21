package com.fsb.networked.utils;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

public class FilePaths {

    public static String getImagePath(String imagePath) {
        URL imageUrl = FilePaths.class.getResource(imagePath);
        if (imageUrl != null) {
            // Create an Image object using the URL
            Image image = new Image(imageUrl.toExternalForm());
            return image.getUrl();
        } else {
            return "error";
        }
    }

    @FXML
    public static File uploadProfilePicture() {
        final FileChooser fc = new FileChooser();
        //set the title
        fc.setTitle("Choose a profile picture to use ");
        //set the initial directory (default one)
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        //extension filters
        //clear all extension filters
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        //set the selected file or use null if no file has been selected
        File file = fc.showOpenDialog(null);
        return file;
    }

    public static String chooseDirectoryToSaveTo() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Destination Folder");

        //optional start destination is home folder
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            System.out.println("Selected folder: " + selectedDirectory.getAbsolutePath());
            return selectedDirectory.getAbsolutePath().toString();
        } else {
            System.out.println("Folder selection cancelled.");
            return "Folder selection cancelled.";
        }
    }
}
