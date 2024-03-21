package com.fsb.networked.utils;
import javafx.scene.Node;
import javafx.scene.control.*;

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
}
