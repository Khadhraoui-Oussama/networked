package com.fsb.networked.dao;

import com.fsb.networked.dto.ImagePostDTO;
import com.fsb.networked.dto.TextPostDTO;
import com.fsb.networked.dto.VideoPostDTO;
import com.fsb.networked.utils.ConxDB;
import com.fsb.networked.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private static Connection connection = ConxDB.getInstance();
    public static List<TextPostDTO> getTextPosts() {
        List<TextPostDTO> textPosts = new ArrayList<>();
        String query = "SELECT v.*, i.FirstName, i.LastName,i.profilePicture " +
                "FROM networked_db.individual_text_post v " +
                "JOIN individual i ON v.PosterID = i.id " +
                "ORDER BY v.PublicationDateTime DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                TextPostDTO textPost = new TextPostDTO(
                        rs.getString("firstName" ) + " " + rs.getString("lastName" ),
                        rs.getInt("PosterID"),
                        rs.getTimestamp("PublicationDateTime").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getInt("Likes"),
                        rs.getInt("Comments"),
                        rs.getBlob("profilePicture")
                );
               // System.out.println("Datetime TEXT : " + rs.getTimestamp("PublicationDateTime"));
            System.out.println("DATETIME WHEN INITIALIZING : " + textPost.getPublicationDateTime());
            textPosts.add(textPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i< textPosts.size();i++)
        {
            System.out.println("HERE TYPE WHEN LOADING TEXT: " + textPosts.get(i).getClass().getName());
        }
        return textPosts;
    }
    public static List<ImagePostDTO> getImagePosts() {
        List<ImagePostDTO> imagePosts = new ArrayList<>();
        String query = "SELECT v.*, i.FirstName, i.LastName " +
                "FROM networked_db.individual_image_post v " +
                "JOIN individual i ON v.PosterID = i.id " +
                "ORDER BY v.PublicationDateTime DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ImagePostDTO textPost = new ImagePostDTO(
                        rs.getString("firstName" ) + " " + rs.getString("lastName" ),
                        rs.getInt("PosterID"),
                        rs.getTimestamp("PublicationDateTime").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getInt("Likes"),
                        rs.getInt("Comments"),
                        rs.getBlob("posterImage"),
                        rs.getBlob("ImageAttachment")
                );
                imagePosts.add(textPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i< imagePosts.size();i++)
        {
            System.out.println("HERE TYPE WHEN LOADING IMAGE: " + imagePosts.get(i).getClass().getName());
        }
        return imagePosts;
    }
    public static List<VideoPostDTO> getVideoPosts() {
        List<VideoPostDTO> videoPosts = new ArrayList<>();
        String query = "SELECT v.*, i.FirstName, i.LastName " +
                "FROM networked_db.individual_video_post v " +
                "JOIN individual i ON v.PosterID = i.id " +
                "ORDER BY v.PublicationDateTime DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                VideoPostDTO videoPost = new VideoPostDTO(
                        rs.getString("firstName" ) + " " + rs.getString("lastName" ),
                        rs.getInt("PosterID"),
                        rs.getTimestamp("PublicationDateTime").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getInt("Likes"),
                        rs.getInt("Comments"),
                        rs.getBlob("posterImage"),
                        rs.getBlob("VideoAttachment")
                );
                videoPosts.add(videoPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i< videoPosts.size();i++)
        {
            System.out.println("HERE TYPE WHEN LOADING VIDEO: " + videoPosts.get(i).getClass().getName());
        }
        return videoPosts;
    }
    public int addTextPost(TextPostDTO textPostDTO) throws SQLException {
        String query = "INSERT INTO individual_text_post " +
                                    "(PosterID, Content," +
                                    " PublicationDateTime, Likes," +
                                    " Comments, PosterImage)" +
                                    " VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SessionManager.ID);
            pstmt.setString(2, textPostDTO.getPostText());
            pstmt.setTimestamp(3, Timestamp.valueOf(textPostDTO.getPublicationDateTime()));
            pstmt.setInt(4, textPostDTO.getNumberOfLikes());
            pstmt.setInt(5, textPostDTO.getNumberOfComments());
            pstmt.setBlob(6, textPostDTO.getOpImgSrc());
            rowsAffected = pstmt.executeUpdate();
        }
        return rowsAffected;
    }
    public int addImagePost(ImagePostDTO imagePostDTO) throws SQLException {
        String query = "INSERT INTO networked_db.individual_image_post " +
                "(PosterID, Content," +
                " PublicationDateTime, Likes," +
                " Comments, PosterImage," +
                "ImageAttachment)" +
                " VALUES (?, ?, ?,?, ?, ?, ?)";
        int rowsAffected = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SessionManager.ID);
            pstmt.setString(2, imagePostDTO.getPostText());
            pstmt.setTimestamp(3, Timestamp.valueOf(imagePostDTO.getPublicationDateTime()));
            pstmt.setInt(4, imagePostDTO.getNumberOfLikes());
            pstmt.setInt(5, imagePostDTO.getNumberOfComments());
            pstmt.setBlob(6, imagePostDTO.getOpImgSrc());
            pstmt.setBlob(7, imagePostDTO.getAttachmentFile());
            rowsAffected = pstmt.executeUpdate();
        }

        return rowsAffected;
    }
    public int addVideoPost(VideoPostDTO videoPostDTO) throws SQLException {
        String query = "INSERT INTO networked_db.individual_video_post " +
                "(PosterID, Content," +
                " PublicationDateTime, Likes," +
                " Comments, PosterImage," +
                "VideoAttachment)" +
                " VALUES (?, ?, ?,?, ?, ?, ?)";
        int rowsAffected = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, SessionManager.ID);
            pstmt.setString(2, videoPostDTO.getPostText());
            pstmt.setTimestamp(3, Timestamp.valueOf(videoPostDTO.getPublicationDateTime()));
            pstmt.setInt(4, videoPostDTO.getNumberOfLikes());
            pstmt.setInt(5, videoPostDTO.getNumberOfComments());
            pstmt.setBlob(6, videoPostDTO.getOpImgSrc());
            pstmt.setBlob(7, videoPostDTO.getAttachmentFile());
            rowsAffected = pstmt.executeUpdate();
        }
        return rowsAffected;
    }

    //TODO TEXT POST
    public int addLikeToTextPost(TextPostDTO textPostDTO) throws SQLException {
        String query = "UPDATE individual_text_post SET Likes = Likes + 1 WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getTextPostId(textPostDTO));
            return pstmt.executeUpdate();
        }
    }

    public int removeLikeFromTextPost(TextPostDTO textPostDTO) throws SQLException {
        String query = "UPDATE individual_text_post SET Likes = Likes - 1 WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, getTextPostId(textPostDTO));
            return pstmt.executeUpdate();
        }
    }

    public int addLikeIndividualEntry(int individualID, int textPostID) throws SQLException {
        String query = "INSERT INTO like_individual_text (individual_id, text_post_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, individualID);
            pstmt.setInt(2, textPostID);
            return pstmt.executeUpdate();
        }
    }

    public int removeLikeIndividualEntry(int individualID, int textPostID) throws SQLException {
        String query = "DELETE FROM like_individual_text WHERE individual_id = ? AND text_post_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, individualID);
            pstmt.setInt(2, textPostID);
            return pstmt.executeUpdate();
        }
    }
    public int getTextPostId(TextPostDTO textPostDTO) throws SQLException {
        int postId = -1;

        // Check if publicationDateTime is not null
        if (textPostDTO.getPublicationDateTime() == null) {
            System.err.println("PublicationDateTime is null in TextPostDTO: " + textPostDTO);
            return postId; // Return -1 indicating error
        }

        // Get the post ID first
        String querySelect = "SELECT PostID FROM individual_text_post WHERE PosterID = ? AND PublicationDateTime = ? AND Content = ?";
        try (PreparedStatement pstmtSelect = connection.prepareStatement(querySelect)) {
            pstmtSelect.setInt(1, textPostDTO.getPosterID());
            pstmtSelect.setTimestamp(2, Timestamp.valueOf(textPostDTO.getPublicationDateTime()));
            pstmtSelect.setString(3, textPostDTO.getPostText());
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    postId = rs.getInt("PostID");
                }
            }
        }
        return postId;
    }
    public int getTextPostLikesCount(int postId) throws SQLException {
        int likesCount = 0;
        String query = "SELECT Likes FROM individual_text_post WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    likesCount = rs.getInt("Likes");
                }
            }
        }
        return likesCount;
    }
    public boolean isUserLikedPost(int individualId, int postId) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM like_individual_text WHERE individual_id = ? AND text_post_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, individualId);
            pstmt.setInt(2, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    return count > 0; // If count > 0, user has liked the post
                }
            }
        }
        return false; // Default to false if query fails or no entry found
    }

}




