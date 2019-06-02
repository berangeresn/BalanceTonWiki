/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.service;

import com.ambre.wiki.repositories.WikiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ambre.wiki.entities.Wiki;
/** 
 * 
 *
 * @author Administrateur
 */
@Service
public class WikiService {
    
    @Autowired
    WikiRepository wikiRepo;
    
    public Wiki createWiki(String title, String content, String user_id) {
       return wikiRepo.createWiki(title, content, user_id);
    }
    
    public Wiki getWikiById(Long id) {
    	return wikiRepo.getWikiById(id);
    }
        
    public List<Wiki> getAllWikis(){
        return wikiRepo.getAllWikis();
    }
    
    public Boolean deleteWiki(String id) {
    return wikiRepo.deleteWiki(id);
    }	
    
    public Wiki updateWiki(String id, String title, String content, String user_id, String category_id){
    return wikiRepo.updateWiki(id, title, content, user_id, category_id);
    }
    
    public List<Wiki> searchWiki(String keyword){
        return wikiRepo.searchWiki(keyword);
    }    
        
}
