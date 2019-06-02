
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.Comment;
import java.util.ArrayList;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CommentRepository extends BaseRepository {
       
    public Comment addComment(String content, String user_id, String wiki_id) {
        Comment comment= new Comment();
        User u = em.find(User.class, Long.valueOf(user_id)); 
        Wiki w = em.find(Wiki.class, Long.valueOf(wiki_id));
        if(u==null && w==null){            
            return null;            
        }  
       comment.setContent(content);
       comment.setWiki(w);
       comment.setUser(u);
        em.persist(comment);
        em.flush();
        return comment;
     }
     
     public Comment getCommentById(Long id){
        return (Comment)em.find(Comment.class, id);
    }
    
    public ArrayList<Comment> getAllComments() {
        String sql = "SELECT comment FROM " +Comment.class.getName()+ " comment";
        Query q = em.createQuery(sql);
  
     return (ArrayList<Comment>)q.getResultList();
    }
    
     
     public Comment updateComment(String id,String content,String user_id){
        Comment comment = em.find(Comment.class,Long.valueOf(id));
        User user = em.find(User.class,Long.valueOf(user_id));
            if(content!=null && user!=null && comment.getUser().equals(user))
             {
                comment.setContent(content);
                em.merge(comment);
                em.flush();
             }
        return comment;
    }

    
    public Comment commentAComment(String id, String content, String user_id) {
        User u = em.find(User.class, Long.valueOf(user_id));
        Comment comment = em.find(Comment.class, Long.valueOf(id));

         if(comment!=null && u !=null){
            Comment newcomment = new Comment(); 
            newcomment.setContent(content);
            newcomment.setUser(u);
            newcomment.setWiki(comment.getWiki());
            comment.getComment().add(newcomment);
            em.persist(comment);
            em.flush();
            }
        return comment;
    }
    
     
      public Boolean deleteComment(String id) {
       Comment com = (Comment)em.find(Comment.class, Long.valueOf(id));
        if (com==null) {
            return false;
        }
        em.remove(com);
      
        return true;
    }

}
