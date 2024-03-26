package com.fsb.networked.service;

import com.fsb.networked.dao.IndividualDAO;

public class IndividualService {
    IndividualDAO individualDAO = new IndividualDAO();
    /*public int saveIndividualToDB()
    {
        //TODO IF ANY TIME IS LEFT FIGURE OUT IF WE SHOULD OD FOR THIS SERVICE THE SAME AS ENTREPRISE SERVICE
        // TODO BECAUSE IT DOENST WORK IN WITH WHAT WE HAVE OF JSON FILE
        int result = 0;
        result = EntrepriseDAO.saveToDB(entrepriseDTO);
        return result;
    }*/
    public int individualExists(String email,String password)
    {
        return individualDAO.individualExists(email,password);
    }
}
