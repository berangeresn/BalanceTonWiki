/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.service.WikiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ambre.wiki.entities.Wiki;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrateur
 */
@RestController
@RequestMapping("/wikis")
public class WikiController {
    
    @Autowired
    WikiService wikiService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Wiki>> getAllWikis() {        
            List<Wiki> wikis = wikiService.getAllWikis();
            return new ResponseEntity<List<Wiki>>(wikis, HttpStatus.OK);
	}
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<Wiki>createWiki(@RequestParam String title, @RequestParam String content, @RequestParam String user_id){
    return new ResponseEntity<Wiki>(wikiService.createWiki(title, content, user_id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/find{id}", method= RequestMethod.GET)
    public ResponseEntity<Wiki>getWikiById(@RequestParam Long id) {
    return new ResponseEntity<Wiki>(wikiService.getWikiById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/delete{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean>deleteWiki(@RequestParam String id) {
    return new ResponseEntity<Boolean>(wikiService.deleteWiki(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/update{id}", method= RequestMethod.PUT)
    public ResponseEntity<Wiki>UpdateWiki(@RequestParam String id,@RequestParam String title, @RequestParam String content, @RequestParam String user_id, @RequestParam String category_id) {
    return new ResponseEntity<Wiki>(wikiService.updateWiki(id, title, content, user_id, category_id), HttpStatus.OK);
    }
    
    @GetMapping(path="/search")
	public @ResponseBody List<Wiki> searchWiki(@RequestParam String keyword) {
		return wikiService.searchWiki(keyword);
	}
        
}
