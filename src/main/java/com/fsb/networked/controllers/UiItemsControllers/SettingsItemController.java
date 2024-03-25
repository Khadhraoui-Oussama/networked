package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.SettingDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SettingsItemController {
    @FXML
    Button settingBtn;
    @FXML
    Label settingNameLabel;

    public void setData(SettingDTO settingDTO) {
        settingNameLabel.setText(settingDTO.getSettingsName());
        settingBtn.setOnAction(settingDTO.getOnActionFunction());
    }
    //TODO CHANGE PFP SEE CHANGE LIST OF FOLLOWERS SEE INFORMATION THE USER HAS ENTERED
}
