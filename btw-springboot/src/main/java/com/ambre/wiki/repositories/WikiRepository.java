
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Category;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.WikiStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
@Transactional
public class WikiRepository extends BaseRepository {
    @Autowired
    CorrectionRepository correctr;
        
     public Wiki createWiki(String title, String content, User u) {
        Wiki w = new Wiki();
        //User u = (User)em.find(User.class, Long.valueOf(user_id));
        
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
    
    // utilisateur normal
    public Wiki updateWiki(Long id, String title, String content, User u, String category_id) {       
        Wiki w = (Wiki)em.find(Wiki.class,id);
        System.out.println(w);
        if (u!=null && w!=null) {             
            if (w.getUser().getId().equals(u.getId())) {
            w.setTitle(title);
            w.setContent(content);
            Category cat = (Category)em.find(Category.class, Long.valueOf(category_id));
                if (cat!=null) {
                    w.getCategories().add(cat);
                } 
            em.persist(w);
            em.flush();
            return w;
            }
        }
        return null;
    }
    
    public Wiki correctWiki(Long id, String title, String content, User u, String category_id) {       
        Wiki w = (Wiki)em.find(Wiki.class,id);
        System.out.println(w);
        if (u!=null && w!=null) {             
            w.setTitle(title);
            w.setContent(content);
            Category cat = (Category)em.find(Category.class, Long.valueOf(category_id));
                if (cat!=null) {
                    w.getCategories().add(cat);
                } 
            em.persist(w);
            em.flush();
           correctr.createCorrection(id, u);
            return w;
            }        
        return null;
    }
    
    public ArrayList<Wiki> searchWiki(String keyword) { 
        String pattern = "%" + keyword + "%";
        Query q = em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w LEFT JOIN "+User.class.getName()+""
                + " u ON w.user.id = u.id WHERE w.title LIKE :pattern OR w.content LIKE :pattern OR u.firstName LIKE :pattern OR u.lastName LIKE :pattern OR "
                + " (SELECT c FROM "+Category.class.getName()+" c WHERE c.category LIKE :pattern) MEMBER OF w.category").setParameter("pattern", pattern);
        
        return (ArrayList<Wiki>)q.getResultList();
    }
    
    public List<Wiki> findValidWikis() {
    Query sql =em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w INNER JOIN "+WikiStatus.class.getName()+""
            + " ws ON w.wikistatus = ws.id WHERE ws.status =:param").setParameter("param", "valide");
        return (List<Wiki>) sql.getResultList(); 
        
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT w ")
//                .append(" FROM ").append(Wiki.class.getName()).append(" w ")
//                .append(" w INNER JOIN ").append(WikiStatus.class.getName()).append(" ws ")
//                .append("ON w.wikistatus = ws.id WHERE ws.id =:param");
//         Query q = em.createQuery(sql.toString()).setParameter("param", Long.valueOf(5));
    }
    
    public List<Wiki> findWikisByUser(User u){
    
    Query sql =em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w INNER JOIN "+User.class.getName()+""
            + " u ON w.user = u.id WHERE u.id =:param").setParameter("param", u);
        return (List<Wiki>) sql.getResultList();
    }
    
//    public List<Wiki> findWikisForCorrector(String userId){
//        Correction corr = (Correction)em.find(Correction.class, Long.valueOf(userId));
//        return (List<Wiki>) corr.getCorrections();
//    }
    
    
}
