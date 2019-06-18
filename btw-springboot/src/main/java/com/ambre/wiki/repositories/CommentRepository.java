
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.helpers.HashtagManager;
import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.entities.Hashtag;

import java.util.ArrayList;
import java.util.Collection;

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

	
	/*
	 * ******************************************************************************************
	 *  
	 * ******************************************************************************************
	 */
	
	/**
	 * Search hashtags into the content field of a comment
	 * New ones are added to hashtag table
	 * The old ones are deleted. 
	 * @param myComment
	 */
	public void searchHashtagIntoComment(Comment myComment) {
		if (myComment!=null) {
			ArrayList<String> myList = HashtagManager.searchHashtag(myComment.getContent());
			if (myList.size()>0) {
				for (String hashtag : myList) {
					updateHashtag(hashtag);
				}
			}
		}
	}
	
	/**
	 * Persists Comment 
	 * @param myEntity
	 * @return
	 */
    public Comment persistComment(Comment myEntity) {
    	if (myEntity!=null) {
    		em.persist(myEntity);
    		em.flush();
    		return myEntity;
		} 
    	return null;
    }	
	

	
	public void addHashtagToComment(String hashtag) {
		// Does this Hashtag exist ?
	}
	
	public void updateHashtag(String hashtag) {
		// Does this Hashtag exist ?
	}
	
	public void consolidate() {
		// delete 
	}
	
	/**
	 * Remove all binds to hashtag table 
	 * @param myEntity
	 */
	public void removeAllHashtagFromComment(Comment myEntity) {
		if (myEntity!=null) {
			if (myEntity.getHashtags().size()>0) {
				myEntity.getHashtags().clear();
	    		em.persist(myEntity);
	    		em.flush();
			}
		}
	}	
	
	/**
	 * Bind every hashtag form the list
	 * @param myEntity
	 * @param myHashtags
	 */
	public void addAllHashtagToComment(Comment myEntity, Collection<Hashtag> myHashtags) {
		if ((myEntity!=null) && (myHashtags!=null)) {
			//myEntity.getHashtags().addAll(myHashtags);
			for (Hashtag hashtag : myHashtags) {
				myEntity.getHashtags().add(hashtag);
				hashtag.getCommentHashtag().add(myEntity);
	    		em.persist(myEntity);
			}
    		em.flush();
		}
	}	
	

}
