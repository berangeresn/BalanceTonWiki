/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.service;

import com.ambre.wiki.entities.Correction;
import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.repositories.CorrectionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorrectionService {
    @Autowired
    CorrectionRepository corrRepo; 
    
    public List<Correction> getAllCorrections() {
        return corrRepo.getAllCorrections();
    }
    
    public List<Correction> getCorrectionByUser(User u) {
        return corrRepo.getCorrectionByUser(u);
    }
    
    public Correction createCorrection(Long wikiId, User u ){
        return corrRepo.createCorrection(wikiId, u);
    }

  
   
}
