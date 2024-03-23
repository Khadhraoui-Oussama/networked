package com.fsb.networked;

import com.fsb.networked.dto.JobOffer;
import com.fsb.networked.dto.ImagePost;
import com.fsb.networked.dto.TextPost;
import com.fsb.networked.dto.VideoPost;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.FilePaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController  implements Initializable {

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
    private VBox messagesLayoutVbox;
    //in notifications and messages button if user has messages or notifications replace the Icon with a red one
    //just change the imageview resource in the tab imageview

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
        //jobsScrollPane
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
        List<JobOffer> jobOffers = new ArrayList<>(getJobOffersFromDB());
            for (int i = 0; i < jobOffers.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("jobPostUiComponent.fxml"));
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
    private List<JobOffer> getJobOffersFromDB()
    {
        //TODO GET JOB OFFERS FROM DATABASE AND FILL THEM HERE INSTEAD OF THIS FOR LOOP
        List<JobOffer> ls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JobOffer job1 = new JobOffer();
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
            if(posts.get(i) instanceof ImagePost)
            {
                fxmlLoader.setLocation(getClass().getResource("imagePostUiComponent.fxml"));
                try {
                    VBox imagePostVbox = fxmlLoader.load();
                    ImagePostItemController controller = fxmlLoader.getController();
                    controller.setData((ImagePost) posts.get(i));
                    postsLayoutVbox.getChildren().add(imagePostVbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(posts.get(i) instanceof VideoPost)
            {
                fxmlLoader.setLocation(getClass().getResource("videoPostUiComponent.fxml"));
                try {
                    VBox videoPostVbox = fxmlLoader.load();
                    VideoPostItemController controller = fxmlLoader.getController();
                    controller.setData((VideoPost) posts.get(i));
                    postsLayoutVbox.getChildren().add(videoPostVbox);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(posts.get(i) instanceof TextPost)
            {
                fxmlLoader.setLocation(getClass().getResource("textPostUiComponent.fxml"));
                try {
                    VBox textPostVbox = fxmlLoader.load();
                    TextPostItemController controller = fxmlLoader.getController();
                    controller.setData((TextPost) posts.get(i));
                    postsLayoutVbox.getChildren().add(textPostVbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(posts.get(i));
        }
    }
    private <T> List<T> getPostsFromDB()
    {
        List<T> ls = new ArrayList<T>();
        //TODO FILL THIS WITH SKELETON DATA
        //TODO
        ImagePost post2 = new ImagePost();
        post2.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2.setNumberOfComments(10);
        post2.setNumberOfLikes(1000);
        post2.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        post2.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2);
        ImagePost post23 = new ImagePost();
        TextPost postds110 = new TextPost();
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

        VideoPost post4 = new VideoPost();
        post4.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post4.setNumberOfComments(10);
        post4.setNumberOfLikes(1000);
        post4.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post4.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post4.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post4.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post4);

        //TODO

        VideoPost post10 = new VideoPost();
        post10.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post10.setNumberOfComments(10);
        post10.setNumberOfLikes(1000);
        post10.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post10.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post10.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        System.out.println(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        post10.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post10);
        VideoPost post1a0 = new VideoPost();
        post1a0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1a0.setNumberOfComments(10);
        post1a0.setNumberOfLikes(1000);
        post1a0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1a0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1a0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        System.out.println(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        post1a0.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1a0);
        TextPost postaz110 = new TextPost();
        postaz110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        postaz110.setNumberOfComments(10);
        postaz110.setNumberOfLikes(1000);
        postaz110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        postaz110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        postaz110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) postaz110);
        VideoPost post1aa0 = new VideoPost();
        post1aa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aa0.setNumberOfComments(10);
        post1aa0.setNumberOfLikes(1000);
        post1aa0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1aa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        System.out.println(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        post1aa0.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePost post2a3 = new ImagePost();
        post2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post2a3.setNumberOfComments(10);
        post2a3.setNumberOfLikes(1000);
        post2a3.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        post2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        post2a3.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) post2a3);
        VideoPost post1aaa0 = new VideoPost();
        post1aaa0.setPostText ("VIDEO Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post1aaa0.setNumberOfComments(10);
        post1aaa0.setNumberOfLikes(1000);
        post1aaa0.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post1aaa0.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post1aaa0.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        System.out.println(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        post1aaa0.setAttachmentFileSrc(FilePaths.getImagePath("/images/test_video.mp4").substring(6));
        //figure it out later post4.setAttachmentFileSrc("/images/");
        ls.add((T) post1aa0);
        ImagePost posat2a3 = new ImagePost();
        posat2a3.setPostText ("IMAGE Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posat2a3.setNumberOfComments(10);
        posat2a3.setNumberOfLikes(1000);
        posat2a3.setOpImgSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        posat2a3.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posat2a3.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        posat2a3.setAttachmentFileSrc(FilePaths.getImagePath("/images/male_avatar.png").substring(6));
        ls.add((T) posat2a3);

        TextPost post110 = new TextPost();
        post110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        post110.setNumberOfComments(10);
        post110.setNumberOfLikes(1000);
        post110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        post110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        post110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) post110);
        TextPost posta110 = new TextPost();
        posta110.setPostText ("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type speci");
        posta110.setNumberOfComments(10);
        posta110.setNumberOfLikes(1000);
        posta110.setOpImgSrc(FilePaths.getImagePath("/images/default_user.png").substring(6));
        posta110.setPublicationDate(Conversions.stringtoLocalDate("2020-10-5"));
        posta110.setPublicationTime(Conversions.stringToLocaleTime("10:15:12"));
        ls.add((T) posta110);

        return ls;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initializeJobOffersVbox();
        initializePostsVbox();
    }

}
