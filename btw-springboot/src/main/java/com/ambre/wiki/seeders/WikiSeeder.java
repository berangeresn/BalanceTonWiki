package com.ambre.wiki.seeders;

import com.ambre.wiki.entities.Wiki;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/wikiseeder")
public class WikiSeeder extends BaseSeeder {
	
	public WikiSeeder() {
		
	}

	
	@GetMapping(path="/create")
    public ResponseEntity<Boolean> create() {
		for (int i = 0; i < 5; i++) {
			addEntity("title"+i, "content"+i);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	private void addEntity(String title, String content) {
		Wiki myEntity = new Wiki();
		myEntity.setTitle(title);
		myEntity.setContent(content);
		em.persist(myEntity);
		myEntity = null;
	} 
		
	
}
