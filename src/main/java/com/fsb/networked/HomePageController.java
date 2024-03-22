package com.fsb.networked;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HomePageController {

    private final String HOMEPAGECONTROLLERPATH = "HomePageController";

    //textfields
    @FXML
    TextField searchTextField;
    @FXML
    TextField postTextField;

    //labels
    @FXML
    Label welcomeNameLabel;

    //buttons
    @FXML
    Button searchBtn;
    @FXML
    Button normalPostBtn;
    @FXML
    Button mediaPostBtn;
    @FXML
    Button changeAddPfpBtn;
    @FXML
    Button addConnectionBtn;

    //panes
    @FXML
    TabPane tabPane;

    //tabs
    @FXML
    Tab tabHome;
    @FXML
    Tab tabProfile;
    @FXML
    Tab tabSettings;
    @FXML
    Tab tabMessages;
    @FXML
    Tab tabNotifications;
    @FXML
    Tab tabJobs;

    //imageViews
    @FXML
    ImageView profilePictureImageView;
    @FXML
    ImageView messageImageView;
    @FXML
    ImageView NotificationImageView;
    @FXML
    ImageView jobImageView;

    //listviews
    @FXML
    ListView homeListView;
    @FXML
    ListView notificationsListView;
    @FXML
    ListView messagesListView;
    @FXML
    ListView jobsListView;
    //TODO PROFILE AND SETTINGS LISTVIEW ??? OR VBOX HBOX
    @FXML
    ListView connectionSuggestionsListView;

    //in notifications and messages button if user has messages or notifications replace the Icon with a red one
    //just change the imageview resource in the tab imageview
    @FXML
    private void search()
    {

    }
    @FXML
    private void reloadHome() throws IOException {
        System.out.println("Reloaded");
    }

    @FXML
    private void loadTabNotifications()
    {

    }


    @FXML
    private void loadTabMessages()
    {

    }

    @FXML
    private void loadTabProfile()
    {

    }
    @FXML
    private void loadTabSettings()
    {

    }
    @FXML
    private void loadTabJobs()
    {

    }

    @FXML
    private void createNormalPost()
    {

    }
    @FXML
    private void createMediaPost()
    {

    }
    @FXML
    private void addConnection()
    {

    }
    @FXML
    private void addChangePfp()
    {

    }


}
