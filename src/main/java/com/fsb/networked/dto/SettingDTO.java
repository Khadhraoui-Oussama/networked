package com.fsb.networked.dto;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SettingDTO {
    private String settingsName;
    private EventHandler<ActionEvent> onActionFunction;

    public SettingDTO(String settingsName, EventHandler<ActionEvent> functionName) {
        this.settingsName = settingsName;
        this.onActionFunction = functionName;
    }

    public SettingDTO() {
    }

    public String getSettingsName() {
        return settingsName;
    }

    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }

    public EventHandler<ActionEvent> getOnActionFunction() {
        return onActionFunction;
    }

    public void setOnActionFunction(EventHandler<ActionEvent> onActionFunction) {
        this.onActionFunction = onActionFunction;
    }

    @Override
    public String toString() {
        return "SettingDTO{" +
                "settingsName='" + settingsName + '\'' +
                ", onActionFunction ='" + onActionFunction + '\'' +
                '}';
    }
}
