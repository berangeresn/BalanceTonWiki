package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Hashtag;
import com.ambre.wiki.helpers.HashtagManager;
import com.ambre.wiki.service.HashtagService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    @Autowired
    HashtagService hashtagService;
    
	@PostMapping(path="/parse")
	public ResponseEntity<ArrayList<String>> parse (@RequestParam String search) {
		ArrayList<String> myList = HashtagManager.searchHashtag(search);
		return new ResponseEntity<ArrayList<String>>(myList, HttpStatus.OK);
	}

	@GetMapping(path="/{id}")
	public ResponseEntity<Hashtag> findHashtagById(@PathVariable("id") Long id) {
		return new ResponseEntity<Hashtag>(hashtagService.findHashtagById(id), HttpStatus.OK);
	}

	@GetMapping(path="/all")
	public ResponseEntity<Iterable<Hashtag>> findAllHashtag() {
		return new ResponseEntity<Iterable<Hashtag>>(hashtagService.findAllHashtag(), HttpStatus.OK);
	}

    @PostMapping(path="/create") 
	public ResponseEntity<Hashtag> createHashtag(@RequestParam String title) {
        return new ResponseEntity<Hashtag>(hashtagService.createHashtag(title), HttpStatus.OK);
	}
	
    @PutMapping(path="/update/{id}") 
    public ResponseEntity<Hashtag> updateHashtag(@PathVariable("id") Long id, @RequestParam String title) {
		return new ResponseEntity<Hashtag>(hashtagService.updateHashtag(id, title), HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Boolean> deleteHashtag(@PathVariable("id") Long id) {
    	return new ResponseEntity<Boolean>(hashtagService.deleteHashtag(id), HttpStatus.OK);
    }

	@PostMapping(path="/search")
	public ResponseEntity<Iterable<Hashtag>> findHashtagByTitle(@RequestParam String title) {
		Iterable<Hashtag> myList = hashtagService.findHashtagByTitle(title);
        return new ResponseEntity<Iterable<Hashtag>>(myList, HttpStatus.OK);
	}

	
	@GetMapping(path="/demo")
	public ResponseEntity<Boolean> demo() {
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	
	
}
