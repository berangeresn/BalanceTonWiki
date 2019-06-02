package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.News;
import com.ambre.wiki.repositories.BaseRepository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class NewsRepository extends BaseRepository {

    /**
     * Create a news
     * @param title
     * @return
     */
    public News addNews(News myNews) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(myNews);
		em.flush();
		em.getTransaction().commit();
		return myNews;
    }
    
    /**
     * Read a news by id
     * @param id
     * @return
     */
    public News findNewsById(Long id) {
    	return (News)em.find(News.class, id);
    }

    /**
     * Read all the news
     * @return
     */
    public Iterable<News> findAllNews() {
		StringBuilder from = new StringBuilder();
		TypedQuery<News> lQuery = em.createQuery(from.append("from ").append(News.class.getName()).toString(), News.class);
		return (Iterable<News>) lQuery.getResultList();
    }

	/**
	 * Find the last news
	 * @return
	 */
	public News findLastNews() {
        String sql = "SELECT u FROM " + News.class.getName() + " u ORDER BY id DESC";
        Query myQuery = em.createQuery(sql);
        myQuery.setMaxResults(1);
        return (News) myQuery.getSingleResult();
	}
    
    /**
     * Update the news
     * @param id
     * @param myNews
     * @return
     */
	public News updateNews(Long id, News myNews) {
        if (myNews!=null) {
            em = emf.createEntityManager();
            News myNewsTarget = this.findNewsById(id);
            if (myNewsTarget!=null) {
                myNewsTarget.setTitle(myNews.getTitle());
                em.getTransaction().begin();
                em.persist(myNewsTarget);
                em.flush();
                em.getTransaction().commit();
                return myNewsTarget;
            }
        }
        return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Boolean deleteNews(Long id) {
        News myNewsTarget = this.findNewsById(id);
        if (myNewsTarget!=null) {
        	em.remove(myNewsTarget); 
        	return true;
        }
		return false;
	}
    
}
