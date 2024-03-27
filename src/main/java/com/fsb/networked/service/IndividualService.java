package com.fsb.networked.service;

import com.fsb.networked.dao.IndividualDAO;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class IndividualService {
    IndividualDAO individualDAO = new IndividualDAO();
    public int saveIndividualToDB(byte[] pdf_resume)
    {
        //TODO IF ANY TIME IS LEFT FIGURE OUT IF WE SHOULD OD FOR THIS SERVICE THE SAME AS ENTREPRISE SERVICE
        // TODO BECAUSE IT DOENST WORK IN WITH WHAT WE HAVE OF JSON FILE
        int result = 0;
        result = IndividualDAO.saveToDB(pdf_resume);
        return result;
    }
    public Blob getIndividualImageBlobFromDB(int individualID) throws SQLException, IOException {
        Blob image = IndividualDAO.getIndividualImageFromDB(individualID);
        return image;
    }
    public String getIndividualNameFromDB(int individualID) throws SQLException, IOException {
        String name = IndividualDAO.getIndividualNameFromDB(individualID);
        return name;
    }
    public int individualExists(String email,String password)
    {
        return individualDAO.individualExists(email,password);
    }
}
