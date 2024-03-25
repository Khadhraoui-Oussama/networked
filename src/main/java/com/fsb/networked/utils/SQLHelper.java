package com.fsb.networked.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {
    public static boolean AttributeIsUnique(String attribute,String value,String tableName){
        Connection connection = ConxDB.getInstance();
        String sql = "SELECT COUNT(*) FROM " + tableName +" WHERE "+ attribute +"  = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,value);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1) == 0;
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
