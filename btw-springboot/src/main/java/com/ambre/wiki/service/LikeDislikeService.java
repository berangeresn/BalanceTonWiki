/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.service;

import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.repositories.LikeDislikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class LikeDislikeService {

@Autowired
LikeDislikeRepository likedislike; 

public Wiki likeWiki(String user_id, String wiki_id) {
    return likedislike.likeWiki(user_id, wiki_id);
}
public Comment likeComment(String user_id, String comment_id) {
    return likedislike.likeComment(user_id, comment_id);
}

}
