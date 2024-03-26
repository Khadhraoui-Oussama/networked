package com.fsb.networked.service;

import com.fsb.networked.dao.EntrepriseDAO;
import com.fsb.networked.dto.EntrepriseDTO;

public class EntrepriseService {
    EntrepriseDAO entrepriseDAO = new EntrepriseDAO();
    public int saveEntrepriseToDB(EntrepriseDTO entrepriseDTO)
    {
        //TODO IF ANY BUSINESS LOGIC NEEDS TO BE DONE IT SHOULD BE HERE
        int result = 0;
        result = EntrepriseDAO.saveToDB(entrepriseDTO);
        return result;
    }

    public int entrepriseExists(String email, String password) {
        return entrepriseDAO.entrepriseExists(email,password);
    }
}
