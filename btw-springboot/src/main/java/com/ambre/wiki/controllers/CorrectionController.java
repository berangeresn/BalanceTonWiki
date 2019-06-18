/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Correction;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ambre.wiki.service.CorrectionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Administrateur
 */
@RestController
@RequestMapping("/v1/correction")
public class CorrectionController {
    
    @Autowired
    CorrectionService corrService;
    
    @RequestMapping(value = "/corrector/list", method = RequestMethod.GET)
	public ResponseEntity<List<Correction>> getAllCorrections() {        
            return new ResponseEntity<List<Correction>>(corrService.getAllCorrections(), HttpStatus.OK);
	}
        
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	public ResponseEntity<Correction> createCorrection(@PathVariable ("id") Long wikiId, @RequestAttribute("user") User u) {        
            return new ResponseEntity<Correction>(corrService.createCorrection(wikiId, u), HttpStatus.OK);
    }
        
}
