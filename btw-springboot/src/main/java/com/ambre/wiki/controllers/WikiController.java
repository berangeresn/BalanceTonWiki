/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.service.WikiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ambre.wiki.entities.Wiki;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrateur
 */
@RestController
@CrossOrigin
@RequestMapping("/wikis")
public class WikiController {
    
    @Autowired
    WikiService wikiService;
 
       @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
	public ResponseEntity<List<Wiki>> getAllWikisVV() {        
            List<Wiki> wikis = wikiService.getAllWikis();
            return new ResponseEntity<List<Wiki>>(wikis, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/v1/admin/list", method = RequestMethod.GET)
	public ResponseEntity<List<Wiki>> getAllWikis() {        
            List<Wiki> wikis = wikiService.getAllWikis();
            return new ResponseEntity<List<Wiki>>(wikis, HttpStatus.OK);
	}
    @RequestMapping(value="/v1/contri/create", method = RequestMethod.POST)
    public ResponseEntity<Wiki>createWiki(@RequestParam String title, @RequestParam String content, @RequestAttribute("user") User u){
        return new ResponseEntity<Wiki>(wikiService.createWiki(title, content, u), HttpStatus.OK);
    }
    
    @RequestMapping(value="/v1/admin/find/{id}", method= RequestMethod.GET)
    public ResponseEntity<Wiki>getWikiById(@RequestParam Long id) {
    return new ResponseEntity<Wiki>(wikiService.getWikiById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/v1/admin/delete/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean>deleteWiki(@RequestParam String id) {
    return new ResponseEntity<Boolean>(wikiService.deleteWiki(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/v1/contri/update/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Wiki>UpdateWiki(@PathVariable ("id") Long wikiId,@RequestParam String title, @RequestParam String content, @RequestAttribute("user") User user, @RequestParam String category_id) {
        System.out.println(user);
        return new ResponseEntity<Wiki>(wikiService.updateWiki(wikiId, title, content, user, category_id), HttpStatus.OK);
    }
    
    @GetMapping(path="/contri/search")
	public @ResponseBody List<Wiki> searchWiki(@RequestParam String keyword) {
		return wikiService.searchWiki(keyword);
    }
    
    @RequestMapping(value = "/allvalid", method = RequestMethod.GET)
	public List<Wiki> findValidWikis() {        
            return wikiService.findValidWikis();
	}
    
    @RequestMapping(value = "/v1/contri/{id}", method = RequestMethod.GET)
	public List<Wiki> findWikisByUser(@PathVariable("id") User user) {        
            return wikiService.findWikisByUser(user);
	}
        
}
