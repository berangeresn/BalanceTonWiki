
package com.ambre.wiki.repositories;

import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.WikiStatus;
import java.util.List;
import javax.persistence.Query;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class WikiStatusRepository extends BaseRepository {

	/**
	 * Possible states of Wiki Status
	 */
	public static final Long WIKI_STATUS_ID_INVALIDE = 1L;
	public static final Long WIKI_STATUS_ID_FOR_CORRECTION = 2L;
	public static final Long WIKI_STATUS_ID_CORRECTED = 3L;
	public static final Long WIKI_STATUS_ID_VALIDATED = 4L;
	public static final Long WIKI_STATUS_ID_ARCHIVED = 5L;
	public static final Long WIKI_STATUS_ID_CORRECTION_IN_PROGRESS = 6L;
	public static final Long WIKI_STATUS_MAX_ID = 6L;

	
    WikiStatus ustatus = new WikiStatus();
    
    public WikiStatus addStatus(String Status) {
    	ustatus.setStatus(Status);
    	//em.getTransaction().begin();
    	em.persist(ustatus);
    	//em.getTransaction().commit();
    	return ustatus;
    }
    
    /**
     * Read a wiki status by id
     * @param id
     * @return
     */
    public WikiStatus findWikiStatusById(Long id) {
    	return (WikiStatus)em.find(WikiStatus.class, id);
    }

    /**
     * Read all the wiki status
     * @return
     */
	public Iterable<WikiStatus> findAllWikiStatus() {
		StringBuilder from = new StringBuilder();
		TypedQuery<WikiStatus> lQuery = em.createQuery(from.append("from ").append(WikiStatus.class.getName()).toString(), WikiStatus.class);
		return (Iterable<WikiStatus>) lQuery.getResultList();
	}
    
    /**
     * 
     * @param wikiId
     * @param statusId
     * @return
     */
    public boolean updateStatusOfWiki(Wiki myWiki, Long statusId) {
    	boolean updated = false;
    	
    	if ((myWiki!=null) && (statusId!=null)) {
			if ((statusId>0) && (statusId<=WIKI_STATUS_MAX_ID)) {
				WikiStatus myWikiStatus = this.findWikiStatusById(statusId);
				if (myWikiStatus!=null) {
					myWiki.setWikistatus(myWikiStatus);
					em.persist(myWiki);
					em.flush();
					updated = true;
				}
			}
		}
    	
    	return updated;
    }
    
    public List<Wiki> findWikiByStatus (String statusId) {               
        Query q = em.createQuery("SELECT w FROM "+Wiki.class.getName()+" w JOIN "+WikiStatus.class.getName()+" s ON s.id=w.wikistatus.id WHERE w.wikistatus.id =:id_status").setParameter("id_status", Long.valueOf(statusId));
        return (List<Wiki>)q.getResultList();        
    }

}
