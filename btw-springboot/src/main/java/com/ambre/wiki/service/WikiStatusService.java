package com.ambre.wiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.WikiStatus;
import com.ambre.wiki.repositories.WikiStatusRepository;
import java.util.List;

@Service
public class WikiStatusService {

	@Autowired
	private WikiStatusRepository myRepository;

	/**
	 * Read a news by id
	 * @param id
	 * @return
	 */
	public WikiStatus findWikiStatusById(Long id) {
		return myRepository.findWikiStatusById(id);
	}	
	
	/**
	 * Read all the news
	 * @return
	 */
	public Iterable<WikiStatus> findAllWikiStatus() {
		return myRepository.findAllWikiStatus();
	}	

	/**
	 * Update a status of a wiki
	 * @param myWiki
	 * @param statusId
	 * @return
	 */
	public boolean updateStatusOfWiki(Wiki myWiki, Long statusId) {
		return myRepository.updateStatusOfWiki(myWiki, statusId);
	}
        
        public List<Wiki> findWikiByStatus(String statusId){
        return myRepository.findWikiByStatus(statusId);
    }
	
}
