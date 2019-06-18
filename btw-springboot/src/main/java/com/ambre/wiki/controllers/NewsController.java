package com.ambre.wiki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ambre.wiki.entities.News;
import com.ambre.wiki.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService myService;

	@PostMapping(path="/v1/admin/add") 
	public @ResponseBody News addNews (@RequestParam String title) {
		News myNews = new News();
		myNews.setTitle(title);
		return myService.addNews(myNews);
	}

	@GetMapping(path="/v1/admin/{id}")
	public @ResponseBody News findNewsById(@PathVariable("id") Long id) {
		return myService.findNewsById(id);
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<News> findAllNews() {
		return myService.findAllNews();
	}

	@GetMapping(path="/v1/contri/last")
	public @ResponseBody News findLastNews() {
		return myService.findLastNews();
	}
	
	@PutMapping(path="/v1/admin/update/{id}")
	public @ResponseBody News updateNews(@PathVariable("id") Long id, @RequestParam String title) {
		News myNews = new News();
		myNews.setTitle(title);
		return myService.updateNews(id, myNews);
	}

	@DeleteMapping(path="/v1/admin/delete/{id}")
	public @ResponseBody Boolean deleteNews(@PathVariable("id") Long id) {
		return myService.deleteNews(id);
	}		
    
}
