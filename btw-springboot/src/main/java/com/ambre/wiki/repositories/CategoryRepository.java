
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Category;
import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.repositories.BaseRepository;
import java.util.ArrayList;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class CategoryRepository extends BaseRepository {

    
    
    public Category newCategory(String category) {
     Category cat = new Category();  
     cat.setCategory(category);
       em.persist(cat);
       em.flush();
       return cat;
    }
     public Category getById(Long id) {
        return (Category)em.find(Category.class, id);
    }
     
     public ArrayList<Category> getAllCategories() {
        String sql = "SELECT cat FROM " +Category.class.getName()+ " cat";
         Query q = em.createQuery(sql);
  
     return (ArrayList<Category>)q.getResultList();
    }
     
    public Category updateCategory(String id, String category){
        Category cat = (Category)em.find(Category.class, Long.valueOf(id));
         if (cat!=null) {
             cat.setCategory(category);
             em.persist(cat);
             em.flush();
             return cat;
         }
         return null;
    }
    
    public boolean deleteCategory(Long id) {
        Category cat = (Category)em.find(Category.class, Long.valueOf(id));
         if (cat!=null) {
             em.remove(cat);
             return true;
         }
         return false; 
    }
    
    public boolean addCategoryToWiki(String categoryId, Wiki wiki){
        boolean added = false;
        
        if ((wiki!=null) && (categoryId!=null)) { 
            Category myCat = em.find(Category.class, Long.valueOf(categoryId));
            wiki.getCategories().add(myCat);
            em.persist(wiki);
            em.flush();
            
            added = true;
        }
      
        return added;
    }
    
     public boolean removeCategoryFromWiki(String categoryId, Wiki wiki){
         boolean removed = false;
                
         if ((wiki!=null) && (categoryId!=null)) { 
            Category myCat = em.find(Category.class, Long.valueOf(categoryId));
            wiki.getCategories().remove(myCat);
            em.persist(wiki);
            em.remove(myCat);
            
            removed = true;
        }
         
        return removed;
    }


}
