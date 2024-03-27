package com.fsb.networked.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionManager {
    public static int ID = -1;

    public static void setID(int ID) {
        SessionManager.ID = ID;
    }

    public static int getID() {
        return ID;
    }
    public static int generateSessionID(int userID) {
        int randomValue = (int) (Math.random() * Integer.MAX_VALUE);
        long result = (long) userID * randomValue * 31;
        // make sure the value doesn't exceed the integer max value
        int sessionID = (int) (result % Integer.MAX_VALUE);
        return sessionID;
    }
    private static Connection connection = ConxDB.getInstance();
    public static int getSessionIDEntreprise() throws SQLException {
        int sessionID = -1;
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT sessionID FROM session WHERE entrepriseID = ?")) {
            pstmt.setInt(1, ID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sessionID = rs.getInt("sessionID");
                }
            }
        }
        return sessionID;
    } public static int getSessionIDIndividual() throws SQLException {
        int sessionID = -1;
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT sessionID FROM session WHERE individualID = ?")) {
            pstmt.setInt(1, ID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    sessionID = rs.getInt("sessionID");
                }
            }
        }
        return sessionID;
    }
    public static int setSessionIDIndividual() throws SQLException {
        int sessionID = -1;
        PreparedStatement pstmt = null; // we use a prepared statement to avoid any SQL injection attacks.
        ResultSet rs = null;
        String sqlGetSession = "INSERT INTO session(individualID) VALUES (?)";
        pstmt = connection.prepareStatement(sqlGetSession);
        pstmt.setInt(1,ID);
        sessionID = pstmt.executeUpdate();
        return sessionID;
    }
    public static int setSessionIDEntreprise() throws SQLException {
        int sessionID = -1;
        PreparedStatement pstmt = null; // we use a prepared statement to avoid any SQL injection attacks.
        ResultSet rs = null;
        String sqlGetSession = "INSERT INTO session(entrepriseID) VALUES (?)";
        pstmt = connection.prepareStatement(sqlGetSession);
        sessionID = SessionManager.generateSessionID(ID);
        pstmt.setInt(1,sessionID);
        pstmt.executeUpdate();
        return sessionID;
    }
    public static void cleanSessionRow(int individualID, int sessionID) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM session WHERE individualID = ? AND sessionID = ?")) {
            pstmt.setInt(1, individualID);
            pstmt.setInt(2, sessionID);
            pstmt.executeUpdate();
        }
    }

}