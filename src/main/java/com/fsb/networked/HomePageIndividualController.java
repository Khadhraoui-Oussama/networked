package com.fsb.networked;

import com.fsb.networked.controllers.ChildWindowControllers.CreateVideoPostChildWindowController;
import com.fsb.networked.controllers.UiItemsControllers.*;
import com.fsb.networked.dto.*;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.FileLoader;
import javafx.event.ActionEvent;
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
        //disposeMediaPlayers();
        List<T> posts = new ArrayList<>(getPostsFromDB());
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
                } catch (IOException e) {
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
                } catch (IOException e) {
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
                    postsLayoutVbox.getChildren().add(textPostVbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
   /*private void disposeMediaPlayers() {
        // Iterate through all video post items and dispose their media players
        for (Node node : postsLayoutVbox.getChildren()) {
            if (node instanceof VBox) {
                VBox postVbox = (VBox) node;
                Object controller = postVbox.getProperties().get("controller");
                if (controller instanceof VideoPostItemController) {
                    VideoPostItemController videoController = (VideoPostItemController) controller;
                    videoController.disposeMediaPlayer();
                }
            }
        }
    }*/
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
    private <T> List<T> getPostsFromDB()
    {
        List<T> ls = new ArrayList<T>();
        //TODO FILL THIS WITH SKELETON DATA
        //TODO
        ImagePostDTO post2 = new ImagePostDTO();
        post2.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2.setNumberOfComments(10);
        post2.setNumberOfLikes(1000);
        post2.setOpImgSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        post2.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2.setAttachmentFileSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2);
        ImagePostDTO post23 = new ImagePostDTO();
        TextPostDTO postds110 = new TextPostDTO();
        postds110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        postds110.setNumberOfComments(10);
        postds110.setNumberOfLikes(1000);
        postds110.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        postds110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        postds110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) postds110);
        post23.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post23.setNumberOfComments(10);
        post23.setNumberOfLikes(1000);
        post23.setOpImgSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        post23.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post23.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post23.setAttachmentFileSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2);

        VideoPostDTO post4 = new VideoPostDTO();
        post4.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post4.setNumberOfComments(10);
        post4.setNumberOfLikes(1000);
        post4.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post4.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post4.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post4.setAttachmentFileSrc(FileLoader.getImagePath("/images/o2.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post4);

        //TODO

        VideoPostDTO post10 = new VideoPostDTO();
        post10.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post10.setNumberOfComments(10);
        post10.setNumberOfLikes(1000);
        post10.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post10.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post10.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post10.setAttachmentFileSrc(FileLoader.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post10);
        VideoPostDTO post1a0 = new VideoPostDTO();
        post1a0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1a0.setNumberOfComments(10);
        post1a0.setNumberOfLikes(1000);
        post1a0.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post1a0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1a0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1a0.setAttachmentFileSrc(FileLoader.getImagePath("/images/o2.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1a0);
        TextPostDTO postaz110 = new TextPostDTO();
        postaz110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        postaz110.setNumberOfComments(10);
        postaz110.setNumberOfLikes(1000);
        postaz110.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        postaz110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        postaz110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) postaz110);
        VideoPostDTO post1aa0 = new VideoPostDTO();
        post1aa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aa0.setNumberOfComments(10);
        post1aa0.setNumberOfLikes(1000);
        post1aa0.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post1aa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1aa0.setAttachmentFileSrc(FileLoader.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePostDTO post2a3 = new ImagePostDTO();
        post2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2a3.setNumberOfComments(10);
        post2a3.setNumberOfLikes(1000);
        post2a3.setOpImgSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        post2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2a3.setAttachmentFileSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2a3);
        VideoPostDTO post1aaa0 = new VideoPostDTO();
        post1aaa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aaa0.setNumberOfComments(10);
        post1aaa0.setNumberOfLikes(1000);
        post1aaa0.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post1aaa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aaa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1aaa0.setAttachmentFileSrc(FileLoader.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePostDTO posat2a3 = new ImagePostDTO();
        posat2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posat2a3.setNumberOfComments(10);
        posat2a3.setNumberOfLikes(1000);
        posat2a3.setOpImgSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        posat2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posat2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        posat2a3.setAttachmentFileSrc(FileLoader.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) posat2a3);

        TextPostDTO post110 = new TextPostDTO();
        post110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post110.setNumberOfComments(10);
        post110.setNumberOfLikes(1000);
        post110.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        post110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) post110);
        TextPostDTO posta110 = new TextPostDTO();
        posta110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posta110.setNumberOfComments(10);
        posta110.setNumberOfLikes(1000);
        posta110.setOpImgSrc(FileLoader.getImagePath("/images/default_user.png").substring(6));
        posta110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posta110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) posta110);

        return ls;
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
        settings.add(new SettingDTO("Become Admin",this::becomeAdmin));
        System.out.println(settings.size());
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
        System.out.println(settings.size());
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
    private void becomeAdmin(ActionEvent event)
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
        try {
            loadHomeTab();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
       /* tabPane.getSelectionModel().selectedItemProperty().addListener((observable ,oldTab,newTab) -> {
            if (oldTab.equals(tabHome)) {
                disposeMediaPlayers();
            }
        });*/
    }
}

