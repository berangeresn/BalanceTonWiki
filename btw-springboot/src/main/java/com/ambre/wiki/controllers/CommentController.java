
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.service.CommentService;
import java.util.List;
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
@RequestMapping("/v1/comments")
public class CommentController {
    @Autowired
    CommentService coms;
    
    
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<Comment>addComment(@RequestParam String content, @RequestParam String user_id, @RequestParam String wiki_id){
    return new ResponseEntity<Comment>(coms.addComment(content, user_id, wiki_id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> getAllComments() {        
            List<Comment> comments = coms.getAllComments();
            return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}
      
    @RequestMapping(value="/find{id}", method= RequestMethod.GET)
    public ResponseEntity<Comment>getCommentById(@RequestParam Long id) {
    return new ResponseEntity<Comment>(coms.getCommentById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/delete{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Boolean>deleteComment(@RequestParam String id) {
    return new ResponseEntity<Boolean>(coms.deleteComment(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/update{id}", method= RequestMethod.PUT)
    public ResponseEntity<Comment>updateComment(@RequestParam String id, @RequestParam String content, @RequestParam String user_id) {
    return new ResponseEntity<Comment>(coms.updateComment(id, content, user_id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/addcomment", method = RequestMethod.POST)
    public ResponseEntity<Comment>commentAComment(@RequestParam String id, @RequestParam String content, @RequestParam String user_id){
    return new ResponseEntity<Comment>(coms.commentAComment(id, content, user_id), HttpStatus.OK);
    }
}
