package com.ambre.wiki.service;

import com.ambre.wiki.entities.News;
import com.ambre.wiki.repositories.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

	@Autowired
	private NewsRepository myRepository;
	
	/**
	 * Create a news
	 * @param title
	 * @return
	 */
	public News addNews(News myNews) {
		return myRepository.addNews(myNews);
	}
	
	/**
	 * Read a news by id
	 * @param id
	 * @return
	 */
	public News findNewsById(Long id) {
		return myRepository.findNewsById(id);
	}
	
	
	/**
	 * Read all the news
	 * @return
	 */
	public Iterable<News> findAllNews() {
		return myRepository.findAllNews();
	}

	
	/**
	 * Update a News
	 * @param id
	 * @param myNews 
	 * @return
	 */
	public News updateNews(Long id, News myNews) {
		return myRepository.updateNews(id, myNews);
	}

	/**
	 * Delete a news
	 * @param id
	 * @return
	 */
	public Boolean deleteNews(Long id) {
		return myRepository.deleteNews(id);
	}

	/**
	 * Find the last news
	 * @return
	 */
	public News findLastNews() {
		return myRepository.findLastNews();
	}
	
	
	
}
