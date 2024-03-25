package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.ConnectionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ConnectionSuggestionItemController {

    @FXML
    ImageView connSuggestionImg;
    @FXML
    Label connSuggestionFirstNameField;
    @FXML
    Label connSuggestionLastNameField;
    @FXML
    Button followSuggestionBtn;
    @FXML
    Label statusLabel;
    public void followSuggestion()
    {

    }
    public void setData(ConnectionDTO connectionDTO) {
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/job_icon.png")));
        connSuggestionImg.setImage(logoImage);
        connSuggestionFirstNameField.setText(connectionDTO.getFirstName());
        connSuggestionLastNameField.setText(connectionDTO.getLastName());
        statusLabel.setText("change to : following or empty or error happened.");
    }
}
