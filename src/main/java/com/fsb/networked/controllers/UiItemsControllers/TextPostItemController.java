package com.fsb.networked.controllers.UiItemsControllers;

import com.fsb.networked.dto.TextPostDTO;
import com.fsb.networked.service.PostService;
import com.fsb.networked.utils.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class TextPostItemController implements Initializable {
    @FXML
    ImageView opImgView;
    @FXML
    Label opNameLabel;
    @FXML
    Label dateOfPublicationLabel;
    @FXML
    Label postContentLabel;
    @FXML
    Label numberOfLikesLabel;
    @FXML
    Label numberOfCommentsLabel;
    @FXML
    Button likeBtn;
    @FXML
    Button commentBtn;
    @FXML
    Button shareBtn;
    private boolean userLikedPost;
    PostService postService = new PostService();
    public static TextPostDTO thisTextPostDTO ;
    public void likeUnlikePost() throws SQLException {
        int postId = postService.getTextPostId(thisTextPostDTO);
        if (postId != -1) {
            if (!userLikedPost) {
                postService.addLikeToTextPost(thisTextPostDTO); // Add like to the post
                postService.addLikeIndividualEntry(SessionManager.ID, postId); // Add entry in like_individual_text
                int newLikesCount = postService.getTextPostLikesCount(postId); // Get updated likes count
                updateLikesCount(newLikesCount); // Update the likes count label
                userLikedPost = true;
                // Change the appearance of the like button
                likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(30, 144, 255, 1.0), null, null)));
            } else {
                postService.removeLikeFromTextPost(thisTextPostDTO); // Remove like from the post
                postService.removeLikeIndividualEntry(SessionManager.ID, postId); // Remove entry from like_individual_text
                int newLikesCount = postService.getTextPostLikesCount(postId); // Get updated likes count
                updateLikesCount(newLikesCount); // Update the likes count label
                userLikedPost = false;
                // Change the appearance of the like button
                likeBtn.setBackground(new Background(new BackgroundFill(Color.rgb(186, 186, 186, 1.0), null, null)));
            }
        } else {
            System.out.println("Post ID not found."); // Handle case when post ID is not found
        }
    }
    public void updateLikesCount(int newLikesCount) {
        String result = newLikesCount != 1 ? " likes" : " like";
        result = newLikesCount + result;
        numberOfLikesLabel.setText(result);
    }

    private boolean checkIfUserLikedPost() throws SQLException {
        // Get the post ID
        int postId = postService.getTextPostId(thisTextPostDTO);
        if (postId != -1) {
            // Check if the current user has liked the post in like_individual_text table
            return postService.isUserLikedPost(SessionManager.ID, postId);
        } else {
            System.out.println("Post ID not found."); // Handle case when post ID is not found
            return false;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userLikedPost = checkIfUserLikedPost();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    public void sharePost()
    {

    }
    public void commentOnPost()
    {

    }

    public TextPostDTO setData(TextPostDTO textPostDTO)
    {
        thisTextPostDTO = new TextPostDTO();
        //get  image from database
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/female_avatar.png")));
        opImgView.setImage(img);
        opNameLabel.setText(textPostDTO.getOriginalPosterName());
        dateOfPublicationLabel.setText(textPostDTO.getPublicationDateTime().toString());
        postContentLabel.setText(textPostDTO.getPostText());
        numberOfCommentsLabel.setText(textPostDTO.getNumberOfComments() + " : Comments");
        numberOfLikesLabel.setText(textPostDTO.getNumberOfLikes() + " : Likes");
        this.thisTextPostDTO.setPostText(textPostDTO.getPostText());
        System.out.println("TEXT: " + textPostDTO.getPostText());
        this.thisTextPostDTO.setOriginalPosterName(textPostDTO.getOriginalPosterName());
        this.thisTextPostDTO.setPublicationDateTime(textPostDTO.getPublicationDateTime());
        System.out.println("PublicationDateTime: " + textPostDTO.getPublicationDateTime());

        this.thisTextPostDTO.setOpImgSrc(textPostDTO.getOpImgSrc());
        this.thisTextPostDTO.setNumberOfLikes(textPostDTO.getNumberOfLikes());
        this.thisTextPostDTO.setNumberOfComments(textPostDTO.getNumberOfComments());
        this.thisTextPostDTO.setPosterID(textPostDTO.getPosterID());
        TextPostDTO thisTextPostDTO1 = thisTextPostDTO;
        return thisTextPostDTO1;
    }

    @Override
    public String toString() {
        return "textPostItemController{" +
                "opImgView=" + opImgView +
                ", opNameLabel=" + opNameLabel +
                ", dateOfPublicationLabel=" + dateOfPublicationLabel +
                ", postContentLabel=" + postContentLabel +
                ", numberOfLikesLabel=" + numberOfLikesLabel +
                ", numberOfCommentsLabel=" + numberOfCommentsLabel +
                ", likeBtn=" + likeBtn +
                ", commentBtn=" + commentBtn +
                ", shareBtn=" + shareBtn +
                '}';
    }
}
