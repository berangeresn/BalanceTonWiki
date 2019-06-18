package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class HashtagRepository extends BaseRepository {

	public HashtagRepository() {
		
	}
	
    public Hashtag findHashtagById(Long id) {
    	return (Hashtag)em.find(Hashtag.class, id);
    }

    public Iterable<Hashtag> findAllHashtag() {
		StringBuilder from = new StringBuilder();
		TypedQuery<Hashtag> lQuery = em.createQuery(from.append("from ").append(Hashtag.class.getName()).toString(), Hashtag.class);
		return (Iterable<Hashtag>) lQuery.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public Iterable<Hashtag> findHashtagByTitle(String title) { 
        String pattern = title; 
        Query q = em.createQuery("SELECT h FROM " + Hashtag.class.getName() + " h "
                + " WHERE h.title LIKE :pattern ").setParameter("pattern", pattern);
        return (Iterable<Hashtag>) q.getResultList();
    }    
    
    public Hashtag persistHashtag(Hashtag myEntity) {
    	if (myEntity!=null) {
    		em.persist(myEntity);
    		em.flush();
    		return myEntity;
		} 
    	return null;
    }

    public Hashtag createHashtag(Hashtag myEntity) {
		return this.persistHashtag(myEntity);
    }

    public Hashtag createHashtag(String title) {
    	Hashtag myEntity = new Hashtag(); 
    	myEntity.setTitle(title);
		return this.persistHashtag(myEntity);
    }

    public Hashtag updateHashtag(Long id, String title) {
    	if ((title!=null) && (id!=null)) {
            Hashtag myEntity = this.findHashtagById(id);
            if (myEntity!=null) {
        		List<Hashtag> myList = (List<Hashtag>) this.findHashtagByTitle(title);
        		if (myList!=null) {
    				if (myList.size()==0) {
    					myEntity.setTitle(title);
    					return this.persistHashtag(myEntity);
    				}
    			}
			}
		}
    	return null;
    }

    /**
     * Delete a hashtag by id
     * Force deletion.
     * @param id
     * @return
     */
    public Boolean deleteHashtag(Long id) {
        Hashtag myEntity = this.findHashtagById(id);
        if (myEntity!=null) {
        	em.remove(myEntity); 
        	return true;
        }
		return false;
    }
    
    /**
     * Check if Hashtag is bound to Comment.
     * Delete if not.
     * @param id
     * @param deleteIfNotBound
     * @return
     */
    public Boolean deleteHashtag(Long id, boolean deleteIfNotBound) {
    	if (deleteIfNotBound) {
            Hashtag myEntity = this.findHashtagById(id);
            if (myEntity!=null) {
            	if (myEntity.getCommentHashtag().size()==0) {
                	em.remove(myEntity);
                	return true;
				}
            	return false;
            }
		} else {
			return this.deleteHashtag(id);
		}
    	return null;
    }
       
	public Hashtag addHashtagToComment(Hashtag myHashtag, Comment myComment) {
		if ((myHashtag!=null) && (myComment!=null)) {
			myComment.getHashtags().add(myHashtag);
			myHashtag.getCommentHashtag().add(myComment);
			return this.persistHashtag(myHashtag);
		}
		return null;
	}

	public Hashtag addHashtagToComment(Long hashtagId, Comment myComment) {
        if ((hashtagId!=null) && (myComment!=null)) {
        	if (hashtagId>0) {
        		Hashtag myHashtag = this.findHashtagById(hashtagId);
        		return this.addHashtagToComment(myHashtag, myComment);
        	}
        }
		return null;
	}
	
    public Boolean removeHashtagFromComment(Hashtag myHashtag, Comment myComment) {
        if ((myHashtag!=null) && (myComment!=null)) { 
			myComment.getHashtags().remove(myHashtag);
			em.persist(myComment);
	        return true;
        }
        return false;
	}	
	
    public Boolean removeHashtagFromComment(Long hashtagId, Comment myComment) {
        if ((hashtagId!=null) && (myComment!=null)) { 
        	if (hashtagId>0) {
        		Hashtag myHashtag = this.findHashtagById(hashtagId);
        		return this.removeHashtagFromComment(myHashtag, myComment);
			}
        }
        return false;
	}	


    //-----------------------------------------------------------------------------------------------------
    
	/**
	 * Create a hashtag by title
	 * Check if title exists before creating
	 * @param title
	 * @return
	 */
	public Hashtag createHashtagIfNotExistElseGet(String title) {
		if (title!=null) {
			List<Hashtag> myList = (List<Hashtag>) this.findHashtagByTitle(title);
			if (myList==null) {
				Hashtag myEntity = new Hashtag();
				myEntity.setTitle(title);
				return this.createHashtag(myEntity);
			} else {
				return myList.get(0);
			}
		}
		return null;
	}
    
	/**
	 * Find the hashtags by titles 
	 * @param myTitles
	 * @return
	 */
	public Iterable<Hashtag> findHashtagByTitles(Iterable<String> myTitles) {
		Hashtag myEntity = null;
		ArrayList<Hashtag> myList = new ArrayList<Hashtag>();
		if (myTitles!=null) {
			for (String myTitle : myTitles) {
				myEntity = this.createHashtagIfNotExistElseGet(myTitle);
				if (myEntity!=null) {
					myList.add(myEntity);
				}
			}
			return myList;
		}
		return null;
	}
    
}
