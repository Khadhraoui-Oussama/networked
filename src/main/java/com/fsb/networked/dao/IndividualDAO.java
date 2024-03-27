package com.fsb.networked.dao;

import com.fsb.networked.dto.EducationDTO;
import com.fsb.networked.dto.ProjectDTO;
import com.fsb.networked.dto.SkillDTO;
import com.fsb.networked.dto.WorkDTO;
import com.fsb.networked.utils.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.*;
public class IndividualDAO {

    //connection
    public static int saveToDB(byte[] pdfFile)
    {
        //TODO FIX THE SAVE FUNCTION JUST SAVES BASIC INFO EVEN IF EDUCATION INFO IS NOT CORRECT OR CAUSED ERRORS
        //TODO NONE OF THAT IS NECCESARY AS ALL DATA WILL BE CORRECT BECAUSE OF FRONT END INPUT CHECKS VALIDATEINFO FUNCTIONS COMING IN CLUTCH
        int createdIndividualID = saveBasicInfoToDB(pdfFile);
        saveEducationsToDB(createdIndividualID);
        saveWorkExperiencesToDB(createdIndividualID);
        saveSkillsToDB(createdIndividualID);
        saveProjectsToDB(createdIndividualID);
        return  createdIndividualID;
    }
    private static Connection connection = ConxDB.getInstance();
    public static int saveBasicInfoToDB(byte[] pdfFile)
    {
        int individualId = 0;
        PreparedStatement pstmt = null; // we use a prepared statement to avoid any SQL injection attacks.
        ResultSet rs = null;
        try
        {
            String sqlSave = "INSERT INTO individual(email,password," +
                                            "firstName,lastName," +
                                            "profilePicture,video_cv," +
                                            "country,birth_date" +
                                            ",isAdmin,gender,pdf_resume)" +
                                            " VALUES (?,?, ?, ?, ?, ?, ?,?, ?, ? ,?)";
            pstmt = connection.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS);
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
            File pictureFile = new File(picturePath);
            byte[] pictureByteArray = Conversions.convertFileToByteArray(pictureFile);
            System.out.println("byte array size image : "  + pictureByteArray.length);
            pstmt.setBytes(5, pictureByteArray);

            //video resume
            String videoPath = (JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpVideo", "videoPath")).toString().substring(6);
            File videoResumeFile = new File(videoPath);
            byte[] videoResumeByteArray = Conversions.convertFileToByteArray(videoResumeFile);
            System.out.println("byte array size video : "  + videoResumeByteArray.length);
            pstmt.setBytes(6, videoResumeByteArray);

            //country
            pstmt.setString(7, JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","country"));

            //birth_date
            pstmt.setDate(8, Date.valueOf(Conversions.stringtoLocalDate(JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","dob"))));
            //isAdmin
            pstmt.setBoolean(9,false);
            //gender
            pstmt.setString(10,JSONParser.getValueFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpBasic","gender"));
            //pdfresume
            pstmt.setBytes(11,pdfFile);
            System.out.println("byte array size pdf : "  + pdfFile.length);

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            //get id needed to populate the other tables
            if(rs.next())
            {
                individualId = rs.getInt(1);
            }
            String sqlSaveSession = "INSERT INTO session(individualID, sessionID) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sqlSaveSession);
            pstmt.setInt(1, individualId); // Assuming userID is a foreign key in the session table
            pstmt.setInt(2, SessionManager.generateSessionID(individualId));
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return individualId;
    }
    public static void saveEducationsToDB(int accountId) {
        if (accountId == -1) return;
        PreparedStatement pstmt = null;
        try{
            String sql = "INSERT INTO education (user_id, diploma," +
                                                " institute, description," +
                                                " type, startDate," +
                                                " endDate)" +
                                                " VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            JSONArray educationArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON,"signUpEducation");
            for (int i = 0; i < educationArray.length();i++ ) {
                //get educationDTO from the educationArray JSON object item (cast from Object) at index i
                EducationDTO educationDTO =  JSONParser.JSONObjectToEducationDTO((JSONObject) educationArray.get(i));
                pstmt.setInt(1, accountId);
                pstmt.setString(2, educationDTO.getDiploma());
                pstmt.setString(3, educationDTO.getInstitute());
                pstmt.setString(4, educationDTO.getDescription());
                pstmt.setString(5, educationDTO.getType());
                pstmt.setDate(6, java.sql.Date.valueOf(educationDTO.getStartDate()));
                pstmt.setDate(7, java.sql.Date.valueOf(educationDTO.getEndDate()));

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveWorkExperiencesToDB(int accountId) {
        if (accountId == -1) return;
        PreparedStatement pstmt = null;
        try{
            String sql = "INSERT INTO work (" +
                                    "user_id, position," +
                                    " company, description," +
                                    " type, startDate," +
                                    " endDate)" +
                                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            JSONArray educationArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpWork");
            for (int i = 0; i < educationArray.length();i++ ) {
                //get educationDTO from the educationArray JSON object item (cast from Object) at index i
                WorkDTO workDTO =  JSONParser.JSONObjectToWorkDTO((JSONObject) educationArray.get(i));
                pstmt.setInt(1, accountId);
                pstmt.setString(2, workDTO.getPosition());
                pstmt.setString(3, workDTO.getCompany());
                pstmt.setString(4, workDTO.getDescription());
                pstmt.setString(5, workDTO.getType());
                pstmt.setDate(6, java.sql.Date.valueOf(workDTO.getStartDate()));
                pstmt.setDate(7, java.sql.Date.valueOf(workDTO.getEndDate()));

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveSkillsToDB(int accountId) {
        if (accountId == -1) return;
        PreparedStatement pstmt = null;
        try{
            String sql = "INSERT INTO skill (" +
                    "user_id, title," +
                    " technology, description," +
                    " level)" +
                    " VALUES ( ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            JSONArray skillsArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpSkills");
            for (int i = 0; i < skillsArray.length();i++ ) {
                SkillDTO skillDTO =  JSONParser.JSONObjectToSkillDTO((JSONObject) skillsArray.get(i));
                pstmt.setInt(1, accountId);
                pstmt.setString(2, skillDTO.getTitle());
                pstmt.setString(3, skillDTO.getTechnology());
                pstmt.setString(4, skillDTO.getDescription());
                pstmt.setString(5, skillDTO.getLevel());

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveProjectsToDB(int accountId) {
        if (accountId == -1) return;
        PreparedStatement pstmt = null;
        try{
            String sql = "INSERT INTO project (" +
                    "user_id, title," +
                    " technology, description," +
                    " link)" +
                    " VALUES ( ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            JSONArray projectsJSONArray = JSONParser.getJSONArrayFromJSONFile(ImportantFileReferences.INDIVIDUALJSON, "signUpProjects");
            for (int i = 0; i < projectsJSONArray.length();i++ ) {
                ProjectDTO projectDTO =  JSONParser.JSONObjectToProjectDTO((JSONObject) projectsJSONArray.get(i));
                pstmt.setInt(1, accountId);
                pstmt.setString(2, projectDTO.getTitle());
                pstmt.setString(3, projectDTO.getTechnology());
                pstmt.setString(4, projectDTO.getDescription());
                pstmt.setString(5, projectDTO.getLink());

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int individualExists(String email, String password) {
        int resultInt = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //check if the email and password match
            String sqlCheckUser = "SELECT id FROM individual WHERE email = ? AND password = ?";
            pstmt = connection.prepareStatement(sqlCheckUser);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Email and password match, return the user ID
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
    public static Blob getIndividualImageFromDB(int individualID) throws SQLException, IOException {
        String query = "SELECT profilePicture FROM individual WHERE id = ?";
        Blob imageBlob = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, individualID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the image blob from the result set
                    imageBlob = rs.getBlob("profilePicture");
                }
            }
        }

        return imageBlob;
    }

    public static String getIndividualNameFromDB(int individualID) throws SQLException, IOException {
        String query = "SELECT individual.firstName, individual.lastName FROM individual WHERE id = ?";
        String name = "";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, individualID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the image blob from the result set
                    name = rs.getString("firstName");
                    name += " ";
                    name += rs.getString("lastName");
                }
            }
        }

        return name;
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