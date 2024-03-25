package com.fsb.networked.dao;

import com.fsb.networked.utils.*;

import java.io.File;
import java.io.IOException;
import java.sql.*;
public class IndividualDAO {

    //connection
    private static Connection conn = ConxDB.getInstance();
    public static int save()
    {
        int individualId = 0;
        PreparedStatement pstmt = null; // we use a prepared statement to avoid any SQL injection attacks.
        PreparedStatement pstmt2 = null; // we use a prepared statement to avoid any SQL injection attacks.
        ResultSet rs = null;
        try
        {
            String sqlSave = "INSERT INTO user(email,password," +
                                            "firstName,lastName," +
                                            "profile_picture,video_cv," +
                                            "country,birth_date) VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
            pstmt = conn.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS);
            //JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","emailAddress")
            //email
            pstmt.setString(1, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","emailAddress"));
            //password
            pstmt.setString(2, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","password"));
            //firstName
            pstmt.setString(3, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","firstName"));
            //lastName
            pstmt.setString(4, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","lastName"));
            //profile_picture
            //create an array of bytes for the pfp from the file that is created from the pathof image in the individual.json file
            String picturePath = (JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpBasic", "picture")).toString().substring(6);
            //URI picturePathUri = URI.create(picturePath);
            File pictureFile = new File(picturePath);
            byte[] profilePicture = ImageConverter.convertFileToByteArray(pictureFile);
            System.out.println("byte array size: "  + profilePicture.length);
            pstmt.setBytes(5, profilePicture);
            //TODO video_cv
            pstmt.setBytes(6, null);
            //country
            pstmt.setString(7, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","country"));
            //birth_date
            pstmt.setDate(8, Date.valueOf(Conversions.stringtoLocalDate(JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","dob"))));

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next())
            {
                individualId = rs.getInt(1);
            }
            //SET THE USER account type 1 if individual and 2 if entreprise
            String sqlUserAccountType = "INSERT INTO user_account_type(userID,account_type) VALUES (?, ?)";
            pstmt2 = conn.prepareStatement(sqlUserAccountType, Statement.RETURN_GENERATED_KEYS);
            pstmt2.setInt(1, individualId);
            pstmt2.setInt(2,1);
            pstmt2.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return individualId;
    }

/*
    public static int delete(String n, String p) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM personne WHERE nom = ? AND prenom = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, n);
            pstmt.setString(2, p);

            rowsAffected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }


    public static int deleteLast(String text, String text2) {
        int rowsAffected = 0;
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM personne ORDER BY id DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rowsAffected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
        /*public static List<IndividualDTO> findAll()
    {
        Statement stmt = null;
        ResultSet rs = null;

        List<IndividualDTO> individuals = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next())
            {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                IndividualDTO p = new IndividualDTO();
                individuals.add(p);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return individuals;
    }
*/
}