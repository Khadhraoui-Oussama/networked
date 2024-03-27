package com.fsb.networked.utils;

public class Regexes {
    //public static String LINK_REGEX = "^(http(s)?://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ;,./?%&=]*)?$";
    public static String LINK_REGEX = "^(https|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
   /* public static String UPPER = "^[A-Z]+$";
    public static String DESCRIPTION_REGEX = "^(?=.{1,150}$)[a-zA-Z0-9]+(?:[ _-][a-zA-Z0-9]+)*$";
    public static String EDUCATION_FIELD_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9 _-]{1,150}$";

    public static String POSITION_REGEX = "[a-zA-Z][a-zA-Z_-]{1,38}[a-zA-Z]$";
    public static String TECHNOLOGY_REGEX = "[a-zA-Z0-9][a-zA-Z0-9 _-]{1,38}[a-zA-Z0-9]$";
     public static String COMPANY_NAME_REGEX = "^[A-Z0-9][a-z0-9-_]{1,39}$";
    */
    public static String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
   public static String FOUNDERS_REGEX = "^([-A-Za-z]+(?:\\s[-A-Za-z]+)*(?:\\s*,\\s*[-A-Za-z]+(?:\\s[-A-Za-z]+)*)*$|^[A-Za-z-]+(?:\\s[-A-Za-z]+)*(?:\\s*-\\s*[A-Za-z-]+(?:\\s[-A-Za-z]+)*)*$)";
    public static String NAME_REGEX = "^[A-Z][a-z]{1,39}$";
    public static String TITLE_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9 _,-]{1,38}[a-zA-Z0-9]$";
    public static String POSITION_REGEX = "^[a-zA-Z][a-zA-Z _,-]{1,38}[a-zA-Z]$";
    public static String TECHNOLOGY_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9 _,-]{1,38}[a-zA-Z0-9]$";
    public static String LOCATION_REGEX = "^[\\w\\s,.'-]{1,50}$";
    public static String DESCRIPTION_REGEX = "^.{1,150}$";
    public static String POST_CONTENT_REGEX = "^.{1,249}$";
    public static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,30}$";



}
