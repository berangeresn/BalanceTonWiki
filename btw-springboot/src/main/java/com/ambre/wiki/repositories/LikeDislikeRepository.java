
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.entities.Dislikes;
import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.Likes;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.repositories.BaseRepository;
import com.mysql.jdbc.log.Log;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public class LikeDislikeRepository extends BaseRepository {
    
     public Wiki likeWiki(String user_id, String wiki_id) {
        Likes like = new Likes(); 
        Dislikes dislike = new Dislikes();
        User u = em.find(User.class, Long.valueOf(user_id)); 
        Wiki w = em.find(Wiki.class, Long.valueOf(wiki_id));    
        
        if(u==null && w==null){            
            return new Wiki();            
        }   
         ArrayList<Likes> liked=(ArrayList<Likes>)em.createQuery("SELECT l FROM "+Likes.class.getName()+" l WHERE user_id=:id_user AND wiki_id =:id_wiki")
                .setParameter("id_user", user_id)
                .setParameter("id_wiki", wiki_id)
                .getResultList();    
         ArrayList<Dislikes> disliked=(ArrayList<Dislikes>)em.createQuery("SELECT d FROM "+Dislikes.class.getName()+" d WHERE user_id=:id_user AND wiki_id =:id_wiki")
                .setParameter("id_user", user_id)
                .setParameter("id_wiki", wiki_id)
                .getResultList();   
        if(liked.isEmpty()){
                if (!disliked.isEmpty()) {
                em.remove(disliked.get(0));
                em.flush();
            }
            like.setUser(u);
            like.setLikesWiki(w); 
            w.getLikes().add(like);             
        }else          
        if(disliked.isEmpty()){
            if (!liked.isEmpty()) {
                w.getLikes().remove(liked.get(0));
                em.remove(liked.get(0));
                em.flush();
            }
            dislike.setUser(u);
            dislike.setDislikesWiki(w);            
            w.getDislikes().add(dislike);
        }               
        em.persist(w);
        em.flush();
              
        return w;
     }
     
     public Comment likeComment(String user_id, String comment_id) {
        Likes like = new Likes(); 
        Dislikes dislike = new Dislikes();
        User u = em.find(User.class, Long.valueOf(user_id)); 
        Comment com = em.find(Comment.class, Long.valueOf(comment_id));        
        if(u==null && com==null){            
            return new Comment();            
        }   
         ArrayList<Likes> liked=(ArrayList<Likes>)em.createQuery("SELECT l FROM "+Likes.class.getName()+" l WHERE user_id=:id_user AND comment_id =:id_com")
                .setParameter("id_user", user_id)
                .setParameter("id_com", comment_id)
                .getResultList();    
         ArrayList<Dislikes> disliked=(ArrayList<Dislikes>)em.createQuery("SELECT d FROM "+Dislikes.class.getName()+" d WHERE user_id=:id_user AND comment_id =:id_com")
                .setParameter("id_user", user_id)
                .setParameter("id_com", comment_id)
                .getResultList();   
         
        if(liked.isEmpty()){
            if (!disliked.isEmpty()) {
                em.remove(disliked.get(0));
                em.flush();
            }
            like.setUser(u);
            like.setLikesComment(com);            
            com.getLikes().add(like);
        }else        
        if(disliked.isEmpty()){
            if (!liked.isEmpty()) {
                com.getLikes().remove(liked.get(0));
                em.remove(liked.get(0));
                em.flush();
            }
            dislike.setUser(u);
            dislike.setDislikesComment(com);            
            com.getDislikes().add(dislike);
        }        
        em.persist(com);
        em.flush();
        
        return com;
     }
    
     
   
}
