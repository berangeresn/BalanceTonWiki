/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambre.wiki.controllers;

import com.ambre.wiki.entities.User;
import com.ambre.wiki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrateur
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService us;
    
    @RequestMapping(value="/admin/add", method = RequestMethod.POST)
    public ResponseEntity<User>createUser(@RequestParam String lastName, @RequestParam String firstName, @RequestParam String emailAddress, @RequestParam String password, @RequestParam String user_role) {
    return new ResponseEntity<User>(us.createUser(lastName, firstName, emailAddress, password, user_role), HttpStatus.OK); 
    }
    
    @RequestMapping(value="/admin/find{id}", method= RequestMethod.GET)
    public ResponseEntity<User>getUserById(@RequestParam Long id) {
    return new ResponseEntity<User>(us.getUserById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value="/contri/update/{id}", method= RequestMethod.PUT)
    public ResponseEntity<User>updateUser(@PathVariable("id") Long id, 
                                          @RequestParam String lastName, 
                                          @RequestParam String firstName, 
                                          @RequestParam String emailAddress, 
                                          @RequestParam String image,
                                          @RequestParam String activity ) {
    	return new ResponseEntity<User>(us.updateUser(id, lastName, firstName, emailAddress, image, activity), HttpStatus.OK);
    }
    
    @RequestMapping(value="/contri/update/{id}/password", method= RequestMethod.PUT)
    public ResponseEntity<Boolean> updateUserPasswordById(@PathVariable("id") Long id, @RequestParam String password ) {
		return new ResponseEntity<Boolean>(Boolean.valueOf(us.updateUserPasswordById(id, password)), HttpStatus.OK);
    }
    
    @RequestMapping(value="/admin/search", method= RequestMethod.GET)
    public ResponseEntity<Iterable<User>> findUsersByUserRoleIdAndKeyword(@RequestParam(value="roleid", required=false) Long roleid,
    @RequestParam(value="keyword", required=false) String keyword) {
    	
    return new ResponseEntity<Iterable<User>> (us.findUsersByUserStatusIdAndKeyword(roleid, keyword), HttpStatus.OK);
    }
    
}
