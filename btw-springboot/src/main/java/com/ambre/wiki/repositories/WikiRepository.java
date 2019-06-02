
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Category;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.User;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Repository
@Transactional
public class WikiRepository extends BaseRepository {
        
     public Wiki createWiki(String title, String content, String user_id) {
        Wiki w = new Wiki();
        User u = (User)em.find(User.class, Long.valueOf(user_id));
        
        if(u==null){            
            return null;            
        }       
        w.setTitle(title);
        w.setContent(content);
        w.setUser(u);
        em.persist(w);
        em.flush();
        return w;
     }
     
    public Wiki getWikiById(Long id) {
        return (Wiki)em.find(Wiki.class, id);
    }
    
    public Boolean deleteWiki(String id) {
       Wiki w = (Wiki)em.find(Wiki.class, Long.valueOf(id));
        if (w==null) {
            return false;
        }
        em.remove(w);
      
        return true;
    }
    
    public ArrayList<Wiki> getAllWikis() {
        String sql = "SELECT u FROM " +Wiki.class.getName()+ " u";
        Query q = em.createQuery(sql);
  
     return (ArrayList<Wiki>)q.getResultList();
    }
    
    
    public Wiki updateWiki(String id, String title, String content, String user_id, String category_id) {
        User u = (User)em.find(User.class, Long.valueOf(user_id));
        Wiki w = (Wiki)em.find(Wiki.class, Long.valueOf(id));
        if (u!=null && w!=null) {
            if (w.getUser().equals(u)) {
            w.setTitle(title);
            w.setContent(content);
            Category cat = (Category)em.find(Category.class, Long.valueOf(category_id));
                if (cat!=null) {
                    w.getCategories().add(cat);
                }
            em.merge(w);
            em.flush();
            return w;
            }
        }
        return null;
    }

//    public Wiki updateWiki(Wiki wiki) {
//        em.persist(wiki);
//        return wiki;
//    }
    
    public ArrayList<Wiki> searchWiki(String keyword) { 
        String pattern = "%" + keyword + "%";
        Query q = em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w LEFT JOIN "+User.class.getName()+""
                + " u ON w.user.id = u.id WHERE w.title LIKE :pattern OR w.content LIKE :pattern OR u.firstName LIKE :pattern OR u.lastName LIKE :pattern OR "
                + " (SELECT c FROM "+Category.class.getName()+" c WHERE c.category LIKE :pattern) MEMBER OF w.category").setParameter("pattern", pattern);
        
        return (ArrayList<Wiki>)q.getResultList();
    }
    
}
