package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.Wiki;
import com.ambre.wiki.entities.WikiStatus;
import com.ambre.wiki.service.WikiService;
import com.ambre.wiki.service.WikiStatusService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/wikistatus")
public class WikiStatusController {

	@Autowired
	private WikiStatusService myService;
	
	@Autowired
	private WikiService myWikiService;
	
	
	@GetMapping(path="/admin/{id}")
	public @ResponseBody WikiStatus findNewsById(@PathVariable("id") Long id) {
		return myService.findWikiStatusById(id);
	}

	@GetMapping(path="/admin/all")
	public @ResponseBody Iterable<WikiStatus> findAllWikiStatus() {
		return myService.findAllWikiStatus();
	}
	
	@PutMapping(path="/corrector/{id}/wiki/{wikiid}")
	public @ResponseBody boolean updateStatusOfWiki(@PathVariable("id") Long id, @PathVariable("wikiid") Long wikiId) {
		Wiki myWiki = myWikiService.getWikiById(wikiId);
		return myService.updateStatusOfWiki(myWiki, id);
	}
	
        @GetMapping(path="/corrector/wikis/{id}")
	public @ResponseBody List<Wiki> findWikiByStatus(@PathVariable("id") String statusId) {
		return myService.findWikiByStatus(statusId);
	}
        
        
}
