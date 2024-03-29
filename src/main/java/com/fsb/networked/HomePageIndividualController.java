package com.fsb.networked;

import com.fsb.networked.controllers.ChildWindowControllers.CreateVideoPostChildWindowController;
import com.fsb.networked.controllers.UiItemsControllers.*;
import com.fsb.networked.dto.*;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.FileLoader;
import com.fsb.networked.utils.SessionManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageIndividualController implements Initializable {

    //labels
    @FXML
    Label welcomeNameLabel;

    //buttons
    @FXML
    Button searchBtn;
    @FXML
    Button normalPostBtn;
    @FXML
    Button imagePostBtn;
    @FXML
    Button videoPostBtn;
    @FXML
    Button changeAddPfpBtn;
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

    //SCROLLPANES
    @FXML
    ScrollPane homeScrollPane;
    @FXML
    ScrollPane notificationsScrollPane;
    @FXML
    ScrollPane messagesScrollpane;
    @FXML
    ScrollPane jobsScrollPane;
    //TODO PROFILE AND SETTINGS LISTVIEW ??? OR VBOX HBOX
    @FXML
    ScrollPane connectionSuggestionsScrollPane;
    @FXML
    ScrollPane profileScrollpane;
    @FXML
    ScrollPane settingsScrollpane;
    //vboxes for the layouts of the posts,offers etc
    @FXML
    private VBox jobPostsLayoutVbox;
    @FXML
    private VBox postsLayoutVbox;
    @FXML
    private VBox notificationsLayoutVbox;
    @FXML
    private VBox settingsLayoutVbox;
    @FXML
    private VBox profileLayoutVbox;
    @FXML
    private VBox messageConnectionLayoutVbox;
    @FXML
    private VBox connectionSuggestionsLayoutVbox;
    // Media Players
    private List<MediaPlayer> mediaPlayers = new ArrayList<>();
    //in notifications and messages button if user has messages or notifications replace the Icon with a red one
    //just change the imageview resource in the tab imageview
    //individual session ID from DB
    int individualSessionID = -1;
    {
        try {
            individualSessionID = SessionManager.getSessionIDIndividual();
            System.out.println("session id here : " + SessionManager.getSessionIDIndividual());
            System.out.println("indiv id here :" + SessionManager.ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void loadHomeTab() throws IOException {
        loadPostsVBox();
    }

    @FXML
    private void loadNotificationsTab()
    {
        loadConnectionSharedPostNotificationLayoutVbox();
    }

    @FXML
    private void loadMessagesTab()
    {
        loadMessageConnectionLayoutVbox();
    }

    @FXML
    private void loadProfileTab()
    {
        loadProfileLayoutVbox();
    }
    @FXML
    private void loadSettingsTab()
    {
        loadSettingsLayoutVbox();
    }
    @FXML
    private void loadJobsTab()
    {
        loadJobOffersVbox();
    }

    @FXML
    private void createNormalPost()
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowCreateTextPost.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create a Text Post");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void createImagePost()
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowCreateImagePost.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create an Image Post");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void createVideoPost()
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowCreateVideoPost.fxml"));
            Parent root = fxmlLoader.load();
            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create a Video Post");

            CreateVideoPostChildWindowController controller = fxmlLoader.getController();
            controller.setOnCloseChildWindow(stage);


            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void addConnection()
    {

    }
    @FXML
    private void addChangePfp()
    {

    }
    @FXML
    private void search()
    {

    }

    //yt video
    //TODO MAKE SURE THAT GETxxFROMdb RETURNS DATA ORDRED BY PUBLICATION DATE AND TIME
    private void loadJobOffersVbox()
    {
        List<JobOfferDTO> jobOffers = new ArrayList<>(getJobOffersFromDB());
        jobPostsLayoutVbox.getChildren().clear();
            for (int i = 0; i < jobOffers.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/JobPostUiComponent.fxml"));
            try {
                VBox jobPostVbox = fxmlLoader.load();
                JobPostItemController jpic = fxmlLoader.getController();
                jpic.setData(jobOffers.get(i));
                jobPostsLayoutVbox.getChildren().add(jobPostVbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<JobOfferDTO> getJobOffersFromDB()
    {
        //TODO GET JOB OFFERS FROM DATABASE AND FILL THEM HERE INSTEAD OF THIS FOR LOOP
        List<JobOfferDTO> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JobOfferDTO job1 = new JobOfferDTO();
            job1.setCompany("Boga cidre");
            job1.setDescription("work in a fast paced environment where you prepare drinks for a living XD,\"work in a fast paced environment where you prepare drinks for a living XD");
            job1.setLogoImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
            job1.setNumberOfApplicants(999);
            job1.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
            ls.add(job1);
        }
        return ls;
    }

    private List<ConnectionDTO> getConnectionSuggestionsFromDB()
    {
        //TODO GET JOB OFFERS FROM DATABASE AND FILL THEM HERE INSTEAD OF THIS FOR LOOP
        List<ConnectionDTO> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ConnectionDTO con = new ConnectionDTO();
            con.setFirstName("Walter");
            con.setLastName("White");
            con.setConnectionImage(FileLoader.getImagePath("/images/icons/job_icon.png").substring(6));
            ls.add(con);
        }
        return ls;
    }
    private void loadSuggestionsVBox() {
        List<ConnectionDTO> connectionSuggestions = new ArrayList<>(getConnectionSuggestionsFromDB());
        connectionSuggestionsLayoutVbox.getChildren().clear();
        for (int i = 0; i < connectionSuggestions.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/ConnectionSuggestionUiComponent.fxml"));
            try {
                VBox connSuggestionVbox = fxmlLoader.load();
                ConnectionSuggestionItemController controller = fxmlLoader.getController();
                controller.setData(connectionSuggestions.get(i));
                connectionSuggestionsLayoutVbox.getChildren().add(connSuggestionVbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private <T> void loadPostsVBox()
    {
        PostService postService = new PostService();
        List<T> posts = new ArrayList<>(postService.getPostsFromDB());
        postsLayoutVbox.getChildren().clear();
        for (int i = 0; i < posts.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            if(posts.get(i) instanceof ImagePostDTO)
            {
                fxmlLoader.setLocation(getClass().getResource("UiComponents/ImagePostUiComponent.fxml"));
                try {
                    VBox imagePostVbox = fxmlLoader.load();
                    ImagePostItemController controller = fxmlLoader.getController();
                    controller.setData((ImagePostDTO) posts.get(i));
                    postsLayoutVbox.getChildren().add(imagePostVbox);
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(posts.get(i) instanceof VideoPostDTO)
            {
                fxmlLoader.setLocation(getClass().getResource("UiComponents/VideoPostUiComponent.fxml"));
                try {
                    VBox videoPostVbox = fxmlLoader.load();
                    VideoPostItemController controller = fxmlLoader.getController();
                    controller.setData((VideoPostDTO) posts.get(i));
                    postsLayoutVbox.getChildren().add(videoPostVbox);
                    mediaPlayers.add(controller.getMediaPLayer());
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(posts.get(i) instanceof TextPostDTO)
            {
                fxmlLoader.setLocation(getClass().getResource("UiComponents/TextPostUiComponent.fxml"));
                try {
                    VBox textPostVbox = fxmlLoader.load();
                    TextPostItemController controller = fxmlLoader.getController();
                    controller.setData((TextPostDTO) posts.get(i));
                    controller.thisTextPostDTO = (TextPostDTO) posts.get(i);
                    System.out.println("here AAAA: " + posts.get(i));
                    postsLayoutVbox.getChildren().add(textPostVbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private void disposeMediaPlayers() {
       // Dispose all media players and stop playing videos
       for (MediaPlayer mediaPlayer : mediaPlayers) {
           if (mediaPlayer != null) {
               mediaPlayer.stop();
               mediaPlayer.dispose();
           }
       }
       // Clear the list of media players
       mediaPlayers.clear();
   }

    private void loadMessageConnectionLayoutVbox()
    {
        disposeMediaPlayers();
        List<MessageConnectionDTO> messageConnections = new ArrayList<>(getMessageConnectionFromDB());
        for (int i = 0; i < messageConnections.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/MessageConnectionUiComponent.fxml"));
            try {
                VBox messageConnectionVBox = fxmlLoader.load();
                MessageConnectionItemController controller = fxmlLoader.getController();
                controller.setData(messageConnections.get(i));
                messageConnectionLayoutVbox.getChildren().add(messageConnectionVBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<MessageConnectionDTO> getMessageConnectionFromDB()
    {

        //TODO gets all the connections and the last message from the database
        //TODO GET JOB OFFERS FROM DATABASE AND FILL THEM HERE INSTEAD OF THIS FOR LOOP
        List<MessageConnectionDTO> ls = new ArrayList<>();
        //for now we fill with random data
        for (int i = 0; i < 5; i++) {
            MessageConnectionDTO msg = new MessageConnectionDTO();
            msg.setConnectionName("Boga cidre Man");
            msg.setLastMessageBody("Fama ken boga !! boga bidha LE !!");
            msg.setConnectionImgSrc(FileLoader.getImagePath("/images/boga_cidre.jpg").substring(6));
            msg.setLastMessageDate(Conversions.stringtoLocalDate("2100-12-31"));
            msg.setLastMessageTime(Conversions.stringToLocaleTime("23-59-59"));
            ls.add(msg);
        }
        return ls;
    }

    private void loadConnectionSharedPostNotificationLayoutVbox()
    {
        disposeMediaPlayers();
        List<NotificationConnectionSharedPostDTO> notifications = new ArrayList<>(getNotificationConnectionSharedPostsFromDB());
        notificationsLayoutVbox.getChildren().clear();
        for (int i = 0; i < notifications.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/NotificationUiComponent.fxml"));
            try {
                VBox notificationVBox = fxmlLoader.load();
                NotificationConnectionSharedPostItemController controller = fxmlLoader.getController();
                controller.setData(notifications.get(i));
                notificationsLayoutVbox.getChildren().add(notificationVBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<NotificationConnectionSharedPostDTO> getNotificationConnectionSharedPostsFromDB()
    {
        //TODO gets all the connections and the last message from the database
        //TODO GET JOB OFFERS FROM DATABASE AND FILL THEM HERE INSTEAD OF THIS FOR LOOP
        List<NotificationConnectionSharedPostDTO> ls = new ArrayList<>();
        //for now, we fill with random data
        for (int i = 0; i < 5; i++) {
            NotificationConnectionSharedPostDTO notification = new NotificationConnectionSharedPostDTO();
            notification.setConnectionName("SuperMan");
            notification.setPostContentStart("So excited to start my new project with batman , Gotham isn't ready ☻☻");
            notification.setConnectionImgSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
            notification.setPostShareDate(Conversions.stringtoLocalDate("2100-12-31"));
            ls.add(notification);
        }
        return ls;
    }
    private void loadProfileLayoutVbox()
    {
        disposeMediaPlayers();
        List<SettingDTO> settings = new ArrayList<>();
        profileLayoutVbox.getChildren().clear();
        settings.add(new SettingDTO("Modify Work Experience", this::modifyWorkExperience));
        settings.add(new SettingDTO("Modify Skills ",this::modifySkills));
        settings.add(new SettingDTO("Modify Education",this::modifyEducation));
        settings.add(new SettingDTO("Modify Personal Projects",this::modifyProjects));
        settings.add(new SettingDTO("Open Admin Portal",this::openAdminPortal));
       for (int i = 0; i < settings.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/SettingsUiComponent.fxml"));
            try {
                VBox settingVBox = fxmlLoader.load();
                SettingsItemController controller = fxmlLoader.getController();
                controller.setData(settings.get(i));
                profileLayoutVbox.getChildren().add(settingVBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void loadSettingsLayoutVbox()
    {
        disposeMediaPlayers();
        List<SettingDTO> settings = new ArrayList<>();
        settingsLayoutVbox.getChildren().clear();
        settings.add(new SettingDTO("Change My Password", this::changePassword));
        settings.add(new SettingDTO("Modifiy Connections List ",this::modifyConnectionsList));
        settings.add(new SettingDTO("Modify Posts List",this::modifyPostsList));
        settings.add(new SettingDTO("Sign out",this::signOut));
        for (int i = 0; i < settings.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UiComponents/SettingsUiComponent.fxml"));
            try {
                VBox settingVBox = fxmlLoader.load();
                SettingsItemController controller = fxmlLoader.getController();
                controller.setData(settings.get(i));
                settingsLayoutVbox.getChildren().add(settingVBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void signOut(ActionEvent actionEvent) {
        Alert confirmSignOut = new Alert(Alert.AlertType.CONFIRMATION);
        confirmSignOut.setTitle("Sign Out");
        confirmSignOut.setHeaderText("Are you sure you want to sign out?");
        confirmSignOut.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                if(confirmSignOut.getResult() == ButtonType.OK) {
                    try {
                        SessionManager.cleanSessionRow(SessionManager.ID, SessionManager.getSessionIDIndividual());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        SessionManager.cleanSessionRow(SessionManager.ID, SessionManager.getSessionIDEntreprise());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        App.setRoot("LogInPage");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        confirmSignOut.showAndWait();
    }

    private void modifyPostsList(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowManagePosts.fxml"));
            Parent root = fxmlLoader.load();
            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Manage Posts List");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyConnectionsList(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowManageConnections.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Manage Connections List");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changePassword(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowChangePassword.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Change Password");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void modifyWorkExperience(ActionEvent event)
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowWorkExperienceIndividual.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Work Experience");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void modifySkills(ActionEvent event)
    {
        try {
        // Load the FXML file for the child window
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowSkillsIndividual.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new stage for the child window
        Stage stage = new Stage();
        stage.setResizable(false);
        //APPLICATION_MODAL THIS MEANS THAT THE APPLICATION IS INACCESSIBLE WHILE THIS WINDOWS IS OPEN
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modify Skills");

        // Set the scene with the FXML content
        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Show the child window
        stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void modifyEducation(ActionEvent event)
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowEducationIndividual.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            //APPLICATION_MODAL THIS MEANS THAT THE APPLICATION IS INACCESSIBLE WHILE THIS WINDOWS IS OPEN
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Education");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void modifyProjects(ActionEvent event)
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowProjectIndividual.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            //APPLICATION_MODAL THIS MEANS THAT THE APPLICATION IS INACCESSIBLE WHILE THIS WINDOWS IS OPEN
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modify Personnal Projects");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openAdminPortal(ActionEvent event)
    {
        try {
            // Load the FXML file for the child window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChildWindows/ChildWindowBecomeAdmin.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the child window
            Stage stage = new Stage();
            stage.setResizable(false);
            //APPLICATION_MODAL THIS MEANS THAT THE APPLICATION IS INACCESSIBLE WHILE THIS WINDOWS IS OPEN
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Become an admin");

            // Set the scene with the FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the child window
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSuggestionsVBox();
        //change the button text based on the user pfp
        if(profilePictureImageView.getImage().getUrl().toString().substring(6)
                .equals(FileLoader.getImagePath("/images/male_avatar.png").substring(6)) ||
                        profilePictureImageView.getImage().getUrl().toString().substring(6)
                                .equals(FileLoader.getImagePath("/images/female_avatar.png").substring(6)))
        {
            changeAddPfpBtn.setText("Add a profile picture");
        }
        else {
            changeAddPfpBtn.setText("Change the profile picture");
        }
    }
}

