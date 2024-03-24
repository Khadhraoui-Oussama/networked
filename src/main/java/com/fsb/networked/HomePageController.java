package com.fsb.networked;

import com.fsb.networked.controllers.*;
import com.fsb.networked.dto.*;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.FilePaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController  implements Initializable {

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
    //in notifications and messages button if user has messages or notifications replace the Icon with a red one
    //just change the imageview resource in the tab imageview

    @FXML
    private void reloadHomeTab() throws IOException {
        System.out.println("Reloaded");
    }

    @FXML
    private void loadNotificationsTab()
    {
        initializeConnectionSharedPostNotificationLayoutVbox();
    }

    @FXML
    private void loadMessagesTab()
    {
        initializeMessageConnectionLayoutVbox();
    }

    @FXML
    private void loadTabProfileTab()
    {

    }
    @FXML
    private void loadSettingsTab()
    {

    }
    @FXML
    private void loadJobsTab()
    {
        initializeJobOffersVbox();
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
    @FXML
    private void search()
    {

    }

    //yt video
    //TODO MAKE SURE THAT GETxxFROMdb RETURNS DATA ORDRED BY PUBLICATION DATE AND TIME
    private void initializeJobOffersVbox()
    {
        List<JobOfferDTO> jobOffers = new ArrayList<>(getJobOffersFromDB());
            for (int i = 0; i < jobOffers.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomePageScenes/JobPostUiComponent.fxml"));
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
            job1.setLogoImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
            job1.setNumberOfApplicants(999);
            job1.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
            ls.add(job1);
        }
        return ls;
    }
    private <T> void initializePostsVbox()
    {
        List<T> posts = new ArrayList<>(getPostsFromDB());
        for (int i = 0; i < posts.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            if(posts.get(i) instanceof ImagePostDTO)
            {
                fxmlLoader.setLocation(getClass().getResource("HomePageScenes/ImagePostUiComponent.fxml"));
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
                fxmlLoader.setLocation(getClass().getResource("HomePageScenes/VideoPostUiComponent.fxml"));
                try {
                    VBox videoPostVbox = fxmlLoader.load();
                    VideoPostItemController controller = fxmlLoader.getController();
                    controller.setData((VideoPostDTO) posts.get(i));
                    postsLayoutVbox.getChildren().add(videoPostVbox);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(posts.get(i) instanceof TextPostDTO)
            {
                fxmlLoader.setLocation(getClass().getResource("HomePageScenes/TextPostUiComponent.fxml"));
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
    private <T> List<T> getPostsFromDB()
    {
        List<T> ls = new ArrayList<T>();
        //TODO FILL THIS WITH SKELETON DATA
        //TODO
        ImagePostDTO post2 = new ImagePostDTO();
        post2.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2.setNumberOfComments(10);
        post2.setNumberOfLikes(1000);
        post2.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        post2.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2);
        ImagePostDTO post23 = new ImagePostDTO();
        TextPostDTO postds110 = new TextPostDTO();
        postds110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        postds110.setNumberOfComments(10);
        postds110.setNumberOfLikes(1000);
        postds110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        postds110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        postds110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) postds110);
        post23.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post23.setNumberOfComments(10);
        post23.setNumberOfLikes(1000);
        post23.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        post23.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post23.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post23.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2);

        VideoPostDTO post4 = new VideoPostDTO();
        post4.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post4.setNumberOfComments(10);
        post4.setNumberOfLikes(1000);
        post4.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post4.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post4.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post4.setAttachmentFileSrc(FilePaths.getImagePath("/images/o2.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post4);

        //TODO

        VideoPostDTO post10 = new VideoPostDTO();
        post10.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post10.setNumberOfComments(10);
        post10.setNumberOfLikes(1000);
        post10.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post10.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post10.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post10.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post10);
        VideoPostDTO post1a0 = new VideoPostDTO();
        post1a0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1a0.setNumberOfComments(10);
        post1a0.setNumberOfLikes(1000);
        post1a0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1a0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1a0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1a0.setAttachmentFileSrc(FilePaths.getImagePath("/images/o2.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1a0);
        TextPostDTO postaz110 = new TextPostDTO();
        postaz110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        postaz110.setNumberOfComments(10);
        postaz110.setNumberOfLikes(1000);
        postaz110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        postaz110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        postaz110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) postaz110);
        VideoPostDTO post1aa0 = new VideoPostDTO();
        post1aa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aa0.setNumberOfComments(10);
        post1aa0.setNumberOfLikes(1000);
        post1aa0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1aa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1aa0.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePostDTO post2a3 = new ImagePostDTO();
        post2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2a3.setNumberOfComments(10);
        post2a3.setNumberOfLikes(1000);
        post2a3.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        post2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2a3.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2a3);
        VideoPostDTO post1aaa0 = new VideoPostDTO();
        post1aaa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aaa0.setNumberOfComments(10);
        post1aaa0.setNumberOfLikes(1000);
        post1aaa0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1aaa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aaa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post1aaa0.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePostDTO posat2a3 = new ImagePostDTO();
        posat2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posat2a3.setNumberOfComments(10);
        posat2a3.setNumberOfLikes(1000);
        posat2a3.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        posat2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posat2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        posat2a3.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) posat2a3);

        TextPostDTO post110 = new TextPostDTO();
        post110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post110.setNumberOfComments(10);
        post110.setNumberOfLikes(1000);
        post110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) post110);
        TextPostDTO posta110 = new TextPostDTO();
        posta110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posta110.setNumberOfComments(10);
        posta110.setNumberOfLikes(1000);
        posta110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        posta110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posta110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) posta110);

        return ls;
    }

    private void initializeMessageConnectionLayoutVbox()
    {
        List<MessageConnectionDTO> messageConnections = new ArrayList<>(getMessageConnectionFromDB());
        for (int i = 0; i < messageConnections.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomePageScenes/MessageConnectionUiComponent.fxml"));
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
            msg.setConnectionImgSrc(FilePaths.getImagePath("/images/boga_cidre.jpg").substring(6));
            msg.setLastMessageDate(Conversions.stringtoLocalDate("2100-12-31"));
            msg.setLastMessageTime(Conversions.stringToLocaleTime("23-59-59"));
            ls.add(msg);
        }
        return ls;
    }

    private void initializeConnectionSharedPostNotificationLayoutVbox()
    {
        List<NotificationConnectionSharedPostDTO> notifications = new ArrayList<>(getNotificationConnectionSharedPostsFromDB());
        for (int i = 0; i < notifications.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomePageScenes/NotificationUiComponent.fxml"));
            try {
                VBox notificationVBox = fxmlLoader.load();
                NotificationConnectionSharedPostController controller = fxmlLoader.getController();
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
            notification.setConnectionImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
            notification.setPostShareDate(Conversions.stringtoLocalDate("2100-12-31"));
            ls.add(notification);
        }
        return ls;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializePostsVbox();
    }

}
