package com.fsb.networked.service;

import com.fsb.networked.dao.PostDAO;
import com.fsb.networked.dto.ImagePostDTO;
import com.fsb.networked.dto.TextPostDTO;
import com.fsb.networked.dto.VideoPostDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    PostDAO postDAO = new PostDAO();

    public <T> List<T> getPostsFromDB()
    {
            List<T> ls = new ArrayList<T>();
            //TODO FIGURE OUT HOW TO ORGANISE POSTS BY PUBLICATION DATE
            List<T> listImagePosts = (List<T>) PostDAO.getImagePosts();
            List<T> listVideoPosts = (List<T>) PostDAO.getVideoPosts();
            List<T> listTextPosts = (List<T>) PostDAO.getTextPosts();

        //add to return list
            ls.addAll(listImagePosts);
            ls.addAll(listVideoPosts);
            ls.addAll(listTextPosts);
            ls.sort(null);
            //limit to 25
            int limit = Math.min(25, ls.size());
            return ls.subList(0, limit);
    }
    public int addTextPost(TextPostDTO textPostDTO) throws SQLException {
        return postDAO.addTextPost(textPostDTO);
    }
    public int addImagePost(ImagePostDTO imagePostDTO) throws SQLException {
        return postDAO.addImagePost(imagePostDTO);
    }
    public int addVideoPost(VideoPostDTO videoPostDTO) throws SQLException {
        return postDAO.addVideoPost(videoPostDTO);
    }

    public int addLikeToTextPost(TextPostDTO textPostDTO) throws SQLException {
        return postDAO.addLikeToTextPost(textPostDTO);
    }

    public int removeLikeFromTextPost(TextPostDTO textPostDTO) throws SQLException {
        return postDAO.removeLikeFromTextPost(textPostDTO);
    }

    public int addLikeIndividualEntry(int individualID, int textPostID) throws SQLException {
        return postDAO.addLikeIndividualEntry(individualID,textPostID);

    }

    public int removeLikeIndividualEntry(int individualID, int textPostID) throws SQLException {
        return postDAO.removeLikeIndividualEntry(individualID,textPostID);
    }
    public int getTextPostId(TextPostDTO textPostDTO) throws SQLException {
        return postDAO.getTextPostId(textPostDTO);
    }
    public int getTextPostLikesCount(int postId) throws SQLException {
        return postDAO.getTextPostLikesCount(postId);
    }
    public boolean isUserLikedPost(int individualId, int postId) throws SQLException {
        return postDAO.isUserLikedPost(individualId,postId);
    }
}
 /*ImagePostDTO post2 = new ImagePostDTO();
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
*/