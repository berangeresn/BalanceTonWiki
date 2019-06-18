package com.ambre.wiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambre.wiki.entities.Comment;
import com.ambre.wiki.entities.Hashtag;
import com.ambre.wiki.repositories.HashtagRepository;

@Service
public class HashtagService {

	@Autowired
	private HashtagRepository myRepository;
	
	/**
	 * Declares the default constructor
	 */
	public HashtagService() {
		
	}
	
	public Hashtag findHashtagById(Long id) {
		return myRepository.findHashtagById(id);
	}
	
	public Iterable<Hashtag> findAllHashtag() {
		return myRepository.findAllHashtag();
	}

	public Iterable<Hashtag> findHashtagByTitle(String title) {
		return myRepository.findHashtagByTitle(title);
	}	

	public Hashtag createHashtag(String title) {
		return myRepository.createHashtag(title);
	}
	
	public Hashtag updateHashtag(Long id, String title) {
		return myRepository.updateHashtag(id, title);
	}
	
	public Boolean deleteHashtag(Long id) {
		return myRepository.deleteHashtag(id);
	}
	
	public Boolean deleteHashtag(Long id, boolean deleteIfNotBound) {
		return myRepository.deleteHashtag(id, deleteIfNotBound);
	}

	public Hashtag addHashtagToComment(Hashtag myHashtag, Comment myComment) {
		return myRepository.addHashtagToComment(myHashtag, myComment);
	}
	
	public Hashtag addHashtagToComment(Long hashtagId, Comment myComment) {
		return myRepository.addHashtagToComment(hashtagId, myComment);
	}
	
	public Boolean removeHashtagFromComment(Hashtag myHashtag, Comment myComment) {
		return myRepository.removeHashtagFromComment(myHashtag,myComment);
	}
	
	 public Boolean removeHashtagFromComment(Long hashtagId, Comment myComment) {
		 return myRepository.removeHashtagFromComment(hashtagId, myComment);
	 }
	
}
