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
            while (rs.next()) {
                TextPostDTO textPost = new TextPostDTO(
                        rs.getString("firstName" ) + " " + rs.getString("lastName" ),
                        rs.getTimestamp("PublicationDateTime").toLocalDateTime(),
                        rs.getString("Content"),
                        rs.getInt("Likes"),
                        rs.getInt("Comments"),
                        rs.getBlob("profilePicture")
                );
                textPosts.add(textPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return videoPosts;
    }
    public static int addTextPost(TextPostDTO textPostDTO) throws SQLException {
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
}

