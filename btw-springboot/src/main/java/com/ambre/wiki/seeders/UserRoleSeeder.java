package com.ambre.wiki.seeders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambre.wiki.entities.UserRole;

@RestController
@RequestMapping("/userroleseeder")
public class UserRoleSeeder extends BaseSeeder {

	public UserRoleSeeder() {
		
	}
	
	@GetMapping(path="/create")
    public ResponseEntity<Boolean> create() {
		addEntity("Visitor");
		addEntity("Contributor");
		addEntity("Corrector");
		addEntity("Administrator");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	private void addEntity(String role) {
		UserRole myEntity = new UserRole();
		myEntity.setRole(role);
		em.persist(myEntity);
		myEntity = null;
	} 
	
	
}