/*
public int getTextPostId(TextPostDTO textPostDTO) throws SQLException {
        int postId = -1;
        System.out.println("datetime : " +textPostDTO.getPublicationDateTime());
        // Get the post ID first
        String querySelect = "SELECT PostID FROM individual_image_post WHERE PosterID = ? AND PublicationDateTime = ? AND Content = ?";
        try (PreparedStatement pstmtSelect = connection.prepareStatement(querySelect)) {
            pstmtSelect.setInt(1, textPostDTO.getPosterID());
            pstmtSelect.setTimestamp(2, Timestamp.valueOf(textPostDTO.getPublicationDateTime()));
            pstmtSelect.setString(3, textPostDTO.getPostText());
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    postId = rs.getInt("PostID");
                }
            }
        }
        return postId;
    }

    //text
    public int changeTextPostReaction(TextPostDTO textPostDTO, int changeByValue) throws SQLException {
        int postId = getTextPostId(textPostDTO);
        System.out.println("iddddd : " + postId);
        if (postId != -1) {
            // Update the reaction number
            String queryUpdate = "UPDATE individual_text_post SET Likes = Likes + ? WHERE PostID = ?";
            try (PreparedStatement pstmtUpdate = connection.prepareStatement(queryUpdate)) {
                pstmtUpdate.setInt(1, changeByValue);  // Use changeByValue parameter here
                pstmtUpdate.setInt(2, postId);
                return pstmtUpdate.executeUpdate();
            }
        }
        return 0;
    }
    public int getTextPostReactionNumber(int postID) throws SQLException {
        int likesCount = 0;
        String query = "SELECT Likes FROM individual_text_post WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, postID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    likesCount = rs.getInt("likes");
                }
            }
        }
        return likesCount;
    }
public boolean userLikedPost(int postID, int userID,String tableName,String individualOrEntreprise) throws SQLException {
        boolean res = false;
        String query = "SELECT like_id FROM " + tableName +" WHERE PostID = ? AND " + individualOrEntreprise + "_id";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, postID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return res;
    }
  public int getImagePostReactionNumber(int postID) throws SQLException {
        int likesCount = 0;
        String query = "SELECT Likes FROM individual_image_post WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, postID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    likesCount = rs.getInt("likes");
                }
            }
        }
        return likesCount;
    }
    public int getVideoPostId(VideoPostDTO videoPostDTO) throws SQLException {
        int postId = -1;
        // Get the post ID first
        String querySelect = "SELECT PostID FROM individual_video_post WHERE PosterID = ? AND PublicationDateTime = ? AND Content = ?";
        try (PreparedStatement pstmtSelect = connection.prepareStatement(querySelect)) {
            pstmtSelect.setInt(1, videoPostDTO.getPosterID());
            pstmtSelect.setTimestamp(2, Timestamp.valueOf(videoPostDTO.getPublicationDateTime()));
            pstmtSelect.setString(3, videoPostDTO.getPostText());
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    postId = rs.getInt("PostID");
                }
            }
        }
        return postId;
    }

    public int getVideoPostReactionNumber(int postID) throws SQLException {
        int likesCount = 0;
        String query = "SELECT Likes FROM individual_image_post WHERE PostID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, postID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    likesCount = rs.getInt("likes");
                }
            }
        }
        return likesCount;
    }

    //image
    public int changeImagePostReaction(ImagePostDTO imagePostDTO, int changeByValue) throws SQLException {
        int postId = getImagePostId(imagePostDTO);

        if (postId != -1) {
            // Update the reaction number
            String queryUpdate = "UPDATE individual_image_post SET Likes = Likes + ? WHERE PostID = ?";
            try (PreparedStatement pstmtUpdate = connection.prepareStatement(queryUpdate)) {
                pstmtUpdate.setInt(1, changeByValue);  // Use changeByValue parameter here
                pstmtUpdate.setInt(2, postId);
                return pstmtUpdate.executeUpdate();
            }
        }
        return -1;
    }
    public int changeVideoPostReaction(VideoPostDTO videoPostDTO, int changeByValue) throws SQLException {
        int postId = getVideoPostId(videoPostDTO);

        if (postId != -1) {
            // Update the reaction number
            String queryUpdate = "UPDATE individual_video_post SET Likes = Likes + ? WHERE PostID = ?";
            try (PreparedStatement pstmtUpdate = connection.prepareStatement(queryUpdate)) {
                pstmtUpdate.setInt(1, changeByValue);  // Use changeByValue parameter here
                pstmtUpdate.setInt(2, postId);
                return pstmtUpdate.executeUpdate();
            }
        }
        return -1;
    }

    public int getImagePostId(ImagePostDTO imagePostDTO) throws SQLException {
        int postId = -1;
        // Get the post ID first
        String querySelect = "SELECT PostID FROM individual_image_post WHERE PosterID = ? AND PublicationDateTime = ? AND Content = ?";
        try (PreparedStatement pstmtSelect = connection.prepareStatement(querySelect)) {
            pstmtSelect.setInt(1, imagePostDTO.getPosterID());
            pstmtSelect.setTimestamp(2, Timestamp.valueOf(imagePostDTO.getPublicationDateTime()));
            pstmtSelect.setString(3, imagePostDTO.getPostText());
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    postId = rs.getInt("PostID");
                }
            }
        }
        return postId;
    }*/

