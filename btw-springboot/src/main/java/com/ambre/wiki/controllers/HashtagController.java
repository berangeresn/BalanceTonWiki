package com.ambre.wiki.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ambre.wiki.helpers.HashtagManager;

@RestController
@RequestMapping("/hashtag")
public class HashtagController {

	@PostMapping(path="/parse")
	public ResponseEntity<ArrayList<String>> parse (@RequestParam String search) {
		ArrayList<String> myList = HashtagManager.searchHashtag(search);
		return new ResponseEntity<ArrayList<String>>(myList, HttpStatus.OK);
	}
	
}
