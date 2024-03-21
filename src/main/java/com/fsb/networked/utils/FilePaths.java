package com.fsb.networked.utils;

import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.net.URL;

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
