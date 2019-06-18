
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Correction;
import com.ambre.wiki.entities.User;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.WikiStatus;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class CorrectionRepository extends BaseRepository {
    
    @Autowired
    WikiStatusRepository wsr;
    @Autowired
    UserRepository ur;
    
    
     public List<Correction> getAllCorrections(){
     String sql = "SELECT c FROM " +Correction.class.getName()+ " c";
        Query q = em.createQuery(sql);
        return (List<Correction>)q.getResultList();
     }
     
      public List<Correction> getCorrectionByUser(User u){
           StringBuilder sql = new StringBuilder();
		sql.append("SELECT c ")
                   .append(" FROM ").append(Correction.class.getName()).append(" c ")
		   .append(" WHERE user_id=:iduser ").append(Wiki.class.getName());
           
           Query query = em.createQuery(sql.toString());
                query.setParameter("iduser", u.getId());
        return (List<Correction>)query.getResultList();
    }
     
//    public List<Correction> getCorrectionInProgress() {
//        StringBuilder sql = new StringBuilder();
//		sql.append("SELECT c ")
//		   .append(" FROM ").append(Correction.class.getName()).append(" c ")
//		   .append(" JOIN ").append(Wiki.class.getName()).append(" w ")
//                   .append(" ON c.wiki = w.id ")
//                   .append(" WHERE w.wikistatus")
     // setParameter("param", "valide");
//        return null;
//    }
    
    
    public Correction createCorrection(Long id, User u ){       
         Correction correct = new Correction();
         Wiki w = (Wiki)em.find(Wiki.class, id);
         correct.setDate(new Date());
          
         correct.setWiki(w);
         correct.getCorrecteurs().add(u);
         em.persist(correct);
         em.flush();
         return correct;
         
     }
//     
//     public boolean deleteCorrection(String id){
//      Correction c = (Correction)em.find( Correction.class, Long.valueOf(id));
//        if(c==null){
//            return false;
//        }        
//      
//        em.remove(c);
//        em.flush();
//        return true;
//    }
//     
//    public Correction getCorrectionByID(String id){
//        Correction c = ( Correction)em.find( Correction.class, Long.valueOf(id)); 
//    return c;
//    }
//    

//     public List<Correction> getCorrectionByWiki(String wiki_id){
//        List<Correction> listeCorrec=(List<Correction>)em.createQuery("SELECT c FROM "+Correction.class.getName()+" c WHERE wiki_id=:wiki_id").setParameter("wiki_id", wiki_id).getResultList();
//       return listeCorrec;
//    }
//    
//    public Correction end_correction(String id){
//    
//        if(getCorrectionByID(id)==null){
//            return null;
//                    }
//        
//        Correction c=getCorrectionByID(id);
//        //c.setEn_correction(Boolean.FALSE);
//        em.merge(c);
//        em.flush();
//        
//       return c;
//       
//    }
    
  


}
