package com.fsb.networked.utils;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static <T> boolean validateField(T field, String regexPattern) {
        if (field instanceof TextField) {
            String fieldValue = ((TextField) field).getText();
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(fieldValue);
            if (fieldValue.isEmpty() || !matcher.matches()) {
                flashRedBorder((Node) field);
                return false;
            } else {
                ((Node) field).setStyle("");
                return true;
            }
        } else if (field instanceof DatePicker) {
            if (((DatePicker) field).getValue() == null) {
                flashRedBorder((Node) field);
                return false;
            } else {
                ((Node) field).setStyle("");
                return true;
            }
        }
        return false;
    }
    public static boolean validateEducationField(TextField field) {
        return validateField(field, Regexes.EDUCATION_FIELD_REGEX);
    }public static boolean validateEducationTextArea(TextArea area) {
        return validateField(area, Regexes.EDUCATION_FIELD_REGEX);
    }
    public static <T> void flashRedBorder(T field) {
        ((Node) field).setStyle("-fx-border-color:red;");
    }
}
