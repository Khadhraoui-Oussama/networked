package com.fsb.networked.utils;

import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;

public class FileLoader {

    public static String getImagePath(String imagePath) {
        URL imageUrl = FileLoader.class.getResource(imagePath);
        if (imageUrl != null) {
            // Create an Image object using the URL
            Image image = new Image(imageUrl.toExternalForm());
            return image.getUrl();
        } else {
            return "error";
        }
    }
    public static File uploadPicture(String windowTitle) {
        final FileChooser fc = new FileChooser();
        //set the title
        fc.setTitle(windowTitle);
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
    public static File uploadVideo(String windowTitle) {
        final FileChooser fc = new FileChooser();
        // Set the title
        fc.setTitle(windowTitle);
        // Set the initial directory (default one)
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        // Extension filters
        // Clear all extension filters
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mov", "*.avi", "*.webm"));
        //.mkv format causes issues ( maybe not supported)
        // Set the selected file or use null if no file has been selected
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
            return selectedDirectory.getAbsolutePath();
        } else {
            System.out.println("Folder selection cancelled.");
            return "-1";
        }
    }


    public static String truncateFileName(String name) {
        String result = name.substring(0, Math.min(name.length(), ImportantFileReferences.MAX_FILE_NAME_LENGTH));
        return  result;
    }
}
