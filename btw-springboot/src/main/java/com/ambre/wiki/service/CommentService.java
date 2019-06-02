
package com.ambre.wiki.service;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.repositories.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class CommentService {
    
    @Autowired
    CommentRepository com;
    
     public Comment addComment(String content, String user_id, String wiki_id) {
       return com.addComment(content, user_id, wiki_id);
    }
    
    public Comment getCommentById(Long id) {
    	return com.getCommentById(id);
    }
        
    public List<Comment> getAllComments(){
        return com.getAllComments();
    }
    
    public Boolean deleteComment(String id) {
    return com.deleteComment(id);
    }	
    
    public Comment updateComment(String id, String content, String user_id){
        return com.updateComment(id, content, user_id);
    }
    
    public Comment commentAComment(String id, String content, String user_id){
        return com.commentAComment(id, content, user_id); 
    }
    
  }
