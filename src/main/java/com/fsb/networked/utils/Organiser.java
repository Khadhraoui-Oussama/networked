package com.fsb.networked.utils;

import java.util.ArrayList;
import java.util.List;

public class Organiser {
    public static <T> List<T> organizePostsByDate(List<T> listTextPosts, List<T> listImagePosts, List<T> listVideoPosts) {
        List<T> organizedPosts = new ArrayList<>();

        organizedPosts.addAll(listTextPosts);
        organizedPosts.addAll(listImagePosts);
        organizedPosts.addAll(listVideoPosts);

        // Sort the list based on natural ordering defined in equals in each PostDTO class
        organizedPosts.sort(null);

        return organizedPosts;
    }
}