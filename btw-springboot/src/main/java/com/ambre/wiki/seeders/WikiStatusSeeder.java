package com.ambre.wiki.seeders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambre.wiki.entities.WikiStatus;

@RestController
@RequestMapping("/wikistatusseeder")
public class WikiStatusSeeder extends BaseSeeder {

	public WikiStatusSeeder() {
		
	}
	
	@GetMapping(path="/create")
    public ResponseEntity<Boolean> create() {
		addEntity("Invalide");
		addEntity("For correction");
		addEntity("Corrected");
		addEntity("Validated");
		addEntity("Archived");
		addEntity("Correction in progress");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	private void addEntity(String status) {
		WikiStatus myEntity = new WikiStatus(); 
		myEntity.setStatus(status);
		em.persist(myEntity);
		myEntity = null;
	} 
	
}
