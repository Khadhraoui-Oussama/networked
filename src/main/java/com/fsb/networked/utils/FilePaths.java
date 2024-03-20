package com.fsb.networked.utils;

import javafx.scene.image.Image;

import java.net.URL;

public class FilePaths {

    public static String getImagePath(String imagePath){
        URL imageUrl = FilePaths.class.getResource(imagePath);
        if(imageUrl !=null)
        {
            // Create an Image object using the URL
            Image image = new Image(imageUrl.toExternalForm());
            return image.getUrl();
        }
        else {
            return "error";
        }
    }
}
