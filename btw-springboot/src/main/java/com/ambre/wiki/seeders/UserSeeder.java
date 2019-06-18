package com.ambre.wiki.seeders;

import com.ambre.wiki.entities.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userseeder")
public class UserSeeder extends BaseSeeder {

	public UserSeeder() {
		
	}
	
	@GetMapping(path="/create")
    public ResponseEntity<Boolean> createUsers(){
        for(int i=0; i<5; i++){
            User u = new User();
            u.setFirstName("john" + i);
            u.setLastName("doe" + i);
            u.setEmailAddress("toto"+i+"@gmail.com");
            u.setPassword("azerty");
            em.persist(u);
        }
        em.flush();
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
     
}
