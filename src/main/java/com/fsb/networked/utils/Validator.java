package com.fsb.networked.utils;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static <T> boolean validateField(T field, String regexPattern, Alert alert) {
        if (field instanceof TextField || field instanceof TextArea) {
            String fieldValue = field instanceof TextField ? ((TextField) field).getText() : ((TextArea) field).getText();
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(fieldValue);
            if (fieldValue.isEmpty() || !matcher.matches()) {
                flashRedBorder((Node) field);
                alert.showAndWait();
                return false;
            } else {
                ((Node) field).setStyle("");
                return true;
            }
        } else if (field instanceof DatePicker) {
            if (((DatePicker) field).getValue() == null) {
                flashRedBorder((Node) field);
                alert.showAndWait();
                return false;
            } else {
                ((Node) field).setStyle("");
                return true;
            }
        }
        return false;
    }
    public static <T> void flashRedBorder(T field) {
        ((Node) field).setStyle("-fx-border-color:red;");
    }

    public static boolean validateTextFieldSQLUnique(TextField field, Alert alert,String attribute,String tableName) {
        String fieldValue = field.getText();
        if(fieldValue.isEmpty() || !SQLHelper.AttributeIsUnique(attribute, field.getText(), tableName)) {
            flashRedBorder(field);
            alert.showAndWait();
            return false;
        } else {
            (field).setStyle("");
            return true;
        }
    }
    //ImageView doesn't have a property for border so we change border color for its parent node
    public static boolean validateImagePath(ImageView profilePictureImg,Node parentNode, Alert alert) {
        if (profilePictureImg.getImage() != null && profilePictureImg.getImage().getUrl().contains("%20")) { // contains whitespace or no but a url encodes it like %20
            flashRedBorder(parentNode);
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public static boolean validateImageSize(ImageView profilePictureImg,Node parentNode, Alert alert) {
        String imagePath = profilePictureImg.getImage().getUrl();
        File img = new File(imagePath.substring(6));
        if (img.length() > 16777214 ) { // maximum is 16777215  bytes (16MB)
            flashRedBorder(parentNode);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public static boolean validateVideoFileSize(File videoFile,MediaView mediaView,Node parentNode, Alert alert) {
        if(videoFile == null || videoFile.length() > 31457280) // about 30MB
        {
            flashRedBorder(parentNode);
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public static boolean validateImageSelected(ImageView imgView ,Node parentNode, Alert alert)
    {
        if(imgView.getImage() == null)
        {
            flashRedBorder(parentNode);
            alert.showAndWait();
            return false;
        }
        System.out.println("validate image post : "+imgView.getImage().getUrl());
        return true;
    }
}


