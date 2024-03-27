package com.fsb.networked.dao;

import com.fsb.networked.dto.EntrepriseDTO;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.ConxDB;
import com.fsb.networked.utils.SessionManager;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class EntrepriseDAO {

    //connection
    private static Connection connection = ConxDB.getInstance();
    public static int saveToDB(EntrepriseDTO entrepriseDTO) {

        int entrepriseID = 0;
        PreparedStatement pstmt = null; // we use a prepared statement to avoid any SQL injection attacks.
        ResultSet rs = null;
        try {
            String sqlSave = "INSERT INTO entreprise(" +
                                        "name,email,password," +
                                        "sector,size," +
                                        "location,founders," +
                                        "website,logoPicture)" +
                                        " VALUES (?,?, ?, ?, ?,?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS);
            //JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","emailAddress")
            //name
            pstmt.setString(1, entrepriseDTO.getEntrepriseName());
            //email
            pstmt.setString(2, entrepriseDTO.getEmail());
            //password
            pstmt.setString(3, entrepriseDTO.getPassword());
            //sector
            pstmt.setString(4, entrepriseDTO.getEntrepriseSector());
            //size
            pstmt.setString(5, entrepriseDTO.getEntrepriseSize());
            pstmt.setString(6, entrepriseDTO.getEntrepriseLocation());
            pstmt.setString(7, entrepriseDTO.getEntrepriseFounders());
            pstmt.setString(8, entrepriseDTO.getEntrepriseWebsite());

            //logo picture
            //create an array of bytes for the pfp from the file that is created from the pathof image in the individual.json file
            String picturePath = entrepriseDTO.getEntrepriseLogoPath().substring(6);
            File pictureFile = new File(picturePath);
            byte[] pictureByteArray = Conversions.convertFileToByteArray(pictureFile);
            System.out.println("byte array size image : " + pictureByteArray.length);
            pstmt.setBytes(9, pictureByteArray);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            //get id needed to populate the other tables
            if (rs.next()) {
                entrepriseID = rs.getInt(1);
            }
            String sqlSaveSession = "INSERT INTO session(entrepriseID, sessionID) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sqlSaveSession);
            pstmt.setInt(1, entrepriseID); // Assuming userID is a foreign key in the session table
            pstmt.setInt(2, SessionManager.generateSessionID(entrepriseID));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entrepriseID;
    }
    public static int entrepriseExists(String email, String password) {
        int resultInt = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //check if the email and password match
            String sqlCheckUser = "SELECT id FROM entreprise WHERE email = ? AND password = ?";
            pstmt = connection.prepareStatement(sqlCheckUser);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // Email and password match, return the entreprise ID
                resultInt = rs.getInt("id");
            } else {
                // Email or password does not match, set appropriate return code
                resultInt = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultInt;
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
