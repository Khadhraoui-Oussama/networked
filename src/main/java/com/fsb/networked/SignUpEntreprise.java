package com.fsb.networked;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class SignUpEntreprise {

    @FXML
    private Button btnCancel;


    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField nomEntrepriseTextField;

    @FXML
    private ImageView logoImageView;

    @FXML
    private TextField secteurTextField;

    @FXML
    private TextField tailleTextField;

    @FXML
    private DatePicker anneeDatePicker;

    @FXML
    private TextField fondateurTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField produitsTextField;

    @FXML
    private TextField localisationTextField;

    @FXML
    private TextField siteWebTextField;

    @FXML
    private Button submitButton;

    @FXML
    void handleSubmitButtonAction(ActionEvent event) {
        if (fieldsAreNotEmpty()) {
            // Process form submission
            // For demonstration, let's just show an alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Form submitted successfully!");
            alert.showAndWait();
        } else {
            // Show error message if any of the required fields are empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
        }
    }

    @FXML
    void handleChooseLogoButtonAction(ActionEvent event) {
        // Check if rootPane is null
        if (rootPane == null) {
            System.err.println("Error: rootPane is null");
            return;
        }

        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();

        // Set the title of the dialog
        fileChooser.setTitle("Choose Logo Image");

        // Set the initial directory to the user's desktop
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));

        // Add filters to only show image files
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());

        // Check if a file was selected
        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            logoImageView.setImage(image);
        } else {
            // If no file was selected, set a default image (optional)
            Image defaultImage = new Image(getClass().getResourceAsStream("/images/anonymous_logo.jpg"));
            logoImageView.setImage(defaultImage);
        }
    }

    @FXML
    private void cancelSignUp() throws IOException
    {
        App.setRoot("SignUpScenes/SignUpPage");
    }
    @FXML
    private ComboBox<String> tailleComboBox;
    @FXML
    private ComboBox<String> secteurComboBox;

    @FXML
    private TextField autreTextField;

    @FXML
    private void handleSectorSelection() {
        if ("Autre".equals(secteurComboBox.getValue())) {
            autreTextField.setVisible(true);
        } else {
            autreTextField.setVisible(false);
        }
    }



    @FXML
    private void initialize() {
        anneeDatePicker.setValue(LocalDate.now());

        secteurComboBox.getSelectionModel().selectFirst();

        tailleComboBox.getSelectionModel().selectFirst();

        autreTextField.setVisible(false);

        secteurComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            autreTextField.setVisible(newValue.equals("Autre"));
            if (!newValue.equals("Autre")) {
                autreTextField.clear(); // Clear the text field if another option is selected
            }
        });

        autreTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 30) {
                autreTextField.setText(oldValue);
            }
        });
    }

    private boolean fieldsAreNotEmpty() {
        boolean allFieldsFilled = true;

        if (nomEntrepriseTextField.getText().isEmpty()) {
            nomEntrepriseTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            nomEntrepriseTextField.setStyle(""); // Reset style if field is not empty
        }

        if (anneeDatePicker.getValue() == null) {
            anneeDatePicker.setStyle("-fx-background-color: pink;"); // Add visual feedback for DatePicker
            allFieldsFilled = false;
        } else {
            anneeDatePicker.setStyle(""); // Reset style if date is selected
        }

        if (fondateurTextField.getText().isEmpty()) {
            fondateurTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            fondateurTextField.setStyle(""); // Reset style if field is not empty
        }

        if (descriptionTextField.getText().isEmpty()) {
            descriptionTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            descriptionTextField.setStyle(""); // Reset style if field is not empty
        }

        if (produitsTextField.getText().isEmpty()) {
            produitsTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            produitsTextField.setStyle(""); // Reset style if field is not empty
        }

        if (localisationTextField.getText().isEmpty()) {
            localisationTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            localisationTextField.setStyle(""); // Reset style if field is not empty
        }

        if (siteWebTextField.getText().isEmpty()) {
            siteWebTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            siteWebTextField.setStyle(""); // Reset style if field is not empty
        }

        // Check if autreTextField is visible and empty when secteur is "Autre"
        if ("Autre".equals(secteurComboBox.getValue()) && autreTextField.isVisible() && autreTextField.getText().isEmpty()) {
            autreTextField.setStyle("-fx-border-color: red;");
            allFieldsFilled = false;
        } else {
            autreTextField.setStyle(""); // Reset style if field is not empty or not visible
        }

        return allFieldsFilled;
    }


}
