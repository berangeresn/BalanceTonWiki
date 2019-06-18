/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.service.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrateur
 */

@RestController
@RequestMapping("/v1/contri/like")
public class LikeDislikeController {
    @Autowired
    LikeDislikeService likedislike;
    
    @RequestMapping(value="/wiki", method = RequestMethod.POST)
    public ResponseEntity<Wiki>likeWiki(@RequestParam String user_id, @RequestParam String wiki_id){
       return new ResponseEntity<Wiki>(likedislike.likeWiki(user_id, wiki_id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/comment", method = RequestMethod.POST)
    public ResponseEntity<Comment>likeComment(@RequestParam String user_id, @RequestParam String comment_id){
       return new ResponseEntity<Comment>(likedislike.likeComment(user_id, comment_id), HttpStatus.OK);
    }
    
}
