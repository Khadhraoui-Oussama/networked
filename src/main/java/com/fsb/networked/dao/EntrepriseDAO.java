package com.fsb.networked.dao;

import com.fsb.networked.dto.EntrepriseDTO;
import com.fsb.networked.utils.Conversions;
import com.fsb.networked.utils.ConxDB;

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
                                        "name,password," +
                                        "sector,size," +
                                        "location,founders," +
                                        "website,logoPicture)" +
                                        " VALUES (?,?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS);
            //JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUp","emailAddress")
            //email
            pstmt.setString(1, entrepriseDTO.getEntrepriseName());
            //password
            pstmt.setString(2, entrepriseDTO.getPassword());
            //sector
            pstmt.setString(3, entrepriseDTO.getEntrepriseSector());
            //size
            pstmt.setString(4, entrepriseDTO.getEntrepriseSize());
            pstmt.setString(5, entrepriseDTO.getEntrepriseLocation());
            pstmt.setString(6, entrepriseDTO.getEntrepriseFounders());
            pstmt.setString(7, entrepriseDTO.getEntrepriseWebsite());

            //logo picture
            //create an array of bytes for the pfp from the file that is created from the pathof image in the individual.json file
            String picturePath = entrepriseDTO.getEntrepriseLogoPath().substring(6);
            File pictureFile = new File(picturePath);
            byte[] pictureByteArray = Conversions.convertFileToByteArray(pictureFile);
            System.out.println("byte array size image : " + pictureByteArray.length);
            pstmt.setBytes(8, pictureByteArray);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            //get id needed to populate the other tables
            if (rs.next()) {
                entrepriseID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entrepriseID;
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
